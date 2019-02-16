package by.chebotar.dao.impl;

import by.chebotar.dao.AbstractJdbcDao;
import by.chebotar.dao.GenericDao;
import by.chebotar.dao.exception.PersistException;
import by.chebotar.domain.TattooOrder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TattooOrderImpl extends AbstractJdbcDao<TattooOrder, Integer> implements GenericDao<TattooOrder,Integer> {

    private static final String SELECT_ALL_QUERY = "SELECT * FROM tattoo_order";
    private static final String SELECT_USER_BY_PK_QUERY = "SELECT * FROM tattoo_order WHERE id = ?";
    private static final String INSERT_NEW_QUERY = "INSERT INTO tattoo_order (user_id, tattoo_id, price," +
            " date) VALUES ( ?, ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE tattoo_order SET user_id=?, tattoo_id=?, price=?, date=?";
    private static final String DELETE_QUERY = "DELETE FROM tattoo_order WHERE id=?";

    @Override
    protected List<TattooOrder> parseResultSet(ResultSet rs) throws SQLException {
        List<TattooOrder> tattooOrdersList = new ArrayList<>();
        while (rs.next()){
            TattooOrder tattooOrder = new TattooOrder();
            tattooOrder.setId(rs.getInt(1));
            tattooOrder.setIdUser(rs.getInt(2));
            tattooOrder.setIdTattoo(rs.getInt(3));
            tattooOrder.setPrice(rs.getFloat(4));
            tattooOrder.setDate(rs.getDate(5));
            tattooOrdersList.add(tattooOrder);
        }
        return tattooOrdersList;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, TattooOrder object) throws SQLException {
        statement.setInt(1,object.getIdUser());
        statement.setInt(2, object.getIdTattoo());
        statement.setFloat(3,object.getPrice());
        statement.setDate(4,object.getDate());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, TattooOrder object) throws SQLException {
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
    public String getSelectByPKQuery() {
        return SELECT_USER_BY_PK_QUERY;
    }

    @Override
    public Optional<TattooOrder> create() throws PersistException {
        return Optional.empty();
    }
}
