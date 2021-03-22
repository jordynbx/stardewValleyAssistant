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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        ItemProcessor processor = new ItemProcessor();

        GenericDao<Note> noteDao = new GenericDao<>(Note.class);
        String message;
        Note noteToUpdate = null;

        if (request.getParameter("submit").equals("update")) {
            int noteId = Integer.parseInt(request.getParameter("noteId"));
            String newNote = request.getParameter("editNote");

            noteToUpdate = noteDao.getById(noteId);
            noteToUpdate.setNoteContent(newNote);
            noteDao.saveOrUpdate(noteToUpdate);
            message = "Your note was successfully updated!";
        } else {
            message = "Your note was not updated";
        }

        // Reconfigure note and item output
        Item item = noteToUpdate.getItem();
        User user = noteToUpdate.getUser();
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

        // forward the request
        RequestDispatcher dispatcher = request.getRequestDispatcher("results.jsp");
        dispatcher.forward(request, response);

    }
}
