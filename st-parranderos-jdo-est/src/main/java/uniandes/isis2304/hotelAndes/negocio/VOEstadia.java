package uniandes.isis2304.hotelAndes.negocio;

import java.sql.Timestamp;

public interface VOEstadia {

	long getIdEstadia();

	void setIdEstadia(long idEstadia);

	Timestamp getFechaLlegada();

	void setFechaLlegada(Timestamp fechaLlegada);

	Timestamp getFechaSalida();

	void setFechaSalida(Timestamp fechaSalida);

	int getNumeroPersonas();

	void setNumeroPersonas(int numeroPersonas);

	int getCheckin();

	void setCheckin(int checkin);

	int getPagado();

	void setPagado(int pagado);

	String toString();

}