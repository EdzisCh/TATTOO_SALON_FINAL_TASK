package by.chebotar.dao;

import by.chebotar.dao.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.chebotar.dao.exception.DaoException;
import by.chebotar.dao.exception.PersistException;

import java.sql.*;
import java.util.List;
import java.util.Optional;

/**
 * Abstract JDBC DAO
 * @param <T> - Identified entity
 * @param <PK> - Type primary key of entity
 */
public abstract class AbstractJdbcDao<T extends Identified<PK>, PK extends Number> implements GenericDao<T, PK> {

    private static final Logger LOGGER = LogManager.getLogger(AbstractJdbcDao.class);
    private static final String EXCEPTION_MESSAGE = "Cannot prepare statement";
    protected Connection connection;

    /*public AbstractJdbcDao() throws ConnectionPoolException {
        connection = ConnectionPoolFactory.getInstance().getConnectionPool().retrieveConnection();
    }*/

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
    public Optional<T> getByPK(PK key) throws DaoException {

        try(PreparedStatement statement = this.connection.prepareStatement(getSelectByPKQuery())) {
            statement.setInt(1, (Integer) key);
            try (ResultSet resultSet = statement.executeQuery()){
                List<T> list = parseResultSet(resultSet);
                return !list.isEmpty() ? Optional.of(list.get((Integer) key)) : Optional.empty();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(EXCEPTION_MESSAGE,e);
        }
    }

    @Override
    public List<T> getAll() throws DaoException {
        try(PreparedStatement statement  = this.connection.prepareStatement(getSelectQuery())) {
            try(ResultSet resultSet = statement.executeQuery()){
                return parseResultSet(resultSet);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(EXCEPTION_MESSAGE, e);
        }
    }

    @Override
    public Optional<T> persist(T object) throws PersistException {
        try(PreparedStatement statement = this.connection.prepareStatement(getCreateQuery(),Statement.RETURN_GENERATED_KEYS)) {
            prepareStatementForInsert(statement, object);
            statement.execute();
            try(ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    PK id = (PK) new Integer(generatedKeys.getInt(1));
                    object.setId(id);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new PersistException(EXCEPTION_MESSAGE, e);
        }
        return Optional.of(object);
    }

    @Override
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
    public void delete(T object) throws DaoException {
        try(PreparedStatement statement = this.connection.prepareStatement(getDeleteQuery())) {
            statement.setInt(1,(Integer) object.getId());
            statement.execute();
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(EXCEPTION_MESSAGE,e);
        }
    }

    @Override
    public Optional<T> create() throws PersistException {
        throw new UnsupportedOperationException();
    }
}
