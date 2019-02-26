package by.chebotar.service;

import by.chebotar.domain.User;
import by.chebotar.service.exception.ServiceException;

/**
 * Example of user service
 */
public interface UserService {

    /**
     * Sign up user
     * @param user - User
     * @return - saved user
     * @throws ServiceException should be clarify
     */
    User signUp(User user) throws ServiceException;

    User register(User user) throws ServiceException;

    User getById(int id) throws ServiceException;

    User getByLogin(String login) throws ServiceException;

    void deleteUser(User user) throws ServiceException;
}
