package EAProject.UMate.transaction;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;

import EAProject.UMate.model.Comentario;
import EAProject.UMate.model.Usuario;

public class PisoT {
	int idpiso;

	int num_hab;

	int num_ba;

	boolean cocina;

	boolean garaje;

	boolean jardin;

	int dimension;

	boolean piscina;

	String calle;

	Usuario creador;

	double precio;

	String localizacion;

	List<Usuario> listUsuarios = new ArrayList<Usuario>();

	List<Comentario> listComentariosP = new ArrayList<Comentario>();

	double longitud;

	public List<Comentario> getListComentariosP() {
		return listComentariosP;
	}

	public double getLongitud() {
		return longitud;
	}

	public double getLatitud() {
		return latitud;
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

	double latitud;

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

	public Usuario getCreador() {
		return creador;
	}

	public void setCreador(Usuario creador) {
		this.creador = creador;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}

	public int getDimension() {
		return dimension;
	}

	public void setDimension(int dimension) {
		this.dimension = dimension;
	}

}
