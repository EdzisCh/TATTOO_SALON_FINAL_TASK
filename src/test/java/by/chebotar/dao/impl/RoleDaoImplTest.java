package by.chebotar.dao.impl;

import by.chebotar.dao.DaoFactoryType;
import by.chebotar.dao.FactoryProducer;
import by.chebotar.dao.GenericDao;
import by.chebotar.dao.exception.DaoException;
import by.chebotar.domain.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.junit.Assert.*;

@RunWith(JUnit4.class)
public class RoleDaoImplTest {

    private static final Logger LOGGER = LogManager.getLogger(RoleDaoImpl.class);
    private static GenericDao<Role, Integer> roleDao;

    @Ignore
    @Before
    public void init(){
       /* try {
            roleDao = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(RoleDaoImpl.class);
        } catch (DaoException e) {
            LOGGER.error(e);
        }*/
    }

    @Test
    public void shouldInsertRole(){

    }
}