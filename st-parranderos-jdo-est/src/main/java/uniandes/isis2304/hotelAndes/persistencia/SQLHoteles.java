package uniandes.isis2304.hotelAndes.persistencia;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Hoteles;


public class SQLHoteles {

	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLHoteles(PersistenciaHotelAndes pha){
		this.pha = pha;
	}

	public long adicionarHotel(PersistenceManager pm, long idHotel, String nombre, String direccion, String ciudad, int estrellas, long idCadenaHotelera) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO HOTELES"  + "(IDHOTEL, NOMBREHOTEL, DIRECCION, CIUDAD, ESTRELLAS, IDCADENAHOTELERA) VALUES (?, ?, ?, ?, ?, ?)");
		q.setParameters(idHotel, nombre, direccion, ciudad, estrellas, idCadenaHotelera);
		return (long) q.executeUnique();
	}

	public long eliminarHotel(PersistenceManager pm, long idHotel){
		Query q = pm.newQuery(SQL, "DELETE FROM HOTELES" + " WHERE IDHOTEL = ?");
		q.setParameters(idHotel);
		return (long) q.executeUnique();
	}
	
	public Hoteles darHotelPorId (PersistenceManager pm, long idHotel) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM HOTELES" + " WHERE IDHOTEL = ?");
		q.setResultClass(Hoteles.class);
		q.setParameters(idHotel);
		return (Hoteles) q.executeUnique();
	}

	public List<Hoteles> darHoteles (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM HOTELES");
		q.setResultClass(Hoteles.class);
		return (List<Hoteles>) q.executeList();
	}





}
