package uniandes.isis2304.hotelAndes.persistencia;

import java.math.BigDecimal;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.TipoUsuario;
import uniandes.isis2304.hotelAndes.negocio.Usuario;

public class SQLUsuario {
	
	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLUsuario(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarUsuario(PersistenceManager pm, long numeroDocumento, String tipoDocumento, String nombre, String correoElectronico, long idTipoUsuario) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO USUARIOS"  + "(NUMERO_DOCUMENTO, TIPO_DOCUMENTO, NOMBRE, CORREO_ELECTRONICO, ID_TIPO_USUARIO) VALUES (?, ?, ?, ?, ?)");
		q.setParameters(numeroDocumento, tipoDocumento, nombre, correoElectronico, idTipoUsuario);
		return (long) q.executeUnique();
	}
	
	public long eliminarUsuario(PersistenceManager pm, long idUsuario, String tipoDoc){
		Query q = pm.newQuery(SQL, "DELETE FROM USUARIOS" + " WHERE NUMERO_DOCUMENTO = ? AND TIPO_DOCUMENTO = ?");
		q.setParameters(idUsuario, tipoDoc);
		return (long) q.executeUnique();
	}
	
	public Usuario darUsuarioPorId (PersistenceManager pm, long idUsuario, String tipoDoc) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM USUARIOS" + " WHERE NUMERO_DOCUMENTO = ? AND TIPO_DOCUMENTO = ?");
		q.setResultClass(Usuario.class);
		q.setParameters(idUsuario, tipoDoc);
		return (Usuario) q.executeUnique();
	}

	public List<Usuario> darUsuarios(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM USUARIOS");
		q.setResultClass(Usuario.class);
		return (List<Usuario>) q.executeList();
	}
	
	public Usuario darUsuarioPorCedula(PersistenceManager pm, String cedula) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM USUARIOS" + " WHERE NUMERO_DOCUMENTO = ?");
		q.setResultClass(Usuario.class);
		q.setParameters(new BigDecimal(cedula));
		Usuario u = (Usuario) q.executeUnique();
		return u ;   
	}

}
