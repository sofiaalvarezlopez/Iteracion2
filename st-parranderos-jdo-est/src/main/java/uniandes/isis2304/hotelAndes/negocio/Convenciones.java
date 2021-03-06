package uniandes.isis2304.hotelAndes.negocio;

import java.sql.Timestamp;

public class Convenciones {

	private long idConvencion;
	private String nombre;
	private int capacidad;
	private Timestamp fechaInicio;
	private Timestamp fechaFin;
	private long idOrganizador;
	private long idPlan;
	private int pago;

	public Convenciones(){
		idConvencion = 0;
		nombre = "";
		capacidad = 0;
		fechaInicio = new Timestamp(0);
		fechaFin = new Timestamp(0);
		idOrganizador = 0;
		idPlan = 0;
		pago = 0;

	}


	public Convenciones(long idConvencion, String nombre, int capacidad, Timestamp fechaInicio, Timestamp fechaFin,
			long idOrganizador, long idPlan, int pago) {
		super();
		this.idConvencion = idConvencion;
		this.nombre = nombre;
		this.capacidad = capacidad;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.idOrganizador = idOrganizador;
		this.idPlan = idPlan;
		this.pago = pago;
	}
	public long getIdConvencion() {
		return idConvencion;
	}
	public void setIdConvencion(long idConvencion) {
		this.idConvencion = idConvencion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public Timestamp getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public Timestamp getFechaFin() {
		return fechaFin;
	}
	public void setFechaFin(Timestamp fechaFin) {
		this.fechaFin = fechaFin;
	}
	public long getIdOrganizador() {
		return idOrganizador;
	}
	public void setIdOrganizador(long idOrganizador) {
		this.idOrganizador = idOrganizador;
	}
	public long getIdPlan() {
		return idPlan;
	}
	public void setIdPlan(long idPlan) {
		this.idPlan = idPlan;
	}
	public int getPago() {
		return pago;
	}
	public void setPago(int pago) {
		this.pago = pago;
	}


}
