package uniandes.isis2304.hotelAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Facturas;
import uniandes.isis2304.hotelAndes.negocio.VOFacturas;

public class SQLFacturas {
	
	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLFacturas(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarFactura(PersistenceManager pm, long numFactura, Timestamp fecha, int pagada, double precio, long idDotacion, long idServicio, long idEstadia, long numDocEmpleado, long idConsumo, long idConvencion, long idDotacionSalon) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO FACTURAS"  + "(NUMFACTURA, FECHA, FUEPAGADA, PRECIO, IDDOTACION, IDSERVICIO, IDESTADIA, NUMDOCEMPLEADO, IDPRODUCTO, IDCONVENCION, IDDOTACIONSALON) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(numFactura, fecha, pagada, precio, idDotacion, idServicio, idEstadia, numDocEmpleado, idConsumo, idConvencion, idDotacionSalon);
		return (long) q.executeUnique();
	}
	
	public long eliminarFactura(PersistenceManager pm, long idFactura){
		Query q = pm.newQuery(SQL, "DELETE FROM FACTURAS" + " WHERE NUMFACTURA = ?");
		q.setParameters(idFactura);
		return (long) q.executeUnique();
	}
	
	public Facturas darFacturaPorId (PersistenceManager pm, long idFactura) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM FACTURAS" + " WHERE NUMFACTURA = ?");
		q.setResultClass(Facturas.class);
		q.setParameters(idFactura);
		return (Facturas) q.executeUnique();
	}
	
	//public Facturas darFacturaPorIdCliente

	public List<Facturas> darFacturas(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM FACTURAS");
		q.setResultClass(Facturas.class);
		return (List<Facturas>) q.executeList();
	}
	
	public long cambiarAPagada (PersistenceManager pm, long idFactura) 
	{
		 Query q = pm.newQuery(SQL, "UPDATE FACTURAS" + " SET FUEPAGADA = 1 WHERE NUMFACTURA = ?");
	     q.setParameters(idFactura);
	     return (long) q.executeUnique();            
	}
	
}
