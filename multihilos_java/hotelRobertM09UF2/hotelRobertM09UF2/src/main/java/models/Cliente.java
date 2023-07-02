package models;

import utils.EstadoHabitacion;
import utils.TipoHabitacion;
import view.PanelHotel;

public class Cliente implements Runnable {
    private int id;
    private String nombre;
    private int personas;
    private TipoHabitacion tipoHabitacion;
    private int dias;
    private PanelHotel panelHotel;

    public Cliente(int id, String nombre, int personas, TipoHabitacion tipoHabitacion, int dias, PanelHotel panelHotel) {
        this.id = id;
        this.nombre = nombre;
        this.personas = personas;
        this.tipoHabitacion = tipoHabitacion;
        this.dias = dias;
        this.panelHotel = panelHotel;
    }

    @Override
    public void run() {
        try {
            Habitacion habitacionDisponible = getHabitacionDisponible();
            if (habitacionDisponible != null) {
                habitacionDisponible.setDisponible(EstadoHabitacion.OCUPADA);
                panelHotel.getHotel().setDinero(panelHotel.getHotel().getDinero() + habitacionDisponible.getPrecioNoche());
                System.out.println(panelHotel.getHotel().getDinero());

                for (int i = 0; i < dias; i++) {
                    Thread.sleep(2000);
                    if (habitacionDisponible.getDisponible().equals(EstadoHabitacion.AVERIADA)) {
                        Habitacion novaHabitacionDisponible = getHabitacionDisponible();
                        if (novaHabitacionDisponible != null) {
                            habitacionDisponible = novaHabitacionDisponible;
                            habitacionDisponible.setDisponible(EstadoHabitacion.OCUPADA);
                        } else {
                            panelHotel.getHotel().setPersonasPerdidas(panelHotel.getHotel().getPersonasPerdidas() + 1);
                            panelHotel.getHotel().setDinero(panelHotel.getHotel().getDinero() - 1000 - habitacionDisponible.getPrecioNoche());
                            i = dias;
                        }
                    }
                }
                habitacionDisponible.setDisponible(EstadoHabitacion.DISPONIBLE);

            } else {
                panelHotel.getHotel().setPersonasPerdidas(1 + panelHotel.getHotel().getPersonasPerdidas());
                panelHotel.getHotel().setDinero(panelHotel.getHotel().getDinero() - 1000);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

    private synchronized Habitacion getHabitacionDisponible() {
        for (Habitacion h : panelHotel.getHotel().getHabitaciones()) {
            if (h.getTipo().equals(tipoHabitacion) && h.getCapacidad() >= personas && h.getDisponible().equals(EstadoHabitacion.DISPONIBLE))
                return h;
        }
        return null;
    }

}
