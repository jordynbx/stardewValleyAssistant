package edu.matc.persistence;

import edu.matc.entity.Item;
import edu.matc.entity.Note;
import edu.matc.entity.User;
import edu.matc.entity.UserSearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * The type User search dao test.
 * @author jordynbx
 */
public class UserSearchDaoTest {

    GenericDao<UserSearch> dao;
    GenericDao<User> userDao;
    GenericDao<Item> itemDao;


    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {

        edu.matc.test.util.Database database = edu.matc.test.util.Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new GenericDao<>(UserSearch.class);
        userDao = new GenericDao<>(User.class);
        itemDao = new GenericDao<>(Item.class);

    }

    /**
     * Verifies gets all notes by user using getByPropertyEqualInt
     */
    @Test
    void getAllSearchesByUserSuccess() {
        List<UserSearch> searches = dao.getByPropertyEqualInt("user", 2);
        assertEquals(7, searches.size());
        assertEquals(2, searches.get(0).getUser().getId());
    }

    /**
     * Verifies that if a user is deleted, all notes by that
     * user is deleted
     */
    @Test
    void deleteSearchesOnUserDeleteSuccess() {
        userDao.delete(userDao.getById(2));

        List<UserSearch> searches = dao.getByPropertyEqualInt("user", 2);
        assertEquals(0, searches.size());

    }

    /**
     * Verifies that if an item is deleted, all searches for that
     * item are deleted
     */
    @Test
    void deleteSearchesOnItemDeleteSuccess() {
        itemDao.delete(itemDao.getById(6));

        List<UserSearch> searches = dao.getByPropertyEqualInt("item", 6);
        assertEquals(0, searches.size());
    }
}
