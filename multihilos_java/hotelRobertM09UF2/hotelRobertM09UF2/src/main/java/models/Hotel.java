package models;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class Hotel {
    private String nombre;
    private Set<Habitacion> habitaciones;
    private List<Tecnico> tecnicos;
    private double dinero;
    private int dia;
    private int personasPerdidas;
    private boolean open;


    public Hotel(String nombre) {
        this.nombre = nombre;
        habitaciones = new TreeSet<Habitacion>();
        tecnicos = new ArrayList<Tecnico>();
        dinero = 4000;
        dia = 0;
        personasPerdidas = 0;
        open = true;
    }

    public void setPersonasPerdidas(int personasPerdidas) {
        this.personasPerdidas = personasPerdidas;
    }

    public void setDinero(double dinero) {
        this.dinero = dinero;
    }

    public String getNombre() {
        return nombre;
    }

    public Set<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public List<Tecnico> getTecnicos() {
        return tecnicos;
    }

    public double getDinero() {
        return dinero;
    }

    public int getDia() {
        return dia;
    }

    public int getPersonasPerdidas() {
        return personasPerdidas;
    }

    public boolean isOpen() {
        return open;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }
}
