package uniandes.isis2304.hotelAndes.persistencia;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Dotacion;


public class SQLDotacion {
	
	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLDotacion(PersistenciaHotelAndes pha){
		this.pha = pha;
	}

	public long adicionarDotacion(PersistenceManager pm, long idDotacion, String nombre, double precio, long idTipoHabitacion) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO DOTACIONES"  + "(ID_DOTACION, NOMBRE, PRECIO, ID_TIPO_HABITACION) VALUES (?, ?, ?, ?)");
		q.setParameters(idDotacion, nombre, precio, idTipoHabitacion);
		return (long) q.executeUnique();
	}
	
	public long eliminarDotacion(PersistenceManager pm, long idDotacion){
		Query q = pm.newQuery(SQL, "DELETE FROM DOTACIONES" + " WHERE ID_DOTACION = ?");
		q.setParameters(idDotacion);
		return (long) q.executeUnique();
	}
	
	public Dotacion darDotacionesPorId (PersistenceManager pm, long idDotacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM DOTACIONES" + " WHERE ID_DOTACION = ?");
		q.setResultClass(Dotacion.class);
		q.setParameters(idDotacion);
		return (Dotacion) q.executeUnique();
	}

	public List<Dotacion> darDotaciones (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM DOTACIONES");
		q.setResultClass(Dotacion.class);
		return (List<Dotacion>) q.executeList();
	}
	
	
}
