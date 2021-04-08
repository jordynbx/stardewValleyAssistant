package edu.matc.controller;

import edu.matc.api.SendEmail;
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
        name = "contactAction",
        urlPatterns = {"/contactAction"}
)
public class ContactAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        SendEmail email = new SendEmail();

        String userMessage = "";
        String returnAddress = "";
        String userMessageSubject = "";

        boolean showUpdateMessage = false;

        if (request.getParameter("submit").equals("submit")) {

            userMessage = request.getParameter("userMessage");
            userMessageSubject = request.getParameter("userMessageSubject");

            if (!request.getParameter("userMessageEmail").equals("")) {
                returnAddress = request.getParameter("userMessageEmail");
            }
        }

        boolean emailSent = email.createEmail(userMessageSubject, userMessage, returnAddress);

        if (emailSent) {
            session.setAttribute("emailMessage", "Your message has been sent!");
            session.setAttribute("messageType", "success");
            userMessage = "";
            returnAddress = "";
            userMessageSubject = "";
            showUpdateMessage = true;
        } else {
            session.setAttribute("emailMessage", "There was an error sending your message, please try again.");
            session.setAttribute("messageType", "danger");
            showUpdateMessage = true;
        }

        session.setAttribute("showUpdateMessage", showUpdateMessage);
        session.setAttribute("userMessageSubjectValue", userMessageSubject);
        session.setAttribute("userMessageValue", userMessage);
        session.setAttribute("returnAddressValue", returnAddress);


        String url = "contact";
        response.sendRedirect(url);
//        RequestDispatcher dispatcher = request.getRequestDispatcher("contact.jsp");
//        dispatcher.forward(request, response);

    }
}
