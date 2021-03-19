package edu.matc.persistence;

import edu.matc.entity.Crop;
import edu.matc.entity.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CropDaoTest {

    GenericDao<Crop> dao;

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records
     * 2. create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {
        edu.matc.test.util.Database database = edu.matc.test.util.Database.getInstance();

        database.runSQL("cleandb.sql");

        dao = new GenericDao<>(Crop.class);
    }

    /**
     * Verifies gets the crop by the itemId
     */
    @Test
    void getCropByItemIdSuccess() {
        Crop retrievedCrop = (Crop)dao.getByUniquePropertyEqualInt("itemId", 3);
        assertNotNull(retrievedCrop);
        assertEquals(80, retrievedCrop.getSeedPrice());
    }

    /**
     * Verifies gets the crop by the itemId
     */
    @Test
    void getCropByIdSuccess() {
        Crop retrievedCrop = (Crop)dao.getById(1);
        assertNotNull(retrievedCrop);
        assertEquals(80, retrievedCrop.getSeedPrice());
    }
}
