package utils;

public enum TipoHabitacion {
    sencilla(1), doble(2), suite(3);

    private int i;

    TipoHabitacion(int i) {
        this.i = i;
    }

    public int getTipo() {
        return i;
    }


}
