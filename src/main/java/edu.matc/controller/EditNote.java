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
        name = "edit",
        urlPatterns = {"/edit"}
)
public class EditNote extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        GenericDao<Note> noteDao = new GenericDao<>(Note.class);
        int noteId = Integer.parseInt(request.getParameter("id"));

        Note note = noteDao.getById(noteId);
        String noteContent = note.getNoteContent();

        log.info("The note content: " + noteContent);


//        RequestDispatcher dispatcher = request.getRequestDispatcher("results.jsp");
//        dispatcher.forward(request, response);
    }
}
