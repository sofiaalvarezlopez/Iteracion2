package uniandes.isis2304.hotelAndes.negocio;
import java.util.HashSet;
import java.util.Set;


public class TipoUsuario implements VOTipoUsuario
{
	
	
	private long idTipoUsuario;

	private String tipoUsuario;

	public TipoUsuario(){
		idTipoUsuario = 0;
		tipoUsuario = "";
	}
	
	public TipoUsuario(long idTipoUsuario, String tipoUsuario){
		this.idTipoUsuario = idTipoUsuario;
		this.tipoUsuario = tipoUsuario;
	}

	public long getIdTipoUsuario() {
		return idTipoUsuario;
	}

	public void setIdTipoUsuario(long idTipoUsuario) {
		this.idTipoUsuario = idTipoUsuario;
	}

	public String getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	public String toString() 
	{
		return "Tipo Usuario [idTipoUsuario=" + idTipoUsuario + ", tipoUsuario =" + tipoUsuario + "]";
	}

}

