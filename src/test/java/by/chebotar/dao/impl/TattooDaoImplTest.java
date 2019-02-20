package by.chebotar.dao.impl;

import by.chebotar.dao.DaoFactoryType;
import by.chebotar.dao.FactoryProducer;
import by.chebotar.dao.GenericDao;
import by.chebotar.dao.exception.DaoException;
import by.chebotar.domain.Tattoo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class TattooDaoImplTest {

    private static final Logger LOGGER = LogManager.getLogger(TattooOrderImpl.class);
    private static GenericDao<Tattoo, Integer> tattooDao;

    @Ignore
    @Before
    public void init(){
       /* try {
            tattooDao = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(TattooDaoImpl.class);
        } catch (DaoException e) {
            LOGGER.error(e);
        }*/
    }

    @Test
    public void shouldInsertTattoo(){

    }
}