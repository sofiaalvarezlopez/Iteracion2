package uniandes.isis2304.hotelAndes.negocio;
import java.sql.Timestamp;



public class Plan implements VOPlan
{
	private long idPlan;
	private String tipo;
	private double costo;
	private double descuentoAlojamiento;
	private Timestamp fechaVencimiento;
	
	public Plan(){
		idPlan = 0;
		tipo = "";
		costo = 0;
		descuentoAlojamiento = 0;
		fechaVencimiento = new Timestamp (0);
	}
	
	public Plan(long idPlan, String tipo, double costo, double descuentoAlojamiento, Timestamp fechaVencimiento)
	{
		this.idPlan = idPlan;
		this.tipo = tipo;
		this.costo = costo;
		this.descuentoAlojamiento = descuentoAlojamiento;
		this.fechaVencimiento = fechaVencimiento;
		
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOPlan#getIdPlan()
	 */
	@Override
	public long getIdPlan() {
		return idPlan;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOPlan#setIdPlan(long)
	 */
	@Override
	public void setIdPlan(long idPlan) {
		this.idPlan = idPlan;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOPlan#getTipo()
	 */
	@Override
	public String getTipo() {
		return tipo;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOPlan#setTipo(java.lang.String)
	 */
	@Override
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOPlan#getCosto()
	 */
	@Override
	public double getCosto() {
		return costo;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOPlan#setCosto(double)
	 */
	@Override
	public void setCosto(double costo) {
		this.costo = costo;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOPlan#getDescuentoAlojamiento()
	 */
	@Override
	public double getDescuentoAlojamiento() {
		return descuentoAlojamiento;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOPlan#setDescuentoAlojamiento(double)
	 */
	@Override
	public void setDescuentoAlojamiento(double descuentoAlojamiento) {
		this.descuentoAlojamiento = descuentoAlojamiento;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOPlan#getFechaVencimiento()
	 */
	@Override
	public Timestamp getFechaVencimiento() {
		return fechaVencimiento;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOPlan#setFechaVencimiento(java.sql.Timestamp)
	 */
	@Override
	public void setFechaVencimiento(Timestamp fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}
	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOPlan#toString()
	 */
	@Override
	public String toString() 
	{
		return "Plan [id=" + idPlan + ", tipo=" + tipo + ", costo=" + costo + ", descuento alojamiento=" + descuentoAlojamiento + ", fecha vencimiento plan =" + fechaVencimiento + "]";
	}
	
	
	

}

