package uniandes.isis2304.hotelAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Convenciones;


public class SQLConvenciones {
	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLConvenciones(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarConvencion(PersistenceManager pm, long idConvencion, String nombre, int capacidad, Timestamp fechaInicio, Timestamp fechaFin, long idOrganizador, long idPlan, int pago) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO CONVENCIONES"  + "(IDCONVENCION, NOMBRE, CAPACIDAD, FECHAINICIO, FECHAFIN, IDORGANIZADOR, IDPLAN, PAGO) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(idConvencion, nombre, capacidad, fechaInicio, fechaFin, idOrganizador, idPlan, pago);
		return (long) q.executeUnique();
	}
	
	public long eliminarConvencion(PersistenceManager pm, long idConvencion){
		Query q = pm.newQuery(SQL, "DELETE FROM CONVENCIONES" + " WHERE IDCONVENCION = ?");
		q.setParameters(idConvencion);
		return (long) q.executeUnique();
	}
	
	public Convenciones darConvencionPorId (PersistenceManager pm, long idConvencion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM CONVENCIONES" + " WHERE IDCONVENCION = ?");
		q.setResultClass(Convenciones.class);
		q.setParameters(idConvencion);
		return (Convenciones) q.executeUnique();
	}

	public List<Convenciones> darConvenciones (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM CONVENCIONES");
		q.setResultClass(Convenciones.class);
		return (List<Convenciones>) q.executeList();
	}
	
	public long cambiarAPagada (PersistenceManager pm, long idConvencion) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE CONVENCIONES" + " SET PAGO = 1 WHERE IDCONVENCION = ?");
	     q.setParameters(idConvencion);
	     return (long) q.executeUnique();            
	}


}
