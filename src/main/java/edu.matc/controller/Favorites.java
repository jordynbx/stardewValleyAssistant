package edu.matc.controller;

import edu.matc.entity.Favorite;
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

/**
 * The purpose of this servlet is to forward to favorites.jsp
 */
@Log4j2
@WebServlet(
        name = "favorites",
        urlPatterns = {"/favorites"}
)
public class Favorites extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        RequestDispatcher dispatcher = request.getRequestDispatcher("favorites.jsp");
        dispatcher.forward(request, response);


    }
}
