package uniandes.isis2304.hotelAndes.negocio;
import java.sql.Timestamp;



public class Facturas implements VOFacturas
{
	
	private long numFactura;
	private Timestamp fecha;
	private int fuePagada;
	private double precio;
	private long idProducto;
	private long idDotacion;
	private long idServicio;
	private long idEstadia;
	private long numDocEmpleado;
	private long idConvencion;
	private long idDotacionSalon;
	
	public Facturas(){
		numFactura = 0;
		fecha = new Timestamp (0);
		fuePagada = 0;
		precio = 0;
		idProducto = 0;
		idDotacion = 0;
		idServicio = 0;
		idEstadia = 0;
		numDocEmpleado = 0;
		setIdConvencion(0);
		idDotacion = 0;
	}
	/**
	 * @param numFactura
	 * @param fecha
	 * @param pagado
	 * @param valor
	 * @param idConsumo
	 * @param idDotacion
	 * @param idServicio
	 * @param idEstadia
	 * @param tipoDocEmpleado
	 * @param numDocEmpleado
	 */
	public Facturas(long numFactura, Timestamp fecha, int pagado, double valor, long idConsumo, long idDotacion,
			long idServicio, long idEstadia, long numDocEmpleado, long idConvencion, long idDotacionSalon) {
		super();
		this.numFactura = numFactura;
		this.fecha = fecha;
		this.fuePagada = pagado;
		this.precio = valor;
		this.idProducto = idConsumo;
		this.idDotacion = idDotacion;
		this.idServicio = idServicio;
		this.idEstadia = idEstadia;
		this.numDocEmpleado = numDocEmpleado;
		this.setIdConvencion(idConvencion);
		this.setIdDotacionSalon(idDotacionSalon);
	}


	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#getNumFactura()
	 */
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOFacturas#getNumFactura()
	 */
	@Override
	public long getNumFactura() {
		return numFactura;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#setNumFactura(long)
	 */
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOFacturas#setNumFactura(long)
	 */
	@Override
	public void setNumFactura(long numFactura) {
		this.numFactura = numFactura;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#getFecha()
	 */
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOFacturas#getFecha()
	 */
	@Override
	public Timestamp getFecha() {
		return fecha;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#setFecha(java.sql.Timestamp)
	 */
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOFacturas#setFecha(java.sql.Timestamp)
	 */
	@Override
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#getPagado()
	 */
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOFacturas#getFuePagada()
	 */
	@Override
	public int getFuePagada() {
		return fuePagada;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#setPagado(int)
	 */
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOFacturas#setFuePagada(int)
	 */
	@Override
	public void setFuePagada(int fuePagada) {
		this.fuePagada = fuePagada;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#getValor()
	 */
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOFacturas#getPrecio()
	 */
	@Override
	public double getPrecio() {
		return precio;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#setValor(double)
	 */
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOFacturas#setPrecio(double)
	 */
	@Override
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#getNombreConsumo()
	 */
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOFacturas#getIdProducto()
	 */
	@Override
	public long getIdProducto() {
		return idProducto;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#setNombreConsumo(java.lang.String)
	 */
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOFacturas#setIdProducto(long)
	 */
	@Override
	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}
	
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOFacturas#getIdDotacion()
	 */
	@Override
	public long getIdDotacion() {
		return idDotacion;
	}

	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOFacturas#setIdDotacion(long)
	 */
	@Override
	public void setIdDotacion(long idDotacion) {
		this.idDotacion = idDotacion;
	}

	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOFacturas#getIdServicio()
	 */
	@Override
	public long getIdServicio() {
		return idServicio;
	}

	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOFacturas#setIdServicio(long)
	 */
	@Override
	public void setIdServicio(long idServicio) {
		this.idServicio = idServicio;
	}

	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOFacturas#getIdEstadia()
	 */
	@Override
	public long getIdEstadia() {
		return idEstadia;
	}

	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOFacturas#setIdEstadia(long)
	 */
	@Override
	public void setIdEstadia(long idEstadia) {
		this.idEstadia = idEstadia;
	}


	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOFacturas#getNumDocEmpleado()
	 */
	@Override
	public long getNumDocEmpleado() {
		return numDocEmpleado;
	}

	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOFacturas#setNumDocEmpleado(long)
	 */
	@Override
	public void setNumDocEmpleado(long numDocEmpleado) {
		this.numDocEmpleado = numDocEmpleado;
	}
	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#toString()
	 */
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOFacturas#toString()
	 */
	@Override
	public String toString() 
	{
		String a =  "Dotacion [numero factura=" + numFactura + ", fecha =" + fecha.toString() + ", valor =" + precio + 
				", id consumo =" + idProducto; 
				if(fuePagada == 0){
					a += "No ha sido pagado";
				}
				else{
					a += "La factura ha sido pagada";
				}
				a += "]";
				
				return a; 
	}
	public long getIdDotacionSalon() {
		return idDotacionSalon;
	}
	public void setIdDotacionSalon(long idDotacionSalon) {
		this.idDotacionSalon = idDotacionSalon;
	}
	public long getIdConvencion() {
		return idConvencion;
	}
	public void setIdConvencion(long idConvencion) {
		this.idConvencion = idConvencion;
	}
	
	
	
	
	

}

