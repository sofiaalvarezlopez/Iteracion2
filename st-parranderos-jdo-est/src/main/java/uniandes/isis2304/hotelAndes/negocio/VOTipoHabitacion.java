package uniandes.isis2304.hotelAndes.negocio;

public interface VOTipoHabitacion {

	long getIdTipoHabitacion();

	void setIdTipoHabitacion(long idTipoHabitacion);

	String getDescripcion();

	void setDescripcion(String descripcion);

	int getCapacidad();

	void setCapacidad(int capacidad);

	int getPrecioPorPersonaPorNoche();

	void setPrecioPorPersonaPorNoche(int precioPorPersonaPorNoche);

	String toString();

}