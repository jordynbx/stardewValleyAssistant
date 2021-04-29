package edu.matc.controller;

import edu.matc.entity.Crop;
import edu.matc.entity.Item;
import edu.matc.entity.Note;
import edu.matc.entity.User;
import edu.matc.persistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(
        urlPatterns = {"/editNoteAction"}
)
@Log4j2
public class EditNoteAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        GenericDao<Note> noteDao = new GenericDao<>(Note.class);
        String message;
        String messageType;
        Note noteToUpdate = null;
        boolean noteIsValid = false;
        String url = "error.jsp";

        if (!request.getParameter("noteIdEdit").equals("")) {

            // Get note id and note content from form
            int noteId = Integer.parseInt(request.getParameter("noteIdEdit"));
            String newNote = request.getParameter("editNote");

            noteToUpdate = noteDao.getById(noteId);
            // If update button was clicked, update note
            if (request.getParameter("submit").equals("update")) {

                noteToUpdate.setNoteContent(newNote);
                noteDao.saveOrUpdate(noteToUpdate);
                message = "Your note was successfully updated!";
                messageType ="success";

            } else if (request.getParameter("submit").equals("cancel")) {
                message = "Your note was not updated";
                messageType = "danger";

            } else {
                message = "There was an error updating the note.";
                messageType = "danger";

            }

            // repopulate the results page
            noteIsValid = true;

        } else {
            message = "There was an error updating the note.";
            messageType = "danger";
            request.setAttribute("message", message);
        }

        // Reconfigure note and item output
        if (noteIsValid) {
            Item item = noteToUpdate.getItem();
            User user = noteToUpdate.getUser();

            request.setAttribute("message", message);
            request.setAttribute("messageType", messageType);
            request.setAttribute("user", user);
            request.setAttribute("item", item);

            url = "configureOutput";
        }

        // forward the request
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }
}
