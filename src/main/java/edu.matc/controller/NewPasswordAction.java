package edu.matc.controller;

import edu.matc.entity.Token;
import edu.matc.entity.User;
import edu.matc.persistence.GenericDao;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.realm.MessageDigestCredentialHandler;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Date;

/**
 * The purpose of this servlet is to process password updates
 * for users who forgot their password and requested a change
 * password link
 */
@WebServlet(
        name = "newPasswordAction",
        urlPatterns = {"/newPasswordAction"}
)
@Log4j2
public class NewPasswordAction extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        GenericDao<User> userDao = new GenericDao<>(User.class);
        GenericDao<Token> tokenDao = new GenericDao<>(Token.class);

        String message = "";
        String url = "";
        String userToken = request.getParameter("token");


        // Get username to update password for
        Token token = tokenDao.getByUniquePropertyEqualString("token", userToken);

        if (token != null) {

            // Get the user associated with the token
            User user = userDao.getById(token.getUser().getId());;

            // Check if timestamp is expired
            Timestamp expirationTime = token.getExpiration();
            Date date = new Date();
            Timestamp currentTime = new Timestamp(date.getTime());

            if (expirationTime.after(currentTime)) {
                // if it is not expired, continue
                String newPassword = request.getParameter("newPassword");
                String confirmPassword = request.getParameter("confirmNewPassword");

                // verify entered passwords match
                if (newPassword.equals(confirmPassword)) {
                    log.debug("passwords match");
                    MessageDigestCredentialHandler credentialHandler
                            = new MessageDigestCredentialHandler();
                    // Hash password
                    try {
                        credentialHandler.setAlgorithm("sha-256");
                    } catch (NoSuchAlgorithmException e) {
                        log.error("Error hashing password: " + e);
                    }

                    // update password
                    credentialHandler.setEncoding("UTF-8");
                    String hashedNewPassword = credentialHandler.mutate(newPassword);
                    user.setPassword(hashedNewPassword);
                    userDao.saveOrUpdate(user);

                    // remove used token from db so it can't be reused
                    tokenDao.delete(token);

                    message = "Your password has been updated.";
                    url = "loginAction";
                } else {
                    message = "Your passwords do not match. Please try again.";
                    url = "newPassword?token=" + userToken;
                }
            } else {
                message = "Your password reset link has expired; please request a new one.";
                url = "loginAction";
            }
        } else {
            message = "This password link has expired.PLease request a new link.";
            url = "loginAction";
        }

        session.setAttribute("updateMessage", message);

        response.sendRedirect(url);

    }
}
