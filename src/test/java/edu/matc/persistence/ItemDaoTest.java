package edu.matc.persistence;

import edu.matc.entity.Item;
import edu.matc.entity.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ItemDaoTest {

    GenericDao<Item> dao;

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records
     * 2. create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {
        edu.matc.test.util.Database database = edu.matc.test.util.Database.getInstance();

        database.runSQL("cleandb.sql");

        dao = new GenericDao<>(Item.class);
    }

    /**
     * verifies gets all items successfully
     */
    @Test
    void getAllItemsSuccess() {
        List<Item> items = dao.getAll();
        assertEquals(7, items.size());
    }

    /**
     * Verifies the get item by name search success
     */
    @Test
    void getItemByNameSuccess() {
        List<Item> items = dao.getByPropertyEqualString("name", "strawberry");
        assertEquals(1, items.size());
        assertEquals(6, items.get(0).getId());
    }

    /**
     * Verifies item is returned correctly based on id
     */
    @Test
    void getByIdSuccess() {
        Item retrievedItem = (Item)dao.getById(6);
        assertNotNull(retrievedItem);
        assertEquals("strawberry", retrievedItem.getName());
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
