package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Producto;

public class SQLProducto {
	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLProducto(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarProducto(PersistenceManager pm, long idProducto, double precio, String nombre, int cantidad, int duracion, String categoria, long idVentaProducto) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO PRODUCTOS"  + "(ID_PRODUCTOS, PRECIO, NOMBRE, CANTIDAD, DURACION, CATEGORIA, ID_VENTA_PRODUCTO) VALUES (?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(idProducto, precio, nombre, cantidad, duracion, categoria, idVentaProducto);
		return (long) q.executeUnique();
	}
	
	public long eliminarProducto(PersistenceManager pm, long idProducto){
		Query q = pm.newQuery(SQL, "DELETE FROM PRODUCTOS" + " WHERE ID_PRODUCTOS = ?");
		q.setParameters(idProducto);
		return (long) q.executeUnique();
	}
	
	public Producto darProductoPorId (PersistenceManager pm, long idProducto) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM PRODUCTOS" + " WHERE ID_PRODUCTOS = ?");
		q.setResultClass(Producto.class);
		q.setParameters(idProducto);
		return (Producto) q.executeUnique();
	}

	public List<Producto> darProductos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM PRODUCTOS");
		q.setResultClass(Producto.class);
		return (List<Producto>) q.executeList();
	}

}
