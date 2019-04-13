package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.TipoHabitacion;


public class SQLTipoHabitacion {
	
	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLTipoHabitacion(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarTipoHabitacion(PersistenceManager pm, long idTipoHabitacion, String descripcion, int capacidad, int precioNoche, long idHotel) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO TIPOS_HABITACION"  + "(ID_TIPO_HABITACION, DESCRIPCION, CAPACIDAD, PRECIO_POR_NOCHE, ID_HOTEL) VALUES (?, ?, ?, ?, ?)");
		q.setParameters(idTipoHabitacion, descripcion, capacidad, precioNoche, idHotel);
		return (long) q.executeUnique();
	}
	
	public long eliminarTipoHabitacion(PersistenceManager pm, long idTipoHabitacion){
		Query q = pm.newQuery(SQL, "DELETE FROM TIPOS_HABITACION" + " WHERE ID_TIPO_HABITACION = ?");
		q.setParameters(idTipoHabitacion);
		return (long) q.executeUnique();
	}
	
	public TipoHabitacion darTipoHabitacionPorId(PersistenceManager pm, long idTipoHabitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM TIPOS_HABITACION" + " WHERE ID_TIPO_HABITACION = ?");
		q.setResultClass(TipoHabitacion.class);
		q.setParameters(idTipoHabitacion);
		return (TipoHabitacion) q.executeUnique();
	}
	
	public List<TipoHabitacion> darTiposHabitacion (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM TIPOS_HABITACION");
		q.setResultClass(TipoHabitacion.class);
		return (List<TipoHabitacion>) q.executeList();
	}


}
