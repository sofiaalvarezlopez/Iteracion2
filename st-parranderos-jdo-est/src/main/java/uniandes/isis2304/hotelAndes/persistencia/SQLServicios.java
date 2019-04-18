package uniandes.isis2304.hotelAndes.persistencia;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Servicios;


public class SQLServicios {
	
	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLServicios(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarServicio(PersistenceManager pm, Long idServicio, String nombreServicio) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO SERVICIOS"  + "(IDSERVICIO, NOMBRESERVICIO) VALUES (?, ?)");
		q.setParameters(idServicio, nombreServicio);
		return (long) q.executeUnique();
	}
	
	public long eliminarServicio(PersistenceManager pm, Long idServicio){
		Query q = pm.newQuery(SQL, "DELETE FROM SERVICIOS" + " WHERE IDSERVICIO = ?");
		q.setParameters(idServicio);
		return (long) q.executeUnique();
	}
	
	public Servicios darServicioPorId (PersistenceManager pm, Long idServicio) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM SERVICIOS" + " WHERE IDSERVICIO = ?");
		q.setResultClass(Servicios.class);
		q.setParameters(idServicio);
		return (Servicios) q.executeUnique();
	}

	public List<Servicios> darServicios (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM SERVICIOS");
		q.setResultClass(Servicios.class);
		return (List<Servicios>) q.executeList();
	}
	

}
