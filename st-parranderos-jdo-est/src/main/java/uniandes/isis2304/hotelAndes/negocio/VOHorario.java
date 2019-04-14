package uniandes.isis2304.hotelAndes.negocio;

import java.sql.Timestamp;

public interface VOHorario {

	long getIdHorario();

	void setIdHorario(long idHorario);

	String getDia();

	void setDia(String dia);

	String getHoraInicio();

	void setHoraInicio(String horaInicio);

	String getHoraFin();

	void setHoraFin(String horaFin);

	String getDuracion();

	void setDuracion(String duracion);

	Timestamp getFechaInicio();

	void setFechaInicio(Timestamp fechaInicio);
	
	Timestamp getFechaFin();

	void setFechaFin(Timestamp fechaFin);

	String toString();

}