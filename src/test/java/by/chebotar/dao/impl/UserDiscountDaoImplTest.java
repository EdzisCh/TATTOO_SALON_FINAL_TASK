package by.chebotar.dao.impl;

import by.chebotar.dao.AbstractJdbcDao;
import by.chebotar.dao.DaoFactoryType;
import by.chebotar.dao.FactoryProducer;
import by.chebotar.dao.GenericDao;
import by.chebotar.dao.exception.DaoException;
import by.chebotar.dao.exception.PersistException;
import by.chebotar.domain.UserDiscount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class UserDiscountDaoImplTest {

    private static AbstractJdbcDao abstractJdbcDao;
    private static GenericDao<UserDiscount, Integer> userDiscountDao;
    private static UserDiscount userDiscount;

    @Before
    public void init(){
        DataBaseInitialization.createDataBase();
        try {
            abstractJdbcDao = (AbstractJdbcDao) JdbcDaoFactory.getInstance().getTransactionalDao(UserDiscount.class);
            userDiscountDao = JdbcDaoFactory.getInstance().getDao(UserDiscount.class);
            userDiscount = new UserDiscount();
            userDiscount.setId(1);
            userDiscount.setIdDiscount(1);
            userDiscount.setIdTattooOrder(1);
            userDiscount.setIdUser(1);
        } catch (DaoException e) {
            throw new RuntimeException();
        }
    }

    @Test
    public void shouldInsertUserDiscount(){
        try {
            Assert.assertEquals(userDiscount.getId(), userDiscountDao.persist(userDiscount).getId());
        } catch (PersistException e) {
            throw new RuntimeException();
        }
    }
}