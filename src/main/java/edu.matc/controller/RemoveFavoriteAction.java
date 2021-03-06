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
 * The purpose of this servlet is to process a user's request to
 * remove a favorite item.
 * @author jordynbx
 */
@WebServlet(
        urlPatterns = {"/removeFavoriteAction"}
)
@Log4j2
public class RemoveFavoriteAction extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        HttpSession session = request.getSession();
        ItemProcessor processor = new ItemProcessor();
        GenericDao<Favorite> favoriteDao = new GenericDao<>(Favorite.class);
        GenericDao<Item> itemDao = new GenericDao<>(Item.class);
        String message;
        String messageType;
        Item item = null;
        User user = null;
        Favorite favoriteToDelete;
        boolean favoriteIsValid = false;
        String url = "error.jsp";

        if (!request.getParameter("favoriteItemId").equals("")) {

            // Get item id, user id, and favorite
            int itemId = Integer.parseInt(request.getParameter("favoriteItemId"));

            // get favorite
            int favoriteId = Integer.parseInt(request.getParameter("favoriteId"));
            favoriteToDelete = favoriteDao.getById(favoriteId);

            // get user and item
            item = itemDao.getById(itemId);
            user = (User) session.getAttribute("loggedInUser");

            if (request.getParameter("submit").equals("confirm")) {

                // delete the favorite and output a success message
                favoriteDao.delete(favoriteToDelete);

                message = "The item was successfully removed from your favorites.";
                messageType ="success";
            } else if (request.getParameter("submit").equals("cancel")) {

                // output confirmation message
                message = "The item was not removed from your favorites.";
                messageType = "danger";

            } else {
                message = "There was an error removing the item from your favorites.";
                messageType = "danger";
            }

            // repopulate the results page
            favoriteIsValid = true;
        } else {
            message = "There was an error removing the item from your favorites.";
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
