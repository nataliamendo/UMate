package EAProject.UMate;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import EAProject.UMate.model.Usuario;


@Path("/login")
public class LoginRegisterResource {
	
	IOperacionesBD io = OperacionesBD.getInstance();
	
	@GET
	@Path("/{name}")
	@Produces(Mediatype.UMATES_USUARIO)
	public Usuario getUsuarioByName(@PathParam("name") String name)
	{
		Usuario p = io.getUsuByName(name);
		
		Usuario pt = new Usuario();
		pt.setPsswordE(p.getPsswordE());
		pt.setIdusuario(p.getIdusuario());
		pt.setSalt(p.getSalt());
		pt.setIdUsuario(p.getIdusuario());
	
		return pt;
	}

	@POST
	@Path("/register")
	@Consumes(Mediatype.UMATES_USUARIO)
	public String nuevoUsuario(Usuario u)
	{
		io.addUsuario(u);
		return null;
	}

}
