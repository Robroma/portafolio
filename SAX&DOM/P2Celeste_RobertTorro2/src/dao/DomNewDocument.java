/*
 * asd.java 13 oct 2022
 *
 *
 * �Copyright 2022 Robert Torr� <robertinyo1@gmail.com>
 *
 * This is free software, licensed under the GNU General Public License v3.
 * See http://www.gnu.org/licenses/gpl.html for more information.
 */
package dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import model.Jugador;

public class DomNewDocument {
	private Document document;
	//private Document document2;
	
	public DomNewDocument() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newDefaultInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			document = builder.newDocument();
			//document2 = builder.newDocument();
		} catch (ParserConfigurationException e) {
			System.out.println("ERROR generating doucment");
		}
	}
	

	public void generateDocument(String file, ArrayList<Jugador> arrayJugadors, ArrayList<String[][]> arrayPantallas) {
		Element pantallas = document.createElement("pantallas");
		document.appendChild(pantallas);
		
		Element pantalla;
		Element pixel;
		Element puntuacion;
		for (Jugador jugador : arrayJugadors) {
			pantalla = document.createElement("pantalla");
			pantalla.setAttribute("jugador", jugador.getNombre());
			pantalla.setAttribute("nivel", ""+(jugador.getNivel()+1));
			pantallas.appendChild(pantalla);
			
			String[][] strings = arrayPantallas.get(jugador.getNivel());
			for (int i = 0; i < strings.length; i++) {
				for (int z = 0; z < strings[i].length; z++) {
					pixel = document.createElement("pixel");
					pixel.setAttribute("col", ""+z);
					pixel.setAttribute("fil", ""+i);
					pixel.setTextContent(strings[i][z]);
					pantalla.appendChild(pixel);	
				}
			}
			puntuacion = document.createElement("puntuacion");
			puntuacion.setTextContent(""+jugador.getPuntuacion());
			pantalla.appendChild(puntuacion);
		}
						
		generateXml(file);
		
	}
	
	private void generateXml(String fil) {
		try {			
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			
			Source source = new DOMSource(document);
			File file = new File(fil);
			FileWriter fw = new FileWriter(file);
			PrintWriter pw = new PrintWriter(fw);
			Result result = new StreamResult(pw);
			
			transformer.transform(source, result);
		} catch (IOException e) {
			System.out.println("Error when creating writter file");
		} catch (TransformerException e) {
			System.out.println("Error transforming the document");
		}
	}
	private int[] foundX(int level, ArrayList<String[][]> pantallas) {
		int[] posiciones = new int[2];
		
		String[][] pantallaAux2 = pantallas.get(level);
		for (int i = 0; i < pantallaAux2.length; i++) {
			for (int j = 0; j < pantallaAux2[i].length; j++) {
				if(pantallaAux2[i][j].equals("X")) {
					posiciones[0] = i;
					posiciones[1] = j;
					return posiciones;
				}
			}
		}
		
		return posiciones;
	}
	public void caminoPantalla(String file, ArrayList<Jugador> arrayJugadors, ArrayList<String[][]> arrayPantallas) {
		Element pantallas = document.createElement("pantallas");
		document.appendChild(pantallas);
		
		Element pantalla;
		Element pixel;
		Element puntuacion;
		int z = 0;
		String [][] pantallaAux = new String[5][10];
				
		
		boolean booleanIMes = true;
		boolean booleanIMenos = true;
		boolean booleanJMes = true;
		boolean booleanJMenos = true;
		boolean boolAcabar = true;	
		boolean boolO = false;
		
		for (Jugador jugador : arrayJugadors) {
			int[] posX = foundX(jugador.getNivel(),arrayPantallas);
			pantalla = document.createElement("pantalla");
			pantalla.setAttribute("jugador", jugador.getNombre());
			pantalla.setAttribute("nivel", ""+(jugador.getNivel()+1));
			
			
			String[][] pantallaCompleta = arrayPantallas.get(jugador.getNivel());
			int i = posX[0];
			int j = posX[1];
			z = 0;
			boolAcabar = true;
			pixel = document.createElement("pixel");
			pixel.setAttribute("col", "" + (i));
			pixel.setAttribute("fil", "" + j);
			pixel.setTextContent(pantallaCompleta[i][j]);
			pantalla.appendChild(pixel);
			while (z<101 && boolAcabar) {
				if(i+1 < pantallaAux.length && booleanIMes) {
					if(!pantallaCompleta[i+1][j].equals(".")) { 
						pixel = document.createElement("pixel");
						pixel.setAttribute("col", "" + (i+1));
						pixel.setAttribute("fil", "" + j);
						pixel.setTextContent(pantallaCompleta[i+1][j]);
						pantalla.appendChild(pixel);
						booleanIMes = true;
						booleanIMenos = false;
						booleanJMes = true;
						booleanJMenos = true;					
						if(pantallaCompleta[i+1][j].equals("o")) boolO = true;
						if(pantallaCompleta[i+1][j].equals("S") && boolO) boolAcabar = false;
						i++;
					}
				}
				if(j-1 >= 0 && booleanJMenos ) {
					if(!pantallaCompleta[i][j-1].equals(".")) {
						pixel = document.createElement("pixel");
						pixel.setAttribute("col", "" + i);
						pixel.setAttribute("fil", "" + (j-1));
						pixel.setTextContent(pantallaCompleta[i][j-1]);
						pantalla.appendChild(pixel);
						booleanIMes = true;
						booleanIMenos = true;
						booleanJMes = false;
						booleanJMenos = true;
						if(pantallaCompleta[i][j-1].equals("o")) boolO = true;
						if(pantallaCompleta[i][j-1].equals("S") && boolO) boolAcabar = false;
						j--;
					}
				}
				if(j+1 < pantallaAux[i].length && booleanJMes ) {
					if(!pantallaCompleta[i][j+1].equals(".")) {
						pixel = document.createElement("pixel");
						pixel.setAttribute("col", "" + i);
						pixel.setAttribute("fil", "" + (j+1));
						pixel.setTextContent(pantallaCompleta[i][j+1]);
						pantalla.appendChild(pixel);
						booleanIMes = true;
						booleanIMenos = true;
						booleanJMes = true;
						booleanJMenos = false;
						if(pantallaCompleta[i][j+1].equals("o")) boolO = true;
						if(pantallaCompleta[i][j+1].equals("S") && boolO) boolAcabar = false;
						j++;
					}
				}
				if(i-1 >= 0 && booleanIMenos ) {
					if(!pantallaCompleta[i-1][j].equals(".")) {
						pixel = document.createElement("pixel");
						pixel.setAttribute("col", "" + (i-1));
						pixel.setAttribute("fil", "" + j);
						pixel.setTextContent(pantallaCompleta[i-1][j]);
						pantalla.appendChild(pixel);
						booleanIMes = false;
						booleanIMenos = true;
						booleanJMes = true;
						booleanJMenos = true;
						if(pantallaCompleta[i-1][j].equals("o")) boolO = true;
						if(pantallaCompleta[i-1][j].equals("S") && boolO) boolAcabar = false;
						i--;
					}
				}
				
				if (z == 100)pantalla = randomSolution(arrayPantallas,posX[0],posX[1],jugador.getNivel(),jugador);		
				z++;

			}
			pantallas.appendChild(pantalla);
			puntuacion = document.createElement("puntuacion");
			puntuacion.setTextContent(""+jugador.getPuntuacion());
			pantalla.appendChild(puntuacion);
		}
						
		generateXml(file);
		
		
	}
	private Element randomSolution(ArrayList<String[][]> pantallas,int i,int j,int nivel,Jugador jugador) {
		String[][] pantallaAux = new String[5][10];
		String[][] pantallaCompleta = pantallas.get(nivel);
		int x=i;
		int y=j;
		Element pixel;
		Element pantalla;
		pantalla = document.createElement("pantalla");
		int z = 300;
		int random = (int)(Math.random()*(4-1+1)+1);
		int noSolucio = 0;
		Element noTeSolucio;
		
		while(z>149) {
			z=0;
			i=x;
			j=y;
			boolean boolAcabar = true;	
			boolean boolO = false;
			pantalla = document.createElement("pantalla");
			pantalla.setAttribute("jugador", jugador.getNombre());
			pantalla.setAttribute("nivel", ""+(jugador.getNivel()+1));
			pixel = document.createElement("pixel");
			pixel.setAttribute("col", "" + (i));
			pixel.setAttribute("fil", "" + j);
			pixel.setTextContent(pantallaCompleta[i][j]);
			pantalla.appendChild(pixel);
						
			while (z <= 150 && boolAcabar && noSolucio<100) {

				if (i + 1 < pantallaAux.length && random == 1 ) {
					if (!pantallaCompleta[i + 1][j].equals(".")) {
						pixel = document.createElement("pixel");
						pixel.setAttribute("col", "" + (i + 1));
						pixel.setAttribute("fil", "" + j);
						pixel.setTextContent(pantallaCompleta[i + 1][j]);
						pantalla.appendChild(pixel);
						if (pantallaCompleta[i + 1][j].equals("o")) {
							boolO = true;
						}
						if (pantallaCompleta[i + 1][j].equals("S") && boolO) {
							boolAcabar = false;
						}
						i++;
					}
				}
				if (j - 1 >= 0 && random == 2 ) {
					if (!pantallaCompleta[i][j - 1].equals(".")) {
						pixel = document.createElement("pixel");
						pixel.setAttribute("col", "" + i);
						pixel.setAttribute("fil", "" + (j - 1));
						pixel.setTextContent(pantallaCompleta[i][j - 1]);
						pantalla.appendChild(pixel);
						if (pantallaCompleta[i][j - 1].equals("o")) {
							boolO = true;
						}
						if (pantallaCompleta[i][j - 1].equals("S") && boolO) {
							boolAcabar = false;
						}
						j--;
					}
				}
				if (j + 1 < pantallaAux[i].length && random == 3 ) {
					if (!pantallaCompleta[i][j + 1].equals(".")) {
						pixel = document.createElement("pixel");
						pixel.setAttribute("col", "" + i);
						pixel.setAttribute("fil", "" + (j + 1));
						pixel.setTextContent(pantallaCompleta[i][j + 1]);
						pantalla.appendChild(pixel);
						if (pantallaCompleta[i][j + 1].equals("o")) {
							boolO = true;
						}
						if (pantallaCompleta[i][j + 1].equals("S") && boolO) {
							boolAcabar = false;
						}
						j++;
					}
				}
				if (i - 1 >= 0 && random == 4 ) {
					if (!pantallaCompleta[i - 1][j].equals(".")) {
						pixel = document.createElement("pixel");
						pixel.setAttribute("col", "" + (i - 1));
						pixel.setAttribute("fil", "" + j);
						pixel.setTextContent(pantallaCompleta[i - 1][j]);
						pantalla.appendChild(pixel);
						if (pantallaCompleta[i - 1][j].equals("o")) {
							boolO = true;
						}
						if (pantallaCompleta[i - 1][j].equals("S") && boolO) {
							boolAcabar = false;
						}
						i--;
					}
				}
				z++;
				random = (int) (Math.random() * (4 - 1 + 1) + 1);
			}
			noSolucio++;
			
		}
		if(noSolucio == 101) {
			noTeSolucio = document.createElement("noTeSolucio");
			noTeSolucio.setTextContent("No te solucio");
			pantalla.appendChild(noTeSolucio);
			System.out.println("No te solucio");
		}
		return pantalla;
	}
}
