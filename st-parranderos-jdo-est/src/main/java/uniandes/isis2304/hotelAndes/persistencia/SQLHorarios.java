package uniandes.isis2304.hotelAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Horarios;

public class SQLHorarios {
	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLHorarios(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarHorario(PersistenceManager pm, long idHorario, String duracion, long idServicio, Timestamp fechaInicio, String dia, String horaINICIO, String horaFIN, Timestamp fechaFin) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO HORARIOS"  + "(IDHORARIO, DURACION, IDSERVICIO, FECHAINICIO, DIA, HORAINICIO, HORAFIN, FECHAFIN) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(idHorario, duracion, idServicio, fechaInicio, dia, horaINICIO, horaFIN, fechaFin);
		return (long) q.executeUnique();
	}
	
	public long eliminarHorario(PersistenceManager pm, long idHorario){
		Query q = pm.newQuery(SQL, "DELETE FROM HORARIOS" + " WHERE IDHORARIO = ?");
		q.setParameters(idHorario);
		return (long) q.executeUnique();
	}
	
	public Horarios darHorarioPorId (PersistenceManager pm, long idHorario) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM HORARIOS" + " WHERE IDHORARIO = ?");
		q.setResultClass(Horarios.class);
		q.setParameters(idHorario);
		return (Horarios) q.executeUnique();
	}

	public List<Horarios> darHorarios (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM HORARIOS");
		q.setResultClass(Horarios.class);
		return (List<Horarios>) q.executeList();
	}
	

}
