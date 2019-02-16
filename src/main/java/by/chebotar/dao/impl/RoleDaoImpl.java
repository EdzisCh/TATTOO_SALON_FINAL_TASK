package by.chebotar.dao.impl;

import by.chebotar.dao.AbstractJdbcDao;
import by.chebotar.dao.GenericDao;
import by.chebotar.dao.exception.PersistException;
import by.chebotar.domain.Role;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RoleDaoImpl extends AbstractJdbcDao<Role, Integer> implements GenericDao<Role, Integer> {

private static final String SELECT_ALL_QUERY = "SELECT * FROM role";
private static final String SELECT_USER_BY_PK_QUERY = "SELECT * FROM role WHERE id = ?";
private static final String INSERT_NEW_QUERY = "INSERT INTO role (role, user_id) VALUES ( ?, ?)";
private static final String UPDATE_QUERY = "UPDATE role SET role=?, user_id=?";
private static final String DELETE_QUERY = "DELETE FROM role WHERE id=?";

    @Override
    protected List<Role> parseResultSet(ResultSet rs) throws SQLException {
        List<Role> userRoles = new ArrayList<>();
        while (rs.next()) {
            String stringStatus = rs.getString(2);
            Role orderStatus = Role.valueOf(stringStatus);
            userRoles.add(orderStatus);
        }
        return userRoles;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Role object) throws SQLException {
        statement.setInt(1,object.getId());
        statement.setInt(2,object.getIdUser());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Role object) throws SQLException {
        throw new UnsupportedOperationException();
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
        return SELECT_USER_BY_PK_QUERY;
    }

    @Override
    public Optional<Role> create() throws PersistException {
        return Optional.empty();
    }
}
