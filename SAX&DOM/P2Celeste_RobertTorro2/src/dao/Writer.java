package dao;

import java.io.FileWriter;
import java.io.IOException;

public class Writer {
	private FileWriter fw;
	
	public Writer(String file) {
		try {
			this.fw = new FileWriter(file);
		} catch (IOException e) {
			System.out.println("Error creando fichero");
		}		
	}
	
	public void write(String str) {
		try {
			fw.write(str+"\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Error escribiendo");
		}
		
	}
	public void closeFile() {
		try {
			this.fw.close();
		} catch (IOException e) {
			System.out.println("Error cerrando el fichero");
		}
	}
	
}
