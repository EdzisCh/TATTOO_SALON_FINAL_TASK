package by.chebotar.service;

import by.chebotar.domain.Role;
import by.chebotar.service.exception.ServiceException;

public interface RoleService {

    Role getRoleById(int id) throws ServiceException;

    void setRole(Role role) throws ServiceException;
}
