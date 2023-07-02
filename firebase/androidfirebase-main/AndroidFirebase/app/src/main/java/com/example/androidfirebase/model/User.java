package com.example.androidfirebase.model;

import android.widget.TextView;

public class User {
    private String ultimoInicio;
    private int ahorcadoGanadas;
    private int paraulogicGanadas;
    private int numConexiones;

    public User() {
    }

    public String getUltimoInicio() {
        return ultimoInicio;
    }

    public void setUltimoInicio(String ultimoInicio) {
        this.ultimoInicio = ultimoInicio;
    }

    public int getAhorcadoGanadas() {
        return ahorcadoGanadas;
    }

    public void setAhorcadoGanadas(int ahorcadoGanadas) {
        this.ahorcadoGanadas = ahorcadoGanadas;
    }

    public int getParaulogicGanadas() {
        return paraulogicGanadas;
    }

    public void setParaulogicGanadas(int paraulogicGanadas) {
        this.paraulogicGanadas = paraulogicGanadas;
    }

    public int getNumConexiones() {
        return numConexiones;
    }

    public void setNumConexiones(int numConexiones) {
        this.numConexiones = numConexiones;
    }
}
