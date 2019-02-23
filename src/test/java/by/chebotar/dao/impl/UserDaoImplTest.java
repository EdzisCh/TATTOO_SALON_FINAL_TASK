package by.chebotar.dao.impl;

import by.chebotar.dao.*;
import by.chebotar.dao.exception.ConnectionPoolException;
import by.chebotar.dao.exception.DaoException;
import by.chebotar.dao.exception.PersistException;
import by.chebotar.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.*;
import java.util.Optional;


@RunWith(JUnit4.class)
public class UserDaoImplTest {

    private static User user;
    private static AbstractJdbcDao abstractJdbcDao;
    private static GenericDao userDao;
    private static PreparedStatement preparedStatement;
    private static Connection connection;

    @Before
    public void init(){
        DataBaseInitialization.createDataBase();
        try {
            abstractJdbcDao = (AbstractJdbcDao) JdbcDaoFactory.getInstance().getTransactionalDao(User.class);
            userDao = JdbcDaoFactory.getInstance().getDao(User.class);
            user = new User();
            user.setId(1);
            user.setFirst_name("name");
            user.setLast_name("surname");
            user.setLogin("login");
            user.setPassword("password");
            user.setEmail("mail@mail.ru");
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    @Test
    public void getByPKTest() throws Exception {
        Assert.assertEquals(user.getId(), userDao.persist(user).getId());
    }

    @Test
    public void shouldCreate(){
    }
}