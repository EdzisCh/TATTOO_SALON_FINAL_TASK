package by.chebotar.domain;

import by.chebotar.dao.Identified;
import java.io.Serializable;
import java.util.Objects;

public class Discount implements Identified<Integer>, Serializable {

    private int id;
    private String description;
    private int percents;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPercents() {
        return percents;
    }

    public void setPercents(int percents) {
        this.percents = percents;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Discount discount = (Discount) o;
        return id == discount.id &&
                percents == discount.percents &&
                Objects.equals(description, discount.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, percents);
    }

    @Override
    public String toString() {
        return "Discount{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", percents=" + percents +
                '}';
    }
}
