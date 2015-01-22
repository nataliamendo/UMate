package EAProject.UMate;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import EAProject.UMate.model.Comentario;
import EAProject.UMate.model.Piso;
import EAProject.UMate.model.Usuario;
import EAProject.UMate.transaction.ComentarioT;
import EAProject.UMate.transaction.Mensaje;
import EAProject.UMate.transaction.PisoT;

@Path("/umates")
public class PisoResource {

	IOperacionesBD io = OperacionesBD.getInstance();

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String resource() throws UnsupportedEncodingException, MalformedURLException {
		// register(CORSResponseFilter.class);
		
		//System.out.println(io.addLatitud("Madrid", "Plaza Mayor"));
		//System.out.println(io.addLongitud("Madrid", "Plaza Mayor"));
		
		return "UMATES";
	}

	@GET
	@Path("/Listarfav/{userid}")
	@Produces(Mediatype.UMATES_PISO)
	public List<PisoT> getListFavoritos(@PathParam("userid") int userid) {
		List<Piso> listut = io.listarFav(userid);

		for (int i = 0; i < listut.size(); i++) {
			System.out.println("***" + listut.get(i).getIdpiso());
		}

		List<PisoT> ldt = new ArrayList<PisoT>();

		for (int i = 0; i < listut.size(); i++) {

			PisoT dt = new PisoT();
			dt.setCocina(listut.get(i).getCocina());
			dt.setDimension(listut.get(i).getDimension());
			dt.setLocalizacion(listut.get(i).getLocalizacion());
			dt.setIdpiso(listut.get(i).getIdpiso());
			dt.setGaraje(listut.get(i).isGaraje());
			dt.setJardin(listut.get(i).isJardin());
			dt.setPiscina(listut.get(i).getPiscina());
			dt.setCalle(listut.get(i).getCalle());
			dt.setPrecio(listut.get(i).getPrecio());
			dt.setNum_ba(listut.get(i).getNum_ba());
			dt.setNum_hab(listut.get(i).getNum_hab());
			dt.setLatitud(listut.get(i).getLatitud());
			dt.setLongitud(listut.get(i).getLongitud());
			dt.setListComentarios(null);
			dt.setListUsuarios(null);

			ldt.add(dt);
		}
		return ldt;

	}

	@DELETE
	@Path("/borrar/{userid}/{idpiso}")
	public List<PisoT> borrar(@PathParam("idpiso") int idpiso,@PathParam("userid") int userid ) throws Exception {
		Piso p = io.getPisoById(idpiso);
		io.EliminarPiso(p, userid);

		List<Piso> listut = io.ListarPisos();

		for (int i = 0; i < listut.size(); i++) {
			System.out.println("***" + listut.get(i).getIdpiso());
		}

		List<PisoT> ldt = new ArrayList<PisoT>();

		for (int i = 0; i < listut.size(); i++) {

			PisoT dt = new PisoT();

			dt.setCocina(listut.get(i).getCocina());
			dt.setDimension(listut.get(i).getDimension());
			dt.setLocalizacion(listut.get(i).getLocalizacion());
			dt.setIdpiso(listut.get(i).getIdpiso());
			dt.setGaraje(listut.get(i).isGaraje());
			dt.setJardin(listut.get(i).isJardin());
			dt.setPiscina(listut.get(i).getPiscina());
			dt.setCalle(listut.get(i).getCalle());
			dt.setPrecio(listut.get(i).getPrecio());
			dt.setNum_ba(listut.get(i).getNum_ba());
			dt.setNum_hab(listut.get(i).getNum_hab());
			dt.setLatitud(listut.get(i).getLatitud());
			dt.setLongitud(listut.get(i).getLongitud());
			dt.setListComentarios(null);
			dt.setListUsuarios(null);

			ldt.add(dt);
		}
		return ldt;
	}

