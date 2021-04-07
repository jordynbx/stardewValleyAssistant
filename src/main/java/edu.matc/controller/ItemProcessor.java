package edu.matc.controller;

import edu.matc.entity.*;
import edu.matc.persistence.GenericDao;
import lombok.extern.log4j.Log4j2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Item processor.
 */
@Log4j2
public class ItemProcessor {

    /**
     * Process crop crop.
     *
     * @param itemId the item id
     * @return the crop
     */
    public Crop processCrop(int itemId) {

        GenericDao<Crop> cropDao = new GenericDao<>(Crop.class);
        return cropDao.getByUniquePropertyEqualInt("item", itemId);
    }

    /**
     * Generate notes list.
     *
     * @param userId the user id
     * @param itemId the item id
     * @return the list
     */
    public List<Note> generateNotes(int userId, int itemId) {

        GenericDao<Note> noteDao = new GenericDao<>(Note.class);
        List<Note> notes = noteDao.getListByMultipleProperties("user", userId, "item", itemId);

        return notes;
    }

    public boolean isFavorite(int userId, int itemId) {
        GenericDao<Favorite> favoriteDao = new GenericDao<>(Favorite.class);
        Favorite favoriteItem = favoriteDao.getUniqueEntityByMultipleProperties("user", userId, "item", itemId);
        if (favoriteItem != null) {
            return true;
        } else {
            return false;
        }
    }


    public void addSearch(int userId, int itemId) {
        GenericDao<UserSearch> searchDao = new GenericDao<>(UserSearch.class);
        GenericDao<User> userDao = new GenericDao<>(User.class);
        GenericDao<Item> itemDao = new GenericDao<>(Item.class);

        User user = userDao.getById(userId);
        Item item = itemDao.getById(itemId);

        UserSearch search = new UserSearch(user, item);
        searchDao.insert(search);
    }

    /**
     * Generate searches list.
     *
     * @param userId the user id
     * @return the list
     */
    public List<String> generateSearches(int userId) {

        GenericDao<UserSearch> searchDao = new GenericDao<>(UserSearch.class);
        List<UserSearch> searches =
                searchDao.getByPropertyEqualInt("user", userId);


        return getMostRecentSearches(searches);
    }

    /**
     * Gets most recent searches.
     *
     * @param searches the searches
     * @return the most recent searches
     */
    public List<String> getMostRecentSearches(List<UserSearch> searches) {

        List<String> uniqueSearches = new ArrayList<>();
        List<String> mostRecentSearches = new ArrayList<>();


        // add all search item ids to list in reverse order
        for (int i = searches.size() - 1; i >= 0; i--) {

            Item item = searches.get(i).getItem();
            String itemName = item.getName();

            if (!uniqueSearches.contains(itemName)) {
                uniqueSearches.add(itemName);
            }
        }

        // get five most recent searches
        int listSize = uniqueSearches.size();

        for (int i = 0; i < 5; i++) {
            if (i < listSize) {
                String itemName = uniqueSearches.get(i);
                mostRecentSearches.add(itemName);
            }
        }

        return mostRecentSearches;
    }
}
