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
 * The purpose of this servlet is to verify a user has permission
 * to remove a favorite item, and if so to forward the request to
 * removeFavoriteItem.jsp.
 * @author jordynbx
 */
@Log4j2
@WebServlet(
        name = "removeFavorite",
        urlPatterns = {"/removeFavorite"}
)
public class RemoveFavorite extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        String url = null;


        if (request.getParameter("id") != null) {

            GenericDao<Item> itemDao = new GenericDao<>(Item.class);
            GenericDao<User> userDao = new GenericDao<>(User.class);
            GenericDao<Favorite> favoriteDao = new GenericDao<>(Favorite.class);

            // Get user and item data
            int itemId = Integer.parseInt(request.getParameter("id"));
            int userId = (int) session.getAttribute("currentUserId");
            Item item = itemDao.getById(itemId);


            // Get the favorite, logged in user, and user with the favorite
            Favorite favorite =
                    favoriteDao.getUniqueEntityByMultipleProperties("user", userId, "item", itemId);
            User favoriteUser = favorite.getUser();
            User loggedInUser = (User)session.getAttribute("loggedInUser");

            // initialize permission error
            boolean permissionError = false;

            /**
             * If logged in user and user who with favorite both exist,
             * check if they're the same. If so, set the favorite as an
             * attribute and forward. If not, forward
             * to error page with error message.
             */
            if (loggedInUser != null && favoriteUser != null) {
                if (favoriteUser.getId() == loggedInUser.getId()) {
                    request.setAttribute("favorite", favorite);
                    request.setAttribute("item", item);
                    url = "removeFavoriteItem.jsp";
                } else {
                    permissionError = true;
                }
            } else {
                permissionError = true;
            }

            /**
             * If logged in user is not the same as user who wrote note,
             * forward to error page and output error message
             */
            if (permissionError) {
                String message = "You don't have permission to remove this favorite.";
                request.setAttribute("message", message);
                url = "error.jsp";
            }
        } else if (request.getParameter("id").equals("")) {
            String message = "There was an error accessing the favorite";
            request.setAttribute("message", message);
            url = "error.jsp";
        } else {
            String message = "There was an error accessing the favorite";;
            request.setAttribute("message", message);
            url = "error.jsp";
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher(url);
        dispatcher.forward(request, response);
    }
}