	@GET
	@Path("/{pisoid}")
	@Produces(Mediatype.UMATES_PISO)
	public PisoT getPisoById(@PathParam("pisoid") int pisoid) {
		PisoT pt = new PisoT();
		Piso p = new Piso();
		try {
			p = io.getAllFromPiso(pisoid);
			System.out.println("PISO: " + p.getCalle() + ", " + p.getIdpiso());

		} catch (Exception e) {
			System.out.println("ERROR. Piso con idPiso " + pisoid
					+ " no se ha encontrado");
		} finally {
			if (p != null) {
				pt.setIdpiso(pisoid);
				pt.setCocina(p.getCocina());
				pt.setDimension(p.getDimension());
				pt.setGaraje(p.isGaraje());
				pt.setJardin(p.isJardin());
				pt.setLocalizacion(p.getLocalizacion());
				pt.setCalle(p.getCalle());
				pt.setPrecio(p.getPrecio());
				pt.setPiscina(p.getPiscina());
				pt.setLatitud(p.getLatitud());
				pt.setLongitud(p.getLongitud());
				pt.setNum_ba(p.getNum_ba());
				pt.setNum_hab(p.getNum_hab());
				pt.setListComentarios(io.getListaCFromPiso(pisoid));

				for (int j = 0; j < pt.getListComentarios().size(); j++) {
					pt.getListComentarios().get(j).setPiso(null);
					pt.getListComentarios().get(j).setUsu(null);
				}
				Usuario u = p.getCreador();
				u.setMisPisos(null);
				u.setFavPisos(null);
				u.setListComentariosU(null);
				pt.setCreador(u);

			}
		}
		return pt;
	}

	@POST
	@Path("/piso/{userid}")
	@Produces(Mediatype.UMATES_PISO)
	@Consumes(Mediatype.UMATES_PISO)
	public void newPiso(Piso pt, @PathParam("userid") int userid) throws UnsupportedEncodingException, MalformedURLException {

		// Piso recibido:
		System.out.println("local" + pt.getCalle());

		Piso p = new Piso();
		
		Usuario u = io.getUsuarioById(userid);
		p = io.crearpiso(pt.getNum_ba(), pt.getPrecio(), pt.getNum_hab(),
				pt.getPiscina(), pt.getCalle(), pt.isJardin(), pt.getCocina(),
				pt.isGaraje(), pt.getLocalizacion(), pt.getDimension(), pt.getLatitud(), pt.getLongitud());
		p.setListComentarios(null);
		p.setListUsuarios(null);
		
		
		//String loc = pt.getLocalizacion().toString();
		//String cal = pt.getCalle().toString();
		
		//System.out.println(io.addLatitud("Madrid", "Plaza Mayor"));
		//System.out.println(io.addLongitud("Madrid", "Plaza Mayor"));
		
		io.addPiso(p, u);

		// return p;

	}

	@GET
	@Path("/ListarPisos")
	@Produces(Mediatype.UMATES_PISO)
	public List<PisoT> getListPisos() {
		List<Piso> listut = io.ListarPisos();

		for (int i = 0; i < listut.size(); i++) {
			System.out.println("***" + listut.get(i).getIdpiso());
		}

		List<PisoT> ldt = new ArrayList<PisoT>();

		for (int i = 0; i < listut.size(); i++) {

			PisoT dt = new PisoT();

			dt.setCocina(listut.get(i).getCocina());
			dt.setDimension(listut.get(i).getDimension());
			dt.setLocalizacion(listut.get(i).getLocalizacion());
			dt.setIdpiso(listut.get(i).getIdpiso());
			dt.setGaraje(listut.get(i).isGaraje());
			dt.setJardin(listut.get(i).isJardin());
			dt.setPiscina(listut.get(i).getPiscina());
			dt.setCalle(listut.get(i).getCalle());
			dt.setPrecio(listut.get(i).getPrecio());
			dt.setNum_ba(listut.get(i).getNum_ba());
			dt.setNum_hab(listut.get(i).getNum_hab());
			dt.setLatitud(listut.get(i).getLatitud());
			dt.setLongitud(listut.get(i).getLongitud());
			dt.setListComentarios(null);
			dt.setListUsuarios(null);

			ldt.add(dt);
		}
		return ldt;

	}

	@GET
	@Path("/mispiso/{name}")
	public List<PisoT> getMisPisosByUsername(@PathParam("name") String name) {
		List<Piso> misPisos = io.listarMisPisos(name);
		List<PisoT> misPisosT = new ArrayList<PisoT>();

		for (int i = 0; i < misPisos.size(); i++) {
			// añadimos la lista de pisos:
			PisoT dt = new PisoT();
			dt.setCocina(misPisos.get(i).getCocina());
			dt.setDimension(misPisos.get(i).getDimension());
			dt.setLocalizacion(misPisos.get(i).getLocalizacion());
			dt.setIdpiso(misPisos.get(i).getIdpiso());
			dt.setGaraje(misPisos.get(i).isGaraje());
			dt.setJardin(misPisos.get(i).isJardin());
			dt.setPiscina(misPisos.get(i).getPiscina());
			dt.setCalle(misPisos.get(i).getCalle());
			dt.setPrecio(misPisos.get(i).getPrecio());
			dt.setNum_ba(misPisos.get(i).getNum_ba());
			dt.setLatitud(misPisos.get(i).getLatitud());
			dt.setLongitud(misPisos.get(i).getLongitud());
			dt.setNum_hab(misPisos.get(i).getNum_hab());
			dt.setListComentarios(null);
			dt.setListUsuarios(null);

			misPisosT.add(dt);

		}
		return misPisosT;
	}

