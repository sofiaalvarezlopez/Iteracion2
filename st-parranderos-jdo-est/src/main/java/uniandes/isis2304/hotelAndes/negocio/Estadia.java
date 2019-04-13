package uniandes.isis2304.hotelAndes.negocio;

import java.sql.Timestamp;

/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Estadia implements VOEstadia
{
	private long idEstadia;
	private Timestamp fechaLlegada;
	private Timestamp fechaSalida;
	private int numeroPersonas;
	private int checkin; 
	private int pagado;
	private String tipoDocCliente;
	private long numDocCliente;
	private long idPlan;
	private long idHabitacion;
	
	public Estadia(){
		idEstadia = 0;
		fechaLlegada = new Timestamp(0);
		fechaSalida = new Timestamp(0);
		numeroPersonas = 0;
		checkin = 0;
		pagado = 0;
		setTipoDocCliente("");
		setNumDocCliente(0);
		setIdPlan(0);
		setIdHabitacion(0);
	}
	
	public Estadia(long idEstadia, Timestamp fechaLlegada, Timestamp fechaSalida, int numeroPersonas, int checkin, int pagado, String tipoDocCliente, long numDocCliente, long idPlan, long idHabitacion){
		this.idEstadia = idEstadia;
		this.fechaLlegada = fechaLlegada;
		this.fechaSalida = fechaSalida;
		this.numeroPersonas = numeroPersonas;
		this.checkin = checkin;
		this.pagado = pagado;
		this.setTipoDocCliente(tipoDocCliente);
		this.setNumDocCliente(numDocCliente);
		this.setIdPlan(idPlan);
		this.setIdHabitacion(idHabitacion);
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

	public String getTipoDocCliente() {
		return tipoDocCliente;
	}

	public void setTipoDocCliente(String tipoDocCliente) {
		this.tipoDocCliente = tipoDocCliente;
	}

	public long getNumDocCliente() {
		return numDocCliente;
	}

	public void setNumDocCliente(long numDocCliente) {
		this.numDocCliente = numDocCliente;
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
	

}

