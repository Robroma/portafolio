package com.example.apiroberttorro.models;

public class Inquilino {
    public int id;
    public int casa;

    public Inquilino() {

    }

    public Inquilino(int id, int casa) {
        this.id = id;
        this.casa = casa;
    }

    @Override
    public String toString() {
        return "Inquilino{" +
                "id=" + id +
                ", casa=" + casa +
                '}';
    }

    public int getId() {
        return id;
    }

    public int getCasa() {
        return casa;
    }
}
