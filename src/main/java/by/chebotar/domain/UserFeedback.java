package by.chebotar.domain;

import by.chebotar.dao.Identified;
import java.io.Serializable;
import java.util.Objects;

public class UserFeedback implements Identified<Integer>, Serializable {

    private int id;
    private int idUser;
    private String feedback;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserFeedback that = (UserFeedback) o;
        return id == that.id &&
                idUser == that.idUser &&
                Objects.equals(feedback, that.feedback);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUser, feedback);
    }

    @Override
    public String toString() {
        return "UserFeedback{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
