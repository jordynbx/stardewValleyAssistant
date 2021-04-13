package edu.matc.controller;

import edu.matc.entity.Favorite;
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

/**
 * The purpose of this servlet is to forward to favorites.jsp
 */
@Log4j2
@WebServlet(
        name = "resetPasswordAction",
        urlPatterns = {"/resetPasswordAction"}
)
public class ResetPasswordAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        GenericDao<User> userDao = new GenericDao<>(User.class);

        // Check if username and email match valid user
        String email = request.getParameter("userEmail");
        String username = request.getParameter("userUsername");

        User user = (User) userDao.getUniqueEntityByMultiplePropertyStrings
                ("email", email, "username", username);

        if (user != null) {

        }

//        RequestDispatcher dispatcher = request.getRequestDispatcher("favorites.jsp");
//        dispatcher.forward(request, response);


    }
}
