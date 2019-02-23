package by.chebotar.service.impl;

import by.chebotar.dao.DaoFactory;
import by.chebotar.dao.DaoFactoryType;
import by.chebotar.dao.FactoryProducer;
import by.chebotar.dao.GenericDao;
import by.chebotar.dao.exception.DaoException;
import by.chebotar.dao.exception.PersistException;
import by.chebotar.domain.User;
import by.chebotar.service.UserService;
import by.chebotar.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServlet;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Example of user service implementation
 */
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserServiceImpl.class);
    private GenericDao<User,Integer> userDao;

    public UserServiceImpl() throws ServiceException {
        try {
            userDao = FactoryProducer.getDaoFactory(DaoFactoryType.JDBC).getDao(User.class);
        } catch (DaoException e) {
            throw new ServiceException("Failed to get userDao", e);
        }
    }

    @Override
    public User signUp(User user) throws ServiceException {

        throw new UnsupportedOperationException();
    }

    @Override
    public User register(User user) {
        return null;
    }

    @Override
    public User getById(int id) {
        return null;
    }

    @Override
    public void deleteUser(User user) {

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
