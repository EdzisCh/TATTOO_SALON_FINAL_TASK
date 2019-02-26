package by.chebotar.dao.impl;

import by.chebotar.dao.AbstractJdbcDao;
import by.chebotar.dao.DaoFactoryType;
import by.chebotar.dao.FactoryProducer;
import by.chebotar.dao.GenericDao;
import by.chebotar.dao.exception.DaoException;
import by.chebotar.dao.exception.PersistException;
import by.chebotar.domain.TattooOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Date;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class TattooOrderImplTest {

    private static AbstractJdbcDao abstractJdbcDao;
    private static GenericDao<TattooOrder, Integer> tattooOrderDao;
    private static TattooOrder tattooOrder;

    @Before
    public void init(){
        DataBaseInitialization.createDataBase();
        try {
            abstractJdbcDao = (AbstractJdbcDao) JdbcDaoFactory.getInstance().getTransactionalDao(TattooOrder.class);
            tattooOrderDao = JdbcDaoFactory.getInstance().getDao(TattooOrder.class);
            tattooOrder = new TattooOrder();
            tattooOrder.setId(1);
            tattooOrder.setIdUser(1);
            tattooOrder.setIdTattoo(1);
            tattooOrder.setDate(new Date(209,4,25));
            tattooOrder.setPrice(210);
        } catch (DaoException e) {
            throw new RuntimeException();
        }
    }

    @Test
    public void shouldInsertOrder(){
        try {
            Assert.assertEquals(tattooOrder.getId(),tattooOrderDao.persist(tattooOrder).getId());
        } catch (PersistException e) {
            throw new RuntimeException();
        }
    }
}