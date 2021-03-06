package by.chebotar.command;

import java.util.Optional;
import java.util.stream.Stream;

public enum CommandType {

    VIEW_USER_LIST,
    GET_CATALOG,
    REGISTER_USER,
    DELETE_USER,
    SHOW_EMPTY_PAGE,
    LOG_IN,
    LOG_OUT,
    GET_LOG_IN_PAGE,
    GET_REGISTRATION_PAGE,
    GET_ORDER_PAGE,
    MAKE_ORDER;

    public static Optional<CommandType> of(String name) {
        return Stream.of(CommandType.values()).filter(type -> type.name().equalsIgnoreCase(name)).findFirst();
    }
}
