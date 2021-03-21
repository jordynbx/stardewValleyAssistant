package edu.matc.controller;

import edu.matc.entity.Crop;
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
import java.util.ArrayList;
import java.util.List;

//TODO add functionality to display note
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

    List<Item> items;
    Item item = null;

    Boolean processItem = false;

    // Get item from form and check if it exists
    GenericDao<Item> itemDao = new GenericDao<>(Item.class);
    if (request.getParameter("submit").equals("search")) {
        items = itemDao.getByPropertyEqualString("name",
               request.getParameter("searchTerm"));
        if (items.size() == 1) {
            item = items.get(0);
            request.setAttribute("item", item);
            processItem = true;
        } else {
            request.setAttribute("message", "The item was not found, please try again");
        }
    }

    request.setAttribute("success", processItem);

    /**
     * In V1 of this project, all items will be crops, so this seems unnecessary. However, in future
     * versions, items could be crops, fish, animal products, gems, or something else, and each
     * type of item will require it's own if statement
     */
    //TODO add output message indicating support hasn't been added yet for non-
        // TODO maybe move this to a bean since it's duplicated?
    if (processItem) {
        int searchItemId = item.getId();
        int userId = (int) session.getAttribute("currentUserId");

        if (item.getType().equals("crop")) {
            GenericDao<Crop> cropDao = new GenericDao<>(Crop.class);

            Crop crop = cropDao.getByUniquePropertyEqualInt("itemId", searchItemId);
            log.info(searchItemId);
            request.setAttribute("crop", crop);
        }

        // Get notes regardless of which type of item
        GenericDao<Note> noteDao = new GenericDao<>(Note.class);

        List<Note> notes = noteDao.getListByMultipleProperties("user", userId, "item", searchItemId);

        request.setAttribute("itemNotes", notes);

    }

    RequestDispatcher dispatcher = request.getRequestDispatcher("/results.jsp");
    dispatcher.forward(request, response);
    }
}
