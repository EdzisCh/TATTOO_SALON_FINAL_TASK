package by.chebotar.domain;

import by.chebotar.dao.Identified;

public enum Role implements Identified<Integer> {
    Client(1),
    Master(2),
    Admin(3);

    private int id;
    private int idUser;

    Role(int id){
        this.id = id;
    }

    @Override
    public Integer getId() {
        return null;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
