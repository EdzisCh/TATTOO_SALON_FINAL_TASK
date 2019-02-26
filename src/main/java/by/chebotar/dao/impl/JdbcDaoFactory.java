package by.chebotar.dao.impl;

import by.chebotar.dao.*;
import by.chebotar.dao.exception.DaoException;
import by.chebotar.domain.*;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.util.Arrays;
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
    private static Lock lock = new ReentrantLock();
    private Map<Class, Supplier<GenericDao>> creators = new HashMap<>();

    private class DaoInvocationHandler implements InvocationHandler {
        private final GenericDao dao;

        DaoInvocationHandler(GenericDao dao) {
            this.dao = dao;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            Object result;

            if (Arrays.stream(dao.getClass().getMethods())
                    .filter(m -> m.isAnnotationPresent(AutoConnection.class))
                    .map(Method::getName)
                    .anyMatch(m -> m.equals(method.getName()))) {

                ConnectionPool connectionPool = ConnectionPoolFactory.getInstance().getConnectionPool();
                Connection connection = connectionPool.retrieveConnection();

                TransactionManager.setConnectionWithReflection(dao, connection);

                result = method.invoke(dao, args);

                connectionPool.putBackConnection(connection);
                TransactionManager.setConnectionWithReflection(dao, null);

            } else {
                result = method.invoke(dao, args);
            }

            return result;
        }

    }

    private JdbcDaoFactory() {
        creators.put(User.class, UserDaoImpl::new);
        creators.put(Discount.class, DiscountDaoImpl::new);
        creators.put(Role.class, RoleDaoImpl::new);
        creators.put(Tattoo.class,TattooDaoImpl::new);
        creators.put(TattooOrder.class, TattooOrderImpl::new);
        creators.put(UserDiscount.class, UserDiscountDaoImpl::new);
        creators.put(UserFeedback.class, UserFeedbackDaoImpl::new);
    }

    public static JdbcDaoFactory getInstance() {
        lock.lock();
        try {
            if (instance == null) {
                instance = new JdbcDaoFactory();
            }

        } finally {
            lock.unlock();
        }

        return instance;
    }

    @Override
    public <T extends Identified<PK>, PK extends Serializable> GenericDao<T, PK> getDao(Class<T> entityClass) throws DaoException {
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
    public <T extends Identified<PK>, PK extends Serializable> GenericDao<T, PK> getTransactionalDao(Class<T> entityClass) throws DaoException {
        Supplier<GenericDao> daoCreator = creators.get(entityClass);
        if (daoCreator == null) {
            throw new DaoException("Entity Class cannot be find");
        }

        return daoCreator.get();
    }
}