package by.chebotar.dao.impl;

import by.chebotar.dao.ConnectionPool;
import by.chebotar.dao.exception.ConnectionPoolException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

public class DataBaseInitialization {

    private static volatile boolean isCreate = false;

    public static void createDataBase(){
        if(isCreate) {
            return;
        }
        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
        try(Connection connection = connectionPool.retrieveConnection()){
            String script = Files.readAllLines(Paths.get("tattoo_salon.sql")).stream().collect(Collectors.joining());
            Statement statement = connection.createStatement();
            statement.execute(script);
            statement.close();
        } catch (SQLException | ConnectionPoolException | IOException e) {
            e.printStackTrace();
        }
    }
}
