package by.chebotar.dao.impl;

import by.chebotar.dao.DaoFactoryType;
import by.chebotar.dao.FactoryProducer;
import by.chebotar.dao.GenericDao;
import by.chebotar.dao.exception.DaoException;
import by.chebotar.domain.UserDiscount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class UserDiscountDaoImplTest {

    private static final Logger LOGGER = LogManager.getLogger(UserDiscountDaoImplTest.class);
    private static GenericDao<UserDiscount, Integer> userDiscount;

    @Ignore
    @Before
    public void init(){
        /*try {
            userDiscount = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(UserDiscountDaoImpl.class);
        } catch (DaoException e) {
            LOGGER.error(e);
        }*/
    }

    @Test
    public void shouldInsertUserDiscount(){

    }
}