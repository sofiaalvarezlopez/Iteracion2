package uniandes.isis2304.hotelAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Reserva;


public class SQLReserva {
	
	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLReserva(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarReserva(PersistenceManager pm, long numReserva, long idEstadia, long idServicio, long idHorario, long idConsumo) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO RESERVAS"  + "(NUM_RESERVA, ID_ESTADIA, ID_SERVICIO, ID_HORARIO, ID_CONSUMO) VALUES (?, ?, ?, ?, ?)");
		q.setParameters(numReserva, idEstadia, idServicio, idHorario, idConsumo);
		return (long) q.executeUnique();
	}
	
	public long eliminarReserva(PersistenceManager pm, long idReserva){
		Query q = pm.newQuery(SQL, "DELETE FROM RESERVAS" + " WHERE NUM_RESERVA = ?");
		q.setParameters(idReserva);
		return (long) q.executeUnique();
	}
	
	public Reserva darReservaPorId (PersistenceManager pm, long idReserva) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM RESERVAS" + " WHERE NUM_RESERVA = ?");
		q.setResultClass(Reserva.class);
		q.setParameters(idReserva);
		return (Reserva) q.executeUnique();
	}

	public List<Reserva> darReservas(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM RESERVAS");
		q.setResultClass(Reserva.class);
		return (List<Reserva>) q.executeList();
	}
	
	

}
