package edu.matc.persistence;

import edu.matc.entity.Note;
import edu.matc.entity.Token;
import edu.matc.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class TokenDaoTest {

    GenericDao<Token> dao;

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {

        edu.matc.test.util.Database database = edu.matc.test.util.Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new GenericDao<>(Token.class);
    }

    /**
     * Verifies successful deletion of token without user assigned to it
     */
    @Test
    void deleteTokenSuccess() {
        GenericDao<User> userDao = new GenericDao<>(User.class);
        Token token = dao.getById(3);
        User user = userDao.getById(token.getUser().getId());

        dao.delete(token);

        user = userDao.getById(user.getId());

        assertNull(dao.getById(3));
        assertNotNull(user);
    }
}
