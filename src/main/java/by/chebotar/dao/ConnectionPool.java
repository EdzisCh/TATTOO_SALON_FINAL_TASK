package by.chebotar.dao;

import by.chebotar.dao.exception.ConnectionPoolException;

import java.sql.Connection;

public interface ConnectionPool {

    Connection retrieveConnection() throws ConnectionPoolException;
    void putBackConnection(Connection connection);
    void destroyPool() throws ConnectionPoolException;
}
