package edu.matc.persistence;

import edu.matc.entity.Item;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import java.util.List;

public class ItemDao {

    private final Logger logger = LogManager.getLogger(this.getClass());
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Gets all items
     *
     * @return all the users
     */
    public List<Item> getAllItems() {
        // create the connection
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Item> query = builder.createQuery(Item.class);
        Root<Item> root = query.from(Item.class);
        List<Item> items = session.createQuery(query).getResultList();

        session.close();
        return items;
    }

    /**
     * Gets items by searched for name
     *
     * @return all items with the searched for name
     */
    public List<Item> getItemByName(String name) {
        Session session = sessionFactory.openSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Item> query = builder.createQuery(Item.class);
        Root<Item> root = query.from(Item.class);

        Expression<String> propertyPath = root.get("name");
        query.where(builder.like(propertyPath, "%" + name + "%"));
        List<Item> items = session.createQuery(query).getResultList();

        session.close();
        return items;
    }

    /**
     * Gets an item by id
     *
     * @return an item
     */
    public Item getById(int id) {
        Session session = sessionFactory.openSession();
        Item item = session.get(Item.class, id);

        session.close();
        return item;
    }

    /**
     * update item
     * @param item  ITem to be inserted or updated
     */
    public void saveOrUpdate(Item item) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(item);
        transaction.commit();
        session.close();
    }

    /**
     * update item
     * @param item  Item to be inserted or updated
     */
    public int insert(Item item) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(item);
        transaction.commit();
        session.close();
        return id;
    }

    /**
     * Delete a user
     * @param item Item to be deleted
     */
    public void delete(Item item) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(item);
        transaction.commit();
        session.close();
    }
}
