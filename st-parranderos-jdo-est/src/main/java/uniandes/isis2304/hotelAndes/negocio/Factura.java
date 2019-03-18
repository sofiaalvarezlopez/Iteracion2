package uniandes.isis2304.hotelAndes.negocio;
import java.sql.Timestamp;



public class Factura implements VOFactura
{
	
	private long numFactura;
	private Timestamp fecha;
	private int pagado;
	private double valor;
	private long idConsumo;
	private long idDotacion;
	private long idServicio;
	private long idEstadia;
	private String tipoDocEmpleado;
	private long numDocEmpleado;
	
	public Factura(){
		numFactura = 0;
		fecha = new Timestamp (0);
		pagado = 0;
		valor = 0;
		idConsumo = 0;
		idDotacion = 0;
		idServicio = 0;
		idEstadia = 0;
		tipoDocEmpleado = "";
		numDocEmpleado = 0;
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
	public Factura(long numFactura, Timestamp fecha, int pagado, double valor, long idConsumo, long idDotacion,
			long idServicio, long idEstadia, String tipoDocEmpleado, long numDocEmpleado) {
		super();
		this.numFactura = numFactura;
		this.fecha = fecha;
		this.pagado = pagado;
		this.valor = valor;
		this.idConsumo = idConsumo;
		this.idDotacion = idDotacion;
		this.idServicio = idServicio;
		this.idEstadia = idEstadia;
		this.tipoDocEmpleado = tipoDocEmpleado;
		this.numDocEmpleado = numDocEmpleado;
	}


	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#getNumFactura()
	 */
	@Override
	public long getNumFactura() {
		return numFactura;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#setNumFactura(long)
	 */
	@Override
	public void setNumFactura(long numFactura) {
		this.numFactura = numFactura;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#getFecha()
	 */
	@Override
	public Timestamp getFecha() {
		return fecha;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#setFecha(java.sql.Timestamp)
	 */
	@Override
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#getPagado()
	 */
	@Override
	public int getPagado() {
		return pagado;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#setPagado(int)
	 */
	@Override
	public void setPagado(int pagado) {
		this.pagado = pagado;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#getValor()
	 */
	@Override
	public double getValor() {
		return valor;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#setValor(double)
	 */
	@Override
	public void setValor(double valor) {
		this.valor = valor;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#getNombreConsumo()
	 */
	@Override
	public long getIdConsumo() {
		return idConsumo;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#setNombreConsumo(java.lang.String)
	 */
	@Override
	public void setIdConsumo(long idConsumo) {
		this.idConsumo = idConsumo;
	}
	
	/**
	 * @return the idDotacion
	 */
	public long getIdDotacion() {
		return idDotacion;
	}

	/**
	 * @param idDotacion the idDotacion to set
	 */
	public void setIdDotacion(long idDotacion) {
		this.idDotacion = idDotacion;
	}

	/**
	 * @return the idServicio
	 */
	public long getIdServicio() {
		return idServicio;
	}

	/**
	 * @param idServicio the idServicio to set
	 */
	public void setIdServicio(long idServicio) {
		this.idServicio = idServicio;
	}

	/**
	 * @return the idEstadia
	 */
	public long getIdEstadia() {
		return idEstadia;
	}

	/**
	 * @param idEstadia the idEstadia to set
	 */
	public void setIdEstadia(long idEstadia) {
		this.idEstadia = idEstadia;
	}

	/**
	 * @return the tipoDocEmpleado
	 */
	public String getTipoDocEmpleado() {
		return tipoDocEmpleado;
	}

	/**
	 * @param tipoDocEmpleado the tipoDocEmpleado to set
	 */
	public void setTipoDocEmpleado(String tipoDocEmpleado) {
		this.tipoDocEmpleado = tipoDocEmpleado;
	}

	/**
	 * @return the numDocEmpleado
	 */
	public long getNumDocEmpleado() {
		return numDocEmpleado;
	}

	/**
	 * @param numDocEmpleado the numDocEmpleado to set
	 */
	public void setNumDocEmpleado(long numDocEmpleado) {
		this.numDocEmpleado = numDocEmpleado;
	}
	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#toString()
	 */
	@Override
	public String toString() 
	{
		String a =  "Dotacion [numero factura=" + numFactura + ", fecha =" + fecha.toString() + ", valor =" + valor + 
				", id consumo =" + idConsumo; 
				if(pagado == 0){
					a += "No ha sido pagado";
				}
				else{
					a += "La factura ha sido pagada";
				}
				a += "]";
				
				return a; 
	}
	
	
	
	
	

}

