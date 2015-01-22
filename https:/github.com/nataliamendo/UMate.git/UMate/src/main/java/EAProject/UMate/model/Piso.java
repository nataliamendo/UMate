package EAProject.UMate.model;

import java.awt.geom.Point2D;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import maps.java.Geocoding;

@Entity
public class Piso {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "idpiso")
	int idpiso;

	@Column(name = "num_hab")
	int num_hab;

	@Column(name = "num_ba")
	int num_ba;

	// @Type(type="boolean")
	@Column(name = "cocina")
	boolean cocina;

	@Column(name = "piscina")
	boolean piscina;

	@Column(name = "calle")
	String calle;

	@Column(name = "precio")
	double precio;

	@Column(name = "garaje")
	boolean garaje;

	@Column(name = "jardin")
	boolean jardin;

	@Column(name = "dimension")
	int dimension;

	@Column(name = "localizacion")
	String localizacion;

	@Column(name = "longitud")
	double longitud;

	@Column(name = "latitud")
	double latitud;

	@ManyToOne
	@JoinColumn(insertable = true, updatable = true, name = "creador")
	Usuario creador;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinTable(name = "Piso_Usuario", joinColumns = { @JoinColumn(referencedColumnName = "idpiso") }, inverseJoinColumns = { @JoinColumn(referencedColumnName = "idusuario") })
	List<Usuario> listUsuarios = new ArrayList<Usuario>();

	// Comentarios:
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	List<Comentario> listComentariosP = new ArrayList<Comentario>();

	public boolean add(Comentario e) {
		return listComentariosP.add(e);
	}

	public Comentario remove(int index) {
		return listComentariosP.remove(index);
	}

	public int getIdpiso() {
		return idpiso;
	}

	public void setIdpiso(int idpiso) {
		this.idpiso = idpiso;
	}

	public List<Comentario> getListComentarios() {
		return listComentariosP;
	}

	public void setListComentarios(List<Comentario> listComentarios) {
		this.listComentariosP = listComentarios;
	}

	public boolean add(Usuario e) {
		return listUsuarios.add(e);
	}

	public boolean remove(Object o) {
		return listUsuarios.remove(o);
	}

	public List<Usuario> getListUsuarios() {
		return listUsuarios;
	}

	public void setListUsuarios(List<Usuario> listEstudiantes) {
		this.listUsuarios = listEstudiantes;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public int getNum_hab() {
		return num_hab;
	}

	public void setNum_hab(int num_hab) {
		this.num_hab = num_hab;
	}

	public int getNum_ba() {
		return num_ba;
	}

	public void setNum_ba(int num_ba) {
		this.num_ba = num_ba;
	}

	public boolean getCocina() {
		return cocina;
	}

	public void setCocina(boolean cocina) {
		this.cocina = cocina;
	}

	public boolean isGaraje() {
		return garaje;
	}

	public void setGaraje(boolean garaje) {
		this.garaje = garaje;
	}

	public boolean isJardin() {
		return jardin;
	}

	public void setJardin(boolean jardin) {
		this.jardin = jardin;
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

	public boolean getPiscina() {
		return piscina;
	}

	public void setPiscina(boolean piscina) {
		this.piscina = piscina;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public Usuario getCreador() {
		return creador;
	}

	public void setCreador(Usuario creador) {
		this.creador = creador;
	}

	public double getLongitud() {
		return longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public List<Comentario> getListComentariosP() {
		return listComentariosP;
	}

	public void setListComentariosP(List<Comentario> listComentariosP) {
		this.listComentariosP = listComentariosP;
	}



	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}

	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}

}
