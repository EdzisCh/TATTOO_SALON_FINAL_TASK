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
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;
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

    private final Deque<Connection> pool;
    private final List<Connection> connections ;
    private final Semaphore semaphore;
    private final Lock lock = new ReentrantLock();
    private static final Lock instanceLock = new ReentrantLock();
    private Properties properties;
    private String propertiesFile = "database.properties";

    private ConnectionPoolImpl() {
        getPropertiesFromFile();
        this.driverClass = this.properties.getProperty("driverClass");
        this.jdbcUrl = this.properties.getProperty("jdbcUrl");
        this.user = this.properties.getProperty("user");
        this.password = this.properties.getProperty("password");
        int poolCapacity = Integer.parseInt(this.properties.getProperty("poolCapacity"));
        this.semaphore = new Semaphore(poolCapacity);
        this.pool = new ConcurrentLinkedDeque<>();
        connections = new ArrayList<>();
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
            if(connections.isEmpty()){
                return createConnection();
            }
            return pool.pop();
        } catch (InterruptedException | SQLException e) {
            LOGGER.error(e);
            throw new ConnectionPoolException("Exception in ConnectionPoolImpl", e);
        }
    }

    @Override
    public void putBackConnection(Connection connection) {
        pool.push(connection);
        semaphore.release();
    }

    private Connection createConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(jdbcUrl,user,password);
        connections.add(connection);
        InvocationHandler handler = (proxy, method, args ) -> {
            if (method.getName().equals("close")){
                putBackConnection((Connection) proxy);
                return null;
            }
            return method.invoke(connection,args);
        };
        return (Connection) Proxy.newProxyInstance(connection.getClass().getClassLoader(),
                connection.getClass().getInterfaces(), handler);
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

    private void getPropertiesFromFile(){
        try {
            this.properties = new PropertyConfiguration().configure(this.propertiesFile);
        } catch (DaoException e) {
            LOGGER.error(e);
        }
    }


    @Override
    public void destroyPool() throws ConnectionPoolException {
        try {
            for (Connection currentConnection : connections) {
                currentConnection.close();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new ConnectionPoolException("Exception in ConnectionPoolImpl",e);
        }
    }
}
