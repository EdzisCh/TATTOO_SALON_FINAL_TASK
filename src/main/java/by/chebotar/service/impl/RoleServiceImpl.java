package by.chebotar.service.impl;

import by.chebotar.dao.RoleDao;
import by.chebotar.dao.exception.DaoException;
import by.chebotar.dao.impl.JdbcDaoFactory;
import by.chebotar.domain.Role;
import by.chebotar.service.RoleService;
import by.chebotar.service.exception.ServiceException;

public class RoleServiceImpl implements RoleService {

    @Override
    public Role getRoleById(int id) throws ServiceException {
        try {
            RoleDao roleDao = (RoleDao) JdbcDaoFactory.getInstance().getDao(Role.class);
            return roleDao.getRoleById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void setRole(Role role) throws ServiceException{
        try {
            RoleDao roleDao = (RoleDao) JdbcDaoFactory.getInstance().getDao(Role.class);
            roleDao.setRole(role);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
