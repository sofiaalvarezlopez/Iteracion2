package uniandes.isis2304.hotelAndes.persistencia;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Servicio;


public class SQLServicio {
	
	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLServicio(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarServicio(PersistenceManager pm, long idServicio, String nombreServicio) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO SERVICIOS"  + "(ID_SERVICIO, NOMBRE_SERVICIO) VALUES (?, ?)");
		q.setParameters(idServicio, nombreServicio);
		return (long) q.executeUnique();
	}
	
	public long eliminarServicio(PersistenceManager pm, long idServicio){
		Query q = pm.newQuery(SQL, "DELETE FROM SERVICIOS" + " WHERE ID_SERVICIO = ?");
		q.setParameters(idServicio);
		return (long) q.executeUnique();
	}
	
	public Servicio darServicioPorId (PersistenceManager pm, long idServicio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM SERVICIOS" + " WHERE ID_SERVICIO = ?");
		q.setResultClass(Servicio.class);
		q.setParameters(idServicio);
		return (Servicio) q.executeUnique();
	}

	public List<Servicio> darServicios (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM SERVICIOS");
		q.setResultClass(Servicio.class);
		return (List<Servicio>) q.executeList();
	}
	

}
