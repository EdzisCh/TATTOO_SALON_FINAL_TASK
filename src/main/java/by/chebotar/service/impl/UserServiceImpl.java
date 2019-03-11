package by.chebotar.service.impl;

import by.chebotar.dao.DaoFactory;
import by.chebotar.dao.DaoFactoryType;
import by.chebotar.dao.FactoryProducer;
import by.chebotar.dao.UserDao;
import by.chebotar.dao.exception.DaoException;
import by.chebotar.dao.exception.PersistException;
import by.chebotar.dao.impl.JdbcDaoFactory;
import by.chebotar.domain.User;
import by.chebotar.service.UserService;
import by.chebotar.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Example of user service implementation
 */
public class UserServiceImpl implements UserService {

    //TODO VALIDATION
    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);


    @Override
    public User signUp(User user) throws ServiceException {
        DaoFactory daoFactory = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC);
        try {
            UserDao userDao = (UserDao) daoFactory.getDao(User.class);
            return userDao.logIn(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User register(User user) throws ServiceException {
        try {
            UserDao userDao = (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
            user.setPassword(encryptPassSHA256(user));
            return userDao.persist(user);
        } catch (PersistException | DaoException e) {
            throw new ServiceException();
        }
    }

    @Override
    public User getById(int id) throws ServiceException {
        try {
            UserDao userDao = (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
            return userDao.getByPK(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getByLogin(String login) throws ServiceException {
        try {
            UserDao userDao = (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
            List<User> users = userDao.getAll();
            return getUserFromDBByLogin(login, users);
        } catch (DaoException e) {
            throw new ServiceException();
        }

    }

    private User getUserFromDBByLogin(String login, List<User> users){
        for (User user : users) {
            if (user.getLogin().equals(login)){
                return user;
            }
        }
        return null;
    }

    @Override
    public void deleteUser(User user) throws ServiceException {
        try {
            UserDao userDao = (UserDao) JdbcDaoFactory.getInstance().getDao(User.class);
            userDao.delete(user);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private String encryptPassSHA256(User user) throws ServiceException {
        String password = user.getPassword();

        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
            byte[] messageDigest = md.digest(password.getBytes());

            BigInteger no = new BigInteger(1, messageDigest);
            String hashtext = no.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error(e);
            throw new ServiceException("Cannot encrypt pass", e);
        }
    }
}
