package by.chebotar.service;

import by.chebotar.service.impl.RoleServiceImpl;
import by.chebotar.service.impl.UserServiceImpl;

/**
 * Service factory
 */
public class ServiceFactory {

    private final UserService userService;
    private final RoleService roleService;
    private static ServiceFactory instance = new ServiceFactory();

    private ServiceFactory(){
        userService = new UserServiceImpl();
        roleService = new RoleServiceImpl();
    }

    public static ServiceFactory getInstance() {
        return instance;
    }

    public UserService getUserService(){
        return userService;
    }

    public RoleService getRoleService() {
        return roleService;
    }
}
