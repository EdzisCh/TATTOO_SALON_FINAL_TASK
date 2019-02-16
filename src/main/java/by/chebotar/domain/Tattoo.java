package by.chebotar.domain;

import by.chebotar.dao.Identified;
import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;

public class Tattoo implements Identified<Integer>, Serializable {

    private int id;
    private String description;
    /* Need to clarify*/
    private int photo;
    private float price;
    private Date dateOfCreation;
    private int idUser;
    private int idUserFeedback;

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdUserFeedback() {
        return idUserFeedback;
    }

    public void setIdUserFeedback(int idUserFeedback) {
        this.idUserFeedback = idUserFeedback;
    }

    public java.sql.Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tattoo tattoo = (Tattoo) o;
        return id == tattoo.id &&
                Float.compare(tattoo.price, price) == 0 &&
                idUser == tattoo.idUser &&
                idUserFeedback == tattoo.idUserFeedback &&
                Objects.equals(description, tattoo.description) &&
                Objects.equals(dateOfCreation, tattoo.dateOfCreation);
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, description, price, dateOfCreation, idUser, idUserFeedback);
    }
}
