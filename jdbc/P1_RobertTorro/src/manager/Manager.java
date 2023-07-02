package manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import dao.Bbdd;
import model.Carta;

public class Manager {
	private static Manager manager;
	private ArrayList<Object> objects;

	private Bbdd bbdd;
	private static Scanner sc = new Scanner(System.in);;

	private ArrayList<Integer> ids_cartes;

	private Manager() {
		this.objects = new ArrayList<>();
	}

	public static Manager getInstance() {
		if (manager == null) {
			manager = new Manager();
		}
		return manager;
	}

	public void init() {
		bbdd = new Bbdd();
		try {
			bbdd.connectar();
		} catch (SQLException e) {
			System.out.println("No s'ha pogut connectar a la base de dades");
		}
		try {
			ids_cartes = new ArrayList<>();
			int id_user = validarUsuari();
			if (id_user != 0) {
				if (bbdd.usuariSenseCartes(id_user)) {
					bbdd.robarCarta(id_user, 7);
					System.out.println("Has robado 7 cartas...");
				}

				boolean sortir = bbdd.ultimaCartaExcepcions(id_user);

				while (!sortir) {
					Carta carta = bbdd.ultimaCarta();
					System.out.print("Ultima carta Jugada: ");
					if (carta == null) {
						System.out.println("No hay ultima jugada");
						ids_cartes = bbdd.showCartasNoJugada(id_user);
					} else {
						System.out.println(carta.toString());
						ids_cartes = bbdd.showCartas(id_user, true);
					}

					int cartaSelecionar = dataEntryIntMinMax("Seleciona la carta a jugar. -1 para robar", -1,
							ids_cartes.size() - 1);
					if (cartaSelecionar != -1) {
						boolean mateixColor;
						if (carta != null)
							mateixColor = bbdd.mateixColor(carta.getColor(), ids_cartes.get(cartaSelecionar));
						else
							mateixColor = true;
						if (mateixColor) {
							bbdd.cartaSelecionada(ids_cartes.get(cartaSelecionar));
							sortir = true;
						} else
							System.out.println("Carta incorrecta,seleciona una nueva carta\n");
						;
					} else {
						System.out.println("Has robado una carta");
						bbdd.robarCarta(id_user, 1);
					}
				}
				if (bbdd.finaltizarPartida(id_user))
					System.out.println("Has ganado la partida");
				else
					System.out.println("Fin de juego");
				bbdd.desconectar();
			} else
				System.out.println("Usuari incorrecta");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static int dataEntryIntMinMax(String text, double min, double max) {
		int number = 0;
		System.out.println(text);
		boolean isValid = false;
		while (!isValid) {
			if (sc.hasNextInt()) {
				number = sc.nextInt();
				if (number >= min && number <= max) {
					isValid = true;
				} // else {System.out.println("");}
			} else {
				sc.next(); // consume next line
				System.out.println("Error! \n" + text);
			}
		}
		return number;
	}

	public int validarUsuari() throws SQLException {
		int i = dataEntryIntMinMax("0.Login\n1.Registre", 0, 1);
		if (i == 0) {
			sc.nextLine();
			System.out.print("Nombre de usuario: ");
			String user = sc.nextLine();
			System.out.print("Password: ");
			String password = sc.nextLine();

			return bbdd.usuariCorrecta(password, user);
		}
		else {
			sc.nextLine();
			System.out.print("Nombre de usuario: ");
			String user = sc.nextLine();
			System.out.print("Password: ");
			String password = sc.nextLine();
			System.out.print("Nombre: ");
			String name = sc.nextLine();
			System.out.println("Usuario creado\n");
			return bbdd.crearUsuari(password, user, name);
		}
	}

}
