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
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

@WebServlet(
        name = "newPasswordAction",
        urlPatterns = {"/newPasswordAction"}
)
@Log4j2
public class NewPasswordAction extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        GenericDao<User> userDao = new GenericDao<>(User.class);
        GenericDao<Token> tokenDao = new GenericDao<>(Token.class);

        String userToken = request.getParameter("token");
        log.info("TOKEN: " + userToken);

        // Get username to update password for
        Token token = tokenDao.getByUniquePropertyEqualString("token", userToken);
        log.info("TOKEN: " + token);
        if (token != null) {
            User user = userDao.getById(token.getUser().getId());
            log.info("USER: " + user);
            // somehow check if the date is before the expiration time

            // if it is, update the password
            String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmNewPassword");

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
                credentialHandler.setEncoding("UTF-8");
                String hashedNewPassword = credentialHandler.mutate(newPassword);
                user.setPassword(hashedNewPassword);
                userDao.saveOrUpdate(user);
            }
        }

        response.sendRedirect("loginAction");
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/loginAction");
//        dispatcher.forward(request, response);


    }
}
