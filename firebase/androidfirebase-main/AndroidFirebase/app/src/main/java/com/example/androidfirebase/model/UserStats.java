package com.example.androidfirebase.model;

import java.sql.Timestamp;
import java.util.Date;

public class UserStats {
    private Date UltimoInicio;
    private int ahorcadoGanadas;
    private int paraulogicGanadas;
    private int numConexiones;

    public UserStats() {
    }

    public Date getUltimoInicio() {
        return UltimoInicio;
    }

    public void setUltimoInicio(Date ultimoInicio) {
        this.UltimoInicio = ultimoInicio;
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
