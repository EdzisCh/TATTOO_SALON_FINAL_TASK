package by.chebotar.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.chebotar.dao.exception.DaoException;
import by.chebotar.dao.exception.PersistException;

import java.sql.*;
import java.util.List;

/**
 * Abstract JDBC DAO
 * @param <T> - Identified entity
 * @param <PK> - Type primary key of entity
 */
public abstract class AbstractJdbcDao<T extends Identified<PK>, PK extends Number> implements GenericDao<T, PK> {

    private static final Logger LOGGER = LogManager.getLogger(AbstractJdbcDao.class);
    private static final String EXCEPTION_MESSAGE = "Cannot prepare statement";
    protected Connection connection;

    protected abstract List<T> parseResultSet(ResultSet rs) throws SQLException;

    protected abstract void prepareStatementForInsert(PreparedStatement statement, T object) throws SQLException;

    protected abstract void prepareStatementForUpdate(PreparedStatement statement, T object) throws SQLException;

    protected abstract void prepareStatement(PreparedStatement statement, T object) throws SQLException;

    public abstract String getSelectQuery();

    public abstract String getCreateQuery();

    public abstract String getUpdateQuery();

    public abstract String getDeleteQuery();

    public abstract String getSelectByPKQuery();

    @Override
    @AutoConnection
    public T getByPK(PK key) throws DaoException {

        try (PreparedStatement statement = connection.prepareStatement(getSelectByPKQuery())){
            statement.setInt(1,(Integer) key);
            return parseResultSet(statement.executeQuery()).get(0);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    @Override
    @AutoConnection
    public List<T> getAll() throws DaoException {
        try(PreparedStatement statement  = this.connection.prepareStatement(getSelectQuery())) {
            return parseResultSet(statement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    @AutoConnection
    public T persist(T object) throws PersistException {
        try (PreparedStatement statement =
                     connection.prepareStatement(getCreateQuery(), Statement.RETURN_GENERATED_KEYS)) {
            prepareStatementForInsert(statement, object);
            if (statement.executeUpdate() < 1) {
                throw new PersistException("Cannot insert");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    object.setId(generatedKeys.getInt(1));
                    return object;
                } else {
                    throw new PersistException("No Id obtained.");
                }
            }
        } catch (SQLException e) {
            throw new PersistException(EXCEPTION_MESSAGE,e);
        }
    }

    @Override
    @AutoConnection
    public void update(T object) throws DaoException {
        try(PreparedStatement statement = this.connection.prepareStatement(getUpdateQuery())) {
            prepareStatementForUpdate(statement, object);
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    @AutoConnection
    public void delete(T object) throws DaoException {
        try(PreparedStatement statement = this.connection.prepareStatement(getDeleteQuery())) {
            statement.setInt(1,(Integer) object.getId());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(EXCEPTION_MESSAGE,e);
        }
    }

}
