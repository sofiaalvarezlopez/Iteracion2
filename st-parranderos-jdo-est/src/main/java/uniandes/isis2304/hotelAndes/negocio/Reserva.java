package uniandes.isis2304.hotelAndes.negocio;


public class Reserva implements VOReserva
{
	private long idReserva;
	private long idConsumo;
	private long idEstadia;
	private long idServicio;
	private long idHorario;
	
	public Reserva(){
		idReserva = 0;
		idConsumo = 0;
		idEstadia = 0;
		idServicio = 0;
		idHorario = 0;
	}
	
	public Reserva(long idReserva, long idConsumo, long idEstadia, long idServicio, long idHorario){
		this.idReserva = idReserva;
		this.idConsumo = idConsumo;
		this.idEstadia = idEstadia;
		this.idServicio = idServicio;
		this.idHorario = idHorario;
	}

	public long getIdReserva() {
		return idReserva;
	}

	public void setIdReserva(long idReserva) {
		this.idReserva = idReserva;
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
		return "Reserva [idReserva=" + idReserva + ", id del consumo=" + idConsumo + "]";
	}
	
	
}

