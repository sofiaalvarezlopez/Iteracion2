package uniandes.isis2304.hotelAndes.negocio;


/**
 * <!-- begin-user-doc -->
 * <!--  end-user-doc  -->
 * @generated
 */

public class Servicio implements VOServicio
{
	protected long idServicio;

	private String nombreServicio;	

	public Servicio(){
		idServicio = 0;
		nombreServicio = "";
	}
	
	public Servicio(long idServicio, String nombreServicio){
		this.idServicio = idServicio;
		this.nombreServicio = nombreServicio;
	}
	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOServicio#getIdServicio()
	 */
	@Override
	public long getIdServicio() {
		return idServicio;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOServicio#setIdServicio(long)
	 */
	@Override
	public void setIdServicio(long idServicio) {
		this.idServicio = idServicio;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOServicio#getNombreServicio()
	 */
	@Override
	public String getNombreServicio() {
		return nombreServicio;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOServicio#setNombreServicio(java.lang.String)
	 */
	@Override
	public void setNombreServicio(String nombreServicio) {
		this.nombreServicio = nombreServicio;
	}
	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOServicio#toString()
	 */
	@Override
	public String toString() 
	{
		return "Producto [id=" + idServicio + ", nombre servicio=" + nombreServicio + "]";
	}
	
	
	
	
	

	

}

