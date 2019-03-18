package uniandes.isis2304.hotelAndes.negocio;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class DotacionSalon implements VODotacionSalon
{
	private long idDotacion;
	private double valor;
	private String nombre;
	private long idSalon;
	
	public DotacionSalon(){
		idDotacion = 0;
		valor = 0;
		nombre = "";
		setIdSalon(0);
	}
	
	public DotacionSalon(long idDotacion, double precio, String nombre, long idSalon){
		this.idDotacion = idDotacion;
		this.valor = precio;
		this.nombre = nombre;
		this.setIdSalon(idSalon);
	}

	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VODotacionSalon#getIdDotacion()
	 */
	@Override
	public long getIdDotacion() {
		return idDotacion;
	}


	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VODotacionSalon#setIdDotacion(long)
	 */
	@Override
	public void setIdDotacion(long idDotacion) {
		this.idDotacion = idDotacion;
	}

	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VODotacionSalon#getPrecio()
	 */
	@Override
	public double getPrecio() {
		return valor;
	}

	
	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VODotacionSalon#setPrecio(double)
	 */
	@Override
	public void setPrecio(double precio) {
		this.valor = precio;
	}

	
	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VODotacionSalon#getNombre()
	 */
	@Override
	public String getNombre() {
		return nombre;
	}

	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VODotacionSalon#setNombre(java.lang.String)
	 */
	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VODotacionSalon#toString()
	 */
	@Override
	public String toString() 
	{
		return "Dotacion [id=" + idDotacion + ", nombre=" + nombre + ", direccion=" + ", valor =" + valor + "]";
	}

	/**
	 * @return the idSalon
	 */
	public long getIdSalon() {
		return idSalon;
	}

	/**
	 * @param idSalon the idSalon to set
	 */
	public void setIdSalon(long idSalon) {
		this.idSalon = idSalon;
	}

}

