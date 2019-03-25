package by.chebotar.service.impl;

import by.chebotar.dao.TattooDao;
import by.chebotar.dao.exception.DaoException;
import by.chebotar.dao.impl.JdbcDaoFactory;
import by.chebotar.domain.Tattoo;
import by.chebotar.service.TattooService;
import by.chebotar.service.exception.ServiceException;

import java.util.List;

public class TattooServiceImpl implements TattooService {
    @Override
    public List<Tattoo> getAllTattoo() throws ServiceException {
        try {
            TattooDao tattooDao = (TattooDao) JdbcDaoFactory.getInstance().getDao(Tattoo.class);
            return tattooDao.getAll();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
