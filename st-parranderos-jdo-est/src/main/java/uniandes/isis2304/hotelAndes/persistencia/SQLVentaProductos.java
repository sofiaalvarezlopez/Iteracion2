package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.VentaProductos;


public class SQLVentaProductos {
	
	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLVentaProductos(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarVentaProducto(PersistenceManager pm, long idServicio, int capacidad, String estilo, String tipo) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO VENTAPRODUCTOS"  + "(IDSERVICIO, CAPACIDAD, ESTILO, TIPO) VALUES (?, ?, ?, ?)");
		q.setParameters(idServicio, capacidad, estilo, tipo);
		return (long) q.executeUnique();
	}
	
	public long eliminarVentaProducto(PersistenceManager pm, long idServicio){
		Query q = pm.newQuery(SQL, "DELETE FROM VENTAPRODUCTOS" + " WHERE IDSERVICIO = ?");
		q.setParameters(idServicio);
		return (long) q.executeUnique();
	}
	
	public VentaProductos darVentaProductoPorId (PersistenceManager pm, long idVentaProducto) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM VENTAPRODUCTOS" + " WHERE IDSERVICIO = ?");
		q.setResultClass(VentaProductos.class);
		q.setParameters(idVentaProducto);
		return (VentaProductos) q.executeUnique();
	}

	public List<VentaProductos> darVentasProducto (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM VENTAPRODUCTOS");
		q.setResultClass(VentaProductos.class);
		return (List<VentaProductos>) q.executeList();
	}

}
