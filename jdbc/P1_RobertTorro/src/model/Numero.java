package model;

import java.util.Random;

public enum Numero {
	CERO, UNO, DOS, TRES, CUATRO, CINCO, SEIS, SIETE, OCHO, NUEVE, CAMBIO, MASDOS, SALTO, CAMBIOCOLOR, MASCUATRO;
	
	private static final Random RNG = new Random();
	
	public static Numero randomNumero() {
		Numero[] numeros = values();
		return numeros[RNG.nextInt(numeros.length)];
	}

}
