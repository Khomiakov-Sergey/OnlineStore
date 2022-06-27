package by.it.academy.repositories.connection;

import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * This class is responsible for creating entity manager factory and gets from it entity manager or session.
 */
public class DataSource {
    private static DataSource instance;
    private static EntityManagerFactory entityManagerFactory;

    /**
     * This method creates entity manager factory.
     */
    private DataSource() {
        entityManagerFactory = Persistence.createEntityManagerFactory("IShop");
    }

    /**
     * This method checks data source is created or not.
     */
    public static DataSource getInstance() {
        if (instance == null) {
            instance = new DataSource();
        }
        return instance;
    }

    /**
     * This method gets entity manager from entity manager factory.
     */
    public EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }

    /**
     * This method gets session manager from entity manager.
     */
    public Session getSession() {
        return getEntityManager().unwrap(Session.class);
    }
}