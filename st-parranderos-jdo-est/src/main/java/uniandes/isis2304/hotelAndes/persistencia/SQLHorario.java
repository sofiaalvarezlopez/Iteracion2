package uniandes.isis2304.hotelAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.hotelAndes.negocio.Horario;

public class SQLHorario {
	private final static String SQL = PersistenciaHotelAndes.SQL;
	private PersistenciaHotelAndes pha;

	public SQLHorario(PersistenciaHotelAndes pha){
		this.pha = pha;
	}
	
	public long adicionarHorario(PersistenceManager pm, long idHorario, String duracion, long idServicio, Timestamp fechaInicio, String dia, String horaINICIO, String horaFIN, Timestamp fechaFin) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO HORARIOS"  + "(ID_HORARIO, DURACION, ID_SERVICIO, FECHA_INICIO, DIA, HORA_INICIO, HORA_FIN, FECHA_FIN) VALUES (?, ?, ?, ?, ?, ?, ?,?)");
		q.setParameters(idHorario, duracion, idServicio, fechaInicio, dia, horaINICIO, horaFIN, fechaFin);
		return (long) q.executeUnique();
	}
	
	public long eliminarHorario(PersistenceManager pm, long idHorario){
		Query q = pm.newQuery(SQL, "DELETE FROM HORARIOS" + " WHERE ID_HORARIO = ?");
		q.setParameters(idHorario);
		return (long) q.executeUnique();
	}
	
	public Horario darHorarioPorId (PersistenceManager pm, long idHorario) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM HORARIOS" + " WHERE ID_HORARIO = ?");
		q.setResultClass(Horario.class);
		q.setParameters(idHorario);
		return (Horario) q.executeUnique();
	}

	public List<Horario> darHorarios (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM HORARIOS");
		q.setResultClass(Horario.class);
		return (List<Horario>) q.executeList();
	}
	

}
