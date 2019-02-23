package by.chebotar.dao;

import java.io.Serializable;

public interface Identified<PK extends Serializable> {

    PK getId();
    void setId(int id);
}
