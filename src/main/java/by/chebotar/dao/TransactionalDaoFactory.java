package by.chebotar.dao;

import by.chebotar.dao.exception.DaoException;

import java.io.Serializable;

/**
 * Transactional DAO Factory
 * @param <T>
 */
public interface TransactionalDaoFactory<T> {
    /**
     * Get generic DAO of entity without connection
     * @param entityClass
     * @return
     * @throws DaoException should be clarify
     */
    <T extends Identified<PK>, PK extends Serializable> GenericDao<T, PK> getTransactionalDao(Class<T> entityClass) throws DaoException;
}
