package edu.matc.controller;

import edu.matc.entity.*;
import edu.matc.persistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Log4j2
@WebServlet(
        name = "searchItem",
        urlPatterns = {"/searchItem"}
)
public class SearchItem extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {

    HttpSession session = request.getSession();
    ItemProcessor processor = new ItemProcessor();

    List<Item> items;
    Item item = null;

    boolean itemExists = false;

    // Get item from form and check if it exists
    GenericDao<Item> itemDao = new GenericDao<>(Item.class);
    if (request.getParameter("submit").equals("search")) {
        items = itemDao.getByPropertyEqualString("name",
               request.getParameter("searchTerm"));
        if (items.size() == 1) {
            item = items.get(0);
            itemExists = true;
        } else {
            session.setAttribute("message", "The item was not found, please try again");
        }
    }

    session.setAttribute("success", itemExists);

    /*
      In V1 of this project, all items will be crops, so this seems unnecessary. However, in future
      versions, items could be crops, fish, animal products, gems, or something else, and each
      type of item will require it's own if statement
     */
    if (itemExists) {
        int searchItemId = item.getId();

        /*
            Initialize attributes for item types
         */
        session.setAttribute("isCrop", "false");
        session.setAttribute("isForage", "false");
        session.setAttribute("isFish", "false");
        session.setAttribute("isRecipe", "false");
        session.setAttribute("isMineral", "false");
        session.setAttribute("isWeapon", "false");
        session.setAttribute("isAnimalProduct", "false");

        /*
            Process item based on type
         */
        if (item.getType().equals("crop")) {
            Crop crop = processor.processCrop(searchItemId);
            session.setAttribute("crop", crop);
            session.setAttribute("isCrop", "true");
        }

        if (item.getType().equals("forage")) {
            itemNotYetImplemented(session, "forage items");
        }

        if (item.getType().equals("fish")) {
            itemNotYetImplemented(session, "fish");
        }

        if (item.getType().equals("recipe")) {
            itemNotYetImplemented(session, "recipes");
        }

        if (item.getType().equals("mineral")) {
            itemNotYetImplemented(session, "minerals");
        }

        if (item.getType().equals("weapon")) {
            itemNotYetImplemented(session, "weapons");
        }

        if (item.getType().equals("animal product")) {
            itemNotYetImplemented(session, "animal products");
        }

        if (request.isUserInRole("user") || request.isUserInRole("admin")) {

            int userId = (int) session.getAttribute("currentUserId");

            // configure notes
            List<Note> notes = processor.generateNotes(userId, searchItemId);
            session.setAttribute("itemNotes", notes);

            // configure recent searches
            processor.addSearch(userId, searchItemId);
            List<String> searches = processor.generateSearches(userId);
            session.setAttribute("userSearchItemNames", searches);

            // figure out if item is a favorite
            Boolean isFavoriteItem = processor.isFavorite(userId, searchItemId);
            session.setAttribute("isFavoriteItem", isFavoriteItem);

        }
        session.setAttribute("item", item);
    }

        String url = "results";
        response.sendRedirect(url);
    }

    public void itemNotYetImplemented(HttpSession session, String itemType) {
        String thisMessage = "<strong>We're sorry!</strong>\n" +
                "Support for " + itemType + " has not yet been added.\n" +
                "<a href=\"index.jsp\" class=\"alert-link\"> Try searching again.</a>";
        session.setAttribute("itemType", itemType);
        session.setAttribute("showUpdateMessage", "true");
        session.setAttribute("messageType", "danger");
        session.setAttribute("updateMessage", thisMessage);
    }
}
