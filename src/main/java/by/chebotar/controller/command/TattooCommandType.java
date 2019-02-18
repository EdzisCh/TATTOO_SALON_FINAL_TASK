package by.chebotar.controller.command;

import java.util.Optional;
import java.util.stream.Stream;

public enum TattooCommandType {

    VIEW_TATTOO_LIST, VIEW_TATTOO_DETAILS, CREATE_TATTOO, DELETE_TATTOO;

    public static Optional<TattooCommandType> of(String name) {
        return Stream.of(TattooCommandType.values()).filter(type -> type.name().equalsIgnoreCase(name)).findFirst();
    }
}
