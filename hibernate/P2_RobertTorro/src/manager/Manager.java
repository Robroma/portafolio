package manager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import model.Bodega;
import model.Campo;
import model.Entrada;
import model.TipoVid;
import model.Vid;

public class Manager {
	private static Manager manager;
	private ArrayList<Object> objects;

	private Session session;
	private Transaction tx;
	
	private Campo campo;
	private Bodega bodega;
	private List<Vid> listVids;
	private Vid vid;

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
		initSession();

		int i = 1;
		Entrada entrada;
		List<Entrada> entradas = new ArrayList<>();
		while ((entrada = getEntrada(i)) != null) {
			entradas.add(entrada);
			i++;
		}

		int id_bodega = 0;
		for (Entrada entr : entradas) {
			switch (entr.getInstruccion().split(" ")[0]) {
			case "B":
				id_bodega = insertBodega(entr.getInstruccion().split(" ")[1]);

				break;
			case "C":
				insertCampo(id_bodega);
				
				break;
			case "V":
				insertVid(TipoVid.valueOf(entr.getInstruccion().split(" ")[1].toUpperCase()), Integer.valueOf(entr.getInstruccion().split(" ")[2]));
				
				break;
			case "#":
				insertVerma();
				
				break;
			default:
				break;
			}
		}

		endSession();
	}

	private int insertVerma() {
		for (Vid vid : listVids) {
			System.out.println(vid.toString());
			System.out.println(bodega.toString());
		}
		
		bodega.getVids().addAll(campo.getVids());
		
		try {

			tx = session.beginTransaction();

			int id = (Integer) session.save(vid);

			tx.commit();
			System.out.println("Inserted Successfully.");

			return id;

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback(); // Roll back if any exception occurs.
			System.out.println("Error insertant bodega");
		}

		return 0;
		
	}

	private int insertVid(TipoVid tipo_vid, int cantidad) {
		vid = new Vid(cantidad, tipo_vid);
		campo.getVids().add(vid);
		listVids.add(vid);
		
		try {

			tx = session.beginTransaction();

			int id = (Integer) session.save(vid);

			tx.commit();
			System.out.println("Inserted Successfully.");

			return id;

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback(); // Roll back if any exception occurs.
			System.out.println("Error insertant bodega");
		}

		return 0;
	}

	private int insertCampo(int id_bodega) {
		listVids = new ArrayList<>();
		campo = new Campo(id_bodega);
		
		try {

			tx = session.beginTransaction();

			int id = (Integer) session.save(campo);
			
			tx.commit();
			System.out.println("Inserted Successfully.");

			return id;

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback(); // Roll back if any exception occurs.
			System.out.println("Error insertant bodega");
		}

		return 0;
	}

	private int insertBodega(String bodegaName) {
		bodega = new Bodega(bodegaName);

		try {

			tx = session.beginTransaction();

			int id = (Integer) session.save(bodega);

			tx.commit();
			System.out.println("Inserted Successfully.");

			return id;

		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback(); // Roll back if any exception occurs.
			System.out.println("Error insertant bodega");
		}

		return 0;
	}

	private Entrada getEntrada(int i) {
		try {
			tx = session.beginTransaction();
			Entrada entrada = session.get(Entrada.class, i);

			tx.commit();
			System.out.println("Saved Successfully.");
			return entrada;
		} catch (HibernateException e) {
			if (tx != null)
				tx.rollback();
			System.out.println("Error agafant entrada");

		}
		return null;
	}

	private void endSession() {
		session.close();
	}

	private void initSession() {
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		// Get the session object.
		session = sessionFactory.openSession();
	}

}
