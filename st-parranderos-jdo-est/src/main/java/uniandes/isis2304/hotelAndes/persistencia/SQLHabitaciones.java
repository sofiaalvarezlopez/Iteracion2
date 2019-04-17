package uniandes.isis2304.hotelAndes.persistencia;

import java.math.BigDecimal;
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
	
	public Long adicionarHabitacion(PersistenceManager pm, Long numHabitacion, Long idTipoHabitaciones) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO HABITACIONES"  + "(NUMEROHABITACION, TIPOHABITACION) VALUES (?, ?)");
		q.setParameters(numHabitacion, idTipoHabitaciones);
		return (Long) q.executeUnique();
	}
	
	public Long eliminarHabitacion(PersistenceManager pm, Long numHabitacion){
		Query q = pm.newQuery(SQL, "DELETE FROM HABITACIONES" + " WHERE NUMEROHABITACION = ?");
		q.setParameters(numHabitacion);
		return (Long) q.executeUnique();
	}
	
	public Habitaciones darHabitacionPorNumeroHabitacion (PersistenceManager pm, Long numHabitacion) 
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
	
	public Long darTipoHabitacionDeHabitacion(PersistenceManager pm, Long numHabitacion){
		Query q = pm.newQuery(SQL, "SELECT TIPOHABITACION FROM HABITACIONES" + " WHERE NUMEROHABITACION = ?");
		q.setParameters(numHabitacion);
		return ((BigDecimal) q.executeUnique()).longValue();
	}
	
	
}
