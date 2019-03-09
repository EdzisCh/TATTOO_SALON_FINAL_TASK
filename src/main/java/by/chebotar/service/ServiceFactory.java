package by.chebotar.service;

import by.chebotar.service.impl.UserServiceImpl;

/**
 * Service factory
 */
public class ServiceFactory {

    private final UserService userService;
    private static ServiceFactory instance = new ServiceFactory();

    private ServiceFactory(){
        userService = new UserServiceImpl();
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService(){
        return userService;
    }
}
