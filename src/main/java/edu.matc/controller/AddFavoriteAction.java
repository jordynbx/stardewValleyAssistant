package edu.matc.controller;

import edu.matc.entity.*;
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
 * The purpose of this servlet is to verify a request to add a favorite item
 * is valid and process that request by eitheradding it as a favorite, returning the
 * user to the results page without adding it, or sending the user to an appropriate
 * error page.
 * @author jordynbx
 */
@WebServlet(
        urlPatterns = {"/addFavoriteAction"}
)
@Log4j2
public class AddFavoriteAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        HttpSession session = request.getSession();
        GenericDao<Favorite> favoriteDao = new GenericDao<>(Favorite.class);
        GenericDao<Item> itemDao = new GenericDao<>(Item.class);
        String message;
        String messageType;
        Item item = null;
        User user = null;
        boolean favoriteIsValid = false;
        String url = "error.jsp";

        if (!request.getParameter("favoriteItemId").equals("")) {

            // Get item id and user id
            int itemId = Integer.parseInt(request.getParameter("favoriteItemId"));
            item = itemDao.getById(itemId);

            user = (User) session.getAttribute("loggedInUser");

            if (request.getParameter("submit").equals("confirm")) {

                // make sure item isn't already in favorites list
                Favorite existingFavorite =
                        favoriteDao.getUniqueEntityByMultipleProperties("user", user.getId(), "item",
                        item.getId());

                if (existingFavorite != null) {
                    message = "This item was already added as a favorite";
                    messageType = "danger";
                } else {
                    // Add the item to favorites and output success message
                    Favorite favorite = new Favorite(user, item);
                    favoriteDao.insert(favorite);
                    message = "The item was successfully added as a favorite!";
                    messageType ="success";
                }
            } else if (request.getParameter("submit").equals("cancel")) {

                // output confirmation message
                message = "The item was not added as a favorite.";
                messageType = "danger";

            } else {
                message = "There was an error adding the item as a favorite.";
                messageType = "danger";
            }

            // repopulate the results page
            favoriteIsValid = true;
        } else {
            message = "There was an error adding the item as a favorite.";
            messageType = "danger";
            request.setAttribute("message", message);
        }
        if (favoriteIsValid) {
            request.setAttribute("message", message);
            request.setAttribute("messageType", messageType);
            request.setAttribute("user", user);
            request.setAttribute("item", item);
            url = "configureOutput";
        }

        // forward the request
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }
}
