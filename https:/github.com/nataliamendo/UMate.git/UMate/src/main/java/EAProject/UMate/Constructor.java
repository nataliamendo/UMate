package EAProject.UMate;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

import EAProject.UMate.model.Comentario;
import EAProject.UMate.model.Piso;
import EAProject.UMate.model.Usuario;
import EAProject.UMate.security.SHA1;


public class Constructor {
	public static void main (String []args) throws IOException, NoSuchAlgorithmException{
		IOperacionesBD io = OperacionesBD.getInstance();
		
		int i = 0;
		while(i<4)
		{
			Piso c = new Piso();
			c.setCocina(true);
			c.setDimension(60+i);
			c.setGaraje(true);
			c.setNum_ba(1+i);
			c.setJardin(true);
			c.setNum_hab(2+i);
			c.setLocalizacion("Barcelona");
			c.setPiscina(false);
			c.setPrecio(800.0);
			c.setCalle("Calle"+i);	
			c.setLatitud(41.35985);
			c.setLongitud(2.1459);
			io.addPiso(c);
			
			Usuario es  = new Usuario();
			es.setMail("usuario"+i+"@gmail.com");
			es.setNombreE("usuario"+i);
			
			//la password pasa por la función de hash
			SHA1 sha1 = new SHA1();
			String salt = sha1.getSalt();
			es.setPsswordE(sha1.get_SHA_1_SecurePassword("password",salt));
			es.setSalt(salt);
			es.setTelefE("62345678"+i);
			
			c.setCreador(es);
			
			io.addUsuario(es);
			io.addPisoToUsuario(es, c);
			
			Comentario co = new Comentario();
			co.setcText("Comentario"+i);
			co.setUsu(es);
			co.setPiso(c);
			Date ahora = new Date();
			co.setFecha(ahora);
			
			io.addCommentOfUserToPiso(co, c, es);
			
			i++;
			
		}
		
		
	}
	
}
