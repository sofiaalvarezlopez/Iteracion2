package uniandes.isis2304.hotelAndes.persistencia;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.CadenaHotelera;


public class SQLCadenaHotelera {

	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;
	
	public SQLCadenaHotelera(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	
	
	public long adicionarCadenaHotelera(PersistenceManager pm, long idCadena, String nombre) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO CADENAS_HOTELERAS"  + "(ID_CADENA, NOMBRE_CADENA) values (?, ?)");
        q.setParameters(idCadena, nombre);
        return (long) q.executeUnique();
	}
	
	public long eliminarCadenaHotelera (PersistenceManager pm, long idCadena)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM CADENAS_HOTELERAS" + " WHERE ID_CADENA = ?");
        q.setParameters(idCadena);
        return (long) q.executeUnique();
	}
	
	public CadenaHotelera darCadenaHoteleraPorId (PersistenceManager pm, long idCadena) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM CADENAS_HOTELERAS" + " WHERE id = ?");
		q.setResultClass(CadenaHotelera.class);
		q.setParameters(idCadena);
		return (CadenaHotelera) q.executeUnique();
	}
	
	public List<CadenaHotelera> darCadenasHoteleras (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM CADENAS_HOTELERAS");
		q.setResultClass(CadenaHotelera.class);
		return (List<CadenaHotelera>) q.executeList();
	}
	
	
}
