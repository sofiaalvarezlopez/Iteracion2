package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Salones;


public class SQLSalones {

	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLSalones(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarSalon(PersistenceManager pm, long idServicio, int capacidad, double costoPorHora, String tipo) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO SALONES"  + "(IDSERVICIO, CAPACIDAD, COSTOPORHORA, TIPO) VALUES (?, ?, ?, ?)");
		q.setParameters(idServicio, capacidad, costoPorHora, tipo);
		return (long) q.executeUnique();
	}
	
	public long eliminarSalon(PersistenceManager pm, long idServicio){
		Query q = pm.newQuery(SQL, "DELETE FROM SALONES" + " WHERE IDSERVICIO = ?");
		q.setParameters(idServicio);
		return (long) q.executeUnique();
	}
	
	public Salones darSalonPorId (PersistenceManager pm, long idSalon) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM SALONES" + " WHERE IDSERVICIO = ?");
		q.setResultClass(Salones.class);
		q.setParameters(idSalon);
		return (Salones) q.executeUnique();
	}

	public List<Salones> darSalones (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM SALONES");
		q.setResultClass(Salones.class);
		return (List<Salones>) q.executeList();
	}
	
	
}
