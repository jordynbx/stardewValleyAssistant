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
//TODO add checks so that user can only delete their own note
@WebServlet(
        urlPatterns = {"/deleteNoteAction"}
)
@Log4j2
public class DeleteNoteAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        ItemProcessor processor = new ItemProcessor();
        GenericDao<Note> noteDao = new GenericDao<>(Note.class);
        String message = null;
        Item item = null;
        User user = null;
        Note noteToDelete;
        Boolean noteIsValid = false;
        String url = "error.jsp";

        if ((request.getParameter("submit").equals("delete"))
                && (!request.getParameter("noteId").equals(""))) {

            // Get note id from form
            int noteId = Integer.parseInt(request.getParameter("noteId"));
            noteToDelete = noteDao.getById(noteId);

            // Get user and item data for reload before deleting the note
            item = noteToDelete.getItem();
            user = noteToDelete.getUser();

            // delete the note
            noteDao.delete(noteDao.getById(noteId));

            // output a success message
            message = "Your note was successfully deleted!";

            // repopulate the results page
            noteIsValid = true;

        } else if ((request.getParameter("submit").equals("cancel"))
                && (!request.getParameter("noteId").equals(""))) {
            noteIsValid = true;
            message = "Your note was note deleted.";
        } else {
            message = " accessing the note to delete";
            request.setAttribute("message", message);
        }

        if (noteIsValid) {
            // Reconfigure note and item output
            if (item.getType().equals("crop")) {
                Crop crop = processor.processCrop(item.getId());
                request.setAttribute("crop", crop);
            }

            // reconfigure notes
            List<Note> notes = processor.generateNotes(user.getId(), item.getId());
            request.setAttribute("itemNotes", notes);
            // set display attributes
            request.setAttribute("item", item);
            request.setAttribute("success", true);
            request.setAttribute("updateMessage", message);
            request.setAttribute("showUpdateMessage", true);

            url = "results.jsp";
        }


        // forward the request
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
