package by.chebotar.dao.impl;

import by.chebotar.dao.DaoFactoryType;
import by.chebotar.dao.FactoryProducer;
import by.chebotar.dao.GenericDao;
import by.chebotar.dao.exception.DaoException;
import by.chebotar.domain.TattooOrder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class TattooOrderImplTest {

    private static final Logger LOGGER = LogManager.getLogger(TattooOrderImpl.class);
    private static GenericDao<TattooOrder, Integer> tattooOrderDao;

    @Ignore
    @Before
    public void init(){
        /*try {
           // tattooOrderDao = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(TattooOrderImpl.class);
        } catch (DaoException e) {
            LOGGER.error(e);
        }*/
    }

    @Test
    public void shouldInsertOrder(){

    }
}