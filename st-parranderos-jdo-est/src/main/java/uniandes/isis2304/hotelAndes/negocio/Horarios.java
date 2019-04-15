package uniandes.isis2304.hotelAndes.negocio;
import java.sql.Timestamp;



public class Horarios implements VOHorario
{
	private long idHorario;
	private String duracion;
	private long idServicio;
	private Timestamp fechaInicio;
	private String dia;
	private String horaInicio;
	private String horaFin;
	private Timestamp fechaFin;
	


	
	public Horarios(){
		idHorario = 0;
		dia = "";
		horaInicio = "";
		horaFin = "";
		duracion = "";
		fechaInicio = new Timestamp (0); 
		fechaFin = new Timestamp(0);
		setIdServicio(0);
	}
	
	public Horarios(long idHorario, String dia, String horaInicio, String horaFin, String duracion, Timestamp fechaInicio, long idServicio, Timestamp fechaFin){
		this.idHorario = idHorario;
		this.dia = dia;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
		this.duracion = duracion;
		this.fechaInicio = fechaInicio;
		this.setIdServicio(0);
		this.fechaFin = fechaFin;
		
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHorario#getIdHorario()
	 */
	@Override
	public long getIdHorario() {
		return idHorario;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHorario#setIdHorario(long)
	 */
	@Override
	public void setIdHorario(long idHorario) {
		this.idHorario = idHorario;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHorario#getDia()
	 */
	@Override
	public String getDia() {
		return dia;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHorario#setDia(java.lang.String)
	 */
	@Override
	public void setDia(String dia) {
		this.dia = dia;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHorario#getHoraInicio()
	 */
	@Override
	public String getHoraInicio() {
		return horaInicio;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHorario#setHoraInicio(java.lang.String)
	 */
	@Override
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHorario#getHoraFin()
	 */
	@Override
	public String getHoraFin() {
		return horaFin;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHorario#setHoraFin(java.lang.String)
	 */
	@Override
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHorario#getDuracion()
	 */
	@Override
	public String getDuracion() {
		return duracion;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHorario#setDuracion(java.lang.String)
	 */
	@Override
	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHorario#getFecha()
	 */
	@Override
	public Timestamp getFechaInicio() {
		return fechaInicio;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHorario#setFecha(java.sql.Timestamp)
	 */
	@Override
	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	
	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHorario#getFecha()
	 */
	@Override
	public Timestamp getFechaFin() {
		return fechaFin;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHorario#setFecha(java.sql.Timestamp)
	 */
	@Override
	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
	}
	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHorario#toString()
	 */
	@Override
	public String toString() 
	{
		return "Horario [id=" + idHorario + ", dia=" + dia + ", hora Inicio =" + horaInicio + ", hora Fin =" + horaFin + ", duracion =" + duracion + ", fechaInicio = " + fechaInicio.toString() + ", fechaFin = " + fechaFin.toString() +"]";
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
	
	

}

