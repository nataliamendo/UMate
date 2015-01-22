package EAProject.UMate;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.List;

import EAProject.UMate.model.Comentario;
import EAProject.UMate.model.Piso;
import EAProject.UMate.model.Usuario;
import EAProject.UMate.transaction.Mensaje;
import EAProject.UMate.transaction.PisoT;

public interface IOperacionesBD {

	String addPiso(Piso c);

	String addUsuario(Usuario es);

	String addPisoToUsuario(Usuario es, Piso c);

	public void addCommentOfUserToPiso(Comentario c, Piso p, Usuario u);

	public void addPiso(Piso p, Usuario U);

	public Piso crearpiso(int numhab, double precio, int numba,
			boolean piscina, String calle, boolean jardin, boolean cocina,
			boolean garaje, String localizacion, int dimension, double latitud,
			double longitud) throws UnsupportedEncodingException,
			MalformedURLException;

	// Funcionalidad obtener piso por su identificador junto con coments
	public Piso getAllFromPiso(int idPiso);

	public Usuario getUsuarioById(int idUser);

	public List<Piso> listarFav(int userid);

	public List<Piso> ListarPisos();

	public void addFav(int idpiso, int userid);

	public Usuario getUsuByName(String name);

	public Piso getPisoById(int idPiso);

	public List<Usuario> getListUsuFromPiso(int pisoid);

	public List<Piso> Bylocalizacion(String localizacion) throws Exception;

	public List<Piso> Byhabiatciones();

	public List<Piso> Bysuperficie();

	public List<Piso> ByPrecio();

	public List<Piso> ByCalle(String calle) throws Exception;

	public List<Comentario> getListaCFromPiso(int idPiso);

	public void EliminarPiso(Piso p, int userid) throws Exception;

	public List<Piso> cargarPiso(Usuario u);

	public List<Comentario> getListaCFromUsuario(int idUsuario);

	public List<Piso> getMisPisosFromUsuario(int userid);

	public Usuario getAllfromUsuario(int idUser);

	public void updatePiso(Piso p);

	public double addLatitud(String ciudad, String calle)
			throws UnsupportedEncodingException, MalformedURLException;

	public double addLongitud(String ciudad, String calle)
			throws UnsupportedEncodingException, MalformedURLException;

	public void removeFav(int idpiso, int userid);

	public List<Comentario> cargarComentariosUsu(Usuario u);

	public List<Piso> listarMisPisos(String username);

	public void updateUsuario(Usuario u);

	public Mensaje isFav(int idPiso, int userid);

	public Comentario getComentarioById(int idComent);

	public List<Comentario> removeComent(int idComent, int idUsuario, int idPiso)
			throws Exception;

}
