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

@Log4j2
@WebServlet(
        name = "getFavorites",
        urlPatterns = {"/getFavorites"}
)
public class GetFavorites extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        GenericDao<Favorite> favoriteDao = new GenericDao<>(Favorite.class);
        GenericDao<Crop> cropDao = new GenericDao<>(Crop.class);

        String message = "";

        int userId = (int) session.getAttribute("currentUserId");
        List<Favorite> favorites = favoriteDao.getByPropertyEqualInt("user", userId);
        List<Integer> favoriteItemIds = new ArrayList<>();
        List<Crop> favoriteCrops = new ArrayList<>();
        List<Item> favoriteItems = new ArrayList<>();

        if (favorites.size() > 0) {

            for (Favorite favorite : favorites) {
                Item item = favorite.getItem();
                int itemId = item.getId();
                favoriteItemIds.add(itemId);
                favoriteItems.add(item);

                if (item.getType().equals("crop")) {
                    Crop crop = cropDao.getByUniquePropertyEqualInt("itemId", itemId);
                    favoriteCrops.add(crop);
                }
            }


            message = "";
        } else {
            message = "Add some favorites to see them on your list!";

        }


        session.setAttribute("favoriteMessage", message);
//        session.setAttribute("userFavorites", favorites);
//        session.setAttribute("favoriteItemIds", favoriteItemIds);
        session.setAttribute("favoriteCrops", favoriteCrops);
//        session.setAttribute("favoriteItems", favoriteItems);


        RequestDispatcher dispatcher = request.getRequestDispatcher("favorites");
        dispatcher.forward(request, response);


    }
}
