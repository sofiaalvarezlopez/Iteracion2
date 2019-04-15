package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Habitaciones;


public class SQLHabitaciones {

	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLHabitaciones(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarHabitacion(PersistenceManager pm, long numHabitacion, long idTipoHabitaciones) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO HABITACIONES"  + "(NUMEROHABITACION, TIPOHABITACION) VALUES (?, ?)");
		q.setParameters(numHabitacion, idTipoHabitaciones);
		return (long) q.executeUnique();
	}
	
	public long eliminarHabitacion(PersistenceManager pm, long numHabitacion){
		Query q = pm.newQuery(SQL, "DELETE FROM HABITACIONES" + " WHERE NUMEROHABITACION = ?");
		q.setParameters(numHabitacion);
		return (long) q.executeUnique();
	}
	
	public Habitaciones darHabitacionPorNumeroHabitacion (PersistenceManager pm, long numHabitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM HABITACIONES" + " WHERE NUMEROHABITACION = ?");
		q.setResultClass(Habitaciones.class);
		q.setParameters(numHabitacion);
		return (Habitaciones) q.executeUnique();
	}

	public List<Habitaciones> darHabitaciones (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM HABITACIONES");
		q.setResultClass(Habitaciones.class);
		return (List<Habitaciones>) q.executeList();
	}
	
	
}
