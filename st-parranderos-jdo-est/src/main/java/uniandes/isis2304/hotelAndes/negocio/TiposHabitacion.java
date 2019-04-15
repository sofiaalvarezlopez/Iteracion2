package uniandes.isis2304.hotelAndes.negocio;

public class TiposHabitacion implements VOTipoHabitacion
{
	private long idTipoHabitacion;
	private String descripcion;
	private int capacidad;
	private int precioPorNoche;
	private long idHotel;

	public TiposHabitacion()
	{
		idTipoHabitacion = 0;
		descripcion = "";
		capacidad = 0;
		precioPorNoche = 0;
		setIdHotel(0);
		
	}
	
	public TiposHabitacion(long idTipoHabitacion, String descripcion, int capacidad, int precioPorPersonaPorNoche, long idHotel){
		this.idTipoHabitacion = idTipoHabitacion;
		this.descripcion = descripcion;
		this.capacidad = capacidad;
		this.precioPorNoche = precioPorPersonaPorNoche;
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
	public int getPrecioPorNoche() {
		return precioPorNoche;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOTipoHabitacion#setPrecioPorPersonaPorNoche(int)
	 */
	@Override
	public void setPrecioPorNoche(int precioPorNoche) {
		this.precioPorNoche = precioPorNoche;
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

