package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Bodega")
public class Bodega {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name = "id", unique = true, nullable = true) 
	private int id_bodega;
	
	@Column
	private String nombre;
	
	@OneToMany 
    @JoinColumn(name = "bodega_id")
	private List<Vid> vids;

	
	public Bodega() {}
	public Bodega(String nombre) {
		this.nombre = nombre;
		vids = new ArrayList<>();
	}
	public List<Vid> getVids() {
		return vids;
	}
	public int getId_bodega() {
		return id_bodega;
	}
	public String getNombre() {
		return nombre;
	}
	public void setVids(List<Vid> vids) {
		this.vids = vids;
	}
	@Override
	public String toString() {
		return "Bodega [id_bodega=" + id_bodega + ", nombre=" + nombre + ", vids=" + vids + "]";
	}

	
	
	
	
	
}
