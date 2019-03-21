package by.chebotar.dao;

import by.chebotar.dao.exception.DaoException;
import by.chebotar.domain.User;

import java.util.List;

public interface UserDao extends GenericDao<User, Integer> {

    void register(User user) throws DaoException;

    User logIn(User user) throws DaoException;
}
