package models;

import utils.EstadoHabitacion;
import utils.TipoHabitacion;

public class Habitacion implements Comparable<Habitacion> {
    private int numero;
    private EstadoHabitacion disponible;
    private TipoHabitacion tipo;
    private int capacidad;
    private double precioNoche;

    public Habitacion(int numero, EstadoHabitacion disponible, TipoHabitacion tipo, int capacidad, double precioNoche) {
        this.numero = numero;
        this.disponible = disponible;
        this.tipo = tipo;
        this.capacidad = capacidad;
        this.precioNoche = precioNoche;
    }

    public void setDisponible(EstadoHabitacion disponible) {
        this.disponible = disponible;
    }

    public int getNumero() {
        return numero;
    }

    public EstadoHabitacion getDisponible() {
        return disponible;
    }

    public TipoHabitacion getTipo() {
        return tipo;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public double getPrecioNoche() {
        return precioNoche;
    }

    @Override
    public int compareTo(Habitacion o) {
        return numero - o.numero;
    }
}
