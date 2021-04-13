package edu.matc.persistence;

import edu.matc.entity.Favorite;
import edu.matc.entity.Item;
import edu.matc.entity.Note;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A generic DAO  inspired by rodrigoucha.wordpress.com
 */
@Log4j2
public class GenericDao<T> {

    private Class<T> type;
    SessionFactory sessionFactory = SessionFactoryProvider.getSessionFactory();

    /**
     * Instantiates a new Generic dao.
     *
     * @param type the entity type, for example, User.
     */
    public GenericDao(Class<T> type) {
        this.type = type;
    }


    /**
     * Gets all entities
     *
     * @return the all entities
     */
    public List<T> getAll() {
        Session session = getSession();

        CriteriaBuilder builder = session.getCriteriaBuilder();

        CriteriaQuery<T> query = builder.createQuery(type);
        Root<T> root = query.from(type);
        List<T> list = session.createQuery(query).getResultList();
        session.close();
        return list;

    }

    /**
     * Gets a entity by id
     * @param id entity id to search by
     * @return a entity
     */
    public <T>T getById(int id) {
        Session session = getSession();
        T entity = (T)session.get(type, id);
        session.close();
        return entity;
    }

    /**
     * Deletes the entity.
     *
     * @param entity entity to be deleted
     */
    public void delete(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    /**
     * update entity
     * @param entity  entity to be inserted or updated
     */
    public void saveOrUpdate(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
    }

    /**
     * update entity
     * @param entity  Note to be inserted or updated
     */
    public int insert(T entity) {
        int id = 0;
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        id = (int)session.save(entity);
        transaction.commit();
        session.close();
        return id;
    }


    /** Get order by property (exact match)
     * sample usage: getByPropertyEqualInt("lastName", "Curry")
     *
     * @param propertyName entity property to search by
     * @param value value of the property to search for
     * @return list of orders meeting the criteria search
     */
    public List<T> getByPropertyEqualInt(String propertyName, int value) {
        Session session = getSession();

//        log.debug("Searching for order with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery( type );
        Root<T> root = query.from(type );
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<T> entities = session.createQuery( query ).getResultList();

        session.close();
        return entities;
    }

    /** Get order by property (exact match)
     * sample usage: getByPropertyEqualInt("lastName", "Curry")
     *
     * @param propertyName entity property to search by
     * @param value value of the property to search for
     * @return list of orders meeting the criteria search
     */
    public <T>T getByUniquePropertyEqualInt(String propertyName, int value) {
        Session session = getSession();

//        log.debug("Searching for order with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = (CriteriaQuery<T>) builder.createQuery( type );
        Root<T> root = (Root<T>) query.from(type );
        query.select(root).where(builder.equal(root.get(propertyName), value));
        T entity = session.createQuery( query ).uniqueResult();

        session.close();
        return entity;
    }

    /** Get order by property (exact match)
     * sample usage: getByPropertyEqualInt("lastName", "Curry")
     *
     * @param propertyName entity property to search by
     * @param value value of the property to search for
     * @return list of orders meeting the criteria search
     */
    //TODO add test for this method
    public <T>T getByUniquePropertyEqualString(String propertyName, String value) {
        Session session = getSession();

//        log.debug("Searching for order with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = (CriteriaQuery<T>) builder.createQuery( type );
        Root<T> root = (Root<T>) query.from(type );
        query.select(root).where(builder.equal(root.get(propertyName), value));
        T entity = session.createQuery( query ).uniqueResult();

        session.close();
        return entity;
    }

    /**
     * Gets by multiple ids.
     *
     * @param <T>            the type parameter
     * @param firstProperty  the first property
     * @param firstValue     the first value
     * @param secondProperty the second property
     * @param secondValue    the second value
     * @return the entity
     */
    public <T>T getUniqueEntityByMultipleProperties(String firstProperty, int firstValue,
            String secondProperty, int secondValue) {

        Session session = getSession();

//        log.debug("Searching for something with " + firstProperty + " = " + firstValue
//                + " and " + secondProperty + " = " + secondValue);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = (CriteriaQuery<T>) builder.createQuery( type );
        Root<T> root = (Root<T>) query.from(type );

        List<Predicate> restrictions = new ArrayList<>();
        restrictions.add(builder.equal(root.get(firstProperty), firstValue));
        restrictions.add(builder.equal(root.get(secondProperty), secondValue));
        query.where(restrictions.toArray(new Predicate[restrictions.size()]));

        T entity = session.createQuery( query ).uniqueResult();

        session.close();
        return entity;
    }

//    TODO add test for this method
    /**
     * Gets by multiple ids.
     *
     * @param <T>            the type parameter
     * @param firstProperty  the first property
     * @param firstValue     the first value
     * @param secondProperty the second property
     * @param secondValue    the second value
     * @return the entity
     */
    public <T>T getUniqueEntityByMultiplePropertyStrings(String firstProperty, String firstValue,
                                                    String secondProperty, String secondValue) {

        Session session = getSession();

//        log.debug("Searching for something with " + firstProperty + " = " + firstValue
//                + " and " + secondProperty + " = " + secondValue);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = (CriteriaQuery<T>) builder.createQuery( type );
        Root<T> root = (Root<T>) query.from(type );

        List<Predicate> restrictions = new ArrayList<>();
        restrictions.add(builder.equal(root.get(firstProperty), firstValue));
        restrictions.add(builder.equal(root.get(secondProperty), secondValue));
        query.where(restrictions.toArray(new Predicate[restrictions.size()]));

        T entity = session.createQuery( query ).uniqueResult();

        session.close();
        return entity;
    }

    /**
     * Gets by multiple ids.
     *
     * @param firstProperty  the first property
     * @param firstValue     the first value
     * @param secondProperty the second property
     * @param secondValue    the second value
     * @return the entity
     */
    public List<T> getListByMultipleProperties(String firstProperty, int firstValue,
                                                    String secondProperty, int secondValue) {

        Session session = getSession();

//        log.debug("Searching for something with " + firstProperty + " = " + firstValue
//                + " and " + secondProperty + " = " + secondValue);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = (CriteriaQuery<T>) builder.createQuery( type );
        Root<T> root = (Root<T>) query.from(type );

        List<Predicate> restrictions = new ArrayList<>();
        restrictions.add(builder.equal(root.get(firstProperty), firstValue));
        restrictions.add(builder.equal(root.get(secondProperty), secondValue));
        query.where(restrictions.toArray(new Predicate[restrictions.size()]));

        List<T> entities = session.createQuery( query ).getResultList();

        session.close();
        return entities;
    }

    /**
     * Gets items by searched for name
     *
     * @return all items with the searched for name
     */
    public List<T> getByPropertyEqualString(String propertyName, String value) {
        Session session = getSession();

//        log.debug("Searching for order with " + propertyName + " = " + value);

        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery( type );
        Root<T> root = query.from(type );
        query.select(root).where(builder.equal(root.get(propertyName), value));
        List<T> entities = session.createQuery( query ).getResultList();

        session.close();
        return entities;
    }

    /**
     * Returns an open session from the SessionFactory
     * @return session
     */
    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }
}