package model;

import java.util.Arrays;
import java.util.Random;

public enum Color {
	ROJO, AMARILLO, VERDE, AZUL, NEGRO;
	
	private static final Random RNG = new Random();
	
	public static Color randomColor() {
		Color[] colors = values();
		colors = Arrays.copyOf(colors, colors.length - 1);

		return colors[RNG.nextInt(colors.length)];
	}
}

