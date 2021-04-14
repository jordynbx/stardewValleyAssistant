package edu.matc.persistence;

import edu.matc.entity.Note;
import edu.matc.entity.Token;
import edu.matc.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;

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
     * Verifies successfully insert of new token
     */
    @Test
    void insertTokenSuccess() {
        GenericDao<User> userDao = new GenericDao<>(User.class);
        User user = userDao.getById(3);

        Calendar date = Calendar.getInstance();
        long timeInSecs = date.getTimeInMillis();
        Timestamp timestamp = new Timestamp(timeInSecs + (30 * 60 * 1000));

        Token token = new Token(user, "new-token", timestamp);
        int id = dao.insert(token);
        assertNotEquals(0, id);
        Token insertedToken = dao.getById(id);
        assertEquals("new-token", insertedToken.getToken());
    }

    /**
     * Verifies successful update of existing token
     */
    @Test
    void updateTokenSuccess () {
        String newToken = "updated-token";
        Token tokenToUpdate = dao.getById(2);
        tokenToUpdate.setToken(newToken);
        dao.saveOrUpdate(tokenToUpdate);
        Token retrievedToken = dao.getById(2);
        assertEquals(newToken, retrievedToken.getToken());
    }

    /**
     * Verifies successful deletion of token without user assigned to it
     */
    @Test
    void deleteTokenSuccess() {
        GenericDao<User> userDao = new GenericDao<>(User.class);
        Token token = dao.getById(2);
        User user = userDao.getById(token.getUser().getId());

        dao.delete(token);

        user = userDao.getById(user.getId());

        assertNull(dao.getById(2));
        assertNotNull(user);
    }

}
