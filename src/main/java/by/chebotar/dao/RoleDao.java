package by.chebotar.dao;

import by.chebotar.dao.exception.DaoException;
import by.chebotar.domain.Role;

public interface RoleDao extends GenericDao<Role, Integer> {

    Role getRoleById(int id) throws DaoException;

    void setRole(Role role) throws DaoException;
}
