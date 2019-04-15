package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.ServiciosAdicionales;


public class SQLServiciosAdicionales {
	
	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLServiciosAdicionales(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarServicioAdicional(PersistenceManager pm, long idServicio, int capacidad) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO SERVICIOSADICIONALES"  + "(IDSERVICIO, CAPACIDAD) VALUES (?, ?)");
		q.setParameters(idServicio, capacidad);
		return (long) q.executeUnique();
	}
	
	public long eliminarServicioAdicional(PersistenceManager pm, long idServicio){
		Query q = pm.newQuery(SQL, "DELETE FROM SERVICIOSADICIONALES" + " WHERE IDSERVICIO = ?");
		q.setParameters(idServicio);
		return (long) q.executeUnique();
	}
	
	public ServiciosAdicionales darServicioAdicionalPorId (PersistenceManager pm, long idServicioAdicional) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM SERVICIOSADICIONALES" + " WHERE IDSERVICIO = ?");
		q.setResultClass(ServiciosAdicionales.class);
		q.setParameters(idServicioAdicional);
		return (ServiciosAdicionales) q.executeUnique();
	}

	public List<ServiciosAdicionales> darServiciosAdicionales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM SERVICIOSADICIONALES");
		q.setResultClass(ServiciosAdicionales.class);
		return (List<ServiciosAdicionales>) q.executeList();
	}

}
