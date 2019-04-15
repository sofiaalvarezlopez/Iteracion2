package uniandes.isis2304.hotelAndes.persistencia;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Descuentos;

public class SQLDescuentos {

	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLDescuentos(PersistenciaHotelAndes pha){
		this.pha = pha;
	}

	public long adicionarDescuento(PersistenceManager pm, long idDescuento, long idPlan, long idServicio, long idProducto, long valor, int limiteVeces) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO DESCUENTOS"  + "(IDDESCUENTO, IDPLAN, IDSERVICIO, IDPRODUCTO, VALOR, LIMITEVECES) VALUES (?, ?, ?, ?, ?, ?)");
		q.setParameters(idDescuento, idPlan, idServicio, idProducto, valor, limiteVeces);
		return (long) q.executeUnique();
	}
	
	public long eliminarDescuento(PersistenceManager pm, long idDescuento){
		Query q = pm.newQuery(SQL, "DELETE FROM DESCUENTOS" + " WHERE IDDESCUENTO = ?");
		q.setParameters(idDescuento);
		return (long) q.executeUnique();
	}
	
	public Descuentos darDescuentoPorId (PersistenceManager pm, long idDescuento) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM DESCUENTOS" + " WHERE IDDESCUENTO = ?");
		q.setResultClass(Descuentos.class);
		q.setParameters(idDescuento);
		return (Descuentos) q.executeUnique();
	}

	public List<Descuentos> darDescuentos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM DESCUENTOS");
		q.setResultClass(Descuentos.class);
		return (List<Descuentos>) q.executeList();
	}


}
