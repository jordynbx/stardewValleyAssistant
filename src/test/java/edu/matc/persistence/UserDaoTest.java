package edu.matc.persistence;

import edu.matc.entity.Token;
import edu.matc.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserDaoTest {

    GenericDao<User> dao;

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {

        edu.matc.test.util.Database database = edu.matc.test.util.Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new GenericDao<>(User.class);
    }

    /**
     * Verifies get of user by username and email
     */
    @Test
    void getByUsernameAndEmailSuccess() {
        User retrievedUser = (User)dao.getUniqueEntityByMultiplePropertyStrings("username", "user1", "email",
                "test@example.com");

        assertNotNull(retrievedUser);
        assertEquals(1, retrievedUser.getId());
    }

}
