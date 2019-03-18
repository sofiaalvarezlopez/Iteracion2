package uniandes.isis2304.hotelAndes.negocio;



public class Habitacion implements VOHabitacion 
{
	private long numeroHabitacion;
	private long idTipoHabitacion;
	
	
	public Habitacion(){
		numeroHabitacion = 0;
		setIdTipoHabitacion(0);
	}
	
	public Habitacion(long numeroHabitacion, long idTipoHabitacion) {
		this.numeroHabitacion = numeroHabitacion;
		this.setIdTipoHabitacion(idTipoHabitacion);
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

