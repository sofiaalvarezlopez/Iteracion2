package uniandes.isis2304.hotelAndes.persistencia;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Mantenimiento;


public class SQLMantenimiento {
	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLMantenimiento(PersistenciaHotelAndes pha){
		this.pha = pha;
	}

	public void adicionarMantenimiento(PersistenceManager pm, Long idMantenimiento, String causa, Long idHorario, Long idServicio, Long numHabitacion, int finalizado) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO MANTENIMIENTO"  + "(IDMANTENIMIENTO, CAUSA, IDHORARIO, IDSERVICIO, NUMHABITACION, FINALIZADO) VALUES (?, ?, ?, ?, ?, ?)");
		q.setParameters(idMantenimiento, causa, idHorario, idServicio, numHabitacion, finalizado);
		q.executeUnique();
	}

	public long eliminarMantenimiento(PersistenceManager pm, Long idMantenimiento){
		Query q = pm.newQuery(SQL, "DELETE FROM MANTENIMIENTO" + " WHERE IDMANTENIMIENTO = ?");
		q.setParameters(idMantenimiento);
		return (long) q.executeUnique();
	}
	
	public Mantenimiento darMantenimientoPorId (PersistenceManager pm, Long idMantenimiento) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM MANTENIMIENTO" + " WHERE IDMANTENIMIENTO = ?");
		q.setResultClass(Mantenimiento.class);
		q.setParameters(idMantenimiento);
		return (Mantenimiento) q.executeUnique();
	}

	public List<Mantenimiento> darMantenimientos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM MANTENIMIENTO");
		q.setResultClass(Mantenimiento.class);
		return (List<Mantenimiento>) q.executeList();
	}

	public void actualizarAFinalizado(PersistenceManager pm, Long idMantenimiento) {
		Query q = pm.newQuery(SQL, "UPDATE MANTENIMIENTO SET FINALIZADO = 1 WHERE IDMANTENIMIENTO = ?");
		q.setParameters(idMantenimiento);
		q.executeUnique();
		
	}

}
