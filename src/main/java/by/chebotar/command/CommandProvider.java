package by.chebotar.command;

import java.util.HashMap;
import java.util.Map;

public class CommandProvider {

    private static CommandProvider instance = new CommandProvider();
    private Map<CommandType, Command> commandMap = new HashMap<>();

    public static CommandProvider getInstance() {
        return instance;
    }

    private CommandProvider() {
        commandMap.put(CommandType.REGISTER_USER, new CommandRegisterUser());
        commandMap.put(CommandType.VIEW_USER_DETAILS, new CommandViewUserDetails());
        commandMap.put(CommandType.DELETE_USER, new CommandDeleteUser());
        commandMap.put(CommandType.VIEW_USER_LIST, new CommandViewUserList());
        commandMap.put(CommandType.LOG_IN, new CommandLogIn());
        commandMap.put(CommandType.LOG_OUT, new CommandLogOut());
        commandMap.put(CommandType.SHOW_EMPTY_PAGE, new CommandGetEmptyPage());
        commandMap.put(CommandType.GET_LOG_IN_PAGE, new CommandGetLoginPage());
        commandMap.put(CommandType.GET_REGISTRATION_PAGE, new CommandGetRegistrationPage());
    }


    /**
     * Return command by name
     *
     * @param commandType name
     * @return command implementation
     */
    public Command takeCommand(CommandType commandType) {
        return commandMap.get(commandType);
    }
}
