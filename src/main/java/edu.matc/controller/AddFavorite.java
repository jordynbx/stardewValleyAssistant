package edu.matc.controller;

import edu.matc.entity.Favorite;
import edu.matc.entity.Item;
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

/**
 * The purpose of this servlet is to verify the item id exists and forward it to
 * the addFavoriteItem JSP.
 * @author jordynbx
 */
@Log4j2
@WebServlet(
        name = "addFavorite",
        urlPatterns = {"/addFavorite"}
)
public class AddFavorite extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String url = null;

        if (request.getParameter("id") != null) {

            GenericDao<Item> itemDao = new GenericDao<>(Item.class);

            // Get user and item data
            int itemId = Integer.parseInt(request.getParameter("id"));
            Item item = itemDao.getById(itemId);

            request.setAttribute("item", item);
            url = "addFavoriteItem.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);

    }

}


