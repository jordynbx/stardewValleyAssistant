package edu.matc.persistence;

import edu.matc.entity.Note;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Log4j2
public class NoteDao {

    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Gets all notes
     *
     * @return all the notes
     */
    public List<Note> getAllNotes() {

        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Note> query = builder.createQuery(Note.class);
        Root<Note> root = query.from(Note.class);
        List<Note> notes = session.createQuery(query).getResultList();

        session.close();
        return notes;
    }

    /**
     * Gets a note by id
     *
     * @return a note
     */
    public Note getById(int id) {
        Session session = sessionFactory.openSession();
        Note note = session.get(Note.class, id);

        session.close();
        return note;
    }
    
    /**
     * Get notes by user
     */
    public List<Note> getByUser(String propertyName, int value) {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Note> query = builder.createQuery( Note.class );
        Root<Note> root = query.from( Note.class );
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<Note> notes = session.createQuery(query).getResultList();

        session.close();
        return notes;
    }

    /**
     * update note
     * @param note  Note to be inserted or updated
     */
    public void saveOrUpdate(Note note) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(note);
        transaction.commit();
        session.close();
    }

    /**
     * update note
     * @param note  Note to be inserted or updated
     */
    public int insert(Note note) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(note);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Delete a note
     * @param note Note to be deleted
     */
    public void delete(Note note) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(note);
        transaction.commit();
        session.close();
    }
}
