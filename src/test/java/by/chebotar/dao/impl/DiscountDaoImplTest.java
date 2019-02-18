package by.chebotar.dao.impl;

import by.chebotar.dao.DaoFactoryType;
import by.chebotar.dao.FactoryProducer;
import by.chebotar.dao.GenericDao;
import by.chebotar.dao.exception.DaoException;
import by.chebotar.domain.Discount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class DiscountDaoImplTest {

    private static final Logger LOGGER = LogManager.getLogger(DiscountDaoImplTest.class);
    private static GenericDao<Discount, Integer> discountDao;

    @Before
    public void init(){
        try {
            discountDao = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(DiscountDaoImpl.class);
        } catch (DaoException e) {
            LOGGER.error(e);
        }
    }

    @Test
    public void shouldInsertDiscount(){

    }

}