package uniandes.isis2304.hotelAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Plan;


public class SQLPlan {
	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLPlan(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarPlan(PersistenceManager pm, long idPlan, String tipo, double costo, double descuentoAlojamiento, Timestamp fecha) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO PLANES"  + "(ID_PLAN, TIPO, COSTO, DESCUENTO_ALOJAMIENTO, FECHA_VENCIMIENTO) VALUES (?, ?, ?, ?, ?)");
		q.setParameters(idPlan, tipo, costo, descuentoAlojamiento, fecha);
		return (long) q.executeUnique();
	}

	
	public long eliminarPlan(PersistenceManager pm, long idPlan){
		Query q = pm.newQuery(SQL, "DELETE FROM PLANES" + " WHERE ID_PLAN = ?");
		q.setParameters(idPlan);
		return (long) q.executeUnique();
	}
	
	public Plan darPlanesPorId (PersistenceManager pm, long idPlan) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM PLANES" + " WHERE ID_PLAN = ?");
		q.setResultClass(Plan.class);
		q.setParameters(idPlan);
		return (Plan) q.executeUnique();
	}

	public List<Plan> darPlanes (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM PLANES");
		q.setResultClass(Plan.class);
		return (List<Plan>) q.executeList();
	}
	
	
}
