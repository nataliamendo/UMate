package EAProject.UMate.transaction;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class ComentarioT {

	private int idComent;
	private Date fecha;
	
	private String cText;
	
	PisoT piso;
	
	UsuarioT usu;

	
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



	public PisoT getPiso() {
		return piso;
	}

	public void setPiso(PisoT piso) {
		this.piso = piso;
	}

	public UsuarioT getUsu() {
		return usu;
	}

	public void setUsu(UsuarioT usu) {
		this.usu = usu;
	}

}
