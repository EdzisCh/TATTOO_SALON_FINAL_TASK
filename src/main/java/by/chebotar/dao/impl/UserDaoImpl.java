package by.chebotar.dao.impl;

import by.chebotar.dao.AbstractJdbcDao;
import by.chebotar.dao.GenericDao;
import by.chebotar.domain.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Example User DAO implementation
 */
public class UserDaoImpl extends AbstractJdbcDao<User, Integer> implements GenericDao<User, Integer> {

    private static final String SELECT_ALL_QUERY = "SELECT * FROM user";
    private static final String SELECT_USER_BY_PK_QUERY = "SELECT * FROM user WHERE id = ?";
    private static final String INSERT_NEW_QUERY = "INSERT INTO user (login, first_name, last_name," +
            " password, email) VALUES ( ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE user SET login=?, first_name=?, last_name=?, " +
            "password=?, email=? WHERE id=?";
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
        prepareStatement(statement, object);
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, User object) throws SQLException {
        prepareStatement(statement,object);
        statement.setInt(5,object.getId());
    }

    @Override
    protected void prepareStatement(PreparedStatement statement, User object) throws SQLException {
        int counter = 1;
        statement.setString(counter++,object.getLogin());
        statement.setString(counter++,object.getFirst_name());
        statement.setString(counter++,object.getLast_name());
        statement.setString(counter++,object.getPassword());
        statement.setString(counter,object.getEmail());
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
}
