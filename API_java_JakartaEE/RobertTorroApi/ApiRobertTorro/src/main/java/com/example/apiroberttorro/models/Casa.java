package com.example.apiroberttorro.models;

import java.util.ArrayList;

public class Casa {
    public int id;

    public String barrio;

    public Casa() {

    }

    public String getBarrio() {
        return barrio;
    }

    public ArrayList<Inquilino> inquilinos;

    public Casa(int id) {
        this.id = id;
        this.inquilinos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public ArrayList<Inquilino> getInquilinos() {
        return inquilinos;
    }

    @Override
    public String toString() {
        return "Casa{" +
                "id=" + id +
                ", inquilinos=" + inquilinos +
                '}';
    }
}
