package by.chebotar.dao.impl;

import by.chebotar.dao.AbstractJdbcDao;
import by.chebotar.dao.exception.ConnectionPoolException;
import by.chebotar.dao.exception.DaoException;
import by.chebotar.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@RunWith(JUnit4.class)
public class UserDaoImplTest {

    private static User user;
    private static AbstractJdbcDao abstractJdbcDao;
    private static UserDaoImpl userDao;
    private static PreparedStatement preparedStatement;
    private static Connection connection;

    @Before
    public void init(){
        /*DataBaseInitialization.createDataBase();
        try {
            abstractJdbcDao = (AbstractJdbcDao) JdbcDaoFactory.getInstance().getDao(User.class);
            user = new User();
            user.setFirst_name("name");
            user.setLast_name("surname");
            user.setLogin("login");
            user.setPassword("password");
            user.setEmail("mail@mail.ru");
            connection = ConnectionPoolImpl.getInstance().retrieveConnection();
            preparedStatement = connection.prepareStatement("UPDATE user SET first_name=newName WHERE id=1");

        } catch (DaoException | ConnectionPoolException | SQLException e) {
            throw new RuntimeException();
        }*/
    }

    @Test
    public void shouldInsertUser(){

    }
}