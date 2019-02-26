package by.chebotar.service.impl;

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

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

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
        try {
            return userDao.getByPK(user.getId());
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User register(User user) throws ServiceException {
        user.setPassword(encryptPassSHA256(user));
        try {
            return userDao.persist(user);
        } catch (PersistException e) {
            throw new ServiceException();
        }
    }

    @Override
    public User getById(int id) throws ServiceException {
        try {
            return userDao.getByPK(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User getByLogin(String login) throws ServiceException {
        List<User> users;
        try {
             users = userDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException();
        }
        return getUserFromDBByLogin(login, users);
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
