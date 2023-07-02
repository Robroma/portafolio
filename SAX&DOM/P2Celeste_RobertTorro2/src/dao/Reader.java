package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {
	
	private BufferedReader bf;
	private FileReader fr;
	
	public Reader(String file) {
		try {
			this.fr = new FileReader(file);
			this.bf = new BufferedReader(this.fr);
		} catch (IOException e) {
			System.out.println("Error buscando fichero");
		}		
	}
	
	public String readLine() {
		try {
			return this.bf.readLine();
		} catch (IOException e) {
			System.out.println("Error leyendo");
			return null;
		}
	}
	
	public char readChar() {
		try {
			int element = 0;
			element = fr.read();
			char a = (char)element;
			return a;
		} catch (IOException e) {
			System.out.println("Error leyendo");
			return ' ';
		}
		
	
	}
	
	public void closeFile() {
		try {
			this.bf.close();
		} catch (IOException e) {
			System.out.println("Error cerrando el fichero");
		}
	}
	
	
}
