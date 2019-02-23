package by.chebotar.service;

import by.chebotar.service.exception.ServiceException;
import by.chebotar.service.impl.UserServiceImpl;

/**
 * Service factory
 */
public class ServiceFactory {
    private static ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService() throws ServiceException {
        return new UserServiceImpl();
    }
}
