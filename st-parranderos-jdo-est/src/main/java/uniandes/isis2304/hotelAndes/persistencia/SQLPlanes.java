package uniandes.isis2304.hotelAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Planes;


public class SQLPlanes {
	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLPlanes(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarPlan(PersistenceManager pm, long idPlan, String tipo, double costo, double descuentoAlojamiento, Timestamp fecha) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO PLANES"  + "(IDPLAN, TIPO, COSTO, DESCUENTOALOJAMIENTO, FECHAVENCIMIENTO) VALUES (?, ?, ?, ?, ?)");
		q.setParameters(idPlan, tipo, costo, descuentoAlojamiento, fecha);
		return (long) q.executeUnique();
	}

	
	public long eliminarPlan(PersistenceManager pm, long idPlan){
		Query q = pm.newQuery(SQL, "DELETE FROM PLANES" + " WHERE IDPLAN = ?");
		q.setParameters(idPlan);
		return (long) q.executeUnique();
	}
	
	public Planes darPlanesPorId (PersistenceManager pm, long idPlan) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM PLANES" + " WHERE IDPLAN = ?");
		q.setResultClass(Planes.class);
		q.setParameters(idPlan);
		return (Planes) q.executeUnique();
	}

	public List<Planes> darPlanes (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM PLANES");
		q.setResultClass(Planes.class);
		return (List<Planes>) q.executeList();
	}
	
	
}
