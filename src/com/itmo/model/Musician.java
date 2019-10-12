package com.itmo.model;

public abstract class Musician {
    private String name;

    abstract void getInfo();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
