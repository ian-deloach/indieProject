package biteSize.persistence;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;

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
     * Get entity by id
     *
     * @param id the entities id to lookup
     * @return the entity
     */
    public <T>T getById(int id) {
        Session session = getSession();
        T entity = (T)session.get(type, id);
        logger.info("Testing if logging is working");
        session.close();
        return entity;
    }

    /**
     * Returns an open session from SessionFactory
     *
     * @return the open session
     */
    private Session getSession() {
        return SessionFactoryProvider.getSessionFactory().openSession();
    }


}
