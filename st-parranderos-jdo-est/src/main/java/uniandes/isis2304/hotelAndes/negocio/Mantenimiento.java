package uniandes.isis2304.hotelAndes.negocio;

public class Mantenimiento implements VOMantenimiento {
	
	private long idMantenimiento;
	private String causa;
	private long idHorario;
	private long idServicio;
	private long numHabitacion;
	private int finalizado;
	
	public Mantenimiento(){
		idMantenimiento = 0;
		causa = "";
		idHorario = 0;
		idServicio = 0;
		numHabitacion = 0;
		setFinalizado(0);
	}
	
	public Mantenimiento(long idMantenimiento, String causa, long idHorario, long idServicio, long numHabitacion, int finalizado) {
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
	@Override
	public long getIdMantenimiento() {
		return idMantenimiento;
	}
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOMantenimiento#setIdMantenimiento(long)
	 */
	@Override
	public void setIdMantenimiento(long idMantenimiento) {
		this.idMantenimiento = idMantenimiento;
	}
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOMantenimiento#getCausa()
	 */
	@Override
	public String getCausa() {
		return causa;
	}
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOMantenimiento#setCausa(java.lang.String)
	 */
	@Override
	public void setCausa(String causa) {
		this.causa = causa;
	}
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOMantenimiento#getIdHorario()
	 */
	@Override
	public long getIdHorario() {
		return idHorario;
	}
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOMantenimiento#setIdHorario(long)
	 */
	@Override
	public void setIdHorario(long idHorario) {
		this.idHorario = idHorario;
	}
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOMantenimiento#getIdServicio()
	 */
	@Override
	public long getIdServicio() {
		return idServicio;
	}
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOMantenimiento#setIdServicio(long)
	 */
	@Override
	public void setIdServicio(long idServicio) {
		this.idServicio = idServicio;
	}
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOMantenimiento#getNumHabitacion()
	 */
	@Override
	public long getNumHabitacion() {
		return numHabitacion;
	}
	/* (non-Javadoc)
	 * @see uniandes.isis2304.hotelAndes.negocio.VOMantenimiento#setNumHabitacion(long)
	 */
	@Override
	public void setNumHabitacion(long numHabitacion) {
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
