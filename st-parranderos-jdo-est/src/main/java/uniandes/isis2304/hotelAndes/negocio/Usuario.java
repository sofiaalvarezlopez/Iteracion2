package uniandes.isis2304.hotelAndes.negocio;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class Usuario implements VOUsuario
{
	private long numDocumento;	
	private String tipoDocumento;
	private String nombre;	
	private String correoElectronico;
	private long idTipoUsuario;


	public Usuario(){
		numDocumento = 0;
		tipoDocumento = "";
		nombre = "";
		correoElectronico = "";
		setIdTipoUsuario(0);
	}
	public Usuario (long numDocumento, String tipoDocumento, String nombre, String correoElectronico, long  idTipoUsuario)
	{
		this.numDocumento = numDocumento;
		this.tipoDocumento = tipoDocumento;
		this.nombre = nombre;
		this.correoElectronico = correoElectronico;
		this.idTipoUsuario = idTipoUsuario;
	}


	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOUsuario#getNumDocumento()
	 */
	@Override
	public long getNumDocumento() {
		return numDocumento;
	}


	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOUsuario#setNumDocumento(long)
	 */
	@Override
	public void setNumDocumento(long numDocumento) {
		this.numDocumento = numDocumento;
	}


	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOUsuario#getTipoDocumento()
	 */
	@Override
	public String getTipoDocumento() {
		return tipoDocumento;
	}


	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOUsuario#setTipoDocumento(java.lang.String)
	 */
	@Override
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}


	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOUsuario#getNombre()
	 */
	@Override
	public String getNombre() {
		return nombre;
	}


	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOUsuario#setNombre(java.lang.String)
	 */
	@Override
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOUsuario#getCorreoElectronico()
	 */
	@Override
	public String getCorreoElectronico() {
		return correoElectronico;
	}


	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOUsuario#setCorreoElectronico(java.lang.String)
	 */
	@Override
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOUsuario#toString()
	 */
	@Override
	public String toString() 
	{
		return "Usuario [numero documento usuario=" + numDocumento + ", tipoDocumento =" + tipoDocumento + 
				", nombre =" + nombre + ", correo electronico =" + correoElectronico + "]";
	}


	public long getIdTipoUsuario() {
		return idTipoUsuario;
	}


	public void setIdTipoUsuario(long idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}
	


}

