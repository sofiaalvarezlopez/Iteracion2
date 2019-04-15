package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Reservas;


public class SQLReservas {
	
	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLReservas(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarReserva(PersistenceManager pm, long numReserva, long idEstadia, long idServicio, long idHorario, long idConsumo, long idConvencion) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO RESERVAS"  + "(NUMRESERVA, IDESTADIA, IDSERVICIO, IDHORARIO, IDCONSUMO, IDCONVENCION) VALUES (?, ?, ?, ?, ?, ?)");
		q.setParameters(numReserva, idEstadia, idServicio, idHorario, idConsumo, idConvencion);
		return (long) q.executeUnique();
	}
	
	public long eliminarReserva(PersistenceManager pm, long idReserva){
		Query q = pm.newQuery(SQL, "DELETE FROM RESERVAS" + " WHERE NUMRESERVA = ?");
		q.setParameters(idReserva);
		return (long) q.executeUnique();
	}
	
	public Reservas darReservaPorId (PersistenceManager pm, long idReserva) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM RESERVAS" + " WHERE NUMRESERVA = ?");
		q.setResultClass(Reservas.class);
		q.setParameters(idReserva);
		return (Reservas) q.executeUnique();
	}

	public List<Reservas> darReservas(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM RESERVAS");
		q.setResultClass(Reservas.class);
		return (List<Reservas>) q.executeList();
	}
	
	

}
