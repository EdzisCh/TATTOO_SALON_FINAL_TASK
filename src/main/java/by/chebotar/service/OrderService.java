package by.chebotar.service;

import by.chebotar.domain.TattooOrder;
import by.chebotar.service.exception.ServiceException;

public interface OrderService {

    void createOrder(TattooOrder tattooOrder) throws ServiceException;
}
