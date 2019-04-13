package uniandes.isis2304.hotelAndes.negocio;

public interface VOUsuario {

	long getNumDocumento();

	void setNumDocumento(long numDocumento);

	String getTipoDocumento();

	void setTipoDocumento(String tipoDocumento);

	String getNombre();

	void setNombre(String nombre);

	String getCorreoElectronico();

	void setCorreoElectronico(String correoElectronico);

	String toString();

}