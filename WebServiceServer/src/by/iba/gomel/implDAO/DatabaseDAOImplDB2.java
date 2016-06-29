package by.iba.gomel.implDAO;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceException;
import javax.persistence.TypedQuery;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import by.iba.gomel.Constants;
import by.iba.gomel.interfaces.DatabaseDAO;
import by.iba.gomel.managers.RequestManager;
import by.iba.gomel.model.Record;
import by.iba.gomel.model.User;

/**
 * This class realizes all methods associated with working in database.
 */
public class DatabaseDAOImplDB2 implements DatabaseDAO, Serializable {

    private static final long   serialVersionUID = 1L;
    private static final Logger LOGGER           = LoggerFactory
                                                         .getLogger(DatabaseDAOImplDB2.class);
    private EntityManager       em;

    public DatabaseDAOImplDB2(final EntityManager em) {
        this.em = em;
    }

    @Override
    public User checkUser(final String userName, final String password) {
        final TypedQuery<User> query = em.createQuery(
                RequestManager.getProperty(Constants.PROPERTY_CHECK_USER), User.class);
        query.setParameter(Constants.FIRST_PARAMETER, userName);
        query.setParameter(Constants.SECOND_PARAMETER, password);
        final User user;
        try {
            user = query.getSingleResult();
        } catch (final NoResultException e) {
            DatabaseDAOImplDB2.LOGGER.error(Constants.NO_RESULT_EXCEPTION, e);
            return null;
        }
        return user;
    }

    @Override
    public boolean registerUser(final User user) {
        try {
            em.persist(user);
        } catch (final PersistenceException e) {
            DatabaseDAOImplDB2.LOGGER.error(Constants.ENTITY_EXISTS_EXCEPTION, e);
            return false;
        }
        return true;
    }

    @Override
    public List<Record> getRecords(final int start) {
        List<Record> listRecords;
        final TypedQuery<Record> query = em
                .createQuery(RequestManager.getProperty(Constants.PROPERTY_GET_ALL_RECORDS),
                        Record.class).setFirstResult(start)
                .setMaxResults((int) Constants.DEFAULT_QUALITY_RECORDS_AND_USERS_ON_PAGE);
        try {
            listRecords = query.getResultList();
        } catch (final NoResultException e) {
            DatabaseDAOImplDB2.LOGGER.error(Constants.NO_RESULT_EXCEPTION, e);
            return null;
        }
        return listRecords;
    }

    @Override
    public long getQualityRecords() {
        final TypedQuery<Long> query = em.createQuery(
                RequestManager.getProperty(Constants.PROPERTY_GET_QUALITY_RECORDS), Long.class);
        Long qualityRecords = Constants.NUMBER_ZERO;
        try {
            qualityRecords = query.getSingleResult();
        } catch (final NoResultException e) {
            DatabaseDAOImplDB2.LOGGER.error(Constants.NO_RESULT_EXCEPTION, e);
            return qualityRecords;
        }
        return qualityRecords;
    }

    @Override
    public boolean addRecord(final Record record) {
        try {
            em.persist(record);
        } catch (final PersistenceException e) {
            DatabaseDAOImplDB2.LOGGER.error(Constants.ENTITY_EXISTS_EXCEPTION, e);
            return false;
        }
        return true;
    }

    @Override
    public Record checkExistRecord(final Record record) {
        final TypedQuery<Record> query = em.createQuery(
                RequestManager.getProperty(Constants.PROPERTY_CHECK_EXIST_RECORD), Record.class);
        query.setParameter(Constants.FIRST_PARAMETER, record.getPhoneNumber());
        Record gotRecord;
        try {
            gotRecord = query.getSingleResult();
        } catch (final NoResultException e) {
            DatabaseDAOImplDB2.LOGGER.error(Constants.NO_RESULT_EXCEPTION, e);
            return null;
        } catch (final NonUniqueResultException e) {
            DatabaseDAOImplDB2.LOGGER.error(Constants.NON_UNIQUE_RESULT_EXCEPTION, e);
            return new Record();
        }
        return gotRecord;
    }

    @Override
    public boolean changeRecord(final Record record) {
        if (em.find(Record.class, record.getItem()) == null) {
            return false;
        }
        em.merge(record);
        return true;
    }

    @Override
    public boolean deleteRecord(final Record record) {
        try {
            em.remove(em.merge(record));
        } catch (final PersistenceException e) {
            DatabaseDAOImplDB2.LOGGER.error(Constants.ENTITY_EXISTS_EXCEPTION, e);
            return false;
        }
        return true;
    }

    @Override
    public List<User> getUsers() {
        final List<User> listUsers;
        final TypedQuery<User> query = em.createQuery(
                RequestManager.getProperty(Constants.PROPERTY_GET_USERS), User.class);

        try {
            listUsers = query.getResultList();
        } catch (final NoResultException e) {
            DatabaseDAOImplDB2.LOGGER.error(Constants.NO_RESULT_EXCEPTION, e);
            return null;
        }
        return listUsers;
    }

    @Override
    public boolean deleteUser(final User user) {
        try {
            em.remove(em.merge(user));
        } catch (final PersistenceException e) {
            DatabaseDAOImplDB2.LOGGER.error(Constants.ENTITY_EXISTS_EXCEPTION, e);
            return false;
        }
        return true;
    }

    @Override
    public List<Record> searchRecord(final String searchPhrase) {
        List<Record> listRecords;
        final TypedQuery<Record> query;
        query = em.createQuery(RequestManager.getProperty(Constants.PROPERTY_SEARCH_ALL_COLUMNS),
                Record.class);
        query.setParameter(Constants.FIRST_PARAMETER, Constants.PERCENT + searchPhrase
                + Constants.PERCENT);
        try {
            listRecords = query.getResultList();
        } catch (final NoResultException e) {
            DatabaseDAOImplDB2.LOGGER.error(Constants.NO_RESULT_EXCEPTION, e);
            return null;
        }
        return listRecords;
    }
}
