package uniandes.isis2304.hotelAndes.negocio;



public class VentaProducto extends Servicio implements VOVentaProducto
{

	
	private String estilo;
	private int capacidad;
	private String tipo;
	private String nombre;
	
	public VentaProducto(){
		idServicio = 0;
		estilo = "";
		capacidad = 0;
		tipo = "";
		nombre = "";
	}
	
	public VentaProducto(long idVentaProducto, String estilo, int capacidad, String tipo, String nombre){
		idServicio = idVentaProducto;
		this.estilo = estilo;
		this.capacidad = capacidad;
		this.tipo = tipo;
		this.nombre = nombre;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOVentaProducto#getEstilo()
	 */
	@Override
	public String getEstilo() {
		return estilo;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOVentaProducto#setEstilo(java.lang.String)
	 */
	@Override
	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOVentaProducto#getCapacidad()
	 */
	@Override
	public int getCapacidad() {
		return capacidad;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOVentaProducto#setCapacidad(int)
	 */
	@Override
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOVentaProducto#getTipo()
	 */
	@Override
	public String getTipo() {
		return tipo;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOVentaProducto#setTipo(java.lang.String)
	 */
	@Override
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOVentaProducto#getNombre()
	 */
	@Override
	public String getNombre() {
		return nombre;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOVentaProducto#setNombre(java.lang.String)
	 */
	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOVentaProducto#toString()
	 */
	@Override
	public String toString() 
	{
		return "VentaProducto [id vento producto =" + idServicio + ", estilo =" + estilo + 
				", nombre =" + nombre + ", capacidad =" + capacidad +
				", tipo =" + tipo + "]";
	}

}

