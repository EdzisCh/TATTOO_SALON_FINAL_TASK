package by.chebotar.dao.impl;

import by.chebotar.dao.DaoFactoryType;
import by.chebotar.dao.FactoryProducer;
import by.chebotar.dao.GenericDao;
import by.chebotar.dao.exception.DaoException;
import by.chebotar.domain.UserFeedback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class UserFeedbackDaoImplTest {

    private static final Logger LOGGER = LogManager.getLogger(UserFeedbackDaoImplTest.class);
    private static GenericDao<UserFeedback, Integer> userFeedback;

    @Before
    public void init(){

    }

    @Test
    public void shouldInsertFeedback(){

    }
}