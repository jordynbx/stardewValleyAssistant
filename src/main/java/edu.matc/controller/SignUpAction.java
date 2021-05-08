package edu.matc.controller;

import edu.matc.entity.Role;
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
 * The purpose of this servlet is to sign a new user up for
 * an account.
 *
 * @author jordynbx
 */

@WebServlet(
        name = "signUpAction",
        urlPatterns = {"/signUpAction"}
)

@Log4j2
public class SignUpAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        GenericDao<User> userDao = new GenericDao<>(User.class);
        GenericDao<Role> roleDao = new GenericDao<>(Role.class);

        // initialize variables that are attributes
        String errorMessage = "";
        String message = "";
        String username = "";
        String email = "";

        String url = "";

        // Get parameters from form
        email = request.getParameter("userEmail");
        username = request.getParameter("userUsername");
        String userPassword = request.getParameter("userPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        boolean createUser = false;

        // Check if username is already in use
        User existingUser = userDao.getByUniquePropertyEqualString("username", username);

        if (existingUser == null) {

            // Verify passwords match
            if (userPassword.equals(confirmPassword)) {
                createUser = true;
            } else {
                errorMessage = "Your passwords do not match. Please re-enter.";
                session.setAttribute("enteredEmail", email);
                session.setAttribute("enteredUsername", username);
                session.setAttribute("errorMessage", errorMessage);
                url = "signup";
            }
        } else {
            errorMessage = "That username is already in use. Please select another username.";
            session.setAttribute("errorMessage", errorMessage);
            session.setAttribute("enteredEmail", email);
            url = "signup";
        }

        // Hash password and create user
        if (createUser) {
            MessageDigestCredentialHandler credentialHandler
                    = new MessageDigestCredentialHandler();

            // Hash password
            try {
                credentialHandler.setAlgorithm("sha-256");
            } catch (NoSuchAlgorithmException e) {
                log.error("Error hashing password: " + e);
            }
            credentialHandler.setEncoding("UTF-8");
            String hashedPassword = credentialHandler.mutate(userPassword);

            // Create user
            User user = new User(email, username, hashedPassword);
            userDao.insert(user);
            User insertedUser = userDao.getByUniquePropertyEqualString("username", username);

            // Assign role
            Role role = new Role("user", username, insertedUser);
            roleDao.insert(role);

            message = "Your account has been created. Please log in.";
            session.setAttribute("updateMessage", message);

            // clear form fields
            session.setAttribute("enteredEmail", "");
            session.setAttribute("enteredUsername", "");

            url = "loginAction";
        }

        response.sendRedirect(url);
    }
}