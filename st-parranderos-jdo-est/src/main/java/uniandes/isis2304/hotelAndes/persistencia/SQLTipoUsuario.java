package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.TipoUsuario;


public class SQLTipoUsuario {
	
	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLTipoUsuario(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarTipoUsuario(PersistenceManager pm, long idTipoUsuario, String nombreTipoUsuario) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO TIPOUSUARIO"  + "(IDTIPOUSUARIO, NOMBRETIPOUSUARIO) VALUES (?, ?)");
		q.setParameters(idTipoUsuario, nombreTipoUsuario);
		return (long) q.executeUnique();
	}
	
	public long eliminarTipoUsuario(PersistenceManager pm, long idTipoUsuario){
		Query q = pm.newQuery(SQL, "DELETE FROM TIPOUSUARIO" + " WHERE IDTIPOUSUARIO = ?");
		q.setParameters(idTipoUsuario);
		return (long) q.executeUnique();
	}
	
	public TipoUsuario darTipoUsuarioPorId (PersistenceManager pm, long idTipoUsuario) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM TIPOUSUARIO" + " WHERE IDTIPOUSUARIO = ?");
		q.setResultClass(TipoUsuario.class);
		q.setParameters(idTipoUsuario);
		return (TipoUsuario) q.executeUnique();
	}

	public List<TipoUsuario> darTiposUsuario (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM TIPOUSUARIO");
		q.setResultClass(TipoUsuario.class);
		return (List<TipoUsuario>) q.executeList();
	}
	

	

}
