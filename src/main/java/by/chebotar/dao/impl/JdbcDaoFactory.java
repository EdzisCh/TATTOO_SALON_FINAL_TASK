package by.chebotar.dao.impl;

import by.chebotar.dao.DaoFactory;
import by.chebotar.dao.TransactionalDaoFactory;
import by.chebotar.dao.GenericDao;
import by.chebotar.dao.ConnectionPool;
import by.chebotar.dao.ConnectionPoolFactory;
import by.chebotar.dao.AbstractJdbcDao;
import by.chebotar.dao.exception.DaoException;
import by.chebotar.domain.*;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

/**
 * Jdbc DAO Factory
 */
public class JdbcDaoFactory implements DaoFactory, TransactionalDaoFactory<Connection> {

    private static JdbcDaoFactory instance;
    private static Lock instanceLock = new ReentrantLock();
    private Map<Class, Supplier<GenericDao>> creators = new HashMap<>();

    private class DaoInvocationHandler implements InvocationHandler {
        private GenericDao dao;

        DaoInvocationHandler(GenericDao dao) {
            this.dao = dao;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
            Connection connection = connectionPool.retrieveConnection();

            setConnectionWithReflection(dao, connection);

            Object result = method.invoke(dao, args);

            connectionPool.putBackConnection(connection);
            setConnectionWithReflection(dao, null);

            return result;
        }

    }

    private JdbcDaoFactory() {
        creators.put(User.class, UserDaoImpl::new);
        creators.put(Role.class, RoleDaoImpl::new);
        creators.put(Tattoo.class, TattooDaoImpl::new);
        creators.put(TattooOrder.class, TattooOrderImpl::new);
        creators.put(Discount.class, DiscountDaoImpl::new);
        creators.put(UserDiscount.class, UserDiscountDaoImpl::new);
        creators.put(UserFeedback.class, UserFeedbackDaoImpl::new);
    }

    public static JdbcDaoFactory getInstance() {
        JdbcDaoFactory localInstance = instance;
        instanceLock.lock();
        if (localInstance == null) {
            localInstance = instance;
            if (localInstance == null) {
                instance = localInstance = new JdbcDaoFactory();
            }
        }
        instanceLock.unlock();
        return instance;
    }

    @Override
    public GenericDao getDao(Class entityClass) throws DaoException {
        Supplier<GenericDao> daoCreator = creators.get(entityClass);
        if (daoCreator == null) {
            throw new DaoException("Entity Class cannot be find");
        }
        GenericDao dao = daoCreator.get();

        return (GenericDao) Proxy.newProxyInstance(dao.getClass().getClassLoader(),
                dao.getClass().getInterfaces(),
                new DaoInvocationHandler(dao));
    }

    @Override
    public GenericDao getTransactionalDao(Class entityClass, Connection connection) throws DaoException {
        Supplier<GenericDao> daoCreator = creators.get(entityClass);
        if (daoCreator == null) {
            throw new DaoException("Entity Class cannot be find");
        }
        GenericDao dao = daoCreator.get();

        setConnectionWithReflection(dao, connection);

        return dao;
    }

    private void setConnectionWithReflection(Object dao, Connection connection) throws DaoException {
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
