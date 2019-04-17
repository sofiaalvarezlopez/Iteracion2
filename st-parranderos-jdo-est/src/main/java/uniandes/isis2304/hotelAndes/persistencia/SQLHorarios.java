package uniandes.isis2304.hotelAndes.persistencia;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	public BigDecimal selectMaxHorario(PersistenceManager pm){
		Query q = pm.newQuery(SQL, "SELECT MAX(IDHORARIO) FROM HORARIOS");
		return (BigDecimal) q.executeUnique();
		
	}

	public void actualizarHorario(PersistenceManager pm, Long idHorario) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Timestamp hoy = Timestamp.valueOf(sdf.format(new Date()));
		Query q = pm.newQuery(SQL, "UPDATE HORARIOS SET FECHAFIN = ? WHERE IDHORARIO = ?");
		q.setParameters(hoy, idHorario);
		q.executeUnique();
		
	}


	

}
