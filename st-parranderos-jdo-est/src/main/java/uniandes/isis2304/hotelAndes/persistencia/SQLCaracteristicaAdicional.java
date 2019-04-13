package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.CaracteristicaAdicional;

public class SQLCaracteristicaAdicional {
	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLCaracteristicaAdicional(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarCaracteristicaAdicional(PersistenceManager pm, long idCaracteristicaAdicional, String nombre, double valor, long idServicioAdicional) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO CARACTERISTICAS_ADICIONALES"  + "(ID_CARACTERISTICA_ADICIONAL, NOMBRE, VALOR, ID_SERVICIO_ADICIONAL) VALUES (?, ?, ?, ?)");
		q.setParameters(idCaracteristicaAdicional, nombre, valor, idServicioAdicional);
		return (long) q.executeUnique();
	}
	
	public long eliminarCaracteristicaAdicional(PersistenceManager pm, long idCaracteristicaAdicional){
		Query q = pm.newQuery(SQL, "DELETE FROM CARACTERISTICAS_ADICIONALES" + " WHERE ID_CARACTERISTICA_ADICIONAL = ?");
		q.setParameters(idCaracteristicaAdicional);
		return (long) q.executeUnique();
	}
	
	public CaracteristicaAdicional darCaracteristicaAdicionalPorId (PersistenceManager pm, long idCaracteristicaAdicional) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM CARACTERISTICAS_ADICIONALES" + " WHERE ID_CARACTERISTICA_ADICIONAL = ?");
		q.setResultClass(CaracteristicaAdicional.class);
		q.setParameters(idCaracteristicaAdicional);
		return (CaracteristicaAdicional) q.executeUnique();
	}

	public List<CaracteristicaAdicional> darCaracteristicasAdicionales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM CARACTERISTICAS_ADICIONALES");
		q.setResultClass(CaracteristicaAdicional.class);
		return (List<CaracteristicaAdicional>) q.executeList();
	}

	
	
}
