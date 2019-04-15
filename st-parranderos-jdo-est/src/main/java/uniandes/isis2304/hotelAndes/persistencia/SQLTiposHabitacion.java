package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.TiposHabitacion;


public class SQLTiposHabitacion {
	
	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLTiposHabitacion(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarTipoHabitacion(PersistenceManager pm, long idTipoHabitacion, String descripcion, int capacidad, int precioNoche, long idHotel) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO TIPOSHABITACION"  + "(IDTIPOHABITACION, DESCRIPCION, CAPACIDAD, PRECIOPORNOCHE, IDHOTEL) VALUES (?, ?, ?, ?, ?)");
		q.setParameters(idTipoHabitacion, descripcion, capacidad, precioNoche, idHotel);
		return (long) q.executeUnique();
	}
	
	public long eliminarTipoHabitacion(PersistenceManager pm, long idTipoHabitacion){
		Query q = pm.newQuery(SQL, "DELETE FROM TIPOSHABITACION" + " WHERE IDTIPOHABITACION = ?");
		q.setParameters(idTipoHabitacion);
		return (long) q.executeUnique();
	}
	
	public TiposHabitacion darTipoHabitacionPorId(PersistenceManager pm, long idTipoHabitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM TIPOSHABITACION" + " WHERE IDTIPOHABITACION = ?");
		q.setResultClass(TiposHabitacion.class);
		q.setParameters(idTipoHabitacion);
		return (TiposHabitacion) q.executeUnique();
	}
	
	public List<TiposHabitacion> darTiposHabitacion (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM TIPOSHABITACION");
		q.setResultClass(TiposHabitacion.class);
		return (List<TiposHabitacion>) q.executeList();
	}


}