	// GET Perfil usuario
	@GET
	@Path("/user/{name}")
	@Produces(Mediatype.UMATES_USUARIO)
	public Usuario getUsuarioByName(@PathParam("name") String name) {
		Usuario p = io.getUsuByName(name);

		Usuario pt = new Usuario();
		pt.setIdUsuario(p.getIdUsuario());
		pt.setNombreE(p.getNombreE());
		pt.setTelefE(p.getTelefE());
		pt.setMail(p.getMail());

		return pt;
	}

	// AÑADIR COMENTARIO DE UN PISO A LA BASE DE DATOS
	@POST
	@Path("/{pisoid}/comment/{username}")
	@Produces(Mediatype.UMATES_PISO)
	@Consumes(Mediatype.UMATES_COMENTARIO)
	public void nuevoComentario(Comentario c, @PathParam("pisoid") int pisoid,
			@PathParam("username") String un) {
		PisoT pt = new PisoT();

		// Obtenemos el usuario:
		Usuario u = io.getUsuByName(un);
		// u.getListComentariosU().add(c);

		// Obtenemos el piso:
		Piso p = io.getPisoById(pisoid);
		// p.getListComentarios().add(c);

		c.setPiso(p);
		c.setUsu(u);

		io.addCommentOfUserToPiso(c, p, u);

	}

	@GET
	@Path("/ByLocalidad/{localizacion}")
	@Produces(Mediatype.UMATES_PISO)
	public List<PisoT> BuscarByLoc(
			@PathParam("localizacion") String localizacion) throws Exception {
		List<Piso> listut = io.Bylocalizacion(localizacion);

		for (int i = 0; i < listut.size(); i++) {
			System.out.println("***" + listut.get(i).getIdpiso());
		}

		List<PisoT> ldt = new ArrayList<PisoT>();

		for (int i = 0; i < listut.size(); i++) {

			PisoT dt = new PisoT();
			dt.setCocina(listut.get(i).getCocina());
			dt.setDimension(listut.get(i).getDimension());
			dt.setLocalizacion(listut.get(i).getLocalizacion());
			dt.setIdpiso(listut.get(i).getIdpiso());
			dt.setGaraje(listut.get(i).isGaraje());
			dt.setJardin(listut.get(i).isJardin());
			dt.setPiscina(listut.get(i).getPiscina());
			dt.setCalle(listut.get(i).getCalle());
			dt.setPrecio(listut.get(i).getPrecio());
			dt.setNum_ba(listut.get(i).getNum_ba());
			dt.setNum_hab(listut.get(i).getNum_hab());
			dt.setLatitud(listut.get(i).getLatitud());
			dt.setLongitud(listut.get(i).getLongitud());
			dt.setListComentarios(null);
			dt.setListUsuarios(null);

			ldt.add(dt);
		}
		return ldt;

	}

	@GET
	@Path("/ByHabitaciones")
	@Produces(Mediatype.UMATES_PISO)
	public List<PisoT> OrdenarByHab() {
		List<Piso> listut = io.Byhabiatciones();

		for (int i = 0; i < listut.size(); i++) {
			System.out.println("***" + listut.get(i).getIdpiso());
		}

		List<PisoT> ldt = new ArrayList<PisoT>();

		for (int i = 0; i < listut.size(); i++) {

			PisoT dt = new PisoT();
			dt.setCocina(listut.get(i).getCocina());
			dt.setDimension(listut.get(i).getDimension());
			dt.setLocalizacion(listut.get(i).getLocalizacion());
			dt.setIdpiso(listut.get(i).getIdpiso());
			dt.setGaraje(listut.get(i).isGaraje());
			dt.setJardin(listut.get(i).isJardin());
			dt.setPiscina(listut.get(i).getPiscina());
			dt.setCalle(listut.get(i).getCalle());
			dt.setPrecio(listut.get(i).getPrecio());
			dt.setNum_ba(listut.get(i).getNum_ba());
			dt.setNum_hab(listut.get(i).getNum_hab());
			dt.setLatitud(listut.get(i).getLatitud());
			dt.setLongitud(listut.get(i).getLongitud());
			dt.setListComentarios(null);
			dt.setListUsuarios(null);

			ldt.add(dt);
		}
		return ldt;

	}

