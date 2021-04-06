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
import java.io.IOException;
import java.util.List;

@WebServlet(
        urlPatterns = {"/configureOutput"}
)
@Log4j2
public class ConfigureOutput extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ItemProcessor processor = new ItemProcessor();
        User user = (User)request.getAttribute("user");
        Item item = (Item)request.getAttribute("item");

        String message = (String) request.getAttribute("message");
        String messageType = (String) request.getAttribute("messageType");


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

        // reconfigure favorite
        boolean isFavoriteItem = processor.isFavorite(user.getId(), item.getId());
        request.setAttribute("isFavoriteItem", isFavoriteItem);

        // set display attributes
        request.setAttribute("item", item);
        request.setAttribute("success", true);
        request.setAttribute("updateMessage", message);
        request.setAttribute("messageType", messageType);
        request.setAttribute("showUpdateMessage", true);

        // forward the request
        RequestDispatcher dispatcher = request.getRequestDispatcher("results.jsp");
        dispatcher.forward(request, response);

    }
}
