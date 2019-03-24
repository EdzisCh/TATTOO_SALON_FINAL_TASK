package by.chebotar.dao.impl;

import by.chebotar.dao.AbstractJdbcDao;
import by.chebotar.dao.AutoConnection;
import by.chebotar.dao.UserDao;
import by.chebotar.dao.exception.DaoException;
import by.chebotar.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Example User DAO implementation
 */
public class UserDaoImpl extends AbstractJdbcDao<User, Integer> implements UserDao {

    private static final Logger LOGGER = LogManager.getLogger(UserDaoImpl.class);
    private static final String SELECT_ALL_QUERY = "SELECT * FROM user";
    private static final String SELECT_USER_BY_PK_QUERY = "SELECT * FROM user WHERE id=?";
    private static final String SELECT_USER_BY_EMAIL_QUERY = "SELECT * FROM user WHERE email=?";
    private static final String INSERT_NEW_QUERY = "INSERT INTO user (login, first_name, last_name," +
            " password, email) VALUES ( ?, ?, ?, ?, ?)";
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
           user.setFirstName(rs.getString(3));
           user.setLastName(rs.getString(4));
           user.setPassword(rs.getString(5));
           user.setEmail(rs.getString(6));
           userList.add(user);
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
        statement.setString(counter++,object.getFirstName());
        statement.setString(counter++,object.getLastName());
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

    public String getSelectUserByEmailQuery() {
        return SELECT_USER_BY_EMAIL_QUERY;
    }

    @Override
    @AutoConnection
    public void register(User user) throws DaoException {
        try(PreparedStatement preparedStatement = connection.prepareStatement(getCreateQuery())){
            preparedStatement.setString(1,user.getLogin());
            preparedStatement.setString(2,user.getFirstName());
            preparedStatement.setString(3,user.getLastName());
            preparedStatement.setString(4,user.getPassword());
            preparedStatement.setString(5,user.getEmail());
            parseResultSet(preparedStatement.executeQuery());
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
    }

    //TODO More beautiful empty list handling
    @Override
    @AutoConnection
    public User logIn(User user) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getSelectUserByEmailQuery())){
            preparedStatement.setString(1, user.getEmail());
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> listOfUsers = parseResultSet(resultSet);
            if (listOfUsers.isEmpty()){
                User incorrectUser = new User();
                incorrectUser.setId(0);
                return incorrectUser;
            }
            User userFromDB = listOfUsers.get(0);
            return userFromDB;
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
    }
}
