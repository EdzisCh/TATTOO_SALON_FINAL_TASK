package by.chebotar.dao.impl;

import by.chebotar.dao.AbstractJdbcDao;
import by.chebotar.dao.GenericDao;
import by.chebotar.dao.exception.ConnectionPoolException;
import by.chebotar.domain.Tattoo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TattooDaoImpl extends AbstractJdbcDao<Tattoo, Integer> implements GenericDao<Tattoo, Integer> {

    private static final String SELECT_ALL_QUERY = "SELECT * FROM tattoo";
    private static final String SELECT_USER_BY_PK_QUERY = "SELECT * FROM tattoo WHERE id = ?";
    private static final String INSERT_NEW_QUERY = "INSERT INTO tattoo (photo, description," +
            " price, creation_date, user_id, user_feedback_id) VALUES ( ?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE tattoo SET photo=?, description=?, price=?, " +
            "creation_date=?, user_id=?, user_feedback_id=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM tattoo WHERE id=?";

    @Override
    protected List<Tattoo> parseResultSet(ResultSet rs) throws SQLException {
        List<Tattoo> tattooList = new ArrayList<>();
        while (rs.next()){
            Tattoo tattoo = new Tattoo();
            tattoo.setPhoto(rs.getInt(1));
            tattoo.setDescription(rs.getString(2));
            tattoo.setPrice(rs.getFloat(3));
            tattoo.setDateOfCreation(rs.getDate(4));
            tattoo.setIdUser(rs.getInt(5));
            tattoo.setIdUserFeedback(rs.getInt(6));
            tattooList.add(tattoo);
        }
        return tattooList;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Tattoo object) throws SQLException {
       prepareStatement(statement,object);
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Tattoo object) throws SQLException {
        prepareStatement(statement,object);
        statement.setInt(7,object.getId());
    }

    @Override
    protected void prepareStatement(PreparedStatement statement, Tattoo object) throws SQLException {
        int counter = 1;
        statement.setInt(counter++,object.getPhoto());
        statement.setString(counter++,object.getDescription());
        statement.setFloat(counter++,object.getPrice());
        statement.setDate(counter++,object.getDateOfCreation());
        statement.setInt(counter++,object.getIdUser());
        statement.setInt(counter,object.getIdUserFeedback());
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
}
