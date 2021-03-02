package edu.matc.persistence;

import edu.matc.entity.Note;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NoteDaoTest {

    GenericDao<Note> dao;

    /**
     * Run set up tasks before each test:
     * 1. execute sql which deletes everything from the table and inserts records)
     * 2. Create any objects needed in the tests
     */
    @BeforeEach
    void setUp() {

        edu.matc.test.util.Database database = edu.matc.test.util.Database.getInstance();
        database.runSQL("cleandb.sql");

        dao = new GenericDao<>(Note.class);
    }

    /**
     * Verifies gets all notes sucessfully
     */
    @Test
    void getAllNotesSuccess() {
        List<Note> notes = dao.getAll();
        assertEquals(3, notes.size());
    }

    /**
     * Verifies gets all notes by user using getByPropertyEqual
     */
    @Test
    void getAllNotesByUserSuccess() {
        List<Note> notes = dao.getByPropertyEqual("user", 1);
        assertEquals(1, notes.size());
        assertEquals(1, notes.get(0).getUser().getId());
    }


}
