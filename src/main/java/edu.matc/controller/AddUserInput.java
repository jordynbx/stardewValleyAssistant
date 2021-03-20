package edu.matc.controller;

import edu.matc.entity.Favorite;
import edu.matc.entity.Item;
import edu.matc.entity.Note;
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

// TODO need to differentiate between adding and updating a note
@Log4j2
@WebServlet(
        name = "addUserInput",
        urlPatterns = {"/addUserInput"}
)
public class AddUserInput  extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // create daos
        GenericDao<Note> noteDao = new GenericDao<>(Note.class);
        GenericDao<Favorite> favoriteDao = new GenericDao<>(Favorite.class);
        GenericDao<User> userDao = new GenericDao<>(User.class);
        GenericDao<Item> itemDao = new GenericDao<>(Item.class);

        // get form values
        String noteContent = request.getParameter("userNote");
        String itemIdString = request.getParameter("itemId");
        int itemId = Integer.parseInt(itemIdString);

        // get insert objects
        Item item = itemDao.getById(itemId);
        int userId = (int) session.getAttribute("currentUserId");
        User user = userDao.getById(userId);


        if (request.getParameter("addToFavorites") == null) {
            //TODO remove from favorites
            //TODO figure out how to make checkbox correct if they already checked it
            log.info("inside addToFavorites is null");
        } else {
            Favorite favorite = new Favorite(user, item);
            favoriteDao.insert(favorite);
        }

        // TODO echo note content into field?
        if (!noteContent.equals("")) {
            Note note = new Note(item, user, noteContent);
            noteDao.insert(note);
        }
        // Create note
        request.setAttribute("updateMessage", "Your item was updated!");

        RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
}

