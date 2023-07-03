package com.example.apiroberttorro.models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Barrio {
    public String name;
    public ArrayList<Casa> casas;

    public Barrio() {

    }

    public Barrio(String name) {
        this.name = name;
        this.casas = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Casa> getCasas() {
        return casas;
    }

    public void agregarCasa(Casa casa) {
        casas.add(casa);
    }

    @Override
    public String toString() {
        return "Barrio{" +
                "name='" + name + '\'' +
                ", casas=" + casas +
                '}';
    }
}
