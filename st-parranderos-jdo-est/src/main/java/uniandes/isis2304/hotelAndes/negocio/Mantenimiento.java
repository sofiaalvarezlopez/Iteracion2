package uniandes.isis2304.hotelAndes.negocio;

public class Mantenimiento {
	
	private Long idMantenimiento;
	private String causa;
	private Long idHorario;
	private Long idServicio;
	private Long numHabitacion;
	private int finalizado;
	
	public Mantenimiento(){
		idMantenimiento = Long.valueOf("0");
		causa = "";
		idHorario =  Long.valueOf("0");
		idServicio =  Long.valueOf("0");
		numHabitacion =  Long.valueOf("0");
		setFinalizado(0);
	}
	
	public Mantenimiento(Long idMantenimiento, String causa, Long idHorario, Long idServicio, Long numHabitacion, int finalizado) {
		this.idMantenimiento = idMantenimiento;
		this.causa = causa;
		this.idHorario = idHorario;
		this.idServicio = idServicio;
		this.numHabitacion = numHabitacion;
		this.setFinalizado(finalizado);
	}

	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOMantenimiento#getIdMantenimiento()
	 */
	public Long getIdMantenimiento() {
		return idMantenimiento;
	}
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOMantenimiento#setIdMantenimiento(Long)
	 */
	public void setIdMantenimiento(Long idMantenimiento) {
		this.idMantenimiento = idMantenimiento;
	}
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOMantenimiento#getCausa()
	 */
	
	public String getCausa() {
		return causa;
	}
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOMantenimiento#setCausa(java.lang.String)
	 */
	public void setCausa(String causa) {
		this.causa = causa;
	}
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOMantenimiento#getIdHorario()
	 */
	public Long getIdHorario() {
		return idHorario;
	}
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOMantenimiento#setIdHorario(Long)
	 */
	public void setIdHorario(Long idHorario) {
		this.idHorario = idHorario;
	}
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOMantenimiento#getIdServicio()
	 */
	public Long getIdServicio() {
		return idServicio;
	}
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOMantenimiento#setIdServicio(Long)
	 */
	public void setIdServicio(Long idServicio) {
		this.idServicio = idServicio;
	}
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOMantenimiento#getNumHabitacion()
	 */
	public Long getNumHabitacion() {
		return numHabitacion;
	}
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOMantenimiento#setNumHabitacion(Long)
	 */
	public void setNumHabitacion(Long numHabitacion) {
		this.numHabitacion = numHabitacion;
	}
	
	public int getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(int finalizado) {
		this.finalizado = finalizado;
	}

	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOMantenimiento#toString()
	 */
	@Override
	public String toString() {
		return "Mantenimiento [idMantenimiento=" + idMantenimiento + ", causa=" + causa + ", idHorario=" + idHorario
				+ ", idServicio=" + idServicio + ", numHabitacion=" + numHabitacion + "]";
	}
	

}
