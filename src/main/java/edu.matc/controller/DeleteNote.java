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
 * The purpose of this servlet is to verify a user is allowed to delete a specific
 * note, and if so, forward the request to delete.jsp. If not, send the user to an
 * appropriate error page.
 * @author jordynbx
 */
@Log4j2
@WebServlet(
        name = "delete",
        urlPatterns = {"/delete"}
)
public class DeleteNote extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        HttpSession session = request.getSession();
        String url = null;

        if (request.getParameter("id") != null) {

            GenericDao<Note> noteDao = new GenericDao<>(Note.class);

            // Get note id from form
            int noteId = Integer.parseInt(request.getParameter("id"));

            // Get the note, the logged in user, and the user who wrote the note
            Note note = noteDao.getById(noteId);
            User noteWriter = note.getUser();
            User loggedInUser = (User)session.getAttribute("loggedInUser");

            // initialize permission error
            boolean permissionError = false;

            /**
             * If logged in user and user who wrote note both exist,
             * check if they're the same. If so, set the note as an
             * attribute and forward to delete jsp. If not, forward
             * to error page with error message.
             */
            if (loggedInUser != null && noteWriter != null) {
                if (noteWriter.getId() == loggedInUser.getId()) {
                    request.setAttribute("note", note);
                    url = "delete.jsp";
                } else {
                    permissionError = true;
                }
            } else {
                permissionError = true;
            }

            /**
             * If logged in user is not the same as user who wrote note,
             * forward to error page and output error message
             */
            if (permissionError) {
                String message = ": you don't have permission to access this note";
                request.setAttribute("message", message);
                url = "error.jsp";
            }
        } else {
            String message = ": there was an error accessing the note to delete";
            request.setAttribute("message", message);
            url = "error.jsp";
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
