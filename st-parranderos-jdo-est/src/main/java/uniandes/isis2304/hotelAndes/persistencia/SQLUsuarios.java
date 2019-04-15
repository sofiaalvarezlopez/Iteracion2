package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Usuarios;

public class SQLUsuarios {
	
	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLUsuarios(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarUsuario(PersistenceManager pm, long numeroDocumento, String tipoDocumento, String nombre, String correoElectronico, long idTipoUsuario) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO USUARIOS"  + "(NUMERODOCUMENTO, TIPODOCUMENTO, NOMBRE, CORREOELECTRONICO, IDTIPOUSUARIO) VALUES (?, ?, ?, ?, ?)");
		q.setParameters(numeroDocumento, tipoDocumento, nombre, correoElectronico, idTipoUsuario);
		return (long) q.executeUnique();
	}
	
	public long eliminarUsuario(PersistenceManager pm, long idUsuario){
		Query q = pm.newQuery(SQL, "DELETE FROM USUARIOS" + " WHERE NUMERODOCUMENTO = ?");
		q.setParameters(idUsuario);
		return (long) q.executeUnique();
	}


	public List<Usuarios> darUsuarios(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM USUARIOS");
		q.setResultClass(Usuarios.class);
		return (List<Usuarios>) q.executeList();
	}
	
	public Usuarios darUsuarioPorCedula(PersistenceManager pm, long cedula) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM Usuarios" + " WHERE numerodocumento = ?");
		q.setResultClass(Usuarios.class);
		q.setParameters(cedula);
		return (Usuarios) q.executeUnique();
  
	}

}
