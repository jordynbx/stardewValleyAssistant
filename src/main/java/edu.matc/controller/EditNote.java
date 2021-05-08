package edu.matc.controller;

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
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * The purpose of this servlet is to verify a user is allowed to edit
 * a specific note, and if so to forward the request to edit.jsp.
 * @author jordynbx
 */
@Log4j2
@WebServlet(
        name = "edit",
        urlPatterns = {"/edit"}
)
public class EditNote extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String url = null;

        // initialize errors
        boolean permissionError = false;
        boolean validityError = false;

        if (request.getParameter("id") != null) {

            GenericDao<Note> noteDao = new GenericDao<>(Note.class);

            // Get note id from form
            int noteId = Integer.parseInt(request.getParameter("id"));

            // Make sure the note is valid
            Note note = noteDao.getById(noteId);

            // If not is not null, continue
            if (note != null) {
                // Get the note, note content, the logged in user, and the user who wrote the note

                String noteContent = note.getNoteContent();
                User noteWriter = note.getUser();
                User loggedInUser = (User)session.getAttribute("loggedInUser");

                /**
                 * If logged in user and user who wrote note both exist,
                 * check if they're the same. If so, set the note as an
                 * attribute and forward to edit jsp. If not, forward
                 * to error page with error message.
                 */
                if (loggedInUser != null && noteWriter != null) {
                    if (noteWriter.getId() == loggedInUser.getId()) {
                        request.setAttribute("note", note);
                        url = "edit.jsp";
                    } else {
                        permissionError = true;
                    }
                } else {
                    permissionError = true;
                }
            } else {
              validityError = true;
            }
        } else {
            validityError = true;
        }

        /**
         * If logged in user is not the same as user who wrote note,
         * forward to error page and output error message
         */
        if (permissionError) {
            String message = ": you don't have permission to edit this note";
            request.setAttribute("message", message);
            url = "error.jsp";
        }

        /**
         * If note does not exist or there is otherwise an error accessing
         * the note, forward to error page and output error message
         */
        if (validityError) {
            String message = ": there was an error accessing this note";
            request.setAttribute("message", message);
            url = "error.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
