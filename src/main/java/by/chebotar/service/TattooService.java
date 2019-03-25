package by.chebotar.service;

import by.chebotar.domain.Tattoo;
import by.chebotar.service.exception.ServiceException;

import java.util.List;

public interface TattooService {

    List<Tattoo> getAllTattoo() throws ServiceException;
}
