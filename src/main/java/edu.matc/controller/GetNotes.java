package edu.matc.controller;

import edu.matc.entity.Crop;
import edu.matc.entity.Favorite;
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
import java.util.List;

@Log4j2
@WebServlet(
        name = "notes",
        urlPatterns = {"/notes"}
)
public class GetNotes extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        GenericDao<Note> noteDao = new GenericDao<>(Note.class);
        GenericDao<Crop> cropDao = new GenericDao<>(Crop.class);

        String message = "";

        int userId = (int) session.getAttribute("currentUserId");

        List<Note> notes = noteDao.getByPropertyEqualInt("user", userId);

        session.setAttribute("userNotes", notes);

        RequestDispatcher dispatcher = request.getRequestDispatcher("notes.jsp");
        dispatcher.forward(request, response);




    }
}
