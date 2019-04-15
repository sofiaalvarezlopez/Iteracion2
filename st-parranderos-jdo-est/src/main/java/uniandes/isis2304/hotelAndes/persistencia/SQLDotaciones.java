package uniandes.isis2304.hotelAndes.persistencia;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Dotaciones;


public class SQLDotaciones {
	
	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLDotaciones(PersistenciaHotelAndes pha){
		this.pha = pha;
	}

	public long adicionarDotacion(PersistenceManager pm, long idDotacion, String nombre, double precio, long idTipoHabitacion) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO DOTACIONES"  + "(IDDOTACION, NOMBRE, PRECIO, IDTIPOHABITACION) VALUES (?, ?, ?, ?)");
		q.setParameters(idDotacion, nombre, precio, idTipoHabitacion);
		return (long) q.executeUnique();
	}
	
	public long eliminarDotacion(PersistenceManager pm, long idDotacion){
		Query q = pm.newQuery(SQL, "DELETE FROM DOTACIONES" + " WHERE IDDOTACION = ?");
		q.setParameters(idDotacion);
		return (long) q.executeUnique();
	}
	
	public Dotaciones darDotacionesPorId (PersistenceManager pm, long idDotacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM DOTACIONES" + " WHERE IDDOTACION = ?");
		q.setResultClass(Dotaciones.class);
		q.setParameters(idDotacion);
		return (Dotaciones) q.executeUnique();
	}

	public List<Dotaciones> darDotaciones (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM DOTACIONES");
		q.setResultClass(Dotaciones.class);
		return (List<Dotaciones>) q.executeList();
	}
	
	
}
