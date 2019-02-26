package by.chebotar.dao.impl;

import by.chebotar.dao.AbstractJdbcDao;
import by.chebotar.dao.DaoFactoryType;
import by.chebotar.dao.FactoryProducer;
import by.chebotar.dao.GenericDao;
import by.chebotar.dao.exception.DaoException;
import by.chebotar.dao.exception.PersistException;
import by.chebotar.domain.Discount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Connection;
import java.sql.PreparedStatement;

@RunWith(JUnit4.class)
public class DiscountDaoImplTest {

    private static AbstractJdbcDao abstractJdbcDao;
    private static GenericDao discountDao;
    private static Discount discount;
    private static PreparedStatement preparedStatement;
    private static Connection connection;

    @Before
    public void init(){
        DataBaseInitialization.createDataBase();
        try {
            abstractJdbcDao = (AbstractJdbcDao) JdbcDaoFactory.getInstance().getTransactionalDao(Discount.class);
            discountDao = JdbcDaoFactory.getInstance().getDao(Discount.class);
            discount = new Discount();
            discount.setId(1);
            discount.setDescription("description");
            discount.setPercents(28);
        } catch (DaoException e) {
            throw new RuntimeException();
        }

    }

    @Test
    public void shouldInsertDiscount(){
        try {
            Assert.assertEquals(discount.getId(),discountDao.persist(discount).getId());
        } catch (PersistException e) {
            throw new RuntimeException();
        }
    }

}