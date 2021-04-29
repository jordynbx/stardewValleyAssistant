package edu.matc.controller;

import edu.matc.api.SendEmail;
import edu.matc.entity.Favorite;
import edu.matc.entity.Token;
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
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.util.Base64;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * The purpose of this servlet is to process requests for users
 * who want to change their password by verifying their username
 * and email match and sending that email a link to reset their
 * password.
 */
@Log4j2
@WebServlet(
        name = "resetPasswordAction",
        urlPatterns = {"/resetPasswordAction"}
)
public class ResetPasswordAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        GenericDao<User> userDao = new GenericDao<>(User.class);
        GenericDao<Token> tokenDao = new GenericDao<>(Token.class);

        String message = "";

        // Check if username and email match valid user
        String email = request.getParameter("userEmail");
        String username = request.getParameter("userUsername");

        User user = userDao.getUniqueEntityByMultiplePropertyStrings
                ("email", email, "username", username);

        if (user != null) {
            // get token
            String generatedToken = generateToken();

            // get time 30 minutes away
            Calendar date = Calendar.getInstance();
            long timeInSecs = date.getTimeInMillis();
//            log.info("orig time: " + date);
            Timestamp expiration = new Timestamp(timeInSecs + (30 * 60 * 1000));
//            log.info("new time: " + expiration);

            // check if there is an existing token for this user
            Token existingToken = tokenDao.getByUniquePropertyEqualInt("user", user.getId());

            // if token exists, replace it with new token
            // if it doesn't exist, create a new token
            if (existingToken != null) {
                existingToken.setToken(generatedToken);
                existingToken.setExpiration(expiration);
                tokenDao.saveOrUpdate(existingToken);
            } else {
                Token newToken = new Token(user, generatedToken, expiration);
                tokenDao.insert(newToken);
            }

            // send user email with link to reset password
            SendEmail resetEmail = new SendEmail();
            boolean sendEmailSuccess = resetEmail.resetPassword(email, generatedToken);

            if (sendEmailSuccess) {
                message = "Please check your email for a link to reset your password.";
            } else {
                message = "There was an error. Please try again again later or contact stardewvalleyassistant@gmail" +
                        ".com for help.";
            }
        } else {
            message = "The username and email combination was not found. Please try again.";
        }

        request.setAttribute("updateMessage", message);

        RequestDispatcher dispatcher = request.getRequestDispatcher("forgotPassword.jsp");
        dispatcher.forward(request, response);

    }

    /**
     * Generate unique token
     * Credit to Dmitriy Dumanskiy (https://stackoverflow
     * .com/questions/13992972/how-to-create-a-authentication-token-using-java)
     *
     * @return token
     */
    public String generateToken() {
        SecureRandom secureRandom = new SecureRandom();
        Base64.Encoder base64Encoder = Base64.getUrlEncoder();

        byte[] randomBytes = new byte[24];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }
}
