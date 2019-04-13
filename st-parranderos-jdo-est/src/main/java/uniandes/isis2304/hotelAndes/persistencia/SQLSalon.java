package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Salon;


public class SQLSalon {

	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLSalon(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarSalon(PersistenceManager pm, long idServicio, int capacidad, double costoPorHora) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO SALONES"  + "(ID_SERVICIO, CAPACIDAD, COSTO_POR_HORA) VALUES (?, ?, ?)");
		q.setParameters(idServicio, capacidad, costoPorHora);
		return (long) q.executeUnique();
	}
	
	public long eliminarSalon(PersistenceManager pm, long idServicio){
		Query q = pm.newQuery(SQL, "DELETE FROM SALONES" + " WHERE ID_SERVICIO = ?");
		q.setParameters(idServicio);
		return (long) q.executeUnique();
	}
	
	public Salon darSalonPorId (PersistenceManager pm, long idSalon) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM SALONES" + " WHERE ID_SERVICIO = ?");
		q.setResultClass(Salon.class);
		q.setParameters(idSalon);
		return (Salon) q.executeUnique();
	}

	public List<Salon> darSalones (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM SALONES");
		q.setResultClass(Salon.class);
		return (List<Salon>) q.executeList();
	}
	
	
}
