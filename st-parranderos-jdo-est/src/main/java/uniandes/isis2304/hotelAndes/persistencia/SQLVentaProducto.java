package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.VentaProducto;


public class SQLVentaProducto {
	
	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLVentaProducto(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarVentaProducto(PersistenceManager pm, long idServicio, int capacidad, String estilo, String tipo, String nombre) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO VENTA_PRODUCTOS"  + "(ID_SERVICIO, CAPACIDAD, ESTILO, TIPO, NOMBRE) VALUES (?, ?, ?, ?, ?)");
		q.setParameters(idServicio, capacidad, estilo, tipo, nombre);
		return (long) q.executeUnique();
	}
	
	public long eliminarVentaProducto(PersistenceManager pm, long idServicio){
		Query q = pm.newQuery(SQL, "DELETE FROM VENTA_PRODUCTOS" + " WHERE ID_SERVICIO = ?");
		q.setParameters(idServicio);
		return (long) q.executeUnique();
	}
	
	public VentaProducto darVentaProductoPorId (PersistenceManager pm, long idVentaProducto) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM VENTA_PRODUCTOS" + " WHERE ID_SERVICIO = ?");
		q.setResultClass(VentaProducto.class);
		q.setParameters(idVentaProducto);
		return (VentaProducto) q.executeUnique();
	}

	public List<VentaProducto> darVentasProducto (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM VENTA_PRODUCTOS");
		q.setResultClass(VentaProducto.class);
		return (List<VentaProducto>) q.executeList();
	}

}
