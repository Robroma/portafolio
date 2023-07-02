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
@Table(name="Campo")
public class Campo {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name = "id", unique = true, nullable = true) 
	private int id_campo;
	
	@Column
	private int id_bodega;
	
	@OneToMany 
    @JoinColumn(name = "campo_id")
	private List<Vid> vids;

	public Campo() {}
	public Campo(int id_bodega) {
		this.id_bodega = id_bodega;
		vids = new ArrayList<>();
	}
	
	public List<Vid> getVids() {
		return vids;
	}
	public int getId_campo() {
		return id_campo;
	}
	public int getId_bodega() {
		return id_bodega;
	}
	
	

	
	
	
}
