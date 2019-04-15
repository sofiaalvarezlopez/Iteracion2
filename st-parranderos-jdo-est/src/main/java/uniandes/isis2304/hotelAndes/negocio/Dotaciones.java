package uniandes.isis2304.hotelAndes.negocio;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Dotaciones implements VODotacion
{
	private long idDotacion;
	private double precio;
	private String nombre;
	private long idTipoHabitacion;
	
	public Dotaciones(){
		idDotacion = 0;
		precio = 0;
		nombre = "";
		setIdTipoHabitacion(0);
	}
	
	public Dotaciones(long idDotacion, double precio, String nombre, long idTipoHabitacion){
		this.idDotacion = idDotacion;
		this.precio = precio;
		this.nombre = nombre;
		this.setIdTipoHabitacion(idTipoHabitacion);
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VODotacion#getIdDotacion()
	 */
	@Override
	public long getIdDotacion() {
		return idDotacion;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VODotacion#setIdDotacion(long)
	 */
	@Override
	public void setIdDotacion(long idDotacion) {
		this.idDotacion = idDotacion;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VODotacion#getPrecio()
	 */
	@Override
	public double getPrecio() {
		return precio;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VODotacion#setPrecio(double)
	 */
	@Override
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VODotacion#getNombre()
	 */
	@Override
	public String getNombre() {
		return nombre;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VODotacion#setNombre(java.lang.String)
	 */
	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VODotacion#toString()
	 */
	@Override
	public String toString() 
	{
		return "Dotacion [id=" + idDotacion + ", nombre=" + nombre + ", direccion=" + ", precio =" + precio + "]";
	}

	/**
	 * @return the idTipoHabitacion
	 */
	public long getIdTipoHabitacion() {
		return idTipoHabitacion;
	}

	/**
	 * @param idTipoHabitacion the idTipoHabitacion to set
	 */
	public void setIdTipoHabitacion(long idTipoHabitacion) {
		this.idTipoHabitacion = idTipoHabitacion;
	}

}

