package uniandes.isis2304.hotelAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Estadias;

public class SQLEstadias {

	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLEstadias(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarEstadia(PersistenceManager pm, long idEstadia, Timestamp fechaLlegada, Timestamp fechaSalida, int numPersonas, long idPlan, long idHabitacion, int checkIn, int pago, long numDoc, long idConvencion) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO ESTADIAS"  + "(IDESTADIA, FECHALLEGADA, FECHASALIDA, NUMEROPERSONAS, IDPLAN, IDHABITACION, CHECKIN, PAGADO, IDCLIENTE, IDCONVENCION) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(idEstadia, fechaLlegada, fechaSalida, numPersonas, idPlan, idHabitacion, checkIn, pago, numDoc, idConvencion);
		return (long) q.executeUnique();
	}
	
	public long eliminarEstadia(PersistenceManager pm, long idServicio){
		Query q = pm.newQuery(SQL, "DELETE FROM ESTADIAS" + " WHERE IDESTADIA = ?");
		q.setParameters(idServicio);
		return (long) q.executeUnique();
	}
	
	public Estadias buscarEstadiaPorId(PersistenceManager pm, long id){
		Query q = pm.newQuery(SQL, "SELECT * FROM ESTADIAS" + " WHERE IDESTADIA = ?");
		q.setResultClass(Estadias.class);
		q.setParameters(id);
		return (Estadias) q.executeUnique();

	}

	public List<Estadias> darEstadias (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM ESTADIAS");
		q.setResultClass(Estadias.class);
		return (List<Estadias>) q.executeList();
	}
	
	public long cambiarAPagada (PersistenceManager pm, long idEstadia) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE ESTADIAS" + " SET PAGADO = 1 WHERE IDESTADIA = ?");
	     q.setParameters(idEstadia);
	     return (long) q.executeUnique();            
	}
	
	public long checkInCliente(PersistenceManager pm, long idEstadia){
		 Query q = pm.newQuery(SQL, "UPDATE ESTADIAS" + " SET CHECKIN = 1 WHERE IDESTADIA = ?");
	     q.setParameters(idEstadia);
	     return (long) q.executeUnique();       
	}
	

}
