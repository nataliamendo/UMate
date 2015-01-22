package EAProject.UMate;

import java.awt.geom.Point2D;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import maps.java.Geocoding;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import EAProject.UMate.model.Comentario;
import EAProject.UMate.model.Piso;
import EAProject.UMate.model.Usuario;
import EAProject.UMate.transaction.Mensaje;

public class OperacionesBD implements IOperacionesBD {

	private static OperacionesBD instance = null;

	public static OperacionesBD getInstance() {
		if (instance == null)
			instance = new OperacionesBD();

		return instance;

	}

	// añadir usuario

	public String addPiso(Piso c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			session.save(c);
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	public List<Piso> listarFav(int userid) {
		List<Piso> listafav = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			/*
			 * Query query = session .createSQLQuery(
			 * "select * from Piso as p inner join p.listUsuarios as u where u.idusuario= :userid"
			 * ) .addEntity(Piso.class) .setParameter("userid", userid);
			 * listafav = query.list();
			 */
			listafav = session.createQuery(
					"from Piso as p inner join fetch p.listUsuarios as u where u.idusuario='"
							+ userid + "'").list();
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return listafav;
	}

	public List<Piso> cargarPiso(Usuario u) {
		List<Piso> pisos = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			Query query = session
					.createSQLQuery(
							"select * from Piso p where p.creador = :creador")
					.addEntity(Piso.class)
					.setParameter("creador", u.getIdusuario());
			pisos = query.list();

			/*
			 * pisos = session.createQuery( "from Piso where creador = '" +
			 * u.getIdusuario() + "'") .list();
			 */

			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return pisos;
	}

	public List<Comentario> cargarComentariosUsu(Usuario u) {
		List<Comentario> lc = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			Query query = session
					.createSQLQuery(
							"select *  from Comentario c where c.usu_idusuario = :creador")
					.addEntity(Comentario.class)
					.setParameter("creador", u.getIdusuario());
			lc = query.list();
			/*
			 * lc = session.createQuery(
			 * "from Comentario where usu_idusuario = '" + u.getIdusuario() +
			 * "'").list();
			 */
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return lc;
	}

	public void addPiso(Piso p, Usuario U) {

		// U.addMiPiso(p);
		// p.setCreador(U);
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			// Usuario u = this.getUsuarioById(U.getIdusuario());
			System.out.println("************PISO: " + p.getIdpiso());
			List<Piso> lp = this.cargarPiso(U);

			List<Comentario> lcu = this.cargarComentariosUsu(U);

			p.setListComentarios(null);
			p.setListUsuarios(null);
			p.setCreador(U);

			lp.add(p);
			U.setListComentariosU(lcu);
			U.setMisPisos(lp);

			session.save(p);

			session.update(U);
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public Piso crearpiso(int numhab, double precio, int numba,
			boolean piscina, String calle, boolean jardin, boolean cocina,
			boolean garaje, String localizacion, int dimension, double latitud,
			double longitud) throws UnsupportedEncodingException,
			MalformedURLException {

		Piso p = new Piso();
		p.setPiscina(piscina);
		p.setCalle(calle);

		p.setPrecio(precio);
		p.setCocina(cocina);
		p.setDimension(dimension);
		p.setGaraje(garaje);
		p.setJardin(jardin);
		p.setLocalizacion(localizacion);
		p.setNum_ba(numba);
		p.setNum_hab(numhab);

		double latitud1 = addLatitud(localizacion, calle);
		p.setLatitud(latitud1);

		double longitud1 = addLongitud(localizacion, calle);

		p.setLongitud(longitud1);

		// p.setLatitud(addLatitud(localizacion, calle));
		// p.setLongitud(addLongitud(localizacion, calle));

		return p;
	}

	public String addUsuario(Usuario es) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.save(es);
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	// public String addPisoToUsuario(Usuario es, Piso c) {
	// Session session = HibernateUtil.getSessionFactory().openSession();
	// Transaction transaction = null;
	//
	// try {
	// transaction = session.beginTransaction();
	// es.getFavCasas().add(c);
	// c.getListUsuarios().add(es);
	// session.update(c);
	// session.update(es);
	// transaction.commit();
	//
	// } catch (HibernateException e) {
	// transaction.rollback();
	// e.printStackTrace();
	// } finally {
	// session.close();
	// }
	// return null;
	// }

	public String addPisoToUsuario(Usuario es, Piso c) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			es.getMisPisos().add(c);
			c.getListUsuarios().add(es);
			session.update(c);
			session.update(es);
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	public void addCommentOfUserToPiso(Comentario c, Piso p, Usuario u) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			p.setListComentarios(this.getListaCFromPiso(p.getIdpiso()));
			p.getListComentarios().add(c);

			// añadimos los usuarios del piso
			List<Usuario> listu = this.getListUsuFromPiso(p.getIdpiso());
			p.setListUsuarios(listu);

			session.update(p);

			// p.setListComentarios(this.getListaCFromPiso(p.getIdpiso()));
			// lp.add(c);
			// p.getListComentarios().add(c);

			session.save(c);

			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		u.setListComentariosU(this.cargarComentariosUsu(u));
		this.updateUsuario(u);
	}

	public void updateUsuario(Usuario u) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			session.update(u);
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public List<Usuario> getListUsuFromPiso(int pisoid) {
		List<Usuario> lu = new ArrayList<Usuario>();

		List<Usuario> allU = this.getAllUsuarios();

		// 1. Obtenemos la lista de pisos de un usuario
		int iu = 1;

		while (iu < allU.size()) {
			// obtenemos lista de pisos del usuario iu
			List<Piso> lp = this.listarFav(iu);

			// ahora comprobamos si algún piso de la lp coincide con pisoid
			int ip = 0;
			while (ip < lp.size()) {

				if (lp.get(ip).getIdpiso() == pisoid) {
					// si coincipde, ese usuario hay que añadirlo a la lista de
					// usuarios:
					Usuario u = this.getUsuarioById(iu);
					lu.add(u);
				}

				ip++;
			}

			iu++;

		}

		return lu;
	}

	public List<Usuario> getAllUsuarios() {
		List<Usuario> lu = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			lu = session.createQuery("from Usuario").list();

			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return lu;
	}

	// *Obtener un piso por su identificador y los comentarios asociados
	public Piso getAllFromPiso(int idPiso) {
		Piso p = new Piso();
		p = this.getPisoById(idPiso);
		p.setListComentarios(this.getListaCFromPiso(idPiso));
		p.setListUsuarios(this.getListUsuFromPiso(idPiso));
		// check por consola:
		System.out.println("PISO: " + p.getIdpiso());
		for (int i = 0; i < p.getListComentarios().size(); i++) {
			System.out.println("Comentario " + i + ": "
					+ p.getListComentarios().get(i).getcText());
		}

		// Obtener el usuario
		Usuario u = this.getUsuarioById(p.getCreador().getIdusuario());
		p.setCreador(u);

		System.out.println("USUARIO CREADOR: " + p.getCreador().getNombreE());

		return p;
	}

	public Usuario getUsuarioById(int idUser) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		Usuario u = new Usuario();
		try {
			transaction = session.beginTransaction();
			/*
			 * Query query = session.createSQLQuery(
			 * "select s.stock_code from stock s where s.stock_code = :stockCode"
			 * ) .setParameter("stockCode", "7277"); List result = query.list();
			 */
			
			Query query = session.createSQLQuery(
					"select * from Usuario where idusuario = :userid")
					.addEntity(Usuario.class)
					.setParameter("userid", idUser);
					u = (Usuario) query.uniqueResult();
			
		/*	Query query = session
					.createQuery("from Usuario where idusuario = '" + idUser
							+ "'");
			u = (Usuario) query.uniqueResult();*/
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return u;
	}

	// *Obtener piso por su identificador
	public Piso getPisoById(int idPiso) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		Piso p = new Piso();
		try {
			transaction = session.beginTransaction();

			Query query = session.createSQLQuery(
					"select * from Piso where idpiso = :pisoid")
					.addEntity(Piso.class)
					.setParameter("pisoid", idPiso);
					p = (Piso) query.uniqueResult();
			
			/*Query query = session.createQuery("from Piso where idpiso = ' "
					+ idPiso + "'");
			p = (Piso) query.uniqueResult();*/
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return p;
	}

	// *Obtener la lista de comentarios de un piso
	public List<Comentario> getListaCFromPiso(int idPiso) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		List<Comentario> lc = new ArrayList<Comentario>();
		Comentario c = new Comentario();
		c.setcText("");
		try {
			transaction = session.beginTransaction();

			lc = session.createQuery(
					"from Comentario where piso_idpiso = '" + idPiso + "'")
					.list();

			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		if (lc.size() == 0)
			lc.add(c);
		return lc;
	}

	// Listar Comentarios de un usuario

	public List<Comentario> getListaCFromUsuario(int idUsuario) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		List<Comentario> lc = new ArrayList<Comentario>();

		try {
			transaction = session.beginTransaction();

			lc = session
					.createQuery(
							"from Comentario where usu_idusuario = '"
									+ idUsuario + "'").list();

			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return lc;
	}

	// Listar Pisos
	public List<Piso> ListarPisos() {
		List<Piso> listaDePisos = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			listaDePisos = session.createQuery("From Piso").list();
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}
		System.out.println("Los pisos son los siguientes:");

		return listaDePisos;

	}

