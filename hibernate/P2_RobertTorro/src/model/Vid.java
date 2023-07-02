package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Vid")
public class Vid {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name = "id", unique = true, nullable = true) 
	private int id_campo;
	
	@Column
	private int cantidad;
	
	@Column
	private TipoVid tipo_vid;
	
	//private int bodega_id;
	//private int campo_id;

	public Vid() {}
	public Vid(int cantidad, TipoVid tipo_vid) {
		this.cantidad = cantidad;
		this.tipo_vid = tipo_vid;
	}
	@Override
	public String toString() {
		return "Vid [id_campo=" + id_campo + ", cantidad=" + cantidad + ", tipo_vid=" + tipo_vid + "]";
	}
	
	
	
}
