package by.chebotar.dao.impl;

import by.chebotar.dao.AbstractJdbcDao;
import by.chebotar.dao.GenericDao;
import by.chebotar.dao.exception.PersistException;
import by.chebotar.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Example User DAO implementation
 */
public class UserDaoImpl extends AbstractJdbcDao<User, Integer> implements GenericDao<User, Integer> {

    private static final String SELECT_ALL_QUERY = "SELECT * FROM user";
    private static final String SELECT_USER_BY_PK_QUERY = "SELECT * FROM user WHERE id = ?";
    private static final String INSERT_NEW_QUERY = "INSERT INTO user (login, first_name, last_name," +
            " password, email) VALUES ( ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE user SET login=?, first_name=?, last_name=?, " +
            "password=?, email=?";
    private static final String DELETE_QUERY = "DELETE FROM user WHERE id=?";

    @Override
    protected List<User> parseResultSet(ResultSet rs) throws SQLException {
       List<User> userList = new ArrayList<>();
       while (rs.next()){
           User user = new User();
           user.setId(rs.getInt(1));
           user.setLogin(rs.getString(2));
           user.setFirst_name(rs.getString(3));
           user.setLast_name(rs.getString(4));
           user.setPassword(rs.getString(5));
           user.setEmail(rs.getString(6));
       }
       return userList;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, User object) throws SQLException {
        statement.setString(1,object.getLogin());
        statement.setString(2,object.getFirst_name());
        statement.setString(3,object.getLast_name());
        statement.setString(4,object.getPassword());
        statement.setString(5,object.getEmail());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws SQLException {
        prepareStatementForInsert(statement,object);
    }

    @Override
    public String getSelectQuery() {
        return SELECT_ALL_QUERY;
    }

    @Override
    public String getCreateQuery() {
        return INSERT_NEW_QUERY;
    }

    @Override
    public String getUpdateQuery() {
        return UPDATE_QUERY;
    }

    @Override
    public String getDeleteQuery() {
        return DELETE_QUERY;
    }

    @Override
    public String getSelectByPKQuery(){
        return SELECT_USER_BY_PK_QUERY;
    }

    @Override
    public Optional<User> create() throws PersistException {
        throw new UnsupportedOperationException();
    }
}
