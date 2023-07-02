package model;

public class Carta {
	private int id;
	private int id_jugador;
	private Numero numero;
	private Color color;
	
	public Carta(int id, int id_jugador, Numero numero, Color color) {
		this.id = id;
		this.id_jugador = id_jugador;
		this.numero = numero;
		this.color = color;
	}

	@Override
	public String toString() {
		return "Carta [id=" + id + ", id_jugador=" + id_jugador + ", numero=" + numero + ", color=" + color + "]";
	}

	public int getId() {
		return id;
	}

	public int getId_jugador() {
		return id_jugador;
	}

	public Numero getNumero() {
		return numero;
	}

	public Color getColor() {
		return color;
	}
	
	
	
	
}
