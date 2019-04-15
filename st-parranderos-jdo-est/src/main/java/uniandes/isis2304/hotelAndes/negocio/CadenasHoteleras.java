package uniandes.isis2304.hotelAndes.negocio;

import java.util.LinkedList;
import java.util.List;

public class CadenasHoteleras implements VOCadenaHotelera {

		/* ****************************************************************
		 * 			Atributos
		 *****************************************************************/
		/**
		 * El identificador �NICO de la cadena hotelera
		 */
		private long idCadena;	
		
		/**
		 * El nombre de la cadena
		 */
		private String nombreCadena;
		
		/**
		 * Los hoteles de una cadena hotelera
		 * Cada visita es una dupla de objetos [CadenaHotelera, Hotel]
		 * 
		 */

		
		/* ****************************************************************
		 * 			Métodos
		 *****************************************************************/
		/**
		 * Constructor por defecto
		 */
		public CadenasHoteleras() 
		{
			this.idCadena = 0;
			this.nombreCadena = "";
		}

		/**
		 * Constructor con valores
		 * @param id - El id de la cadena
		 * @param nombre - El nombre de la cadena
		 */
		public CadenasHoteleras(long id, String nombreCadena) 
		{
			this.idCadena = id;
			this.nombreCadena = nombreCadena;
		}

		/* (non-Javadoc)
		 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOCadenaHotelera#getIdCadena()
		 */

		@Override
		public long getIdCadena() {
			return idCadena;
		}

		/* (non-Javadoc)
		 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOCadenaHotelera#setIdCadena(long)
		 */
		@Override
		public void setIdCadena(long idCadena) {
			this.idCadena = idCadena;
		}

		/* (non-Javadoc)
		 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOCadenaHotelera#getNombreCadena()
		 */
		@Override
		public String getNombreCadena() {
			return nombreCadena;
		}

		/* (non-Javadoc)
		 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOCadenaHotelera#setNombreCadena(java.lang.String)
		 */
		@Override
		public void setNombreCadena(String nombreCadena) {
			this.nombreCadena = nombreCadena;
		}



		/* (non-Javadoc)
		 * @see main.java.uniandes.isis2304.hotelAndes.negocio.VOCadenaHotelera#toString()
		 */
		@Override
		public String toString() 
		{
			return "CadenaHotelera [id=" + idCadena + ", nombre=" + nombreCadena + "]";
		}
}
