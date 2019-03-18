package uniandes.isis2304.hotelAndes.negocio;

public interface VOProducto {

	long getIdProducto();

	void setIdProducto(long idProducto);

	String getNombre();

	void setNombre(String nombre);

	double getPrecio();

	void setPrecio(double precio);

	int getCantidad();

	void setCantidad(int cantidad);

	int getDuracion();

	void setDuracion(int duracion);

	String getCategoria();

	void setCategoria(String categoria);

	String toString();

}