package edu.matc.persistence;

import edu.matc.entity.Item;
import edu.matc.test.util.Database;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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

    /**
     * Verifies the get item by name search success
     */
    @Test
    void getItemByNameSuccess() {
        List<Item> items = dao.getItemByName("s");
        assertEquals(2, items.size());
    }

    /**
     * Verify successful insert of an item
     */
    @Test
    void insertSuccess() {
        Item newItem = new Item("egg", "animal product");
        int id = dao.insert(newItem);
        assertNotEquals(0,id);
        Item insertedItem = dao.getById(id);
        assertEquals(("egg"), insertedItem.getName());
    }

    /**
     * Verify successful update of item
     */
    @Test
    void updateSuccess() {
        String newItemName = "melon";
        Item itemToUpdate = dao.getById(2);
        itemToUpdate.setName(newItemName);
        dao.saveOrUpdate(itemToUpdate);
        Item retrievedItem = dao.getById(2);
        assertEquals(newItemName, retrievedItem.getName());
    }

    /**
     * verify successful delete of an item
     */
    @Test
    void deleteSuccess() {
        dao.delete(dao.getById(3));
        assertNull(dao.getById(3));
    }




}
