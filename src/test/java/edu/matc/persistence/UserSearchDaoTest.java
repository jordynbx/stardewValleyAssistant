package edu.matc.persistence;

import edu.matc.entity.Note;
import edu.matc.entity.UserSearch;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserSearchDaoTest {

    GenericDao<UserSearch> dao;

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
}
