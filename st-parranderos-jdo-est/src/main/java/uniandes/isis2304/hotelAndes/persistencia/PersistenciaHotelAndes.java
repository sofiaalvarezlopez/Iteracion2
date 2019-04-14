package uniandes.isis2304.hotelAndes.persistencia;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.*;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.hotelAndes.negocio.CadenaHotelera;
import uniandes.isis2304.hotelAndes.negocio.CaracteristicaAdicional;
import uniandes.isis2304.hotelAndes.negocio.Descuento;
import uniandes.isis2304.hotelAndes.negocio.Dotacion;
import uniandes.isis2304.hotelAndes.negocio.DotacionSalon;
import uniandes.isis2304.hotelAndes.negocio.Estadia;
import uniandes.isis2304.hotelAndes.negocio.Factura;
import uniandes.isis2304.hotelAndes.negocio.Habitacion;
import uniandes.isis2304.hotelAndes.negocio.Horario;
import uniandes.isis2304.hotelAndes.negocio.Hotel;
import uniandes.isis2304.hotelAndes.negocio.Plan;
import uniandes.isis2304.hotelAndes.negocio.Producto;
import uniandes.isis2304.hotelAndes.negocio.Reserva;
import uniandes.isis2304.hotelAndes.negocio.Salon;
import uniandes.isis2304.hotelAndes.negocio.Servicio;
import uniandes.isis2304.hotelAndes.negocio.ServicioAdicional;
import uniandes.isis2304.hotelAndes.negocio.TipoHabitacion;
import uniandes.isis2304.hotelAndes.negocio.TipoUsuario;
import uniandes.isis2304.hotelAndes.negocio.Usuario;
import uniandes.isis2304.hotelAndes.negocio.VentaProducto;



/**
 * Clase para el manejador de persistencia del proyecto HotelAndes
 * Traduce la informacion entre objetos Java y tuplas de la base de datos, en ambos sentidos
 * Sigue un patron SINGLETON (Solo puede haber UN objeto de esta clase) para comunicarse de manera correcta
 * con la base de datos
 * Se apoya en las clases SQL%, que son 
 * las que realizan el acceso a la base de datos
 * 
 * @author df.serrano y ms.alvarezl
 */
public class PersistenciaHotelAndes 
{
	/* **********************
	 * 			Constantes
	 ***********************/
	/**
	 * Logger para escribir la traza de la ejecuciÃ³n
	 */
	private static Logger log = Logger.getLogger(PersistenciaHotelAndes.class.getName());

	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/* **********************
	 * 			Atributos
	 ***********************/
	/**
	 * Atributo privado que es el Ãºnico objeto de la clase - PatrÃ³n SINGLETON
	 */
	private static PersistenciaHotelAndes instance;

	/**
	 * FÃ¡brica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;

	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 * CadenaHotelera, Hotel, 
	 */
	private List <String> tablas;

	/**
	 * Atributo para el acceso a las sentencias SQL propias a PersistenciaHotelAndes
	 */
	//private SQLUtil sqlUtil;

	/**
	 * Atributo para el acceso a la tabla TIPOS_USUARIO de la base de datos
	 */
	private SQLTipoUsuario sqlTipoUsuario;

	/**
	 * Atributo para el acceso a la tabla USUARIOS de la base de datos
	 */
	private SQLUsuario sqlUsuario;

	/**
	 * Atributo para el acceso a la tabla CADENA_HOTELERA de la base de datos
	 */
	private SQLCadenaHotelera sqlCadenaHotelera;

	/**
	 * Atributo para el acceso a la tabla HOTEL de la base de datos
	 */
	private SQLHotel sqlHotel;

	/**
	 * Atributo para el acceso a la tabla TIPO_HABITACION de la base de datos
	 */
	private SQLTipoHabitacion sqlTipoHabitacion;

	/**
	 * Atributo para el acceso a la tabla HABITACIONES de la base de datos
	 */
	private SQLHabitacion sqlHabitacion;

	/**
	 * Atributo para el acceso a la tabla DOTACION de la base de datos
	 */
	private SQLDotacion sqlDotacion;

