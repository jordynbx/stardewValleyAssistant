package edu.matc.controller;

import edu.matc.entity.Crop;
import edu.matc.entity.Item;
import edu.matc.entity.Note;
import edu.matc.entity.User;
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
 * The purpose of this servlet is to consolidate duplicative code used to
 * reconfigure user-specific output for the results page after the user makes
 * a change, like adds or deletes a note.
 * @author jordynbx
 */
@WebServlet(
        urlPatterns = {"/configureOutput"}
)
@Log4j2
public class ConfigureOutput extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        ItemProcessor processor = new ItemProcessor();
        User user = (User)request.getAttribute("user");
        Item item = (Item)request.getAttribute("item");

        String message = (String) request.getAttribute("message");
        String messageType = (String) request.getAttribute("messageType");


        // Reconfigure note and item output
        if (item.getType().equals("crop")) {
            Crop crop = processor.processCrop(item.getId());
            session.setAttribute("crop", crop);
        }
//
        // reconfigure notes
        List<Note> notes = processor.generateNotes(user.getId(), item.getId());
        session.setAttribute("itemNotes", notes);

        // reconfigure recent searches
        processor.addSearch(user.getId(), item.getId());
        List<String> searches = processor.generateSearches(user.getId());
        session.setAttribute("userSearchItemNames", searches);

        // reconfigure favorite
        boolean isFavoriteItem = processor.isFavorite(user.getId(), item.getId());
        session.setAttribute("isFavoriteItem", isFavoriteItem);

        // set display attributes
        session.setAttribute("item", item);
        session.setAttribute("success", true);
        session.setAttribute("updateMessage", message);
        session.setAttribute("messageType", messageType);
        session.setAttribute("showUpdateMessage", true);


        String url = "results";
        response.sendRedirect(url);

    }
}
