package edu.matc.persistence;

import edu.matc.entity.Item;
import edu.matc.entity.Note;
import edu.matc.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
     * Verifies gets all notes by user using getByPropertyEqualInt
     */
    @Test
    void getAllNotesByUserSuccess() {
        List<Note> notes = dao.getByPropertyEqualInt("user", 1);
        assertEquals(1, notes.size());
        assertEquals(1, notes.get(0).getUser().getId());
    }

    /**
     * Verifies a note is returned correctly based on id search
     */
    @Test
    void getByIdSuccess() {
        Note retrievedNote = (Note)dao.getById(2);
        assertNotNull(retrievedNote);
        assertEquals("lucky lunch", retrievedNote.getNoteContent());
    }

    /**
     * verify success get note by user and item
     */
    @Test
    void getByUserAndItemSuccess() {
        Note retrievedNote =
                (Note)dao.getUniqueEntityByMultipleProperties("user", 2, "item", 2);
        assertNotNull(retrievedNote);
        assertEquals("Need 2 more gold for bundle", retrievedNote.getNoteContent());
    }

    /**
     * verify success get list of notes by user and item
     */
    @Test
    void getByUserAndItemListSuccess() {
        List<Note> retrievedNotes = dao.getListByMultipleProperties("user", 2, "item", 6);
        assertEquals(2, retrievedNotes.size());
        assertEquals("save seeds for next spring", retrievedNotes.get(1).getNoteContent());
    }



    /**
     * Verifies successful delete of note
     */
    @Test
    void deleteSuccess() {
        dao.delete(dao.getById(3));
        assertNull(dao.getById(3));
    }

    /**
     * verify success update of note
     */
    @Test
    void updateSuccess() {
        String newNote = "need 1 more gold for bundle";
        Note noteToUpdate = dao.getById(1);
        noteToUpdate.setNoteContent((newNote));
        dao.saveOrUpdate(noteToUpdate);
        Note retrievedNote = dao.getById(1);
        assertEquals(newNote, retrievedNote.getNoteContent());
    }

    /**
     * verify success insert of note
     */
    @Test
    void insertSuccess() {
        GenericDao<User> userDao = new GenericDao<>(User.class);
        GenericDao<Item> itemDao = new GenericDao<>(Item.class);
        User user = userDao.getById(3);
        Item item = itemDao.getById(6);
        String newNoteContent = "save 10 seeds";
        Note newNote = new Note(item, user, newNoteContent);

        int id = dao.insert(newNote);

        assertNotEquals(0, id);
        Note insertedNote = dao.getById(id);
        String expectedUser = "user3";
        assertEquals(newNoteContent, insertedNote.getNoteContent());
        assertNotNull(insertedNote.getUser());
        assertEquals(expectedUser, insertedNote.getUser().getUsername());
    }


}
