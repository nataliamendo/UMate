package EAProject.UMate.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idusuario")
	private int idusuario;
	
	@Column(name = "psswordE")
	private String psswordE;
	
	@Column(name = "telefE")
	private String telefE;
	
	@Column(name = "mail")
	private String mail;
	
	@Column(name = "nombreE")
	private String nombreE;
	
	@Column(name="salt")
	private String salt;
	
	
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	@ManyToMany(mappedBy="listUsuarios", fetch = FetchType.LAZY)
	@Column(name = "favCasas")
	List<Piso> favPisos = new ArrayList<Piso>(); //Lista de casas favoritas
	
	//Comentarios:
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)	
	List<Comentario> listComentariosU=new ArrayList<Comentario>();
	
	//Lista de Mis pisos
	@OneToMany(fetch = FetchType.LAZY)
	List<Piso> misPisos = new ArrayList<Piso>();
	
	public List<Piso> getMisPisos() {
		return misPisos;
	}
	public void setMisPisos(List<Piso> misPisos) {
		this.misPisos = misPisos;
	}
	public boolean addMiPiso(Piso e) {
		return misPisos.add(e);
	}
	public boolean removeMiPiso(Object o) {
		return misPisos.remove(o);
	}
	public boolean add(Comentario e) {
		return listComentariosU.add(e);
	}
	public Comentario remove(int index) {
		return listComentariosU.remove(index);
	}
	public int getIdusuario() {
		return idusuario;
	}
	public void setIdusuario(int idusuario) {
		this.idusuario = idusuario;
	}
	public List<Piso> getFavPisos() {
		return favPisos;
	}
	public void setFavPisos(List<Piso> favPisos) {
		this.favPisos = favPisos;
	}
	public List<Comentario> getListComentariosU() {
		return listComentariosU;
	}
	public void setListComentariosU(List<Comentario> listComentariosU) {
		this.listComentariosU = listComentariosU;
	}
	public boolean addFav(Piso e) {
		return favPisos.add(e);
	}
	public boolean removeFave(Object o) {
		return favPisos.remove(o);
	}

	public int getIdUsuario() {
		return idusuario;
	}
	public void setIdUsuario(int idusuario) {
		this.idusuario = idusuario;
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
