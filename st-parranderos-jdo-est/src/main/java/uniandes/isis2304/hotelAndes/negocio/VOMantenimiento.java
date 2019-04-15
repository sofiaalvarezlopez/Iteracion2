package uniandes.isis2304.hotelAndes.negocio;

public interface VOMantenimiento {

	long getIdMantenimiento();

	void setIdMantenimiento(long idMantenimiento);

	String getCausa();

	void setCausa(String causa);

	long getIdHorario();

	void setIdHorario(long idHorario);

	long getIdServicio();

	void setIdServicio(long idServicio);

	long getNumHabitacion();

	void setNumHabitacion(long numHabitacion);

	String toString();

}