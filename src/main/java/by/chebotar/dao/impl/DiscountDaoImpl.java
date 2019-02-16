package by.chebotar.dao.impl;

import by.chebotar.dao.AbstractJdbcDao;
import by.chebotar.dao.GenericDao;
import by.chebotar.dao.exception.PersistException;
import by.chebotar.domain.Discount;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DiscountDaoImpl extends AbstractJdbcDao<Discount, Integer> implements GenericDao<Discount,Integer> {

private static final String SELECT_ALL_QUERY = "SELECT * FROM discount";
private static final String SELECT_USER_BY_PK_QUERY = "SELECT * FROM discount WHERE id = ?";
private static final String INSERT_NEW_QUERY = "INSERT INTO discount (description, percents) VALUES ( ?, ?)";
private static final String UPDATE_QUERY = "UPDATE discount SET description=?, percents=?";
private static final String DELETE_QUERY = "DELETE FROM description WHERE id=?";

    @Override
    protected List<Discount> parseResultSet(ResultSet rs) throws SQLException {
        List<Discount> discountList = new ArrayList<>();
        while (rs.next()){
            Discount discount = new Discount();
            discount.setId(rs.getInt(1));
            discount.setDescription(rs.getString(2));
            discount.setPercents(rs.getInt(3));
            discountList.add(discount);
        }
        return discountList;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, Discount object) throws SQLException {
        statement.setString(1,object.getDescription());
        statement.setInt(2,object.getPercents());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, Discount object) throws SQLException {
        prepareStatementForUpdate(statement,object);
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
    public Optional<Discount> create() throws PersistException {
        return Optional.empty();
    }
}
