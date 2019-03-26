package by.chebotar.service.impl;

import by.chebotar.dao.TattooOrderDao;
import by.chebotar.dao.exception.DaoException;
import by.chebotar.dao.exception.PersistException;
import by.chebotar.dao.impl.JdbcDaoFactory;
import by.chebotar.domain.TattooOrder;
import by.chebotar.service.OrderService;
import by.chebotar.service.exception.ServiceException;

public class OrderServiceImpl implements OrderService {
    @Override
    public void createOrder(TattooOrder tattooOrder) throws ServiceException {
        try {
            TattooOrderDao tattooOrderDao = (TattooOrderDao) JdbcDaoFactory.getInstance().getDao(TattooOrder.class);
            tattooOrderDao.persist(tattooOrder);
        } catch (DaoException | PersistException e) {
            throw new ServiceException(e);
        }
    }
}