	/**
	 * Atributo para el acceso a la tabla SERVICIO de la base de datos
	 */
	private SQLServicio sqlServicio;
	/**
	 * Atributo para el acceso a la tabla VENTA_PRODUCTO de la base de datos
	 */
	private SQLVentaProducto sqlVentaProducto;
	/**
	 * Atributo para el acceso a la tabla SALON de la base de datos
	 */
	private SQLSalon sqlSalon;
	/**
	 * Atributo para el acceso a la tabla SERVICIO_ADICIONAL de la base de datos
	 */
	private SQLServicioAdicional sqlServicioAdicional;
	/**
	 * Atributo para el acceso a la tabla PRODUCTOL de la base de datos
	 */
	private SQLProducto sqlProducto;
	/**
	 * Atributo para el acceso a la tabla DOTACION_SALON de la base de datos
	 */
	private SQLDotacionSalon sqlDotacionSalon;
	/**
	 * Atributo para el acceso a la tabla Caracteristica_ADICIONAL de la base de datos
	 */
	private SQLCaracteristicaAdicional sqlCaracteristicaAdicional;
	/**
	 * Atributo para el acceso a la tabla HORARIO de la base de datos
	 */
	private SQLHorario sqlHorario;
	/**
	 * Atributo para el acceso a la tabla PLANES de la base de datos
	 */
	private SQLPlan sqlPlan;
	/**
	 * Atributo para el acceso a la tabla DESCUENTOS de la base de datos
	 */
	private SQLDescuento sqlDescuento;
	/**
	 * Atributo para el acceso a la tabla ESTADIA de la base de datos
	 */
	private SQLEstadia sqlEstadia;
	/**
	 * Atributo para el acceso a la tabla RESERVA de la base de datos
	 */
	private SQLReserva sqlReserva;
	/**
	 * Atributo para el acceso a la tabla Factura de la base de datos
	 */
	private SQLFactura sqlFactura;


	/* **********************
	 * 			MÃ©todos del MANEJADOR DE PERSISTENCIA
	 ***********************/

