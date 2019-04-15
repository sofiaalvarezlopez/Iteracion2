package uniandes.isis2304.hotelAndes.negocio;

import java.sql.Timestamp;

public interface VOFacturas {

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#getNumFactura()
	 */
	long getNumFactura();

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#setNumFactura(long)
	 */
	void setNumFactura(long numFactura);

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#getFecha()
	 */
	Timestamp getFecha();

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#setFecha(java.sql.Timestamp)
	 */
	void setFecha(Timestamp fecha);

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#getPagado()
	 */
	int getFuePagada();

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#setPagado(int)
	 */
	void setFuePagada(int fuePagada);

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#getValor()
	 */
	double getPrecio();

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#setValor(double)
	 */
	void setPrecio(double precio);

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#getNombreConsumo()
	 */
	long getIdProducto();

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#setNombreConsumo(java.lang.String)
	 */
	void setIdProducto(long idProducto);

	/**
	 * @return the idDotacion
	 */
	long getIdDotacion();

	/**
	 * @param idDotacion the idDotacion to set
	 */
	void setIdDotacion(long idDotacion);

	/**
	 * @return the idServicio
	 */
	long getIdServicio();

	/**
	 * @param idServicio the idServicio to set
	 */
	void setIdServicio(long idServicio);

	/**
	 * @return the idEstadia
	 */
	long getIdEstadia();

	/**
	 * @param idEstadia the idEstadia to set
	 */
	void setIdEstadia(long idEstadia);

	/**
	 * @return the numDocEmpleado
	 */
	long getNumDocEmpleado();

	/**
	 * @param numDocEmpleado the numDocEmpleado to set
	 */
	void setNumDocEmpleado(long numDocEmpleado);

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOFactura#toString()
	 */
	String toString();

}