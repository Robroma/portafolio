package model;

public class Jugador {
	private String nombre;
	private int puntuacion;
	private String pantalla;
	private String estado;
	
	public Jugador(String nombre, int puntuacion, String pantalla, String estado) {
		this.nombre = nombre;
		this.puntuacion = puntuacion;
		this.pantalla = pantalla;
		this.estado = estado;
	}
	public Jugador(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getPuntuacion() {
		return puntuacion;
	}
	public void setPuntuacion(int puntuacion) {
		this.puntuacion = puntuacion;
	}
	public String getPantalla() {
		return pantalla;
	}
	public void setPantalla(String pantalla) {
		this.pantalla = pantalla;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Jugador [nombre=" + nombre + ", puntuacion=" + puntuacion + ", pantalla=" + pantalla + ", estado="
				+ estado + "]";
	}
	
	public int getNivel() {
		if (estado.equals("pendiente") && !pantalla.equals("#1")) {
			return -2 + Character.getNumericValue(pantalla.charAt(pantalla.length() - 1));
		} else return -1 + Character.getNumericValue(pantalla.charAt(pantalla.length() - 1));
		
	}
	
	
	
}
