package uniandes.isis2304.hotelAndes.negocio;
import java.util.HashSet;
import java.util.Set;


public class TipoUsuario implements VOTipoUsuario
{
	
	
	private long idTipoUsuario;

	private String nombreTipoUsuario;

	public TipoUsuario(){
		idTipoUsuario = 0;
		nombreTipoUsuario = "";
	}
	
	public TipoUsuario(long idTipoUsuario, String tipoUsuario){
		this.idTipoUsuario = idTipoUsuario;
		this.nombreTipoUsuario = tipoUsuario;
	}

	public long getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(long idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public String getNombreTipoUsuario() {
		return nombreTipoUsuario;
	}

	public void setNombreTipoUsuario(String tipoUsuario) {
		this.nombreTipoUsuario = tipoUsuario;
	}
	
	public String toString() 
	{
		return "Tipo Usuario [idTipoUsuario=" + idTipoUsuario + ", tipoUsuario =" + nombreTipoUsuario + "]";
	}

}