	@GET
	@Path("/ByPrecio")
	@Produces(Mediatype.UMATES_PISO)
	public List<PisoT> OrdenarByprecio() {
		List<Piso> listut = io.ByPrecio();

		for (int i = 0; i < listut.size(); i++) {
			System.out.println("***" + listut.get(i).getIdpiso());
		}

		List<PisoT> ldt = new ArrayList<PisoT>();

		for (int i = 0; i < listut.size(); i++) {

			PisoT dt = new PisoT();
			dt.setCocina(listut.get(i).getCocina());
			dt.setDimension(listut.get(i).getDimension());
			dt.setLocalizacion(listut.get(i).getLocalizacion());
			dt.setIdpiso(listut.get(i).getIdpiso());
			dt.setGaraje(listut.get(i).isGaraje());
			dt.setJardin(listut.get(i).isJardin());
			dt.setPiscina(listut.get(i).getPiscina());
			dt.setCalle(listut.get(i).getCalle());
			dt.setPrecio(listut.get(i).getPrecio());
			dt.setNum_ba(listut.get(i).getNum_ba());
			dt.setNum_hab(listut.get(i).getNum_hab());
			dt.setLatitud(listut.get(i).getLatitud());
			dt.setLongitud(listut.get(i).getLongitud());
			dt.setListComentarios(null);
			dt.setListUsuarios(null);

			ldt.add(dt);
		}
		return ldt;

	}

	@GET
	@Path("/ByDimension")
	@Produces(Mediatype.UMATES_PISO)
	public List<PisoT> OrdenarBydim() {
		List<Piso> listut = io.Bysuperficie();

		for (int i = 0; i < listut.size(); i++) {
			System.out.println("***" + listut.get(i).getIdpiso());
		}

		List<PisoT> ldt = new ArrayList<PisoT>();

		for (int i = 0; i < listut.size(); i++) {

			PisoT dt = new PisoT();
			dt.setCocina(listut.get(i).getCocina());
			dt.setDimension(listut.get(i).getDimension());
			dt.setLocalizacion(listut.get(i).getLocalizacion());
			dt.setIdpiso(listut.get(i).getIdpiso());
			dt.setGaraje(listut.get(i).isGaraje());
			dt.setJardin(listut.get(i).isJardin());
			dt.setPiscina(listut.get(i).getPiscina());
			dt.setCalle(listut.get(i).getCalle());
			dt.setPrecio(listut.get(i).getPrecio());
			dt.setNum_ba(listut.get(i).getNum_ba());
			dt.setNum_hab(listut.get(i).getNum_hab());
			dt.setLatitud(listut.get(i).getLatitud());
			dt.setLongitud(listut.get(i).getLongitud());
			dt.setListComentarios(null);
			dt.setListUsuarios(null);

			ldt.add(dt);
		}
		return ldt;

	}

	// Obtener usuario a partir del login.
	@GET
	@Path("/BynombrE")
	@Produces(Mediatype.UMATES_USUARIO)
	public Usuario BuscarByNombre(@QueryParam("nombrE") String nombrE) {

		Usuario u = io.getUsuByName(nombrE);

		System.out.println("********" + u.getNombreE());

		return u;
	}

