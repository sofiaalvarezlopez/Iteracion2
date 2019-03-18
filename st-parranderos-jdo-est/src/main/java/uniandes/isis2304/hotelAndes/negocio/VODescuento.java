package uniandes.isis2304.hotelAndes.negocio;

public interface VODescuento {

	long getIdDescuento();

	void setIdDescuento(long idDescuento);

	double getValor();

	void setValor(double valor);

	int getNumVeces();

	void setNumVeces(int numVeces);

	String toString();

}