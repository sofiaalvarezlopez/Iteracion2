package uniandes.isis2304.hotelAndes.negocio;

import java.util.List;

public interface VOCadenaHotelera {

	/**
	 * Retorna el identificador unico de la cadena hotelera
	 * @return idCadena.
	 */

	long getIdCadena();

	void setIdCadena(long idCadena);

	String getNombreCadena();

	void setNombreCadena(String nombreCadena);

	List<Object[]> getHoteles();

	void setHoteles(List<Object[]> hoteles);

	/**
	 * @return Una cadena de caracteres con la información básica del bebedor
	 */
	String toString();

}