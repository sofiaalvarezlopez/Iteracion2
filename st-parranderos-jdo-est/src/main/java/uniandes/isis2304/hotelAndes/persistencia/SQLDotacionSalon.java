package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.DotacionSalon;

public class SQLDotacionSalon {
	
	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLDotacionSalon(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarDotacionSalon(PersistenceManager pm, long idDotacion, String nombre, double valor, long idSalon) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO DOTACIONSALON"  + "(IDDOTACION, NOMBRE, VALOR, IDSALON) VALUES (?, ?, ?, ?)");
		q.setParameters(idDotacion, nombre, valor, idSalon);
		return (long) q.executeUnique();
	}
	
	public long eliminarDotacionSalon(PersistenceManager pm, long idDotacion){
		Query q = pm.newQuery(SQL, "DELETE FROM DOTACIONSALON" + " WHERE IDDOTACION = ?");
		q.setParameters(idDotacion);
		return (long) q.executeUnique();
	}
	
	public DotacionSalon darDotacionSalonPorId (PersistenceManager pm, long idDotacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM DOTACIONSALON" + " WHERE IDDOTACION = ?");
		q.setResultClass(DotacionSalon.class);
		q.setParameters(idDotacion);
		return (DotacionSalon) q.executeUnique();
	}

	public List<DotacionSalon> darDotacionSalones (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM DOTACIONSALON");
		q.setResultClass(DotacionSalon.class);
		return (List<DotacionSalon>) q.executeList();
	}

}
