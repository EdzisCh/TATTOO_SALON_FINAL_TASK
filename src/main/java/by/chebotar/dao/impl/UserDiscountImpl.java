package by.chebotar.dao.impl;

import by.chebotar.dao.AbstractJdbcDao;
import by.chebotar.dao.GenericDao;
import by.chebotar.dao.exception.PersistException;
import by.chebotar.domain.UserDiscount;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDiscountImpl extends AbstractJdbcDao<UserDiscount, Integer> implements GenericDao<UserDiscount,Integer> {

    private static final String SELECT_ALL_QUERY = "SELECT * FROM user_discount";
    private static final String SELECT_USER_BY_PK_QUERY = "SELECT * FROM user_discount WHERE id = ?";
    private static final String INSERT_NEW_QUERY = "INSERT INTO user_discount (discount_id, user_id, " +
            "tattoo_order_id) VALUES ( ?, ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE user_discount SET discount_id=?, user_id=?, tattoo_order_id";
    private static final String DELETE_QUERY = "DELETE FROM user_description WHERE id=?";

    @Override
    protected List<UserDiscount> parseResultSet(ResultSet rs) throws SQLException {
        List<UserDiscount> userDiscountList = new ArrayList<>();
        while (rs.next()){
            UserDiscount userDiscount = new UserDiscount();
            userDiscount.setId(rs.getInt(1));
            userDiscount.setIdDiscount(rs.getInt(2));
            userDiscount.setIdUser(rs.getInt(3));
            userDiscount.setIdTattooOrder(rs.getInt(4));
            userDiscountList.add(userDiscount);
        }
        return userDiscountList;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, UserDiscount object) throws SQLException {
        statement.setInt(1,object.getIdDiscount());
        statement.setInt(2,object.getIdUser());
        statement.setInt(3,object.getIdTattooOrder());
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, UserDiscount object) throws SQLException {
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
    public Optional<UserDiscount> create() throws PersistException {
        return Optional.empty();
    }
}
