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

// TODO need to differentiate between adding and updating a note
// TODO cleanup/organize code
@Log4j2
@WebServlet(
        name = "addNote",
        urlPatterns = {"/addNote"}
)
public class AddNote extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // create DAOs
        GenericDao<Note> noteDao = new GenericDao<>(Note.class);
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

        // Create note
        if (!noteContent.equals("")) {
            Note note = new Note(item, user, noteContent);
            noteDao.insert(note);
        }

        String message = "Your note was added!";
        String messageType = "success";

        request.setAttribute("message", message);
        request.setAttribute("messageType", messageType);
        request.setAttribute("user", user);
        request.setAttribute("item", item);
        String url = "configureOutput";

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}

