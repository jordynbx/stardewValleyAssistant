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

@WebServlet(
        urlPatterns = {"/addFavoriteAction"}
)
@Log4j2
public class AddFavoriteAction extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

        HttpSession session = request.getSession();
        ItemProcessor processor = new ItemProcessor();
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

                // Add the item to favorites and output success message
                Favorite favorite = new Favorite(user, item);
                favoriteDao.insert(favorite);
                message = "THe item was successfully added as a favorite!";
                messageType ="success";
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
            // Reconfigure note and item output
            if (item.getType().equals("crop")) {
                Crop crop = processor.processCrop(item.getId());
                request.setAttribute("crop", crop);
            }
//
            // reconfigure notes
            List<Note> notes = processor.generateNotes(user.getId(), item.getId());
            request.setAttribute("itemNotes", notes);

            // reconfigure recent searches
            processor.addSearch(user.getId(), item.getId());
            List<String> searches = processor.generateSearches(user.getId());
            request.setAttribute("userSearchItemNames", searches);

            // reconfigure favorites
            Boolean isFavoriteItem = processor.isFavorite(user.getId(), item.getId());
            request.setAttribute("isFavoriteItem", isFavoriteItem);

            // set display attributes
            request.setAttribute("item", item);
            request.setAttribute("success", true);
            request.setAttribute("updateMessage", message);
            request.setAttribute("messageType", messageType);
            request.setAttribute("showUpdateMessage", true);

            url = "results.jsp";
        }

        // forward the request
        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }
}