	@GET
	@Path("/ByCalle/{calle}")
	@Produces(Mediatype.UMATES_PISO)
	public List<PisoT> BuscarByCalle(@PathParam("calle") String calle)
			throws Exception {
		List<Piso> listut = io.ByCalle(calle);

		for (int i = 0; i < listut.size(); i++) {
			System.out.println("***" + listut.get(i).getIdpiso());
		}

		List<PisoT> ldt = new ArrayList<PisoT>();

		for (int i = 0; i < listut.size(); i++) {

			PisoT dt = new PisoT();
			dt.setCocina(listut.get(i).getCocina());
			dt.setDimension(listut.get(i).getDimension());
			dt.setLocalizacion(listut.get(i).getLocalizacion());
			dt.setIdpiso(listut.get(i).getIdpiso());
			dt.setGaraje(listut.get(i).isGaraje());
			dt.setJardin(listut.get(i).isJardin());
			dt.setPiscina(listut.get(i).getPiscina());
			dt.setCalle(listut.get(i).getCalle());
			dt.setPrecio(listut.get(i).getPrecio());
			dt.setNum_ba(listut.get(i).getNum_ba());
			dt.setNum_hab(listut.get(i).getNum_hab());
			dt.setLatitud(listut.get(i).getLatitud());
			dt.setLongitud(listut.get(i).getLongitud());
			dt.setListComentarios(null);
			dt.setListUsuarios(null);

			ldt.add(dt);
		}
		return ldt;

	}

	// Añadir a favoritos !!!

	@GET
	@Path("/favpiso/{userid}/{idpiso}")
	// @Produces(Mediatype.UMATES_PISO)
	public void newFav(@PathParam("idpiso") int idpiso,
			@PathParam("userid") int userid) {

		System.out
				.println("***********usuario" + userid + "  pisoid " + idpiso);
		io.addFav(idpiso, userid);

	}

	// Este piso es favorito ???
	@GET
	@Path("/isfav/{userid}/{idpiso}")
	@Produces(Mediatype.UMATES_MENSAJE)
	public Mensaje isFav(@PathParam("idpiso") int idpiso,
			@PathParam("userid") int userid) {

		Mensaje m = io.isFav(idpiso, userid);

		return m;
	}

	@DELETE
	@Path("/removeFav/{userid}/{idpiso}")
	public void removeFav(@PathParam("idpiso") int idpiso,
			@PathParam("userid") int userid) {

		// Piso recibido:

		System.out
				.println("***********usuario" + userid + "  pisoid " + idpiso);
		io.removeFav(idpiso, userid);

		// io.addFavorite(userid, idpiso);

		// Piso p = io.getAllFromPiso(idpiso);
		// /return p;

	}

	@PUT
	@Path("/updateuser/{name}")
	@Consumes(Mediatype.UMATES_USUARIO)
	public void updateUsuPerfil(@PathParam("name") String name, Usuario usu) {
		// primero obtenemos el usuario
		Usuario u = io.getUsuByName(name);
		u.setListComentariosU(io.cargarComentariosUsu(u));
		u.setFavPisos(io.listarFav(u.getIdusuario()));
		u.setMisPisos(io.listarMisPisos(u.getNombreE()));
		if (usu.getMail() != null) {
			u.setMail(usu.getMail());
		}
		if (usu.getTelefE() != null) {
			u.setTelefE(usu.getTelefE());
		}

		io.updateUsuario(u);

	}

	@PUT
	@Path("/updatepiso/{pisoid}")
	@Consumes(Mediatype.UMATES_PISO)
	public void updatePiso(@PathParam("pisoid") int pisoid, Piso pt) {
		// primero obtenemos el usuario
		Piso p = io.getAllFromPiso(pisoid);

		if (pt.getCalle() != null) {
			p.setCalle(pt.getCalle());
		}

		if (pt.getLocalizacion() != null) {
			p.setLocalizacion(pt.getLocalizacion());
		}

		if (pt.getDimension() != 0) {
			p.setDimension(pt.getDimension());
		}

		if (pt.getNum_ba() != 0) {
			p.setNum_ba(pt.getNum_ba());
		}

		if (pt.getNum_hab() != 0) {
			p.setNum_hab(pt.getNum_hab());
		}

		if (pt.getPrecio() != 0) {
			p.setPrecio(pt.getPrecio());
		}

		io.updatePiso(p);
		;

	}

	@DELETE
	@Path("/removeComent/{idUsuario}/{idpiso}/{idComent}")
	public void /* List<ComentarioT>*/ borrarComnetario(@PathParam("idUsuario") int idUsuario,
			@PathParam("idpiso") int idpiso, @PathParam("idComent") int idComent) throws Exception {
		
		List<Comentario> listut  = io.removeComent(idComent, idUsuario, idpiso);

		/*List<Comentario> listut = io.getListaCFromPiso(idpiso);

		List<ComentarioT> lct = new ArrayList<ComentarioT>();

		for (int i = 0; i < listut.size(); i++) {

			ComentarioT ct = new ComentarioT();

			lct.add(ct);
		}
		return lct;*/
	}
	
	
	
}
