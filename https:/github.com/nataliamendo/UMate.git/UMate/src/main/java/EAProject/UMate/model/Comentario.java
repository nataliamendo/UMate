package EAProject.UMate.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idComent")
	private int idComent;
	
	@Column(name = "fecha")
	private Date fecha;
	
	@Column(name = "cText")
	private String cText;
	
	@ManyToOne
	@JoinColumn( insertable=true, updatable=true)
	Piso piso;
	
	@ManyToOne
	@JoinColumn( insertable=true, updatable=true)
	Usuario usu;

	
	public int getIdComent() {
		return idComent;
	}

	public void setIdComent(int idComent) {
		this.idComent = idComent;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getcText() {
		return cText;
	}

	public void setcText(String cText) {
		this.cText = cText;
	}



	public Piso getPiso() {
		return piso;
	}

	public void setPiso(Piso piso) {
		this.piso = piso;
	}

	public Usuario getUsu() {
		return usu;
	}

	public void setUsu(Usuario usu) {
		this.usu = usu;
	}

}
