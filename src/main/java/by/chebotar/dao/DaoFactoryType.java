package by.chebotar.dao;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * DAO Factory Type
 * For example JDBC
 */
public enum  DaoFactoryType {
    JDBC;

    public Optional<DaoFactoryType> getFromString(String type){
        return Stream.of(DaoFactoryType.values())
                .filter( e -> e.name().equalsIgnoreCase(type))
                .findFirst();
    }
}
