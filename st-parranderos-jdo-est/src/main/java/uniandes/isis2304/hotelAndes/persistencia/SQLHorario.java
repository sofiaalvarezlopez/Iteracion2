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
	
	public long adicionarHorario(PersistenceManager pm, long idHorario, String duracion, long idServicio, Timestamp fecha, String dia, String horaApertura, String horaCierre) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO HORARIOS"  + "(ID_HORARIO, DURACION, ID_SERVICIO, FECHA, DIA, HORA_APERTURA, HORA_CIERRE) VALUES (?, ?, ?, ?, ?, ?, ?)");
		q.setParameters(idHorario, duracion, idServicio, fecha, dia, horaApertura, horaCierre);
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
