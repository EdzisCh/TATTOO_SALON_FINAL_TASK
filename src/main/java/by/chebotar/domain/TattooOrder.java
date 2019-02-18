package by.chebotar.domain;

import by.chebotar.dao.Identified;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class TattooOrder implements Identified<Integer>, Serializable {

    private int id;
    private int idUser;
    private int idTattoo;
    private float price;
    private Date date;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdTattoo() {
        return idTattoo;
    }

    public void setIdTattoo(int idTattoo) {
        this.idTattoo = idTattoo;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TattooOrder tattooOrder = (TattooOrder) o;
        return id == tattooOrder.id &&
                idUser == tattooOrder.idUser &&
                idTattoo == tattooOrder.idTattoo &&
                price == tattooOrder.price &&
                Objects.equals(date, tattooOrder.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idUser, idTattoo, price, date);
    }

    @Override
    public String toString() {
        return "TattooOrder{" +
                "id=" + id +
                ", idUser=" + idUser +
                ", idTattoo=" + idTattoo +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
