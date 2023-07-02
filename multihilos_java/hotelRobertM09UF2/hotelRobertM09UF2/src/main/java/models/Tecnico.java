package models;

public class Tecnico {
    private int id;
    private Habitacion habitacion;

    public Tecnico(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Habitacion getHabitacion() {
        return habitacion;
    }
}
