package by.chebotar.dao.impl;

import by.chebotar.dao.AbstractJdbcDao;
import by.chebotar.dao.GenericDao;
import by.chebotar.dao.exception.DaoException;
import by.chebotar.dao.exception.PersistException;
import by.chebotar.domain.Tattoo;
import by.chebotar.domain.User;
import by.chebotar.domain.UserFeedback;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Date;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class TattooDaoImplTest {

    private static AbstractJdbcDao abstractJdbcDao;
    private static GenericDao<Tattoo, Integer> tattooDao;
    private static GenericDao<User, Integer> userDao;
    private static GenericDao<UserFeedback, Integer> userFeedbackDao;
    private static Tattoo tattoo;
    private static User user;
    private static UserFeedback userFeedback;

    @Before
    public void init(){
        try {
            abstractJdbcDao = (AbstractJdbcDao) JdbcDaoFactory.getInstance().getTransactionalDao(Tattoo.class);
            tattooDao = JdbcDaoFactory.getInstance().getDao(Tattoo.class);
            tattoo = new Tattoo();
            tattoo.setId(1);
            tattoo.setIdUser(1);
            tattoo.setDateOfCreation(new Date(2019,2,24));
            tattoo.setDescription("description");
            tattoo.setIdUserFeedback(1);
            tattoo.setPhoto("FA12AB");
            tattoo.setPrice(120);
            userDao = JdbcDaoFactory.getInstance().getDao(User.class);
            userFeedbackDao = JdbcDaoFactory.getInstance().getDao(UserFeedback.class);
            user = new User();
            user.setId(2);
            user.setFirst_name("name");
            user.setLast_name("surname");
            user.setLogin("login");
            user.setPassword("password");
            user.setEmail("mail@mail.ru");
            userFeedback = new UserFeedback();
            userFeedback.setFeedback("feedback");
            userFeedback.setId(1);
            userFeedback.setIdUser(1);
        } catch (DaoException e) {
            throw new RuntimeException();
        }
    }

    @Test
    public void shouldInsertTattoo(){
        try {
            userDao.persist(user);
            userFeedbackDao.persist(userFeedback);
            Assert.assertEquals(tattoo.getIdUser(),tattooDao.persist(tattoo).getIdUser());
        } catch (PersistException e) {
            throw new RuntimeException();
        }

    }
}