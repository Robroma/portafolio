package manager;

import java.util.ArrayList;

import dao.DomNewDocument;
import dao.Reader;
import dao.SaxReader;
import model.Jugador;

public class Manager {
	private static Manager manager;
	private ArrayList<Object> objects;
	
	private ArrayList<String[][]> pantallas;
	private ArrayList<Jugador> jugadors;
	
	private Reader rdPantallas;
	private DomNewDocument domNewDocument;
	private DomNewDocument domNewDocument2;
	private SaxReader saxReader;
	
	private Manager() {
		this.objects = new ArrayList<>();
	}
	
	public static Manager getInstance() {
		if(manager == null) {
			manager = new Manager();
		}
		return manager;
	}
	
	public void init() {
		rdPantallas = new Reader("src/inputFiles/pantallas.txt");
		saxReader = new SaxReader();
		jugadors = saxReader.init("src/inputFiles/entrada.xml");
		domNewDocument = new DomNewDocument();	
		domNewDocument2 = new DomNewDocument();
		
		leerPantallas();	
		domNewDocument.generateDocument("src/inputFiles/salida.xml", jugadors, pantallas);
		domNewDocument2.caminoPantalla("src/inputFiles/solucions.xml", jugadors, pantallas);
	}

	private void leerPantallas() {
		int x = 0;
		int y = 0;
		int n = 0;
		String str = rdPantallas.readLine();
		
		while (str != null) {	
			if(str.contains("#")) x++;
			else { 
				n = str.split(" ").length;
				y++;
			}
			str = rdPantallas.readLine();
		}	
		rdPantallas.closeFile();
		
		x -= 1;
		rdPantallas = new Reader("src/inputFiles/pantallas.txt");
		pantallas = new ArrayList<>();
		
		str = rdPantallas.readLine();
		while (str != null) {
			if(!str.contains("#")) {
				String pantalla[][] = new String[y/x][n];
				for(int z = 0; z < pantalla.length;z++) {
					pantalla[z] = str.split(" ");
					str = rdPantallas.readLine();
				}
				pantallas.add(pantalla);

			} 
			str = rdPantallas.readLine();
		}
		rdPantallas.closeFile();	
	}
	
	
}
