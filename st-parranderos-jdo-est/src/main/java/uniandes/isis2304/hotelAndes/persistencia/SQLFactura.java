package uniandes.isis2304.hotelAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Factura;

public class SQLFactura {
	
	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLFactura(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarFactura(PersistenceManager pm, long numFactura, Timestamp fecha, int pagada, double precio, long idDotacion, long idServicio, String tipoDocEmpleado, long idEstadia, long numDocEmpleado, long idConsumo) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO FACTURAS"  + "(NUM_FACTURA, FECHA, FUE_PAGADA, PRECIO, ID_DOTACION, ID_SERVICIO, TIPO_DOC_EMPLEADO, ID_ESTADIA, NUM_DOC_EMPLEADO, ID_CONSUMO) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(numFactura, fecha, pagada, precio, idDotacion, idServicio, tipoDocEmpleado, idEstadia, numDocEmpleado, idConsumo);
		return (long) q.executeUnique();
	}
	
	public long eliminarFactura(PersistenceManager pm, long idFactura){
		Query q = pm.newQuery(SQL, "DELETE FROM FACTURAS" + " WHERE NUM_FACTURA = ?");
		q.setParameters(idFactura);
		return (long) q.executeUnique();
	}
	
	public Factura darFacturaPorId (PersistenceManager pm, long idFactura) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM FACTURAS" + " WHERE NUM_FACTURA = ?");
		q.setResultClass(Factura.class);
		q.setParameters(idFactura);
		return (Factura) q.executeUnique();
	}

	public List<Factura> darFacturas(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM FACTURAS");
		q.setResultClass(Factura.class);
		return (List<Factura>) q.executeList();
	}
	
	public long cambiarAPagada (PersistenceManager pm, long idFactura) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE FACTURAS" + " SET FUE_PAGADA = 1 WHERE NUM_FACTURA = ?");
	     q.setParameters(idFactura);
	     return (long) q.executeUnique();            
	}
	
}
