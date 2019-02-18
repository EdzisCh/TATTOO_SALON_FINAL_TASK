package by.chebotar.dao.impl;

import by.chebotar.dao.DaoFactoryType;
import by.chebotar.dao.FactoryProducer;
import by.chebotar.dao.GenericDao;
import by.chebotar.dao.exception.DaoException;
import by.chebotar.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class UserDaoImplTest {

    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);
    private static GenericDao<User, Integer> userDao;

    @Before
    public void init(){
        try {
            userDao = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(UserDaoImpl.class);
        } catch (DaoException e) {
            LOGGER.error(e);
        }
    }

    @Test
    public void shouldInsertUser(){

    }
}