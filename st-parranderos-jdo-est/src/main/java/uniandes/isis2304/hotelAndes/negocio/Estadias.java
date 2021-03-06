package uniandes.isis2304.hotelAndes.negocio;

import java.sql.Timestamp;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Estadias implements VOEstadia
{
	private Long idEstadia;
	private Timestamp fechaLlegada;
	private Timestamp fechaSalida;
	private int numeroPersonas;
	private Long idPlan;
	private Long idHabitacion;
	private int checkin; 
	private int pagado;
	private Long idCliente;
	private Long idConvencion;
	
	
	public Estadias(){
		idEstadia = 0L;
		fechaLlegada = new Timestamp(0);
		fechaSalida = new Timestamp(0);
		numeroPersonas = 0;
		checkin = 0;
		pagado = 0;
		idCliente = 0L;
		idConvencion = Long.valueOf("0");
		setIdPlan(0);
		setIdHabitacion(0);
	}
	
	public Estadias(Long idEstadia, Timestamp fechaLlegada, Timestamp fechaSalida, int numPersonas, Long idPlan, Long idHabitacion, int checkIn, int pago, Long numDoc, Long idConvencion){
		this.idEstadia = idEstadia;
		this.fechaLlegada = fechaLlegada;
		this.fechaSalida = fechaSalida;
		this.numeroPersonas = numPersonas;
		this.checkin = checkIn;
		this.pagado = pago;
		this.setIdCliente(idCliente);
		this.setIdPlan(idPlan);
		this.setIdHabitacion(idHabitacion);
		this.idConvencion = idConvencion;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOEstadia#getIdEstadia()
	 */
	@Override
	public long getIdEstadia() {
		return idEstadia;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOEstadia#setIdEstadia(long)
	 */
	@Override
	public void setIdEstadia(long idEstadia) {
		this.idEstadia = idEstadia;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOEstadia#getFechaLlegada()
	 */
	@Override
	public Timestamp getFechaLlegada() {
		return fechaLlegada;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOEstadia#setFechaLlegada(java.sql.Timestamp)
	 */
	@Override
	public void setFechaLlegada(Timestamp fechaLlegada) {
		this.fechaLlegada = fechaLlegada;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOEstadia#getFechaSalida()
	 */
	@Override
	public Timestamp getFechaSalida() {
		return fechaSalida;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOEstadia#setFechaSalida(java.sql.Timestamp)
	 */
	@Override
	public void setFechaSalida(Timestamp fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOEstadia#getNumeroPersonas()
	 */
	@Override
	public int getNumeroPersonas() {
		return numeroPersonas;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOEstadia#setNumeroPersonas(int)
	 */
	@Override
	public void setNumeroPersonas(int numeroPersonas) {
		this.numeroPersonas = numeroPersonas;
	}
	
	public long getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(long idPlan) {
		this.idPlan = idPlan;
	}
	
	public long getIdHabitacion() {
		return idHabitacion;
	}

	public void setIdHabitacion(long idHabitacion) {
		this.idHabitacion = idHabitacion;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOEstadia#getCheckin()
	 */
	@Override
	public int getCheckin() {
		return checkin;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOEstadia#setCheckin(int)
	 */
	@Override
	public void setCheckin(int checkin) {
		this.checkin = checkin;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOEstadia#getPagado()
	 */
	@Override
	public int getPagado() {
		return pagado;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOEstadia#setPagado(int)
	 */
	@Override
	public void setPagado(int pagado) {
		this.pagado = pagado;
	}
	
	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	

	public Long getIdConvencion() {
		return idConvencion;
	}

	public void setIdConvencion(Long idConvencion) {
		this.idConvencion = idConvencion;
	}
	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOEstadia#toString()
	 */
	@Override
	public String toString() 
	{
		String a = "Estadia [id=" + idEstadia+ ", fecha llegada=" + fechaLlegada.toString() + 
				", fecha salida=" + fechaSalida.toString() + ", numero personas =" + numeroPersonas;
		if(checkin == 0){
			a += ", checkin =" + "No está chequeado";
		}
		else{
			a += ", checkin =" + "Está chequeado";
		}
		if(pagado == 0){
			a += ", checkin =" + "No ha pagado";
		}
		else{
			a += ", checkin =" + "Ya pago";
		}
		a += "]";
		
		return a; 
		
	}


	

}

