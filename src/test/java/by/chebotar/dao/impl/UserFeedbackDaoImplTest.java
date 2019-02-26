package by.chebotar.dao.impl;

import by.chebotar.dao.AbstractJdbcDao;
import by.chebotar.dao.DaoFactoryType;
import by.chebotar.dao.FactoryProducer;
import by.chebotar.dao.GenericDao;
import by.chebotar.dao.exception.DaoException;
import by.chebotar.dao.exception.PersistException;
import by.chebotar.domain.UserFeedback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class UserFeedbackDaoImplTest {

    private static AbstractJdbcDao abstractJdbcDao;
    private static GenericDao<UserFeedback, Integer> userFeedbackDao;
    private static UserFeedback userFeedback;

    @Before
    public void init(){
        try {
            abstractJdbcDao = (AbstractJdbcDao) JdbcDaoFactory.getInstance().getTransactionalDao(UserFeedback.class);
            userFeedbackDao = JdbcDaoFactory.getInstance().getDao(UserFeedback.class);
            userFeedback = new UserFeedback();
            userFeedback.setId(1);
            userFeedback.setIdUser(1);
            userFeedback.setFeedback("feedback");
        } catch (DaoException e) {
            throw new RuntimeException();
        }
    }

    @Test
    public void shouldInsertFeedback(){
        try {
            Assert.assertEquals(userFeedback.getIdUser(),userFeedbackDao.persist(userFeedback).getIdUser());
        } catch (PersistException e) {
            throw new RuntimeException();
        }
    }
}