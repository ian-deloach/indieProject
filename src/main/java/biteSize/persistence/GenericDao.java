package biteSize.persistence;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * A generic DAO
 */
public class GenericDao<T> {
    private Class<T> type;
    private final Logger logger = LogManager.getLogger(this.getClass());

    /**
     * Instantiates a new generic dao
     *
     * @param type the entities type
     */
    public GenericDao(Class<T> type) {
        this.type = type;
    }

    /**
     * Insert an entity
     *
     * @param entity the entity to be inserted
     *
     */
    public void insert(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Get entity by id
     *
     * @param id the entities id to lookup
     * @return the entity
     */
    public <T>T getById(int id) {
        Session session = getSession();
        T entity = (T)session.get(type, id);
        session.close();
        return entity;
    }

    /**
     * Returns all entities of selected type
     *
     * @return the list of entities
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
     * Update an entity
     *
     * @param entity the entity to be updated
     */
    public void update(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.merge(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Deletes an entity
     *
     * @param entity the entity to be deleted
     */
    public void delete(T entity) {
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    /**
     * Get entity by property (exact match)
     *
     * @param propertyName the property to search by
     * @param value the value of the property you are searching for
     */
//    public List<T> getPropertyEqual(String propertyName, String value) {
//        // TODO
//    }

    /**
     * Get entity by property like
     *
     * @param propertyName the property to search by
     * @param value the value of the property you are searching for
     */
//    public List<T> getPropertyLike(String propertyName, String value) {
//        // TODO
//    }

    /**
     * Returns an open session from SessionFactory
     *
     * @return the open session
     */
    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }


}
