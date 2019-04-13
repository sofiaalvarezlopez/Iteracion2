package uniandes.isis2304.hotelAndes.negocio;

public class Hotel implements VOHotel {
	
	private long idHotel;
	private String nombre;
	private String direccion;
	private String ciudad;
	private int estrellas;
	private long idCadenaHotelera;
	
	public Hotel(){
		idHotel = 0;
		nombre = "";
		direccion = "";
		ciudad = "";
		estrellas = 0;
		setIdCadenaHotelera(0);
	}
	public Hotel(long idHotel, String nombre, String direccion, String ciudad, int estrellas, long idCadenaHotelera){
		
		
		this.idHotel = idHotel;
		this.nombre = nombre;
		this.direccion = direccion;
		this.ciudad = ciudad;
		this.estrellas = estrellas;
		this.setIdCadenaHotelera(idCadenaHotelera);
		
		
	}
	

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHotel#getIdHotel()
	 */
	@Override
	public long getIdHotel() {
		return idHotel;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHotel#setIdHotel(long)
	 */
	@Override
	public void setIdHotel(long idHotel) {
		this.idHotel = idHotel;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHotel#getNombre()
	 */
	@Override
	public String getNombre() {
		return nombre;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHotel#setNombre(java.lang.String)
	 */
	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHotel#getDireccion()
	 */
	@Override
	public String getDireccion() {
		return direccion;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHotel#setDireccion(java.lang.String)
	 */
	@Override
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHotel#getCiudad()
	 */
	@Override
	public String getCiudad() {
		return ciudad;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHotel#setCiudad(java.lang.String)
	 */
	@Override
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHotel#getEstrellas()
	 */
	@Override
	public int getEstrellas() {
		return estrellas;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHotel#setEstrellas(int)
	 */
	@Override
	public void setEstrellas(int estrellas) {
		this.estrellas = estrellas;
	}
	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHotel#toString()
	 */
	@Override
	public String toString() 
	{
		return "Hotel [id=" + idHotel + ", nombre=" + nombre + ", direccion=" + direccion + ", ciudad=" + ciudad + ", estrellas =" + estrellas + "]";
	}
	/**
	 * @return the idCadenaHotelera
	 */
	public long getIdCadenaHotelera() {
		return idCadenaHotelera;
	}
	/**
	 * @param idCadenaHotelera the idCadenaHotelera to set
	 */
	public void setIdCadenaHotelera(long idCadenaHotelera) {
		this.idCadenaHotelera = idCadenaHotelera;
	}

}
