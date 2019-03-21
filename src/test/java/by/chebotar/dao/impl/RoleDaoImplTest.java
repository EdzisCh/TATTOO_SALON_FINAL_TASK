package by.chebotar.dao.impl;

import by.chebotar.dao.AbstractJdbcDao;
import by.chebotar.dao.GenericDao;
import by.chebotar.dao.exception.DaoException;
import by.chebotar.dao.exception.PersistException;
import by.chebotar.domain.Role;
import by.chebotar.domain.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Connection;
import java.sql.PreparedStatement;

@RunWith(JUnit4.class)
public class RoleDaoImplTest {

    private static AbstractJdbcDao abstractJdbcDao;
    private static GenericDao<Role, Integer> roleDao;
    private static GenericDao userDao;
    private static Role role;
    private static User user;
    private static Connection connection;
    private static PreparedStatement preparedStatement;

    @Before
    public void init(){
       DataBaseInitialization.createDataBase();
        try {
            abstractJdbcDao = (AbstractJdbcDao) JdbcDaoFactory.getInstance().getTransactionalDao(Role.class);
            roleDao = JdbcDaoFactory.getInstance().getDao(Role.class);
            userDao = JdbcDaoFactory.getInstance().getDao(User.class);
            role = Role.CLIENT;
            user = new User();
            user.setId(1);
            user.setFirst_name("name");
            user.setLast_name("surname");
            user.setLogin("login");
            user.setPassword("password");
            user.setEmail("mail@mail.ru");
            role.setId(1);
            role.setIdUser(1);
        } catch (DaoException e) {
            throw new RuntimeException();
        }
    }

    @Test
    public void shouldInsertRole(){
        try {
            userDao.persist(user);
            Assert.assertEquals(role.getIdUser(),roleDao.persist(role).getIdUser());
        } catch (PersistException e) {
            throw new RuntimeException();
        }
    }
}