package by.chebotar.domain;

import by.chebotar.dao.Identified;

public enum Role implements Identified<Integer> {
    CLIENT(1),
    MASTER(2),
    ADMIN(3),
    INCORRECT(4);

    private int id;
    private int idUser;

    Role(int id){
        this.id = id;
    }

    @Override
    public Integer getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", idUser=" + idUser +
                '}';
    }
}
