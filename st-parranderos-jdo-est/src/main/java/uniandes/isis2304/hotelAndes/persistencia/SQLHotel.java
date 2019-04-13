package uniandes.isis2304.hotelAndes.persistencia;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Hotel;


public class SQLHotel {

	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLHotel(PersistenciaHotelAndes pha){
		this.pha = pha;
	}

	public long adicionarHotel(PersistenceManager pm, long idHotel, String nombre, String direccion, String ciudad, int estrellas, long idCadenaHotelera) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO HOTELES"  + "(ID_HOTEL, NOMBRE_HOTEL, DIRECCION, CIUDAD, ESTRELLAS, ID_CADENA_HOTELERA) VALUES (?, ?, ?, ?, ?, ?)");
		q.setParameters(idHotel, nombre, direccion, ciudad, estrellas, idCadenaHotelera);
		return (long) q.executeUnique();
	}

	public long eliminarHotel(PersistenceManager pm, long idHotel){
		Query q = pm.newQuery(SQL, "DELETE FROM HOTELES" + " WHERE ID_HOTEL = ?");
		q.setParameters(idHotel);
		return (long) q.executeUnique();
	}
	
	public Hotel darHotelPorId (PersistenceManager pm, long idHotel) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM HOTELES" + " WHERE id = ?");
		q.setResultClass(Hotel.class);
		q.setParameters(idHotel);
		return (Hotel) q.executeUnique();
	}

	public List<Hotel> darHoteles (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM HOTELES");
		q.setResultClass(Hotel.class);
		return (List<Hotel>) q.executeList();
	}





}
