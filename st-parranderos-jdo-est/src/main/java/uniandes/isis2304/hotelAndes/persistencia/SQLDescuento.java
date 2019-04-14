package uniandes.isis2304.hotelAndes.persistencia;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Descuento;

public class SQLDescuento {

	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLDescuento(PersistenciaHotelAndes pha){
		this.pha = pha;
	}

	public long adicionarDescuento(PersistenceManager pm, long idDescuento, long idPlan, long idServicio, long idProducto, long valor, int limiteVeces) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO DESCUENTOS"  + "(ID_DESCUENTO, ID_PLAN, ID_SERVICIO, ID_PRODUCTO, VALOR, LIMITE_VECES) VALUES (?, ?, ?, ?, ?, ?)");
		q.setParameters(idDescuento, idPlan, idServicio, idProducto, valor, limiteVeces);
		return (long) q.executeUnique();
	}
	
	public long eliminarDescuento(PersistenceManager pm, long idDescuento){
		Query q = pm.newQuery(SQL, "DELETE FROM DESCUENTOS" + " WHERE ID_DESCUENTO = ?");
		q.setParameters(idDescuento);
		return (long) q.executeUnique();
	}
	
	public Descuento darDescuentoPorId (PersistenceManager pm, long idDescuento) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM DESCUENTOS" + " WHERE ID_DESCUENTO = ?");
		q.setResultClass(Descuento.class);
		q.setParameters(idDescuento);
		return (Descuento) q.executeUnique();
	}

	public List<Descuento> darDescuentos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM DESCUENTOS");
		q.setResultClass(Descuento.class);
		return (List<Descuento>) q.executeList();
	}


}
