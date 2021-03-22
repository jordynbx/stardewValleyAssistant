package edu.matc.controller;

import edu.matc.entity.Note;
import edu.matc.persistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Log4j2
@WebServlet(
        name = "delete",
        urlPatterns = {"/delete"}
)
public class DeleteNote extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = null;
        if (request.getParameter("id") != null) {
            GenericDao<Note> noteDao = new GenericDao<>(Note.class);
            int noteId = Integer.parseInt(request.getParameter("id"));

            Note note = noteDao.getById(noteId);
            request.setAttribute("note", note);

            url = "delete.jsp";
        } else {
            String message = " accessing the note to delete";
            request.setAttribute("message", message);
            url = "error.jsp";
        }


        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}
