package edu.matc.controller;

import edu.matc.entity.User;
import edu.matc.persistence.GenericDao;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.realm.MessageDigestCredentialHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * The purpose of this servlet is to process password updates
 * for users who are logged in to their account and want to
 * set a new password
 */
@WebServlet(
        name = "changePasswordAction",
        urlPatterns = {"/changePasswordAction"}
)

@Log4j2
public class ChangePasswordAction extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        String message = "";

        GenericDao<User> userDao = new GenericDao<>(User.class);

        // Get parameters from form
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmNewPassword");

        boolean updatePassword = false;
        boolean passwordsMatch = false;

        // get user
        User user = (User) session.getAttribute("loggedInUser");

        // verify new passwords match
        if (newPassword.equals(confirmPassword)) {

            // verify current password is correct
            MessageDigestCredentialHandler credentialHandler
                    = new MessageDigestCredentialHandler();

            // Hash password
            try {
                credentialHandler.setAlgorithm("sha-256");
            } catch (NoSuchAlgorithmException e) {
                log.error("Error hashing password: " + e);
            }
            credentialHandler.setEncoding("UTF-8");

            passwordsMatch = credentialHandler.matches(currentPassword, user.getPassword());

            // if passwords are the same, password can be updated
            if (passwordsMatch) {
                String hashedNewPassword = credentialHandler.mutate(newPassword);
                user.setPassword(hashedNewPassword);
                userDao.saveOrUpdate(user);
                message = "Your password has been updated.";
            } else {
                message = "Current password is not correct.";
            }
        } else {
            message = "Please make sure your new password values match.";
        }

        session.setAttribute("message", message);
        response.sendRedirect("account");
    }
}