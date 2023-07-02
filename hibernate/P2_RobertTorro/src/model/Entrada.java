package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Entrada")
public class Entrada {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name = "id", unique = true, nullable = true) 
	private int id;	
	
	@Column
	private String instruccion;

	public int getId() {
		return id;
	}

	public String getInstruccion() {
		return instruccion;
	}

	@Override
	public String toString() {
		return "Entrada [id=" + id + ", instruccion=" + instruccion + "]";
	}
	
	
	
}
