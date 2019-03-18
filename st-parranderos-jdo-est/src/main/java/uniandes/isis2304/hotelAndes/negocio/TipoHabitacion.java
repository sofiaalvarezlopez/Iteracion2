package uniandes.isis2304.hotelAndes.negocio;

public class TipoHabitacion implements VOTipoHabitacion
{
	private long idTipoHabitacion;
	private String descripcion;
	private int capacidad;
	private int precioPorPersonaPorNoche;
	private long idHotel;

	public TipoHabitacion()
	{
		idTipoHabitacion = 0;
		descripcion = "";
		capacidad = 0;
		precioPorPersonaPorNoche = 0;
		setIdHotel(0);
		
	}
	
	public TipoHabitacion(long idTipoHabitacion, String descripcion, int capacidad, int precioPorPersonaPorNoche, long idHotel){
		this.idTipoHabitacion = idTipoHabitacion;
		this.descripcion = descripcion;
		this.capacidad = capacidad;
		this.precioPorPersonaPorNoche = precioPorPersonaPorNoche;
		this.setIdHotel(idHotel);
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOTipoHabitacion#getIdTipoHabitacion()
	 */
	@Override
	public long getIdTipoHabitacion() {
		return idTipoHabitacion;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOTipoHabitacion#setIdTipoHabitacion(long)
	 */
	@Override
	public void setIdTipoHabitacion(long idTipoHabitacion) {
		this.idTipoHabitacion = idTipoHabitacion;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOTipoHabitacion#getDescripcion()
	 */
	@Override
	public String getDescripcion() {
		return descripcion;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOTipoHabitacion#setDescripcion(java.lang.String)
	 */
	@Override
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOTipoHabitacion#getCapacidad()
	 */
	@Override
	public int getCapacidad() {
		return capacidad;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOTipoHabitacion#setCapacidad(int)
	 */
	@Override
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOTipoHabitacion#getPrecioPorPersonaPorNoche()
	 */
	@Override
	public int getPrecioPorPersonaPorNoche() {
		return precioPorPersonaPorNoche;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOTipoHabitacion#setPrecioPorPersonaPorNoche(int)
	 */
	@Override
	public void setPrecioPorPersonaPorNoche(int precioPorPersonaPorNoche) {
		this.precioPorPersonaPorNoche = precioPorPersonaPorNoche;
	}
	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOTipoHabitacion#toString()
	 */
	@Override
	public String toString() 
	{
		return "Tipo Habitacion [idTipohabitacion=" + idTipoHabitacion + ", capacidad =" + capacidad + 
				", descripcion =" + descripcion + "]";
	}

	/**
	 * @return the idHotel
	 */
	public long getIdHotel() {
		return idHotel;
	}

	/**
	 * @param idHotel the idHotel to set
	 */
	public void setIdHotel(long idHotel) {
		this.idHotel = idHotel;
	}

}

