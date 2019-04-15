package uniandes.isis2304.hotelAndes.persistencia;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.CadenasHoteleras;


public class SQLCadenasHoteleras {

	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;
	
	public SQLCadenasHoteleras(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	
	
	public long adicionarCadenaHotelera(PersistenceManager pm, long idCadena, String nombre) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO CADENASHOTELERAS"  + "(IDCADENA, NOMBRECADENA) values (?, ?)");
        q.setParameters(idCadena, nombre);
        return (long) q.executeUnique();
	}
	
	public long eliminarCadenaHotelera (PersistenceManager pm, long idCadena)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM CADENASHOTELERAS" + " WHERE IDCADENA = ?");
        q.setParameters(idCadena);
        return (long) q.executeUnique();
	}
	
	public CadenasHoteleras darCadenaHoteleraPorId (PersistenceManager pm, long idCadena) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM CADENASHOTELERAS" + " WHERE IDCADENA = ?");
		q.setResultClass(CadenasHoteleras.class);
		q.setParameters(idCadena);
		return (CadenasHoteleras) q.executeUnique();
	}
	
	public List<CadenasHoteleras> darCadenasHoteleras (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM CADENASHOTELERAS");
		q.setResultClass(CadenasHoteleras.class);
		return (List<CadenasHoteleras>) q.executeList();
	}
	
	
}
