package uniandes.isis2304.hotelAndes.negocio;



public class Productos implements VOProducto
{
	private long idProductos;
	private String nombre;
	private double precio;
	private int cantidad;
	private int duracion;
	private String categoria;
	private long idVentaProducto;

	
	public Productos(){
		idProductos = 0;
		nombre = "";
		precio = 0;
		cantidad = 0;
		duracion = 0;
		categoria = "";
		setIdVentaProducto(0);
	}
	
	public Productos(long idProducto, String nombre, double precio, int cantidad, int duracion, String categoria,long idVentaProducto )
	{
		this.idProductos = idProducto;
		this.nombre = nombre;
		this.precio = precio;
		this.cantidad = cantidad;
		this.duracion = duracion;
		this.categoria = categoria;
		this.setIdVentaProducto(idVentaProducto);
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOProducto#getIdProducto()
	 */
	@Override
	public long getIdProductos() {
		return idProductos;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOProducto#setIdProducto(long)
	 */
	@Override
	public void setIdProductos(long idProductos) {
		this.idProductos = idProductos;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOProducto#getNombre()
	 */
	@Override
	public String getNombre() {
		return nombre;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOProducto#setNombre(java.lang.String)
	 */
	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOProducto#getPrecio()
	 */
	@Override
	public double getPrecio() {
		return precio;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOProducto#setPrecio(double)
	 */
	@Override
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOProducto#getCantidad()
	 */
	@Override
	public int getCantidad() {
		return cantidad;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOProducto#setCantidad(int)
	 */
	@Override
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOProducto#getDuracion()
	 */
	@Override
	public int getDuracion() {
		return duracion;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOProducto#setDuracion(int)
	 */
	@Override
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOProducto#getCategoria()
	 */
	@Override
	public String getCategoria() {
		return categoria;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOProducto#setCategoria(java.lang.String)
	 */
	@Override
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOProducto#toString()
	 */
	@Override
	public String toString() 
	{
		return "Producto [id=" + idProductos + ", nombre=" + nombre + ", precio=" + precio + ", cantidad=" + cantidad + ", duracion =" + duracion
				+ ", categoria=" + categoria + "]";
	}

	/**
	 * @return the idVentaProducto
	 */
	public long getIdVentaProducto() {
		return idVentaProducto;
	}

	/**
	 * @param idVentaProducto the idVentaProducto to set
	 */
	public void setIdVentaProducto(long idVentaProducto) {
		this.idVentaProducto = idVentaProducto;
	}
}

