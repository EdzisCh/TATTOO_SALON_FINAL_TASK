package by.chebotar.dao.impl;

import by.chebotar.dao.AbstractJdbcDao;
import by.chebotar.dao.ConnectionPool;
import by.chebotar.dao.ConnectionPoolFactory;
import by.chebotar.dao.GenericDao;
import by.chebotar.dao.exception.ConnectionPoolException;
import by.chebotar.dao.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Implementation of transaction with DAO
 */
public final class TransactionManager {

    private static final Logger LOGGER = LogManager.getLogger(TransactionManager.class);
    private Connection connection;

    public void begin(GenericDao dao, GenericDao ... daos) throws DaoException {

        ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();

        try{
            connection = connectionPool.retrieveConnection();
            connection.setAutoCommit(false);

            Field connectionField = AbstractJdbcDao.class.getDeclaredField("connection");
            if (!connectionField.isAccessible()) {
                connectionField.setAccessible(true);
            }
            connectionField.set(dao,connection);

            for (GenericDao genericDao : daos) {
                connectionField = AbstractJdbcDao.class.getDeclaredField("connection");
                if (!connectionField.isAccessible()) {
                    connectionField.setAccessible(true);
                }
                connectionField.set(genericDao,connection);
            }
        } catch (ConnectionPoolException | SQLException e){
                LOGGER.error(e);
                throw new DaoException("Exception in TransactionManager",e);
        } catch (NoSuchFieldException | IllegalAccessException e) {
                LOGGER.error(e);
                throw new DaoException("");
        }
    }

    public void end() throws SQLException{
        connection.setAutoCommit(true);
        connection.close();
    }

    public void commit() throws SQLException{
        connection.commit();
    }

    public void rollback() throws SQLException{
        connection.rollback();
    }

    static void setConnectionWithReflection(Object dao, Connection connection) throws DaoException {
        if (!(dao instanceof AbstractJdbcDao)) {
            throw new DaoException("DAO implementation does not extend AbstractJdbcDao.");
        }

        try {

            Field connectionField = AbstractJdbcDao.class.getDeclaredField("connection");
            if (!connectionField.isAccessible()) {
                connectionField.setAccessible(true);
            }

            connectionField.set(dao, connection);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new DaoException("Failed to set connection for transactional DAO. ", e);
        }
    }


}
