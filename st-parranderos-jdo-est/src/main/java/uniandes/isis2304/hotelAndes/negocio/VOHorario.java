package uniandes.isis2304.hotelAndes.negocio;

import java.sql.Timestamp;

public interface VOHorario {

	long getIdHorario();

	void setIdHorario(long idHorario);

	String getDia();

	void setDia(String dia);

	String getHoraApertura();

	void setHoraApertura(String horaApertura);

	String getHoraCierre();

	void setHoraCierre(String horaCierre);

	String getDuracion();

	void setDuracion(String duracion);

	Timestamp getFecha();

	void setFecha(Timestamp fecha);

	String toString();

}