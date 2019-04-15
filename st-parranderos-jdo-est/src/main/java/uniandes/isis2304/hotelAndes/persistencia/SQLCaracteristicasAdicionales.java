package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.CaracteristicasAdicionales;

public class SQLCaracteristicasAdicionales {
	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLCaracteristicasAdicionales(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarCaracteristicaAdicional(PersistenceManager pm, long idCaracteristicaAdicional, String nombre, double valor, long idServicioAdicional) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO CARACTERISTICASADICIONALES"  + "(IDCARACTERISTICAADICIONAL, NOMBRE, VALOR, IDSERVICIOADICIONAL) VALUES (?, ?, ?, ?)");
		q.setParameters(idCaracteristicaAdicional, nombre, valor, idServicioAdicional);
		return (long) q.executeUnique();
	}
	
	public long eliminarCaracteristicaAdicional(PersistenceManager pm, long idCaracteristicaAdicional){
		Query q = pm.newQuery(SQL, "DELETE FROM CARACTERISTICASADICIONALES" + " WHERE IDCARACTERISTICAADICIONAL = ?");
		q.setParameters(idCaracteristicaAdicional);
		return (long) q.executeUnique();
	}
	
	public CaracteristicasAdicionales darCaracteristicaAdicionalPorId (PersistenceManager pm, long idCaracteristicaAdicional) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM CARACTERISTICASADICIONALES" + " WHERE IDCARACTERISTICAADICIONAL = ?");
		q.setResultClass(CaracteristicasAdicionales.class);
		q.setParameters(idCaracteristicaAdicional);
		return (CaracteristicasAdicionales) q.executeUnique();
	}

	public List<CaracteristicasAdicionales> darCaracteristicasAdicionales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM CARACTERISTICASADICIONALES");
		q.setResultClass(CaracteristicasAdicionales.class);
		return (List<CaracteristicasAdicionales>) q.executeList();
	}

	
	
}