	// public List<Piso> listarFav(int userid) {
	// List<Piso> lpf = new ArrayList<Piso>();
	//
	// Session session = HibernateUtil.getSessionFactory().openSession();
	// Transaction transaction = null;
	// try {
	// transaction = session.beginTransaction();
	// Usuario u = getUsuarioById(userid);
	// lpf = u.getFavCasas();
	//
	// transaction.commit();
	//
	// } catch (HibernateException e) {
	// transaction.rollback();
	// e.printStackTrace();
	// }
	// System.out.println("Los pisos son los siguientes:");
	//
	// return lpf;
	//
	// }

	public void addFav(int idpiso, int userid) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			Usuario u = this.getAllfromUsuario(userid);
			Piso P = this.getAllFromPiso(idpiso);
			List<Piso> lfav = this.listarFav(userid);

			lfav.add(P);
			u.setFavPisos(lfav);
			// u.setListComentariosU(this.cargarComentariosUsu(u));
			// u.setMisPisos(this.cargarPiso(u));

			session.update(u);

			List<Usuario> Prefer = this.getListUsuFromPiso(P.getIdpiso());
			Prefer.add(u);
			P.setListUsuarios(Prefer);

			session.update(P);
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	public Usuario getUsuByName(String name) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		Usuario u = new Usuario();
		try {
			transaction = session.beginTransaction();

			Query query = session.createSQLQuery(
					"select * from Usuario where nombreE = :nombre")
					.addEntity(Usuario.class)
					.setParameter("nombre", name);
					u = (Usuario) query.uniqueResult();
			
			
			/*Query query = session.createQuery("from Usuario where nombreE = '"
					+ name + "'");
			u = (Usuario) query.uniqueResult();*/
			transaction.commit();
			u.setFavPisos(null);
			u.setMisPisos(null);
			u.setListComentariosU(null);
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return u;
	}

