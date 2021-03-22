package edu.matc.controller;

import edu.matc.entity.User;
import edu.matc.persistence.GenericDao;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.mail.Session;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * A simple servlet whose purpose is to redirect to the home page
 * after a log in attempt
 * @author pwaite
 */

@WebServlet(
        urlPatterns = {"/loginAction"}
)

@Log4j2
public class LoginAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        log.info("The logged in user: " + request.getRemoteUser() + " has a role of admin: " + request.isUserInRole("admin"));


        HttpSession session = request.getSession();
        String username = request.getUserPrincipal().getName();

        GenericDao<User> userDao = new GenericDao<>(User.class);
        User user = (User)userDao.getByUniquePropertyEqualString("username", username);
        session.setAttribute("currentUser", username);
        int userId = user.getId();
        session.setAttribute("currentUserId", userId);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
}