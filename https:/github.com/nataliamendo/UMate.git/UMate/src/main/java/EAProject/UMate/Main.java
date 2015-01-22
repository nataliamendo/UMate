package EAProject.UMate;

import java.util.List;

import EAProject.UMate.model.Comentario;
import EAProject.UMate.model.Piso;
import EAProject.UMate.model.Usuario;

public class Main {

	public static void main(String[] args) throws Exception {

		IOperacionesBD io = OperacionesBD.getInstance();

		// **Obtener piso por su identificador:
		// io.getAllfromUsuario(1);

		// System.out.println(u.getNombreE());

		// io.addFav(1, 1);
		// System.out.println(io.getListaCFromPiso(1));

		// io.addCommentOfUserToPiso(c, p, u);

		// List <Comentario> lc = io.removeComent(3, 3 ,3);

		/*
		 * Geocoding ObjGeocod=new Geocoding(); Point2D.Double
		 * resultadoCD=ObjGeocod.getCoordinates("Barcelona, Plaza Universitat");
		 * System.out.println( resultadoCD.x + "," + resultadoCD.y);
		 */

		// System.out.println(io.getComentarioById(1).getcText());
		/*Usuario u = io.getUsuByName("usuario0");
		List<Comentario> lp = io.cargarComentariosUsu(u);
		for (int i = 0; i < lp.size(); i++) {
			System.out.println("***" + lp.get(i).getcText());
		}*/

		//Usuario u = io.getUsuarioById(1); 
		Piso p = io.getAllFromPiso(10);
		System.out.println(p.getCalle());
		//p.addLatitud("Madrid", "Plaza Mayor");
		//p.addLongitud("Madrid", "Plaza Mayor");
		
		//System.out.println(p.getLatitud());
		//String cal = "Madrid";
		//String loc = "Plaza Mayor";
		//System.out.println(io.addLatitud(loc, cal));
		
		
		// System.out.println(u.getNombreE());

		// List<Comentario> lp = io.getListaCFromPiso(1);
		// int i=0;
		// while(i<lp.size())
		// {
		// System.out.println("ListFav: " + lp.get(i).getcText());
		// i++;
		// }

		/*
		 * Piso c = new Piso(); c.setCocina(true); c.setDimension(60);
		 * c.setGaraje(true); c.setNum_ba(1); c.setJardin(true);
		 * c.setNum_hab(2); c.setLocalizacion("Barcelona"); c.setPiscina(false);
		 * c.setPrecio(800.0); c.setCalle("C/Calle");
		 * 
		 * io.addPiso(c, u);
		 */

		// io.addPiso(c, es);

		// List<Piso> lp = io.ListarPisos();

		//
		//

	}

}
