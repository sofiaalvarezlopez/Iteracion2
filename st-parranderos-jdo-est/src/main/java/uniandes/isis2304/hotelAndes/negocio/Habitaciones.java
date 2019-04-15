package uniandes.isis2304.hotelAndes.negocio;



public class Habitaciones implements VOHabitacion 
{
	private long numeroHabitacion;
	private long tipoHabitacion;
	
	
	public Habitaciones(){
		numeroHabitacion = 0;
		setTipoHabitacion(0);
	}
	
	public Habitaciones(long numeroHabitacion, long idTipoHabitacion) {
		this.numeroHabitacion = numeroHabitacion;
		this.setTipoHabitacion(idTipoHabitacion);
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHabitacion#getNumeroHabitacion()
	 */
	@Override
	public long getNumeroHabitacion() {
		return numeroHabitacion;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHabitacion#setNumeroHabitacion(long)
	 */
	@Override
	public void setNumeroHabitacion(long numeroHabitacion) {
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
	public long getTipoHabitacion() {
		return tipoHabitacion;
	}

	/**
	 * @param idTipoHabitacion the idTipoHabitacion to set
	 */
	public void setTipoHabitacion(long tipoHabitacion) {
		this.tipoHabitacion = tipoHabitacion;
	}

}

