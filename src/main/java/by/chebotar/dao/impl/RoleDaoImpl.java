package by.chebotar.dao.impl;

import by.chebotar.dao.AbstractJdbcDao;
import by.chebotar.dao.AutoConnection;
import by.chebotar.dao.RoleDao;
import by.chebotar.dao.exception.DaoException;
import by.chebotar.domain.Role;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoleDaoImpl extends AbstractJdbcDao<Role, Integer> implements RoleDao {

    private static final Logger LOGGER = LogManager.getLogger(RoleDaoImpl.class);
    private static final String SELECT_ALL_QUERY = "SELECT * FROM role";
    private static final String SELECT_ROLE_BY_PK_QUERY = "SELECT * FROM role WHERE id = ?";
    private static final String INSERT_NEW_QUERY = "INSERT INTO role (role, user_id) VALUES ( ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE role SET role=?, user_id=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM role WHERE id=?";

    @Override
    protected List<Role> parseResultSet(ResultSet rs) throws SQLException {
        List<Role> userRoles = new ArrayList<>();
        while (rs.next()) {
            String stringStatus = rs.getString(2);
            Role orderStatus = Role.valueOf(stringStatus.toUpperCase());
            userRoles.add(orderStatus);
        }
        if (userRoles.isEmpty()){
            userRoles.add(Role.INCORRECT);
        }
        return userRoles;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Role object) throws SQLException {
        prepareStatement(statement,object);
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Role object) throws SQLException {
        throw new UnsupportedOperationException();
    }

    @Override
    protected void prepareStatement(PreparedStatement statement, Role object) throws SQLException {
        int counter = 1;
        statement.setInt(counter++,object.getId());
        statement.setInt(counter,object.getIdUser());
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
    public String getSelectByPKQuery() {
        return SELECT_ROLE_BY_PK_QUERY;
    }

    @Override
    @AutoConnection
    public Role getRoleById(int id) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getSelectByPKQuery())){
            preparedStatement.setInt(1,id);
            return parseResultSet(preparedStatement.executeQuery()).get(0);
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
    }

    @Override
    @AutoConnection
    public void setRole(Role role) throws DaoException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(getCreateQuery())){
            preparedStatement.setString(1, role.name());
            preparedStatement.setInt(2,role.getIdUser());
        } catch (SQLException e) {
            LOGGER.error(e);
            throw new DaoException(e);
        }
    }
}
