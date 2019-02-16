package by.chebotar.dao.impl;

import by.chebotar.dao.ConnectionPool;
import by.chebotar.dao.exception.ConnectionPoolException;
import by.chebotar.dao.exception.DaoException;
import by.chebotar.dao.util.PropertyConfiguration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Properties;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPoolImpl implements ConnectionPool {

    private static final Logger LOGGER = LogManager.getLogger(ConnectionPoolImpl.class);

    private static ConnectionPoolImpl instance;
    private final String driverClass;
    private final String jdbcUrl;
    private final String user;
    private final String password;
    private final int poolCapacity;

    private final Queue<Connection> pool;
    private final Semaphore semaphore;
    private final Lock lock = new ReentrantLock();
    private static final Lock instanceLock = new ReentrantLock();
    private Properties properties;
    private int createdConnectionCount = 0;

    private ConnectionPoolImpl() {
        getPropertiesFile();
        this.driverClass = this.properties.getProperty("driverClass");
        this.jdbcUrl = this.properties.getProperty("jdbcUrl");
        this.user = this.properties.getProperty("user");
        this.password = this.properties.getProperty("password");
        this.poolCapacity = Integer.parseInt(this.properties.getProperty("poolCapacity"));
        this.pool = new ArrayDeque<>(poolCapacity);
        this.semaphore = new Semaphore(poolCapacity);
        initDriver();
    }

    public static ConnectionPoolImpl getInstance(){
        ConnectionPoolImpl localInstance = instance;
        instanceLock.lock();
        if(localInstance == null){
            localInstance = instance;
            if(localInstance == null){
                instance = localInstance = new ConnectionPoolImpl();
            }
        }
        instanceLock.unlock();
        return localInstance;
    }

    @Override
    public Connection retrieveConnection() throws ConnectionPoolException {
        try{
            semaphore.acquire();
            lock.lock();
            if(createdConnectionCount < poolCapacity){
                createdConnectionCount++;
                InvocationHandler handler = this.getHandler();
                Class[] classes = {Connection.class};
                return (Connection) Proxy.newProxyInstance(null, classes, handler);
            }
            return pool.poll();
        } catch (InterruptedException | SQLException e) {
            LOGGER.error(e);
            throw new IllegalStateException();
        } finally {
            lock.unlock();
        }
    }

    private InvocationHandler getHandler() throws SQLException {
        final ConnectionPoolImpl connectionPool = this;
        Connection connection = DriverManager.getConnection(jdbcUrl,user,password);
        return (proxy, method, args) -> {
            String methodName = method.getName();
            if(methodName.equals("close")){
                connectionPool.putBackConnection((Connection) proxy);
                return null;
            } else {
                return method.invoke(connection,args);
            }
        };
    }

    private void initDriver(){
        try{
            lock.lock();
            Class.forName(this.driverClass);
        } catch (ClassNotFoundException e){
            LOGGER.error(e);
            throw new IllegalStateException("Driver cannot be found", e);
        } finally {
            lock.unlock();
        }
    }

    private void getPropertiesFile(){
        try {
            this.properties = PropertyConfiguration.configure("database.properties");
        } catch (DaoException e) {
            LOGGER.error(e);
        }
    }

    @Override
    public void putBackConnection(Connection connection) {
        try {
            lock.lock();
            pool.add(connection);
        } finally {
            semaphore.release();
            lock.unlock();
        }

    }

    @Override
    public void destroyPool() throws ConnectionPoolException {
        throw new UnsupportedOperationException();
    }
}
