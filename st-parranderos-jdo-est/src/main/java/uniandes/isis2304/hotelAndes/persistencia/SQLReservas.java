package uniandes.isis2304.hotelAndes.persistencia;

import java.math.BigDecimal;
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
	
	public Long adicionarReserva(PersistenceManager pm, Long numReserva, Long idEstadia, Long idServicio, Long idHorario, Long idConsumo, Long idConvencion, Long capacidad) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO RESERVAS"  + "(NUMRESERVA, IDESTADIA, IDSERVICIO, IDHORARIO, IDCONSUMO, IDCONVENCION, CAPACIDAD) VALUES (?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(numReserva, idEstadia, idServicio, idHorario, idConsumo, idConvencion, capacidad);
		return (Long) q.executeUnique();
	}
	
	public Long eliminarReserva(PersistenceManager pm, Long idReserva){
		Query q = pm.newQuery(SQL, "DELETE FROM RESERVAS" + " WHERE NUMRESERVA = ?");
		q.setParameters(idReserva);
		return (Long) q.executeUnique();
	}
	
	public Reservas darReservaPorId (PersistenceManager pm, Long idReserva) 
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

	public BigDecimal selectMaxReserva(PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT MAX(NUMRESERVA) FROM RESERVAS");
		return (BigDecimal) q.executeUnique();
	}
	
	

}
