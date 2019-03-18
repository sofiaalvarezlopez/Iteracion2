package uniandes.isis2304.hotelAndes.negocio;

import java.sql.Timestamp;

public interface VOPlan {

	long getIdPlan();

	void setIdPlan(long idPlan);

	String getTipo();

	void setTipo(String tipo);

	double getCosto();

	void setCosto(double costo);

	double getDescuentoAlojamiento();

	void setDescuentoAlojamiento(double descuentoAlojamiento);

	Timestamp getFechaVencimiento();

	void setFechaVencimiento(Timestamp fechaVencimiento);

	String toString();

}