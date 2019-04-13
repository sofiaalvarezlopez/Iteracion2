package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.ServicioAdicional;


public class SQLServicioAdicional {
	
	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLServicioAdicional(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarServicioAdicional(PersistenceManager pm, long idServicio, int capacidad) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO SERVICIOS_ADICIONALES"  + "(ID_SERVICIO, CAPACIDAD) VALUES (?, ?)");
		q.setParameters(idServicio, capacidad);
		return (long) q.executeUnique();
	}
	
	public long eliminarServicioAdicional(PersistenceManager pm, long idServicio){
		Query q = pm.newQuery(SQL, "DELETE FROM SERVICIOS_ADICIONALES" + " WHERE ID_SERVICIO = ?");
		q.setParameters(idServicio);
		return (long) q.executeUnique();
	}
	
	public ServicioAdicional darServicioAdicionalPorId (PersistenceManager pm, long idServicioAdicional) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM SERVICIOS_ADICIONALES" + " WHERE ID_SERVICIO = ?");
		q.setResultClass(ServicioAdicional.class);
		q.setParameters(idServicioAdicional);
		return (ServicioAdicional) q.executeUnique();
	}

	public List<ServicioAdicional> darServiciosAdicionales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM SERVICIOS_ADICIONALES");
		q.setResultClass(ServicioAdicional.class);
		return (List<ServicioAdicional>) q.executeList();
	}

}
