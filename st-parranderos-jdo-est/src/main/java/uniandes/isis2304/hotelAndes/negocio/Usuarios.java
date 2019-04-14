package uniandes.isis2304.hotelAndes.negocio;


import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class Usuarios implements VOUsuarios
{
	private long numeroDocumento;	
	private String tipoDocumento;
	private String nombre;	
	private String correoElectronico;
	private long idTipoUsuario;


	public Usuarios(){
		numeroDocumento = 0;
		tipoDocumento = "";
		nombre = "";
		correoElectronico = "";
		idTipoUsuario = 0;
	}
	public Usuarios (long numero_documento, String tipo_documento, String nombre, String correo_electronico, long  id_tipo_usuario)
	{
		this.numeroDocumento = numero_documento;
		this.tipoDocumento = tipo_documento;
		this.nombre = nombre;
		this.correoElectronico = correo_electronico;
		this.idTipoUsuario = id_tipo_usuario;
	}


	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOUsuario#getnumero_documento()
	 */
	public long getNumeroDocumento() {
		return numeroDocumento;
	}


	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOUsuario#setnumero_documento(long)
	 */
	
	public void setNumeroDocumento(long numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}


	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOUsuario#gettipo_documento()
	 */
	public String getTipoDocumento() {
		return tipoDocumento;
	}


	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOUsuario#settipo_documento(java.lang.String)
	 */
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}


	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOUsuario#getNombre()
	 */
	public String getNombre() {
		return nombre;
	}


	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOUsuario#setNombre(java.lang.String)
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOUsuario#getcorreo_electronico()
	 */
	public String getCorreoElectronico() {
		return correoElectronico;
	}


	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOUsuario#setcorreo_electronico(java.lang.String)
	 */
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	
	public long getIdTipoUsuario() {
		return idTipoUsuario;
	}


	public void setIdTipoUsuario(long id_tipo_usuario) {
		this.idTipoUsuario = id_tipo_usuario;
	}
	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOUsuario#toString()
	 */
	public String toString() 
	{
		return "Usuario [numero documento usuario=" + numeroDocumento + ", tipo_documento =" + tipoDocumento + 
				", nombre =" + nombre + ", correo electronico =" + correoElectronico + "]";
	}


	
	


}

