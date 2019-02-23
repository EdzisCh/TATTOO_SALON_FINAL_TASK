package by.chebotar.controller.command;

import java.util.Optional;
import java.util.stream.Stream;

public enum CommandType {

    VIEW_USER_LIST, VIEW_USER_DETAILS, REGISTER_USER, DELETE_USER;

    public static Optional<CommandType> of(String name) {
        return Stream.of(CommandType.values()).filter(type -> type.name().equalsIgnoreCase(name)).findFirst();
    }
}
