package uniandes.isis2304.hotelAndes.negocio;
import java.sql.Timestamp;



public class Horario implements VOHorario
{
	private long idHorario;
	private String dia;
	private String horaApertura;
	private String horaCierre;
	private String duracion;
	private Timestamp fecha;
	private long idServicio;
	
	public Horario(){
		idHorario = 0;
		dia = "";
		horaApertura = "";
		horaCierre = "";
		duracion = "";
		fecha = new Timestamp (0); 
		setIdServicio(0);
	}
	
	public Horario(long idHorario, String dia, String horaApertura, String horaCierre, String duracion, Timestamp fecha, long idServicio){
		this.idHorario = idHorario;
		this.dia = dia;
		this.horaApertura = horaApertura;
		this.horaCierre = horaCierre;
		this.duracion = duracion;
		this.fecha = fecha;
		this.setIdServicio(0);
		
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
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHorario#getHoraApertura()
	 */
	@Override
	public String getHoraApertura() {
		return horaApertura;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHorario#setHoraApertura(java.lang.String)
	 */
	@Override
	public void setHoraApertura(String horaApertura) {
		this.horaApertura = horaApertura;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHorario#getHoraCierre()
	 */
	@Override
	public String getHoraCierre() {
		return horaCierre;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHorario#setHoraCierre(java.lang.String)
	 */
	@Override
	public void setHoraCierre(String horaCierre) {
		this.horaCierre = horaCierre;
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
	public Timestamp getFecha() {
		return fecha;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHorario#setFecha(java.sql.Timestamp)
	 */
	@Override
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}
	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOHorario#toString()
	 */
	@Override
	public String toString() 
	{
		return "Horario [id=" + idHorario + ", dia=" + dia + ", hora apertura =" + horaApertura + ", hora cierre =" + horaCierre + ", duracion =" + duracion + ", fecha = " + fecha.toString() + "]";
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

