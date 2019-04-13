package uniandes.isis2304.hotelAndes.negocio;



public class ServicioAdicional extends Servicio implements VOServicioAdicional
{
	
	private int capacidad;
	
	public ServicioAdicional(){
		idServicio = 0;
		capacidad = 0;
	}
	
	public ServicioAdicional(long idServicioAdicional, int capacidad){
		idServicio = idServicioAdicional;
		this.capacidad = capacidad;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOServicioAdicional#getCapacidad()
	 */
	@Override
	public int getCapacidad() {
		return capacidad;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOServicioAdicional#setCapacidad(int)
	 */
	@Override
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOServicioAdicional#toString()
	 */
	@Override
	public String toString() 
	{
		return "Servicio Adicional [idServicioAdicional=" + idServicio + ", capacidad =" + capacidad + "]";
	}


}

