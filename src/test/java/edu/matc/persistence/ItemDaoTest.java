package edu.matc.persistence;

import edu.matc.entity.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemDaoTest {

    ItemDao dao;

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records
     * 2. create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {
        edu.matc.test.util.Database database = edu.matc.test.util.Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new ItemDao();
    }

    /**
     * verifies gets all items successfully
     */
    @Test
    void getAllItemsSuccess() {
        List<Item> items = dao.getAllItems();
        assertEquals(6, items.size());
    }

}
