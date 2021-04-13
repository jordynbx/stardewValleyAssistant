package edu.matc.controller;

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
 * The purpose of this servlet is to forward to newPassword.jsp,
 * which is the form used for users to reset their password from
 * a forgotten password link
 */
@Log4j2
@WebServlet(
        name = "newPassword",
        urlPatterns = {"/newPassword"}
)
public class NewPassword extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userToken = request.getParameter("token");

        request.setAttribute("token", userToken);

        RequestDispatcher dispatcher = request.getRequestDispatcher("newPassword.jsp");
        dispatcher.forward(request, response);


    }
}
