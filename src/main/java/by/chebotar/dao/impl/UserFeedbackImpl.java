package by.chebotar.dao.impl;

import by.chebotar.dao.AbstractJdbcDao;
import by.chebotar.dao.GenericDao;
import by.chebotar.domain.UserFeedback;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserFeedbackImpl extends AbstractJdbcDao<UserFeedback, Integer> implements GenericDao<UserFeedback, Integer> {

    private static final String SELECT_ALL_QUERY = "SELECT * FROM user_feedback";
    private static final String SELECT_USER_BY_PK_QUERY = "SELECT * FROM user_feedback WHERE id = ?";
    private static final String INSERT_NEW_QUERY = "INSERT INTO user_feedback (feedback, user_id) VALUES ( ?, ?)";
    private static final String UPDATE_QUERY = "UPDATE user_feedback SET feedback=?, user_id=? WHERE id=?";
    private static final String DELETE_QUERY = "DELETE FROM user_feedback WHERE id=?";

    @Override
    protected List<UserFeedback> parseResultSet(ResultSet rs) throws SQLException {
        List<UserFeedback> userFeedbackList = new ArrayList<>();
        while (rs.next()){
            UserFeedback userFeedback = new UserFeedback();
            userFeedback.setId(rs.getInt(1));
            userFeedback.setFeedback(rs.getString(2));
            userFeedback.setIdUser(rs.getInt(3));
            userFeedbackList.add(userFeedback);
        }
        return userFeedbackList;
    }

    @Override
    protected void prepareStatementForInsert(PreparedStatement statement, UserFeedback object) throws SQLException {
        prepareStatement(statement, object);
    }

    @Override
    protected void prepareStatementForUpdate(PreparedStatement statement, UserFeedback object) throws SQLException {
        prepareStatementForInsert(statement,object);
    }

    @Override
    protected void prepareStatement(PreparedStatement statement, UserFeedback object) throws SQLException {
        int counter = 1;
        statement.setString(counter++,object.getFeedback());
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
        return SELECT_USER_BY_PK_QUERY;
    }
}
