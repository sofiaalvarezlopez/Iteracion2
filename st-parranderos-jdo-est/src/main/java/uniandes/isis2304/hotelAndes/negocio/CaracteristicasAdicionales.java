package uniandes.isis2304.hotelAndes.negocio;


public class CaracteristicasAdicionales implements VOCaracteristicaAdicional
{
	
	private long idCaracteristicaAdicional;
	private String nombre;
	private double valor;
	private long idServicioAdicional;
	
	public CaracteristicasAdicionales(){
		this.idCaracteristicaAdicional = 0;
		nombre = "";
		valor = 0;
		setIdServicioAdicional(0);
	}
	
	public CaracteristicasAdicionales(long idCaracteristicaAdicional, String nombre, double valor, long idServicioAdicional){
		this.idCaracteristicaAdicional = idCaracteristicaAdicional;
		this.nombre = nombre;
		this.valor = valor;
		this.setIdServicioAdicional(idServicioAdicional);
	}
	
	
	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOCaracteristicaAdicional#getIdCaracteristicaAdicional()
	 */
	@Override
	public long getIdCaracteristicaAdicional() {
		return idCaracteristicaAdicional;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOCaracteristicaAdicional#setIdCaracteristicaAdicional(long)
	 */
	@Override
	public void setIdCaracteristicaAdicional(long idCaracteristicaAdicional) {
		this.idCaracteristicaAdicional = idCaracteristicaAdicional;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOCaracteristicaAdicional#getNombre()
	 */
	@Override
	public String getNombre() {
		return nombre;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOCaracteristicaAdicional#setNombre(java.lang.String)
	 */
	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOCaracteristicaAdicional#getValor()
	 */
	@Override
	public double getValor() {
		return valor;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOCaracteristicaAdicional#setValor(double)
	 */
	@Override
	public void setValor(double valor) {
		this.valor = valor;
	}
	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOCaracteristicaAdicional#toString()
	 */
	@Override
	public String toString() 
	{
		return "CaracteristicaAdicional [id=" + idCaracteristicaAdicional + ", nombre=" + nombre + ", valor = " + valor + "]";
	}

	/**
	 * @return the idServicioAdicional
	 */
	public long getIdServicioAdicional() {
		return idServicioAdicional;
	}

	/**
	 * @param idServicioAdicional the idServicioAdicional to set
	 */
	public void setIdServicioAdicional(long idServicioAdicional) {
		this.idServicioAdicional = idServicioAdicional;
	}

	
	
	
	
}

