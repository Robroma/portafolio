package dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import model.Jugador;

public class SaxReader extends DefaultHandler {
	ArrayList<Jugador> jugadors;
	Jugador jugador;
	String value;
	
	public ArrayList<Jugador> init(String fil) {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		SAXParser parser;
		try {
			parser = factory.newSAXParser();
			File file = new File (fil);
			parser.parse(file, this);
		} catch (ParserConfigurationException | SAXException e) {
			System.out.println("ERROR creating the parser");
		} catch (IOException e) {
			System.out.println("ERROR file not found");
		}	
		
		return this.jugadors;
	}
	
	@Override
	public void startDocument() throws SAXException {
		this.jugadors = new ArrayList<>();
	}

	@Override
	public void endDocument() throws SAXException {
		printDocument();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		switch (qName) {
			case "partida":
				this.jugador = new Jugador(attributes.getValue("jugador"));
				break;
			case "pantalla":
				this.jugador.setEstado(attributes.getValue("estado"));
				break;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		//we add the product into the arrayList
		if (qName.equals("partida")) this.jugadors.add(jugador);
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		value = new String(ch,start,length);
		
		//if value contains a "." is because it's a float do it goes to price attribute
		//if not it goes to stock attribute
		if (value.matches("[0-9]*")) this.jugador.setPuntuacion(Integer.valueOf(value));
		else if(value.matches("#[0-9]*")) this.jugador.setPantalla(value);
	}

	private void printDocument() {
		for (Jugador p : jugadors) {
			System.out.println(p.toString());
		}
	}
	
	

}
