package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Productos;

public class SQLProductos {
	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLProductos(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarProducto(PersistenceManager pm, long idProducto, double precio, String nombre, int cantidad, int duracion, String categoria, long idVentaProducto) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO PRODUCTOS"  + "(IDPRODUCTOS, PRECIO, NOMBRE, CANTIDAD, DURACION, CATEGORIA, IDVENTAPRODUCTO) VALUES (?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(idProducto, precio, nombre, cantidad, duracion, categoria, idVentaProducto);
		return (long) q.executeUnique();
	}
	
	public long eliminarProducto(PersistenceManager pm, long idProducto){
		Query q = pm.newQuery(SQL, "DELETE FROM PRODUCTOS" + " WHERE IDPRODUCTOS = ?");
		q.setParameters(idProducto);
		return (long) q.executeUnique();
	}
	
	public Productos darProductoPorId (PersistenceManager pm, long idProducto) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM PRODUCTOS" + " WHERE IDPRODUCTOS = ?");
		q.setResultClass(Productos.class);
		q.setParameters(idProducto);
		return (Productos) q.executeUnique();
	}

	public List<Productos> darProductos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM PRODUCTOS");
		q.setResultClass(Productos.class);
		return (List<Productos>) q.executeList();
	}

}
