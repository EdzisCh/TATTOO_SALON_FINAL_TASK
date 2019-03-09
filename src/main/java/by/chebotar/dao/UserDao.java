package by.chebotar.dao;

import by.chebotar.dao.exception.DaoException;
import by.chebotar.domain.User;

public interface UserDao extends GenericDao<User, Integer> {

    void register(User user);

    User logIn(User user) throws DaoException;
}
