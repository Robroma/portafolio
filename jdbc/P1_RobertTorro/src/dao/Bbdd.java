package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Carta;
import model.Color;
import model.Numero;
import utils.Constants;

public class Bbdd {
	public static final String SCHEMA_NAME = "dam2tm06uf2p1"; // modificar con el nombre de vuestro schema (Base de
																// datos)
	public static final String CONNECTION = "jdbc:mysql://localhost:3306/" + SCHEMA_NAME
			+ "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC&useSSL=false";
	public static final String USER_CONNECTION = "root"; // modificar por vuestro usuario de BBDD (root)
	public static final String PASS_CONNECTION = "root"; // modificar por vuestro password de BBDD (root)

	private Connection conexion;

	public void connectar() throws SQLException {
		String url = CONNECTION;
		String user = USER_CONNECTION;
		String pass = PASS_CONNECTION;
		conexion = DriverManager.getConnection(url, user, pass);
	}

	public void desconectar() throws SQLException {
		if (conexion != null) {
			conexion.close();
		}
	}

	public int usuariCorrecta(String password, String user) throws SQLException {
		int id = 0;
		try (PreparedStatement ps = conexion.prepareStatement(Constants.SELECT_USUARI_CORRECTA)) {
			ps.setString(1, password);
	        ps.setString(2, user);
	        ps.setString(3, user);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					if (rs.getInt(1) != 0)
						id = rs.getInt(1);
					return id;
				}
				return id;
			}
		}
	}
	public int crearUsuari(String password, String user, String name) throws SQLException {
		try (PreparedStatement ps = conexion.prepareStatement(Constants.INSERT_CREAR_USUARI)) {
			ps.setString(1, user);
	        ps.setString(2, password);
	        ps.setString(3, name);
	        ps.executeUpdate();
			return usuariCorrecta(password, user);
			
		}
	}

	public boolean usuariSenseCartes(int id_jugador) throws SQLException {
		int boolInt = 0;
		try (PreparedStatement ps = conexion.prepareStatement(Constants.SELECT_USUARI_SENSE_CARTES)) {
	        ps.setInt(1, id_jugador);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					boolInt = rs.getInt(1);
					return boolInt == 0 ? true : false;
				}
				return false;
			}
		}
	}

	public Carta ultimaCarta() throws SQLException {
		Carta carta = null;
		try (Statement st = conexion.createStatement()) {
			try (ResultSet rs = st.executeQuery(Constants.SELECT_ULTIMA_CARTA)) {
				while (rs.next()) {
					if (rs.getString(3) != null)
						carta = new Carta(rs.getInt(1), rs.getInt(2), Numero.valueOf(rs.getString(3)),
								Color.valueOf(rs.getString(4)));
				}
			}
		}
		return carta;
	}

	public void robarCarta(int id_user, int numCartes) throws SQLException {
		for (int i = 0; i < numCartes; i++) {
			Numero num = Numero.randomNumero();
			Color color = Color.randomColor();
			if (num.equals(Numero.CAMBIOCOLOR) || num.equals(Numero.MASCUATRO)) {
				color = Color.NEGRO;
			}
			try (PreparedStatement ps = conexion.prepareStatement(Constants.INSERT_ROBAR_CARTA)) {
		        ps.setInt(1, id_user);
		        ps.setString(2, num.toString());
		        ps.setString(3, color.toString());
		        ps.executeUpdate();
			}
		}
	}

	public ArrayList<Integer> showCartas(int id_user, boolean aux) throws SQLException {
		ArrayList<Integer> id_cartas = new ArrayList<>();
		int numCartes = 0;
		Carta carta = null;
		System.out.println("\n");
		try (PreparedStatement ps = conexion.prepareStatement(Constants.SELECT_SHOW_CARTES)) {
	        ps.setInt(1, id_user);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					if (rs.getString(3) != null)
						carta = new Carta(rs.getInt(1), rs.getInt(2), Numero.valueOf(rs.getString(3)),
								Color.valueOf(rs.getString(4)));
					if(aux) System.out.println(numCartes + " - " + carta.toString());
					id_cartas.add(rs.getInt(1));
					numCartes++;
				}
			}
			return id_cartas;
		}
	}

	public ArrayList<Integer> showCartasNoJugada(int id_user) throws SQLException {
		ArrayList<Integer> id_cartas = new ArrayList<>();
		int numCartes = 0;
		Carta carta = null;
		System.out.println("\n");
		try (PreparedStatement ps = conexion.prepareStatement(Constants.SELECT_SHOW_CARTES_NO_JUGADA)) {
	        ps.setInt(1, id_user);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					if (rs.getString(3) != null)
						carta = new Carta(rs.getInt(1), rs.getInt(2), Numero.valueOf(rs.getString(3)),
								Color.valueOf(rs.getString(4)));
					System.out.println(numCartes + " - " + carta.toString());
					id_cartas.add(rs.getInt(1));
					numCartes++;
				}
			}
			return id_cartas;
		}
	}

	public void cartaSelecionada(int id_carta) throws SQLException {
		try (Statement st = conexion.createStatement()) {
			st.executeUpdate(Constants.UPDATE_CARTA_SELECIONADA);
			PreparedStatement ps = conexion.prepareStatement(Constants.INSERT_CARTA_SELECIONADA);
	        ps.setInt(1, id_carta);
			ps.executeUpdate();
		}
	}

	public boolean ultimaCartaExcepcions(int id_user) throws SQLException {
		Carta carta = ultimaCarta();
		boolean ultimaJugada = ultimaJugada();
		if (ultimaJugada) {
			try (Statement st = conexion.createStatement()) {
				if (carta.getNumero() == Numero.CAMBIO || carta.getNumero() == Numero.SALTO
						|| carta.getNumero() == Numero.CAMBIOCOLOR) {
					st.executeUpdate(Constants.UPDATE_ULTIMA_CARTA_EXCEPCIONS);
					System.out.println("Pierdes el turno");
					return true;
				} else if (carta.getNumero() == Numero.MASDOS) {
					st.executeUpdate(Constants.UPDATE_ULTIMA_CARTA_EXCEPCIONS);
					robarCarta(id_user, 2);
					System.out.println("Has robado 2 cartas");
					return true;
				} else if (carta.getNumero() == Numero.MASCUATRO) {
					st.executeUpdate(Constants.UPDATE_ULTIMA_CARTA_EXCEPCIONS);
					robarCarta(id_user, 4);
					System.out.println("Has robado 4 cartas");
					return true;
				}

			}

		}
		return false;
	}

	public boolean ultimaJugada() throws SQLException {
		int boolInt = 0;
		try (Statement st = conexion.createStatement()) {
			try (ResultSet rs = st
					.executeQuery(Constants.SELECT_ULTIMA_JUGADA)) {
				while (rs.next()) {
					boolInt = rs.getInt(1);
					return boolInt == 1 ? true : false;
				}
				return false;
			}
		}
	}

	public boolean mateixColor(Color color, int id_carta) throws SQLException {
		String colorString = null;
		if (color == Color.NEGRO)
			return true;
		else {
			try (PreparedStatement ps = conexion.prepareStatement(Constants.SELECT_MATEIX_COLOR)) {
		        ps.setInt(1, id_carta);
				try (ResultSet rs = ps.executeQuery()) {
					while (rs.next()) {
						colorString = rs.getString(1);
					}
				}
				if (colorString.equals("NEGRO"))
					return true;
				if (colorString.equals(color.toString()))
					return true;
			}

		}

		return false;
	}

	public boolean finaltizarPartida(int id_user) throws SQLException {
		
		if (showCartas(id_user,false).isEmpty()) {
			try (Statement st = conexion.createStatement()) {
				st.executeUpdate(Constants.UPDATE_FINALITZA_PARTIDA_PARTIDAS);
				PreparedStatement ps = conexion.prepareStatement(Constants.UPDATE_FINALITZA_PARTIDA_GANADAS);
		        ps.setInt(1, id_user);
				ps.executeUpdate();
				st.execute(Constants.DELETE_FINALITZA_PARTIDA_PARTIDA);
				st.execute(Constants.DELETE_FINALITZA_PARTIDA_CARTA);
			}
			return true;
		} else
			return false;

	}

	
}
