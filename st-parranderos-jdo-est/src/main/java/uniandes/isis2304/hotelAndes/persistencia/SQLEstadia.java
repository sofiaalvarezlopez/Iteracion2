package uniandes.isis2304.hotelAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Estadia;

public class SQLEstadia {

	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLEstadia(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarEstadia(PersistenceManager pm, long idEstadia, Timestamp fechaLlegada, Timestamp fechaSalida, int numPersonas, long idPlan, long idHabitacion, int checkIn, int pago, String tipoDoc, long numDoc) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO ESTADIAS"  + "(ID_ESTADIA, FECHA_LLEGADA, FECHA_SALIDA, NUM_PERSONAS, ID_PLAN, ID_HABITACION, CHECK_IN, PAGO, FK_USER_TIPODOC, FK_USER_NUM) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(idEstadia, fechaLlegada, fechaSalida, numPersonas, idPlan, idHabitacion, checkIn, pago, tipoDoc, numDoc);
		return (long) q.executeUnique();
	}
	
	public long eliminarEstadia(PersistenceManager pm, long idServicio){
		Query q = pm.newQuery(SQL, "DELETE FROM ESTADIAS" + " WHERE ID_ESTADIA = ?");
		q.setParameters(idServicio);
		return (long) q.executeUnique();
	}
	
	public Estadia darEstadiaPorId (PersistenceManager pm, long idEstadia) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM ESTADIAS" + " WHERE ID_ESTADIA = ?");
		q.setResultClass(Estadia.class);
		q.setParameters(idEstadia);
		return (Estadia) q.executeUnique();
	}

	public List<Estadia> darEstadias (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM ESTADIAS");
		q.setResultClass(Estadia.class);
		return (List<Estadia>) q.executeList();
	}
	
	public long cambiarAPagada (PersistenceManager pm, long idEstadia) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE ESTADIAS" + " SET PAGO = 1 WHERE ID_ESTADIA = ?");
	     q.setParameters(idEstadia);
	     return (long) q.executeUnique();            
	}
	
	public long checkInCliente(PersistenceManager pm, long idEstadia){
		 Query q = pm.newQuery(SQL, "UPDATE ESTADIAS" + " SET CHECK_IN = 1 WHERE ID_ESTADIA = ?");
	     q.setParameters(idEstadia);
	     return (long) q.executeUnique();       
	}
	

}
