package uniandes.isis2304.hotelAndes.negocio;


public class Salon extends Servicio implements VOSalon
{

	
	private int capacidad;
	
	private double costoPorHora;
	

	public Salon(){
		idServicio = 0;
		this.capacidad = 0;
		this.costoPorHora = 0;
	}
	
	public Salon(long idSalon, int capacidad, double costoPorHora){
		idServicio = idSalon;
		this.capacidad = capacidad;
		this.costoPorHora = costoPorHora;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOSalon#getCapacidad()
	 */
	@Override
	public int getCapacidad() {
		return capacidad;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOSalon#setCapacidad(int)
	 */
	@Override
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOSalon#getCostoPorHora()
	 */
	@Override
	public double getCostoPorHora() {
		return costoPorHora;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOSalon#setCostoPorHora(double)
	 */
	@Override
	public void setCostoPorHora(double costoPorHora) {
		this.costoPorHora = costoPorHora;
	}



	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOSalon#toString()
	 */
	@Override
	public String toString() 
	{
		return "Salon [idSalon=" + idServicio + ", capacidad =" + capacidad +
				", costo por hora =" + costoPorHora + "]";
	}
	
}

