package by.chebotar.dao.util;

import by.chebotar.dao.exception.DaoException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyConfiguration {

    public  Properties configure(String filepath) throws DaoException {
        Properties properties;
        try (InputStream inputStream = getClass().getClassLoader().getResourceAsStream(filepath)) {
            properties = new Properties();
            properties.load(inputStream);
            return properties;
        }catch (IOException e) {
            throw new DaoException("IOException in PropertyConfiguration",e);
        }
    }

}
