package uniandes.isis2304.hotelAndes.negocio;


public class Descuento implements VODescuento
{
	private long idDescuento;
	private double valor;
	private int numVeces;
	private long idPlan;
	private long idServicio;
	private long idProducto;
	
	public Descuento(){
		this.idDescuento = 0;
		valor = 0;
		numVeces = 0;
		setIdPlan(0);
		setIdServicio(0);
		setIdProducto(0);
	}
	
	public Descuento(long idDescuento, double valor, int numVeces, long idPlan, long idServicio, long idProducto){
		this.idDescuento = idDescuento;
		this.valor = valor;
		this.numVeces = numVeces;
		this.setIdPlan(idPlan);
		this.setIdServicio(idServicio);
		this.setIdProducto(idProducto);
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VODescuento#getIdDescuento()
	 */
	@Override
	public long getIdDescuento() {
		return idDescuento;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VODescuento#setIdDescuento(long)
	 */
	@Override
	public void setIdDescuento(long idDescuento) {
		this.idDescuento = idDescuento;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VODescuento#getValor()
	 */
	@Override
	public double getValor() {
		return valor;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VODescuento#setValor(double)
	 */
	@Override
	public void setValor(double valor) {
		this.valor = valor;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VODescuento#getNumVeces()
	 */
	@Override
	public int getNumVeces() {
		return numVeces;
	}

	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VODescuento#setNumVeces(int)
	 */
	@Override
	public void setNumVeces(int numVeces) {
		this.numVeces = numVeces;
	}
	
	/* (non-Javadoc)
	 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VODescuento#toString()
	 */
	@Override
	public String toString() 
	{
		return "Descuento [id=" + idDescuento + ", valor=" + valor + ", numVeces=" + numVeces + "]";
	}

	public long getIdPlan() {
		return idPlan;
	}

	public void setIdPlan(long idPlan) {
		this.idPlan = idPlan;
	}

	/**
	 * @return the idServicio
	 */
	public long getIdServicio() {
		return idServicio;
	}

	/**
	 * @param idServicio the idServicio to set
	 */
	public void setIdServicio(long idServicio) {
		this.idServicio = idServicio;
	}

	/**
	 * @return the idProducto
	 */
	public long getIdProducto() {
		return idProducto;
	}

	/**
	 * @param idProducto the idProducto to set
	 */
	public void setIdProducto(long idProducto) {
		this.idProducto = idProducto;
	}

}

