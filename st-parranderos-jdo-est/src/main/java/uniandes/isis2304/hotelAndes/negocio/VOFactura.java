package uniandes.isis2304.hotelAndes.negocio;

import java.sql.Timestamp;

public interface VOFactura {

	long getNumFactura();

	void setNumFactura(long numFactura);

	Timestamp getFecha();

	void setFecha(Timestamp fecha);

	int getPagado();

	void setPagado(int pagado);

	double getValor();

	void setValor(double valor);

	void setIdConsumo(long idConsumo);

	String toString();

	long getIdConsumo();

}