package com.example.budget.model;

import java.util.Objects;

public class BaseModel {

    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BaseModel() {
    }

    public BaseModel(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        BaseModel baseModel = (BaseModel) obj;
        return id == baseModel.id;
    }
}