	// buscar piso por la localiacion
	public List<Piso> Bylocalizacion(String localizacion) throws Exception {
		List<Piso> lpB = new ArrayList<Piso>();
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			lpB = session.createQuery(
					"from Piso where localizacion = '" + localizacion + "'")
					.list();
			transaction.commit();
			if (lpB.isEmpty()) {
				throw new Exception("NO HAY NINGUN PISO EN ESTA LOCALIDAD");
			}
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return lpB;

	}

	// ordenar por numero de habitaciones
	public List<Piso> Byhabiatciones() {
		List<Piso> lpB = new ArrayList<Piso>();
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			lpB = session.createQuery("from Piso order by num_hab").list();
			transaction.commit();
			if (lpB.isEmpty()) {

			}
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return lpB;
	}

	// Ordenar por superficie
	/*
	 * public List<Piso> Bysuperficie(int min, int max) throws Exception{
	 * List<Piso> lpB= new ArrayList<Piso>(); Session session =
	 * HibernateUtil.getSessionFactory().openSession();
	 * 
	 * Transaction transaction = null;
	 * 
	 * try { transaction = session.beginTransaction();
	 * 
	 * lpB = session.createQuery("from Piso where dimension < " + max +
	 * " and dimension >" + min).list(); transaction.commit();
	 * if(lpB.isEmpty()){ throw new
	 * Exception("NO HAY NINGUN PISO CON ESTAS MEDIDAS"); } } catch
	 * (HibernateException e) { transaction.rollback(); e.printStackTrace(); }
	 * finally { session.close(); } return lpB; }
	 */

