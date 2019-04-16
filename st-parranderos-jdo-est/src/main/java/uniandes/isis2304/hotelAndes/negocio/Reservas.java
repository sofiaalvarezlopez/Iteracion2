package uniandes.isis2304.hotelAndes.negocio;


public class Reservas implements VOReserva
{
	private long numReserva;
	private long idConsumo;
	private long idEstadia;
	private long idServicio;
	private long idHorario;
	private long idConvencion;
	private long capacidad;
	
	public Reservas(){
		numReserva = 0;
		idConsumo = 0;
		idEstadia = 0;
		idServicio = 0;
		idHorario = 0;
		setIdConvencion(0);
		setCapacidad(0);
	}
	
	public Reservas(long idReserva, long idConsumo, long idEstadia, long idServicio, long idHorario, long idConvencion, long capacidad){
		this.numReserva = idReserva;
		this.idConsumo = idConsumo;
		this.idEstadia = idEstadia;
		this.idServicio = idServicio;
		this.idHorario = idHorario;
		this.setIdConvencion(idConvencion);
		this.setCapacidad(capacidad);
	}

	public long getNumReserva() {
		return numReserva;
	}

	public void setNumReserva(long numReserva) {
		this.numReserva = numReserva;
	}

	public long getIdConsumo() {
		return idConsumo;
	}

	public void setIdConsumo(long idConsumo) {
		this.idConsumo = idConsumo;
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
	 * @return the idHorario
	 */
	public long getIdHorario() {
		return idHorario;
	}

	/**
	 * @param idHorario the idHorario to set
	 */
	public void setIdHorario(long idHorario) {
		this.idHorario = idHorario;
	}
	
	public String toString() 
	{
		return "Reserva [idReserva=" + numReserva + ", id del consumo=" + idConsumo + "]";
	}

	public long getIdConvencion() {
		return idConvencion;
	}

	public void setIdConvencion(long idConvencion) {
		this.idConvencion = idConvencion;
	}

	public long getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(long capacidad) {
		this.capacidad = capacidad;
	}
	
	
}

