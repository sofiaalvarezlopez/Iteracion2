package uniandes.isis2304.hotelAndes.negocio;



public class Habitaciones implements VOHabitacion 
{
	private Long numeroHabitacion;
	private Long tipoHabitacion;
	
	
	public Habitaciones(){
		numeroHabitacion = 0L;
		setTipoHabitacion(0L);
	}
	
	public Habitaciones(Long numeroHabitacion, Long idTipoHabitacion) {
		this.numeroHabitacion = numeroHabitacion;
		this.setTipoHabitacion(idTipoHabitacion);
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHabitacion#getNumeroHabitacion()
	 */
	@Override
	public Long getNumeroHabitacion() {
		return numeroHabitacion;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHabitacion#setNumeroHabitacion(Long)
	 */
	@Override
	public void setNumeroHabitacion(Long numeroHabitacion) {
		this.numeroHabitacion = numeroHabitacion;
	}
	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHabitacion#toString()
	 */
	@Override
	public String toString() 
	{
		return "Habitacion [numero habitacion=" + numeroHabitacion + "]";
	}

	/**
	 * @return the idTipoHabitacion
	 */
	public Long getTipoHabitacion() {
		return tipoHabitacion;
	}

	/**
	 * @param idTipoHabitacion the idTipoHabitacion to set
	 */
	public void setTipoHabitacion(Long tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

}

