package uniandes.isis2304.hotelAndes.negocio;


public class Reservas implements VOReserva
{
	private Long numReserva;
	private Long idConsumo;
	private Long idEstadia;
	private Long idServicio;
	private Long idHorario;
	private Long idConvencion;
	private Long capacidad;
	
	public Reservas(){
		numReserva = 0L;
		idConsumo = 0L;
		idEstadia = 0L;
		idServicio = 0L;
		idHorario = 0L;
		setIdConvencion(0L);
		setCapacidad(0L);
	}
	
	public Reservas(Long idReserva, Long idConsumo, Long idEstadia, Long idServicio, Long idHorario, Long idConvencion, Long capacidad){
		this.numReserva = idReserva;
		this.idConsumo = idConsumo;
		this.idEstadia = idEstadia;
		this.idServicio = idServicio;
		this.idHorario = idHorario;
		this.setIdConvencion(idConvencion);
		this.setCapacidad(capacidad);
	}

	public Long getNumReserva() {
		return numReserva;
	}

	public void setNumReserva(Long numReserva) {
		this.numReserva = numReserva;
	}

	public Long getIdConsumo() {
		return idConsumo;
	}

	public void setIdConsumo(Long idConsumo) {
		this.idConsumo = idConsumo;
	}
	
	/**
	 * @return the idEstadia
	 */
	public Long getIdEstadia() {
		return idEstadia;
	}

	/**
	 * @param idEstadia the idEstadia to set
	 */
	public void setIdEstadia(Long idEstadia) {
		this.idEstadia = idEstadia;
	}

	/**
	 * @return the idServicio
	 */
	public Long getIdServicio() {
		return idServicio;
	}

	/**
	 * @param idServicio the idServicio to set
	 */
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}

	/**
	 * @return the idHorario
	 */
	public Long getIdHorario() {
		return idHorario;
	}

	/**
	 * @param idHorario the idHorario to set
	 */
	public void setIdHorario(Long idHorario) {
		this.idHorario = idHorario;
	}
	
	public String toString() 
	{
		return "Reserva [idReserva=" + numReserva + ", id del consumo=" + idConsumo + "]";
	}

	public Long getIdConvencion() {
		return idConvencion;
	}

	public void setIdConvencion(Long idConvencion) {
		this.idConvencion = idConvencion;
	}

	public Long getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(Long capacidad) {
		this.capacidad = capacidad;
	}
	
	
}

