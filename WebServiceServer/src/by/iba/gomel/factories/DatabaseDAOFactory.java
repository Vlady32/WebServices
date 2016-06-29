package by.iba.gomel.factories;

import javax.persistence.EntityManager;

import by.iba.gomel.implDAO.DatabaseDAOImplDB2;
import by.iba.gomel.interfaces.DatabaseDAO;

/**
 * This factory class has one method which returns instance of database implementation using in this
 * application.
 */
public class DatabaseDAOFactory {

    /**
     * 
     * @return instance of database implementation.
     */
    public static DatabaseDAO getInstance(final EntityManager em) {
        return new DatabaseDAOImplDB2(em);
    }
}
