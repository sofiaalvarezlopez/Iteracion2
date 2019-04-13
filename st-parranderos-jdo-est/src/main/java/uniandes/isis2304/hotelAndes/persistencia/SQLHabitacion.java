package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Habitacion;


public class SQLHabitacion {

	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLHabitacion(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarHabitacion(PersistenceManager pm, long numHabitacion, long idTipoHabitaciones) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO HABITACIONES"  + "(NUMERO_HABITACION, TIPO_HABITACION) VALUES (?, ?)");
		q.setParameters(numHabitacion, idTipoHabitaciones);
		return (long) q.executeUnique();
	}
	
	public long eliminarHabitacion(PersistenceManager pm, long numHabitacion){
		Query q = pm.newQuery(SQL, "DELETE FROM HABITACIONES" + " WHERE NUMERO_HABITACION = ?");
		q.setParameters(numHabitacion);
		return (long) q.executeUnique();
	}
	
	public Habitacion darHabitacionPorNumeroHabitacion (PersistenceManager pm, long numHabitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM HABITACIONES" + " WHERE NUMERO_HABITACION = ?");
		q.setResultClass(Habitacion.class);
		q.setParameters(numHabitacion);
		return (Habitacion) q.executeUnique();
	}

	public List<Habitacion> darHabitaciones (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM HABITACIONES");
		q.setResultClass(Habitacion.class);
		return (List<Habitacion>) q.executeList();
	}
	
	
}
