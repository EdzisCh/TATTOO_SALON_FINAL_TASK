package by.chebotar.domain;

import by.chebotar.dao.Identified;
import java.io.Serializable;
import java.util.Objects;

public class UserDiscount implements Identified<Integer>, Serializable {

    private int id;
    private int idDiscount;
    private int idUser;
    private int idTattooOrder;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getIdDiscount() {
        return idDiscount;
    }

    public void setIdDiscount(int idDiscount) {
        this.idDiscount = idDiscount;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdTattooOrder() {
        return idTattooOrder;
    }

    public void setIdTattooOrder(int idTattooOrder) {
        this.idTattooOrder = idTattooOrder;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDiscount that = (UserDiscount) o;
        return id == that.id &&
                idDiscount == that.idDiscount &&
                idUser == that.idUser &&
                idTattooOrder == that.idTattooOrder;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idDiscount, idUser, idTattooOrder);
    }

    @Override
    public String toString() {
        return "UserDiscount{" +
                "id=" + id +
                ", idDiscount=" + idDiscount +
                ", idUser=" + idUser +
                ", idTattooOrder=" + idTattooOrder +
                '}';
    }
}
