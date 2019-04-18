package uniandes.isis2304.hotelAndes.negocio;



public class VentaProductos extends Servicios implements VOVentaProducto
{

	
	private String estilo;
	private int capacidad;
	private String tipo;

	public VentaProductos(){
		idServicio = 0L;
		estilo = "";
		capacidad = 0;
		tipo = "";

	}
	
	public VentaProductos(Long idVentaProducto, String estilo, int capacidad, String tipo){
		idServicio = idVentaProducto;
		this.estilo = estilo;
		this.capacidad = capacidad;
		this.tipo = tipo;

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
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOVentaProducto#toString()
	 */
	@Override
	public String toString() 
	{
		return "VentaProducto [id vento producto =" + idServicio + ", estilo =" + estilo + ", capacidad =" + capacidad +
				", tipo =" + tipo + "]";
	}

}

