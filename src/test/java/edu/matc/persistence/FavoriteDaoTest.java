package edu.matc.persistence;

import edu.matc.entity.Favorite;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FavoriteDaoTest {

    GenericDao dao;

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {

        edu.matc.test.util.Database database = edu.matc.test.util.Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new GenericDao(Favorite.class);
    }


    /**
     * verifies gets all orders successfully
     */
    @Test
    void getAllFavoritesSuccess() {
        List<Favorite> favorites = dao.getAll();
        assertEquals(7, favorites.size());
    }



}
