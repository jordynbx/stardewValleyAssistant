package edu.matc.controller;

import edu.matc.entity.Crop;
import edu.matc.entity.Favorite;
import edu.matc.entity.Item;
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

/**
 * The purpose of this servlet is to get all of user's favorite items and
 * forward the request to favorites.jsp.
 * @author jordynbx
 */
@Log4j2
@WebServlet(
        name = "favorites",
        urlPatterns = {"/favorites"}
)
public class GetFavorites extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        GenericDao<Favorite> favoriteDao = new GenericDao<>(Favorite.class);
        GenericDao<Crop> cropDao = new GenericDao<>(Crop.class);
        GenericDao<Item> itemDao = new GenericDao<>(Item.class);


        String message = "";

        int userId = (int) session.getAttribute("currentUserId");
        List<Favorite> favorites = favoriteDao.getByPropertyEqualInt("user", userId);
        List<Crop> favoriteCrops = new ArrayList<>();
        List<Item> otherFavorites = new ArrayList<>();

        if (favorites.size() > 0) {

            for (Favorite favorite : favorites) {
                Item item = favorite.getItem();
                int itemId = item.getId();

                if (item.getType().equals("crop")) {
                    Crop crop = cropDao.getByUniquePropertyEqualInt("item", itemId);
                    favoriteCrops.add(crop);
                } else {
                    otherFavorites.add(item);
                }
            }
            message = "";
        } else {
            message = "Add some favorites to see them on your list!";
        }

        session.setAttribute("favoriteMessage", message);
        session.setAttribute("favoriteCrops", favoriteCrops);
        session.setAttribute("favoriteItems", otherFavorites);

        RequestDispatcher dispatcher = request.getRequestDispatcher("favorites.jsp");
        dispatcher.forward(request, response);

    }
}
