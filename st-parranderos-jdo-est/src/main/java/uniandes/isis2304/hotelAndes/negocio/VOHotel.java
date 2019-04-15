package uniandes.isis2304.hotelAndes.negocio;

public interface VOHotel {

	long getIdHotel();

	void setIdHotel(long idHotel);

	String getNombreHotel();

	void setNombreHotel(String nombreHotel);

	String getDireccion();

	void setDireccion(String direccion);

	String getCiudad();

	void setCiudad(String ciudad);

	int getEstrellas();

	void setEstrellas(int estrellas);

	String toString();

}