	/**
	 * Constructor privado con valores por defecto - Patron SINGLETON
	 */
	private PersistenciaHotelAndes ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("HotelAndes");		
		crearClasesSQL ();

		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("TIPO_USUARIO");
		tablas.add ("USUARIOS");
		tablas.add ("CADENAS_HOTELERAS");
		tablas.add ("HOTELES");
		tablas.add ("TIPOS_HABITACION");
		tablas.add ("HABITACIONES");
		tablas.add ("DOTACIONES");
		tablas.add ("SERVICIOS");
		tablas.add ("VENTA_PRODUCTOS");
		tablas.add ("SALONES");
		tablas.add ("SERVICIOS_ADICIONALES");
		tablas.add ("PRODUCTOS");
		tablas.add ("DOTACION_SALON");
		tablas.add ("CARACTERISTICAS_ADICIONALES");
		tablas.add ("HORARIOS");
		tablas.add ("PLANES");
		tablas.add ("DESCUENTOS");
		tablas.add ("ESTADIAS");
		tablas.add ("RESERVAS");
		tablas.add ("FACTURAS");
	}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - PatrÃ³n SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaHotelAndes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);

		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * @return Retorna el Ãºnico objeto PersistenciaHotelAndes existente - PatrÃ³n SINGLETON
	 */
	public static PersistenciaHotelAndes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaHotelAndes ();
		}
		return instance;
	}

	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el Ãºnico objeto PersistenciaHotelAndes existente - PatrÃ³n SINGLETON
	 */
	public static PersistenciaHotelAndes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaHotelAndes (tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexiÃ³n con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}

	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}

		return resp;
	}

	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
		sqlTipoUsuario = new SQLTipoUsuario(this);
		sqlUsuario = new SQLUsuario(this);
		sqlCadenaHotelera = new SQLCadenaHotelera(this);
		sqlHotel = new SQLHotel(this);
		sqlTipoHabitacion = new SQLTipoHabitacion(this);
		sqlHabitacion = new SQLHabitacion (this);
		sqlDotacion = new SQLDotacion(this);
		sqlServicio = new SQLServicio(this);
		sqlVentaProducto = new SQLVentaProducto(this);
		sqlSalon = new SQLSalon(this);
		sqlServicioAdicional = new SQLServicioAdicional(this);
		sqlProducto = new SQLProducto(this);
		sqlDotacionSalon = new SQLDotacionSalon(this);
		sqlCaracteristicaAdicional = new SQLCaracteristicaAdicional(this);
		sqlHorario = new SQLHorario(this);
		sqlPlan = new SQLPlan(this);
		sqlDescuento = new SQLDescuento(this);
		sqlEstadia = new SQLEstadia(this);
		sqlReserva = new SQLReserva(this);
		sqlFactura = new SQLFactura(this);
		//		sqlUtil = new SQLUtil(this);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de TipoUsuario
	 */
	public String darTablaTipoUsuario ()
	{
		return tablas.get (0);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de usuarios
	 */
	public String darTablaUsuario ()
	{
		return tablas.get (1);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de cadenaHotelera
	 */
	public String darTablaCadenaHotelera ()
	{
		return tablas.get (2);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de hotel
	 */
	public String darTablaHotel ()
	{
		return tablas.get (3);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de tipo habitacion
	 */
	public String darTablaTipoHabitacion ()
	{
		return tablas.get (4);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de habitacion
	 */
	public String darTablaHabitacion ()
	{
		return tablas.get (5);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de dotacion
	 */
	public String darTablaDotacion ()
	{
		return tablas.get (6);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Servico
	 */
	public String darTablaServicio()
	{
		return tablas.get (7);
	}

	/**
	 * @return La cadena de caracteres con el nombre de la tabla de VentaProducto
	 */
	public String darTablaVentaProducto ()
	{
		return tablas.get (8);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Salon
	 */
	public String darTablaSalon()
	{
		return tablas.get (9);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Servicio Adicional
	 */
	public String darTablaServicioAdicional()
	{
		return tablas.get (10);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Producto
	 */
	public String darTablaProducto ()
	{
		return tablas.get (11);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de DotacionSalon
	 */
	public String darTablaDotacionSalon ()
	{
		return tablas.get (12);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de CaracteristicaAdicional
	 */
	public String darTablaCaracteristicaAdicional ()
	{
		return tablas.get (13);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Horario
	 */
	public String darTablaHorario ()
	{
		return tablas.get (14);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Planes
	 */
	public String darTablaPlan ()
	{
		return tablas.get (15);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Descuentos
	 */
	public String darTablaDescuento ()
	{
		return tablas.get (16);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Estadia
	 */
	public String darTablaEstadia ()
	{
		return tablas.get (17);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Reserva
	 */
	public String darTablaReserva()
	{
		return tablas.get (18);
	}
	/**
	 * @return La cadena de caracteres con el nombre de la tabla de Factura
	 */
	public String darTablaFactura ()
	{
		return tablas.get (19);
	}



	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle especÃ­fico del problema encontrado
	 * @param e - La excepciÃ³n que ocurrio
	 * @return El mensaje de la excepciÃ³n JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/* **********************
	 * 			Metodos para manejar los TIPOS DE USUARIO
	 ***********************/

	/**
	 * Metodo que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicaciÃ³n
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepcion
	 */	

	public TipoUsuario adicionarTipoUsuario(long idTipoUsuario, String nombreTipoUsuario)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlTipoUsuario.adicionarTipoUsuario(pm, idTipoUsuario, nombreTipoUsuario);
			log.trace ("Insercion de tipo usuario: " + nombreTipoUsuario + ": " + tuplasInsertadas + " tuplas insertadas");

			tx.commit();


			return new TipoUsuario (idTipoUsuario, nombreTipoUsuario);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}


	public CadenaHotelera adicionarCadenaHotelera(long idCadena, String nombreCadena)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlCadenaHotelera.adicionarCadenaHotelera(pm, idCadena, nombreCadena);
			log.trace ("Insercion de cadena hotelera: " + nombreCadena + ": " + tuplasInsertadas + " tuplas insertadas");

			tx.commit();


			return new CadenaHotelera(idCadena, nombreCadena);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public CaracteristicaAdicional adicionarCaracteristicaAdicional(long idCaracteristicaAdicional, String nombre, double valor, long idServicioAdicional)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlCaracteristicaAdicional.adicionarCaracteristicaAdicional(pm, idCaracteristicaAdicional, nombre, valor, idServicioAdicional);
			log.trace ("Insercion de caracteristica adicional: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

			tx.commit();


			return new CaracteristicaAdicional(idCaracteristicaAdicional, nombre, valor, idServicioAdicional);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public Descuento adicionarDescuento( long idDescuento, long idPlan, long idServicio, long idProducto, long valor, int limiteVeces)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlDescuento.adicionarDescuento(pm, idDescuento, idPlan, idServicio, idProducto, valor, limiteVeces);
			log.trace ("Insercion de descuento: " + idDescuento + ": " + tuplasInsertadas + " tuplas insertadas");

			tx.commit();


			return new Descuento(idDescuento, valor, limiteVeces, idPlan, idServicio, idProducto);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public Dotacion adicionarDotacion( long idDotacion, String nombre, double precio, long idTipoHabitacion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlDotacion.adicionarDotacion(pm, idDotacion, nombre, precio, idTipoHabitacion);
			log.trace ("Insercion de dotacionl: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

			tx.commit();


			return new Dotacion(idDotacion, precio, nombre, idTipoHabitacion);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public DotacionSalon adicionarDotacionSalon( long idDotacion, String nombre, double valor, long idSalon)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlDotacionSalon.adicionarDotacionSalon(pm, idDotacion, nombre, valor, idSalon);
			log.trace ("Insercion de dotacion salon: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
			tx.commit();


			return new DotacionSalon(idDotacion, valor, nombre, idSalon);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public Estadia adicionarEstadia( long idEstadia, Timestamp fechaLlegada, Timestamp fechaSalida, int numPersonas, long idPlan, long idHabitacion, int checkIn, int pago, String tipoDoc, long numDoc)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlEstadia.adicionarEstadia(pm, idEstadia, fechaLlegada, fechaSalida, numPersonas, idPlan, idHabitacion, checkIn, pago, tipoDoc, numDoc);
			log.trace ("Insercion de estadia: " + idEstadia + ": " + tuplasInsertadas + " tuplas insertadas");

			tx.commit();


			return new Estadia(idEstadia, fechaLlegada, fechaSalida, numPersonas, checkIn, pago, tipoDoc, numDoc, idPlan, idHabitacion);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public Factura adicionarFactura( long numFactura, Timestamp fecha, int pagada, double precio, long idDotacion, long idServicio, String tipoDocEmpleado, long idEstadia, long numDocEmpleado, long idConsumo)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlFactura.adicionarFactura(pm, numFactura, fecha, pagada, precio, idDotacion, idServicio, tipoDocEmpleado, idEstadia, numDocEmpleado, idConsumo);
			log.trace ("Insercion de factura: " + numFactura + ": " + tuplasInsertadas + " tuplas insertadas");

			tx.commit();


			return new Factura(numFactura, fecha, pagada, precio, idConsumo, idDotacion, idServicio, idEstadia, tipoDocEmpleado, numDocEmpleado);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public Habitacion adicionarHabitacion(long numHabitacion, long idTipoHabitaciones)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlHabitacion.adicionarHabitacion(pm, numHabitacion, idTipoHabitaciones);
			log.trace ("Insercion de habitacion: " + numHabitacion + ": " + tuplasInsertadas + " tuplas insertadas");

			tx.commit();


			return new Habitacion(numHabitacion, idTipoHabitaciones); 
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public Horario adicionarHorario( long idHorario, String duracion, long idServicio, Timestamp fecha, String dia, String horaApertura, String horaCierre)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlHorario.adicionarHorario(pm, idHorario, duracion, idServicio, fecha, dia, horaApertura, horaCierre);
			log.trace ("Insercion de horario: " + idHorario + ": " + tuplasInsertadas + " tuplas insertadas");

			tx.commit();


			return new Horario(idHorario, dia, horaApertura, horaCierre, duracion, fecha, idServicio);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public Hotel adicionarHotel( long idHotel, String nombre, String direccion, String ciudad, int estrellas, long idCadenaHotelera) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlHotel.adicionarHotel(pm, idHotel, nombre, direccion, ciudad, estrellas, idCadenaHotelera);
			log.trace ("Insercion de hotel: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
			tx.commit();


			return new Hotel(idHotel, nombre, direccion, ciudad, estrellas, idCadenaHotelera);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	public Plan adicionarPlan( long idPlan, String tipo, double costo, double descuentoAlojamiento, Timestamp fecha) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlPlan.adicionarPlan(pm, idPlan, tipo, costo, descuentoAlojamiento, fecha);
			log.trace ("Insercion de plan: " + idPlan + ": " + tuplasInsertadas + " tuplas insertadas");
			tx.commit();


			return new Plan(idPlan, tipo, costo, descuentoAlojamiento, fecha);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public Producto adicionarProducto( long idProducto, double precio, String nombre, int cantidad, int duracion, String categoria, long idVentaProducto) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProducto.adicionarProducto(pm, idProducto, precio, nombre, cantidad, duracion, categoria, idVentaProducto);
			log.trace ("Insercion de producto: " + idProducto + ": " + tuplasInsertadas + " tuplas insertadas");

			tx.commit();


			return new Producto(idProducto, nombre, precio, cantidad, duracion, categoria, idVentaProducto);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public Reserva adicionarReserva(long numReserva, long idEstadia, long idServicio, long idHorario, long idConsumo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlReserva.adicionarReserva(pm, numReserva, idEstadia, idServicio, idHorario, idConsumo);
			log.trace ("Insercion de plan: " + numReserva + ": " + tuplasInsertadas + " tuplas insertadas");
			tx.commit();


			return new Reserva(numReserva, idConsumo, idEstadia, idServicio, idHorario);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public Salon adicionarSalon(long idServicio, int capacidad, double costoPorHora, String tipo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlSalon.adicionarSalon(pm, idServicio, capacidad, costoPorHora, tipo);
			log.trace ("Insercion del salon: " + idServicio + ": " + tuplasInsertadas + " tuplas insertadas");

			tx.commit();


			return new Salon(idServicio, capacidad, costoPorHora, tipo);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public Servicio adicionarServicio(long idServicio, String nombreServicio) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlServicio.adicionarServicio(pm, idServicio, nombreServicio);
			log.trace ("Insercion del servicio: " + nombreServicio + ": " + tuplasInsertadas + " tuplas insertadas");

			tx.commit();


			return new Servicio(idServicio, nombreServicio);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}	

	public ServicioAdicional adicionarServicioAdicional(long idServicioAdicional, int capacidad) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlServicioAdicional.adicionarServicioAdicional(pm, idServicioAdicional, capacidad);
			log.trace ("Insercion del servicio adicional: " + idServicioAdicional + ": " + tuplasInsertadas + " tuplas insertadas");

			tx.commit();


			return new ServicioAdicional(idServicioAdicional, capacidad);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public TipoHabitacion adicionarTipoHabitacion(long idTipoHabitacion, String descripcion, int capacidad, int precioNoche, long idHotel) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlTipoHabitacion.adicionarTipoHabitacion(pm, idTipoHabitacion, descripcion, capacidad, precioNoche, idHotel);
			log.trace ("Insercion del tipo usuario: " + idTipoHabitacion + ": " + tuplasInsertadas + " tuplas insertadas");

			tx.commit();


			return new TipoHabitacion(idTipoHabitacion, descripcion, capacidad, precioNoche, idHotel);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public Usuario adicionarUsuario(long numeroDocumento, String tipoDocumento, String nombre, String correoElectronico, long idTipoUsuario) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlUsuario.adicionarUsuario(pm, numeroDocumento, tipoDocumento, nombre, correoElectronico, idTipoUsuario);
			tx.commit();

			log.trace ("Insercion del servicio: " + numeroDocumento + ": " + tuplasInsertadas + " tuplas insertadas");

			return new Usuario(numeroDocumento, tipoDocumento, nombre, correoElectronico, idTipoUsuario);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();;
			}
			pm.close();
		}
	}
	public VentaProducto adicionarVentaProducto(long idServicio, int capacidad, String estilo, String tipo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlVentaProducto.adicionarVentaProducto(pm, idServicio, capacidad, estilo, tipo);
			tx.commit();

			log.trace ("Insercion de la venta producto: " + idServicio + ": " + tuplasInsertadas + " tuplas insertadas");

			return new VentaProducto(idServicio, estilo, capacidad, tipo);	
		}	
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	/**
	 * MÃ©todo que consulta todas las tuplas en la tabla TipoUsuario
	 * @return La lista de objetos TipoBebida, construidos con base en las tuplas de la tabla TIPOBEBIDA
	 */
	public List<TipoUsuario> darTiposUsuario ()
	{
		return sqlTipoUsuario.darTiposUsuario(pmf.getPersistenceManager());
	}

	public TipoUsuario darTipoUsuarioPorId (long idTipoUsuario)
	{
		return sqlTipoUsuario.darTipoUsuarioPorId(pmf.getPersistenceManager(), idTipoUsuario);
	}

	public List<CadenaHotelera> darCadenasHoteleras ()
	{
		return sqlCadenaHotelera.darCadenasHoteleras(pmf.getPersistenceManager());
	}

	public CadenaHotelera darCadenaHoteleraPorId (long idCadena)
	{
		return sqlCadenaHotelera.darCadenaHoteleraPorId(pmf.getPersistenceManager(), idCadena);
	}

	public List<CaracteristicaAdicional> darCaracrteristicasAdicionales(){
		return sqlCaracteristicaAdicional.darCaracteristicasAdicionales(pmf.getPersistenceManager());
	}

	public CaracteristicaAdicional darCaracteristicaAdicionalPorId(long idCaracteristicaAdicional){
		return sqlCaracteristicaAdicional.darCaracteristicaAdicionalPorId(pmf.getPersistenceManager(), idCaracteristicaAdicional);
	}

	public List<Descuento> darDescuentos(){
		return sqlDescuento.darDescuentos(pmf.getPersistenceManager());
	}

	public Descuento darDescuentoPorId(long idDescuento){
		return sqlDescuento.darDescuentoPorId(pmf.getPersistenceManager(), idDescuento);
	}

	public List<Dotacion> darDotaciones(){
		return sqlDotacion.darDotaciones(pmf.getPersistenceManager());
	}

	public Dotacion darDotacionPorId(long idDotacion){
		return sqlDotacion.darDotacionesPorId(pmf.getPersistenceManager(), idDotacion);
	}

	public List<DotacionSalon> darDotacionesSalon(){
		return sqlDotacionSalon.darDotacionSalones(pmf.getPersistenceManager());
	}

	public DotacionSalon darDotacionSalonPorId(long idDotacionSalon){
		return sqlDotacionSalon.darDotacionSalonPorId(pmf.getPersistenceManager(), idDotacionSalon);
	}

	public List<Estadia> darEstadias(){
		return sqlEstadia.darEstadias(pmf.getPersistenceManager());
	}

	public Estadia darEstadiaPorId(long idEstadia){
		return sqlEstadia.darEstadiaPorId(pmf.getPersistenceManager(), idEstadia);
	}

	public long cambiarEstadiaAPagada (long idEstadia)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlEstadia.cambiarAPagada(pm, idEstadia);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//	        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}


	public long checkInCliente (long idEstadia)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlEstadia.checkInCliente(pm, idEstadia);
            tx.commit();
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	
	public List<Factura> darFacturas()
	{
		return sqlFactura.darFacturas(pmf.getPersistenceManager());
	}
	
	public Factura darFacturaPorId(long numFactura){
		return sqlFactura.darFacturaPorId(pmf.getPersistenceManager(), numFactura);
	}
	
	public long cambiarFacturasAPagada (long idFactura)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlFactura.cambiarAPagada(pm, idFactura);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//	        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	
	public List<Habitacion> darHabitaciones(){
		return sqlHabitacion.darHabitaciones(pmf.getPersistenceManager());
	}
	
	public Habitacion darHabitacionPorId(long numHabitacion){
		return sqlHabitacion.darHabitacionPorNumeroHabitacion(pmf.getPersistenceManager(), numHabitacion);
	}
	
	public List<Horario> darHorarios(){
		return sqlHorario.darHorarios(pmf.getPersistenceManager());
	}
	
	public Horario darHorarioPorId(long idHorario){
		return sqlHorario.darHorarioPorId(pmf.getPersistenceManager(), idHorario);
	}
	
	public List<Hotel> darHoteles(){
		return sqlHotel.darHoteles(pmf.getPersistenceManager());
	}
	
	public Hotel darHotelPorId(long idHotel){
		return sqlHotel.darHotelPorId(pmf.getPersistenceManager(), idHotel);
	}
	
	public List<Plan> darPlanes(){
		return sqlPlan.darPlanes(pmf.getPersistenceManager());
	}
	
	public Plan darPlan(long idPlan){
		return sqlPlan.darPlanesPorId(pmf.getPersistenceManager(), idPlan);
	}
	
	public List<Producto> darProductos(){
		return sqlProducto.darProductos(pmf.getPersistenceManager());
	}
	
	public Producto darProductoPorId(long idProducto){
		return sqlProducto.darProductoPorId(pmf.getPersistenceManager(), idProducto);
	}
	
	public List<Reserva> darReservas(){
		return sqlReserva.darReservas(pmf.getPersistenceManager());
	}
	
	public Reserva darReservaPorId(long idReserva){
		return sqlReserva.darReservaPorId(pmf.getPersistenceManager(), idReserva);
	}
	
	public List<Salon> darSalones(){
		return sqlSalon.darSalones(pmf.getPersistenceManager());
	}
	
	public Salon darSalonPorId(long idSalon){
		return sqlSalon.darSalonPorId(pmf.getPersistenceManager(), idSalon);
	}
	
	public List<Servicio> darServicios(){
		return sqlServicio.darServicios(pmf.getPersistenceManager());
	}
	
	public Servicio darServicio(long idServicio){
		return sqlServicio.darServicioPorId(pmf.getPersistenceManager(), idServicio);
	}
	
	public List<ServicioAdicional> darServiciosAdicionales(){
		return sqlServicioAdicional.darServiciosAdicionales(pmf.getPersistenceManager());
	}
	
	public ServicioAdicional darServicioAdicionalPorId(long idServicioAdicional){
		return sqlServicioAdicional.darServicioAdicionalPorId(pmf.getPersistenceManager(), idServicioAdicional);
	}
	
	public List<TipoHabitacion> darTiposHabitacion(){
		return sqlTipoHabitacion.darTiposHabitacion(pmf.getPersistenceManager());
	}
	
	public TipoHabitacion darTipoHabitacionPorId(long idTipoHabitacion){
		return sqlTipoHabitacion.darTipoHabitacionPorId(pmf.getPersistenceManager(), idTipoHabitacion);
	}
	
	public List<Usuario> darUsuarios(){
		return sqlUsuario.darUsuarios(pmf.getPersistenceManager());
	}
	
	public Usuario darUsuarioPorid(long idUsuario, String tipoDoc){
		return sqlUsuario.darUsuarioPorId(pmf.getPersistenceManager(), idUsuario, tipoDoc);
	}
	
	public List<VentaProducto> darVentasProducto(){
		return sqlVentaProducto.darVentasProducto(pmf.getPersistenceManager());
	}
	
	public VentaProducto darVentaProductoPorId(long idVentaProducto){
		return sqlVentaProducto.darVentaProductoPorId(pmf.getPersistenceManager(), idVentaProducto);
	}

	
	


	public Usuario darUsuario(String cedula){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			Usuario ce = sqlUsuario.darUsuarioPorCedula(pm, cedula);
			tx.commit();
			return ce;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();

			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

}