	public List<Piso> Bysuperficie() {
		List<Piso> lpB = new ArrayList<Piso>();
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			lpB = session.createQuery("from Piso order by dimension").list();
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return lpB;
	}

	// *Ordenar by Precio

	public List<Piso> ByPrecio() {
		List<Piso> lpB = new ArrayList<Piso>();
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			lpB = session.createQuery("from Piso order by precio").list();
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return lpB;
	}

	public List<Piso> ByCalle(String calle) throws Exception {
		List<Piso> lpB = new ArrayList<Piso>();
		Session session = HibernateUtil.getSessionFactory().openSession();

		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			
			Query query = session.createSQLQuery(
					"select * from Piso where calle like '% " + calle + "%'")
					.addEntity(Piso.class);
					//.setParameter("calle", calle);
					List result = query.list();
					
			
			/*lpB = session.createQuery(
					"from Piso where calle like '% :creador%'").list();*/
			transaction.commit();
			if (lpB.isEmpty()) {
				throw new Exception("NO HAY NINGUN PISO EN ESTA CALLE");
			}
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return lpB;
	}

	public void EliminarPiso(Piso p, int userid) throws Exception{
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		transaction = session.beginTransaction();
		try {

			Usuario usu = p.getCreador();
			if (usu.getIdUsuario() != userid) throw new Exception ("El usuario no es el que ha credo el piso");
			// List <Piso> lp = cargarPiso(usu);
			// lp.remove(p);

			List<Piso> lp = cargarPiso(usu);
			List<Piso> lpT = lp;
			// Iterator<Piso> it = lp.iterator();
			int i = 0;
			while (i < lp.size()) {
				Piso np = lp.get(i);
				if (np.getIdpiso() == p.getIdpiso()) {
					lpT.remove(np);
					System.out.println("removed " + np);
				}
				i++;
			}

			usu.setMisPisos(lpT);

			session.update(usu);
			session.delete(p);
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	// Obtener pisos creados por usuario

	public List<Piso> getMisPisosFromUsuario(int userid) {
		List<Piso> listaDePisos = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			
			Query query = session.createSQLQuery(
					"select * from Piso where creador = :creador")
					.addEntity(Piso.class)
					.setParameter("creador", userid);
					List result = query.list();
			
			/*listaDePisos = session.createQuery(
					"From Piso where creador = " + userid).list();*/
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}
		System.out.println("Los pisos son los siguientes:");

		return listaDePisos;

	}

	// Obtener todo del usuario

	public Usuario getAllfromUsuario(int idUser) {

		Usuario u = this.getUsuarioById(idUser);
		List<Piso> favpisos = this.listarFav(idUser);
		u.setFavPisos(favpisos);
		List<Piso> MisPisos = this.getMisPisosFromUsuario(idUser);
		u.setMisPisos(MisPisos);
		// List <Comentario> Coments= this.getListaCFromUsuario(idUser);
		// u.setListComentariosU(Coments);
		return u;
	}

	public void updatePiso(Piso p) {

		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();

			session.update(p);
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	// remove from fav

	public void removeFav(int idpiso, int userid) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			Usuario u = this.getAllfromUsuario(userid);
			Piso P = this.getAllFromPiso(idpiso);
			List<Piso> lfav = this.listarFav(userid);

			List<Piso> lpP = lfav;
			// Iterator<Piso> it = lp.iterator();
			int i = 0;
			while (i < lfav.size()) {
				Piso np = lfav.get(i);
				if (np.getIdpiso() == idpiso) {
					lpP.remove(np);

				}
				i++;
			}
			u.setFavPisos(lpP);
			// u.setListComentariosU(this.cargarComentariosUsu(u));
			// u.setMisPisos(this.cargarPiso(u));
			session.update(u);

			// Parte del Piso o Lista de usuarios
			List<Usuario> Prefer = this.getListUsuFromPiso(P.getIdpiso());
			// Prefer.remove(u);

			List<Usuario> lpT = Prefer;
			// Iterator<Piso> it = lp.iterator();
			i = 0;
			while (i < Prefer.size()) {
				Usuario np = Prefer.get(i);
				if (np.getIdusuario() == userid) {
					lpT.remove(np);

				}
				i++;
			}

			P.setListUsuarios(lpT);

			session.update(P);
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

	}

	// obtener lista de pisos de un usuairo:
	public List<Piso> listarMisPisos(String username) {
		List<Piso> lista = this.ListarPisos();
		List<Piso> misPisosLista = new ArrayList<Piso>();
		Usuario u = this.getUsuByName(username);

		for (int i = 0; i < lista.size(); i++) {
			if (lista.get(i).getCreador().getIdUsuario() == u.getIdUsuario()) {
				misPisosLista.add(lista.get(i));
			}

		}

		return misPisosLista;
	}

	public Mensaje isFav(int idPiso, int userid) {
		Mensaje m = new Mensaje();
		boolean found = false;
		List<Piso> lfav = this.listarFav(userid);

		int i = 0;
		if (lfav.size() > 0) {
			while (i < lfav.size() && !found) {
				Piso np = lfav.get(i);
				if (np.getIdpiso() == idPiso) {
					found = true;
				}
				i++;
			}
		}
		if (found)
			m.setMessage("SI");
		else
			m.setMessage("NO");
		return m;
	}

	public List<Comentario> removeComent(int idComent, int idPiso, int idUsuario)
			throws Exception {
		List<Comentario> lc = null;
		List<Comentario> lc2 = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;
		transaction = session.beginTransaction();
		try {

			lc = this.getListaCFromUsuario(idUsuario);
			lc2 = this.getListaCFromPiso(idPiso);
			Usuario u = this.getAllfromUsuario(idUsuario);
			Comentario c = this.getComentarioById(idComent);
			Piso p = this.getAllFromPiso(idPiso);
			if (c.getUsu().getIdUsuario() != idUsuario)
				throw new Exception(
						"Este no es el usuario que ha creado el comentario");

			List<Comentario> lcT = lc;
			List<Comentario> lcT2 = lc2;
			// Iterator<Piso> it = lp.iterator();
			int i = 0;
			while (i < lc.size()) {

				if (idComent == lc.get(i).getIdComent()) {
					lcT.remove(i);

					System.out.println("removed ");
				} else if (idComent == lc2.get(i).getIdComent()) {
					lcT2.remove(i);

					System.out.println("removed 2");
				}
				i++;
			}

			u.setListComentariosU(lcT);
			p.setListComentarios(lcT);
			session.update(u);
			session.update(p);
			session.delete(c);
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}

		return lc;

	}

	// Obtener un comentario

	public Comentario getComentarioById(int idComent) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction transaction = null;

		Comentario c = new Comentario();
		try {
			transaction = session.beginTransaction();

			Query query = session
					.createSQLQuery(
							"select * from comentario c where c.idComent= :idComent")
					.addEntity(Comentario.class)
					.setParameter("idComent", idComent);
			c = (Comentario) query.uniqueResult();

			/*
			 * Query query = session.createQuery("from Piso where idpiso = ' " +
			 * idC + "'"); p = (Piso) query.uniqueResult();
			 */
			transaction.commit();

		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return c;
	}

	// Funciones de coordenadas

	public double addLongitud(String ciudad, String calle)
			throws UnsupportedEncodingException, MalformedURLException {
		Geocoding ObjGeocod = new Geocoding();
		Point2D.Double resultadoCD = ObjGeocod.getCoordinates(ciudad + ","
				+ calle);
		return resultadoCD.y;
	}

	public double addLatitud(String ciudad, String calle)
			throws UnsupportedEncodingException, MalformedURLException {
		Geocoding ObjGeocod = new Geocoding();
		Point2D.Double resultadoCD = ObjGeocod.getCoordinates(ciudad + ","
				+ calle);
		return resultadoCD.x;
	}

}
