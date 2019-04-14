package uniandes.isis2304.hotelAndes.negocio;


import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class Usuario implements VOUsuario
{
	private long numero_documento;	
	private String tipo_documento;
	private String nombre;	
	private String correo_electronico;
	private long id_tipo_usuario;


	public Usuario(){
		numero_documento = 0;
		tipo_documento = "";
		nombre = "";
		correo_electronico = "";
		setid_tipo_usuario(0);
	}
	public Usuario (long numero_documento, String tipo_documento, String nombre, String correo_electronico, long  id_tipo_usuario)
	{
		this.numero_documento = numero_documento;
		this.tipo_documento = tipo_documento;
		this.nombre = nombre;
		this.correo_electronico = correo_electronico;
		this.id_tipo_usuario = id_tipo_usuario;
	}


	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOUsuario#getnumero_documento()
	 */
	@Override
	public long getNumeroDocumento() {
		return numero_documento;
	}


	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOUsuario#setnumero_documento(long)
	 */
	@Override
	public void setNumeroDocumento(long numeroDocumento) {
		this.numero_documento = numeroDocumento;
	}


	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOUsuario#gettipo_documento()
	 */
	@Override
	public String getTipoDocumento() {
		return tipo_documento;
	}


	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOUsuario#settipo_documento(java.lang.String)
	 */
	@Override
	public void setTipoDocumento(String tipo_documento) {
		this.tipo_documento = tipo_documento;
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
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOUsuario#getcorreo_electronico()
	 */
	@Override
	public String getCorreoElectronico() {
		return correo_electronico;
	}


	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOUsuario#setcorreo_electronico(java.lang.String)
	 */
	@Override
	public void setCorreoElectronico(String correo_electronico) {
		this.correo_electronico = correo_electronico;
	}
	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOUsuario#toString()
	 */
	@Override
	public String toString() 
	{
		return "Usuario [numero documento usuario=" + numero_documento + ", tipo_documento =" + tipo_documento + 
				", nombre =" + nombre + ", correo electronico =" + correo_electronico + "]";
	}


	public long getid_tipo_usuario() {
		return id_tipo_usuario;
	}


	public void setid_tipo_usuario(long id_tipo_usuario) {
		this.id_tipo_usuario = id_tipo_usuario;
	}
	


}

