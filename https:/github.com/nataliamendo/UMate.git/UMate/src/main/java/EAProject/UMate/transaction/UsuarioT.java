package EAProject.UMate.transaction;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


public class UsuarioT {
	private int idusuario;
	
	private String psswordE;
	
	private String telefE;
	
	private String mail;
	
	private String nombreE;
	
	List<PisoT> favPisos = new ArrayList<PisoT>(); 
	
	List<ComentarioT> listComentariosU=new ArrayList<ComentarioT>();
	
	public boolean add(ComentarioT e) {
		return listComentariosU.add(e);
	}
	public ComentarioT remove(int index) {
		return listComentariosU.remove(index);
	}
	public int getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}
	public List<PisoT> getFavPisos() {
		return favPisos;
	}
	public void setFavPisos(List<PisoT> favPisos) {
		this.favPisos = favPisos;
	}
	public List<ComentarioT> getListComentariosU() {
		return listComentariosU;
	}
	public void setListComentariosU(List<ComentarioT> listComentariosU) {
		this.listComentariosU = listComentariosU;
	}
	public boolean add(PisoT e) {
		return favPisos.add(e);
	}
	public boolean remove(Object o) {
		return favPisos.remove(o);
	}

	public int getIdUsuario() {
		return idusuario;
	}
	public void setIdUsuario(int idusuario) {
		this.idusuario = idusuario;
	}
	public List<PisoT> getFavCasas() {
		return favPisos;
	}
	public void setFavCasas(List<PisoT> favCasas) {
		this.favPisos = favCasas;
	}
	public String getNombreE() {
		return nombreE;
	}
	public void setNombreE(String nombreE) {
		this.nombreE = nombreE;
	}
	public String getPsswordE() {
		return psswordE;
	}
	public void setPsswordE(String psswordE) {
		this.psswordE = psswordE;
	}
	public String getTelefE() {
		return telefE;
	}
	public void setTelefE(String telefE) {
		this.telefE = telefE;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	

}
