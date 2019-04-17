package uniandes.isis2304.hotelAndes.persistencia;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.*;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import sun.invoke.util.ValueConversions;
import uniandes.isis2304.hotelAndes.negocio.CadenasHoteleras;
import uniandes.isis2304.hotelAndes.negocio.CaracteristicasAdicionales;
import uniandes.isis2304.hotelAndes.negocio.Convenciones;
import uniandes.isis2304.hotelAndes.negocio.Descuentos;
import uniandes.isis2304.hotelAndes.negocio.Dotaciones;
import uniandes.isis2304.hotelAndes.negocio.DotacionSalon;
import uniandes.isis2304.hotelAndes.negocio.Estadias;
import uniandes.isis2304.hotelAndes.negocio.Facturas;
import uniandes.isis2304.hotelAndes.negocio.Habitaciones;
import uniandes.isis2304.hotelAndes.negocio.Horarios;
import uniandes.isis2304.hotelAndes.negocio.Hoteles;
import uniandes.isis2304.hotelAndes.negocio.Planes;
import uniandes.isis2304.hotelAndes.negocio.Productos;
import uniandes.isis2304.hotelAndes.negocio.Reservas;
import uniandes.isis2304.hotelAndes.negocio.Salones;
import uniandes.isis2304.hotelAndes.negocio.Servicios;
import uniandes.isis2304.hotelAndes.negocio.ServiciosAdicionales;
import uniandes.isis2304.hotelAndes.negocio.TiposHabitacion;
import uniandes.isis2304.hotelAndes.negocio.TipoUsuario;
import uniandes.isis2304.hotelAndes.negocio.Usuarios;
import uniandes.isis2304.hotelAndes.negocio.VOFacturas;
import uniandes.isis2304.hotelAndes.negocio.VentaProductos;



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
	private SQLUsuarios sqlUsuario;

	/**
	 * Atributo para el acceso a la tabla CADENA_HOTELERA de la base de datos
	 */
	private SQLCadenasHoteleras sqlCadenaHotelera;

	/**
	 * Atributo para el acceso a la tabla HOTEL de la base de datos
	 */
	private SQLHoteles sqlHotel;

	/**
	 * Atributo para el acceso a la tabla TIPO_HABITACION de la base de datos
	 */
	private SQLTiposHabitacion sqlTipoHabitacion;

	/**
	 * Atributo para el acceso a la tabla HABITACIONES de la base de datos
	 */
	private SQLHabitaciones sqlHabitacion;

	/**
	 * Atributo para el acceso a la tabla DOTACION de la base de datos
	 */
	private SQLDotaciones sqlDotacion;

	/**
	 * Atributo para el acceso a la tabla SERVICIO de la base de datos
	 */
	private SQLServicios sqlServicio;
	/**
	 * Atributo para el acceso a la tabla VENTA_PRODUCTO de la base de datos
	 */
	private SQLVentaProductos sqlVentaProducto;
	/**
	 * Atributo para el acceso a la tabla SALON de la base de datos
	 */
	private SQLSalones sqlSalon;
	/**
	 * Atributo para el acceso a la tabla SERVICIO_ADICIONAL de la base de datos
	 */
	private SQLServiciosAdicionales sqlServicioAdicional;
	/**
	 * Atributo para el acceso a la tabla PRODUCTOL de la base de datos
	 */
	private SQLProductos sqlProducto;
	/**
	 * Atributo para el acceso a la tabla DOTACION_SALON de la base de datos
	 */
	private SQLDotacionSalon sqlDotacionSalon;
	/**
	 * Atributo para el acceso a la tabla Caracteristica_ADICIONAL de la base de datos
	 */
	private SQLCaracteristicasAdicionales sqlCaracteristicaAdicional;
	/**
	 * Atributo para el acceso a la tabla HORARIO de la base de datos
	 */
	private SQLHorarios sqlHorario;
	/**
	 * Atributo para el acceso a la tabla PLANES de la base de datos
	 */
	private SQLPlanes sqlPlan;
	/**
	 * Atributo para el acceso a la tabla DESCUENTOS de la base de datos
	 */
	private SQLDescuentos sqlDescuento;
	/**
	 * Atributo para el acceso a la tabla ESTADIA de la base de datos
	 */
	private SQLEstadias sqlEstadia;
	/**
	 * Atributo para el acceso a la tabla RESERVA de la base de datos
	 */
	private SQLReservas sqlReserva;
	/**
	 * Atributo para el acceso a la tabla Factura de la base de datos
	 */
	private SQLFacturas sqlFactura;

	private SQLConvenciones sqlConvencion;

	private SQLMantenimiento sqlMantenimiento;


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
		tablas.add ("TIPOUSUARIO");
		tablas.add ("USUARIOS");
		tablas.add ("CADENASHOTELERAS");
		tablas.add ("HOTELES");
		tablas.add ("TIPOSHABITACION");
		tablas.add ("HABITACIONES");
		tablas.add ("DOTACIONES");
		tablas.add ("SERVICIOS");
		tablas.add ("VENTAPRODUCTOS");
		tablas.add ("SALONES");
		tablas.add ("SERVICIOSADICIONALES");
		tablas.add ("PRODUCTOS");
		tablas.add ("DOTACIONSALON");
		tablas.add ("CARACTERISTICASADICIONALES");
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
		sqlUsuario = new SQLUsuarios(this);
		sqlCadenaHotelera = new SQLCadenasHoteleras(this);
		sqlHotel = new SQLHoteles(this);
		sqlTipoHabitacion = new SQLTiposHabitacion(this);
		sqlHabitacion = new SQLHabitaciones (this);
		sqlDotacion = new SQLDotaciones(this);
		sqlServicio = new SQLServicios(this);
		sqlVentaProducto = new SQLVentaProductos(this);
		sqlSalon = new SQLSalones(this);
		sqlServicioAdicional = new SQLServiciosAdicionales(this);
		sqlProducto = new SQLProductos(this);
		sqlDotacionSalon = new SQLDotacionSalon(this);
		sqlCaracteristicaAdicional = new SQLCaracteristicasAdicionales(this);
		sqlHorario = new SQLHorarios(this);
		sqlPlan = new SQLPlanes(this);
		sqlDescuento = new SQLDescuentos(this);
		sqlEstadia = new SQLEstadias(this);
		sqlReserva = new SQLReservas(this);
		sqlFactura = new SQLFacturas(this);
		sqlMantenimiento = new SQLMantenimiento(this);
		sqlConvencion = new SQLConvenciones(this);
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


	public CadenasHoteleras adicionarCadenaHotelera(long idCadena, String nombreCadena)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlCadenaHotelera.adicionarCadenaHotelera(pm, idCadena, nombreCadena);
			log.trace ("Insercion de cadena hotelera: " + nombreCadena + ": " + tuplasInsertadas + " tuplas insertadas");

			tx.commit();


			return new CadenasHoteleras(idCadena, nombreCadena);
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

	public CaracteristicasAdicionales adicionarCaracteristicaAdicional(long idCaracteristicaAdicional, String nombre, double valor, long idServicioAdicional)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlCaracteristicaAdicional.adicionarCaracteristicaAdicional(pm, idCaracteristicaAdicional, nombre, valor, idServicioAdicional);
			log.trace ("Insercion de caracteristica adicional: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

			tx.commit();


			return new CaracteristicasAdicionales(idCaracteristicaAdicional, nombre, valor, idServicioAdicional);
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

	public Descuentos adicionarDescuento( long idDescuento, long idPlan, long idServicio, long idProducto, long valor, int limiteVeces)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlDescuento.adicionarDescuento(pm, idDescuento, idPlan, idServicio, idProducto, valor, limiteVeces);
			log.trace ("Insercion de descuento: " + idDescuento + ": " + tuplasInsertadas + " tuplas insertadas");

			tx.commit();


			return new Descuentos(idDescuento, valor, limiteVeces, idPlan, idServicio, idProducto);
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

	public Descuentos adicionarDescuentoConvencion( long idDescuento, long idPlan, long idServicio, long idProducto, long valor, int limiteVeces)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		try
		{
			long tuplasInsertadas = sqlDescuento.adicionarDescuento(pm, idDescuento, idPlan, idServicio, idProducto, valor, limiteVeces);
			log.trace ("Insercion de descuento: " + idDescuento + ": " + tuplasInsertadas + " tuplas insertadas");

			return new Descuentos(idDescuento, valor, limiteVeces, idPlan, idServicio, idProducto);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}

	}

	public Dotaciones adicionarDotacion( long idDotacion, String nombre, double precio, long idTipoHabitacion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlDotacion.adicionarDotacion(pm, idDotacion, nombre, precio, idTipoHabitacion);
			log.trace ("Insercion de dotacionl: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

			tx.commit();


			return new Dotaciones(idDotacion, precio, nombre, idTipoHabitacion);
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

	public Estadias adicionarEstadia( long idEstadia, Timestamp fechaLlegada, Timestamp fechaSalida, int numPersonas, long idPlan, long idHabitacion, int checkIn, int pago, long numDoc, Long idConvencion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			Query q = pm.newQuery(SQL, "INSERT INTO ESTADIAS"  + "(IDESTADIA, FECHALLEGADA, FECHASALIDA, NUMEROPERSONAS, IDPLAN, IDHABITACION, CHECKIN, PAGADO, IDCLIENTE, IDCONVENCION) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
			q.setParameters(idEstadia, fechaLlegada, fechaSalida, numPersonas, idPlan, idHabitacion, checkIn, pago, numDoc, idConvencion);
			q.executeUnique();
			log.trace ("Insercion de estadia: " + idEstadia + ": " + " tuplas insertadas");

			tx.commit();
			return new Estadias(idEstadia, fechaLlegada, fechaSalida, numPersonas, idPlan, idHabitacion, checkIn, pago, numDoc, idConvencion);

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

	public Estadias adicionarEstadiaConvencion( Long idEstadia, Timestamp fechaLlegada, Timestamp fechaSalida, int numPersonas, Long idPlan, Long idHabitacion, int checkIn, int pago, Long numDoc, Long idConvencion)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		try
		{
			long tuplasInsertadas = sqlEstadia.adicionarEstadia(pm, idEstadia, fechaLlegada, fechaSalida, numPersonas, idPlan, idHabitacion, checkIn, pago, numDoc, idConvencion);
			log.trace ("Insercion de estadia: " + idEstadia + ": " + tuplasInsertadas + " tuplas insertadas");


			return new Estadias(idEstadia, fechaLlegada, fechaSalida, numPersonas, idPlan, idHabitacion, checkIn, pago, numDoc, idConvencion);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
	}

	public VOFacturas adicionarFactura( long numFactura, Timestamp fecha, int pagada, double precio, long idDotacion, long idServicio, long idEstadia, long numDocEmpleado, long idConsumo, long idConvencion, long idDotacionSalon)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlFactura.adicionarFactura(pm, numFactura, fecha, pagada, precio, idDotacion, idServicio, idEstadia, numDocEmpleado, idConsumo, idConvencion, idDotacionSalon);
			log.trace ("Insercion de factura: " + numFactura + ": " + tuplasInsertadas + " tuplas insertadas");

			tx.commit();


			return new Facturas(numFactura, fecha, pagada, precio, idDotacion, idServicio, idEstadia, numDocEmpleado, idConsumo, idConvencion, idDotacionSalon);
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

	public Habitaciones adicionarHabitacion(long numHabitacion, long idTipoHabitaciones)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlHabitacion.adicionarHabitacion(pm, numHabitacion, idTipoHabitaciones);
			log.trace ("Insercion de habitacion: " + numHabitacion + ": " + tuplasInsertadas + " tuplas insertadas");

			tx.commit();


			return new Habitaciones(numHabitacion, idTipoHabitaciones); 
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

	public Horarios adicionarHorario( long idHorario, String duracion, long idServicio, Timestamp fechaInicio, String dia, String horaInicio, String horaFin, Timestamp fechaFin)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlHorario.adicionarHorario(pm, idHorario, duracion, idServicio, fechaInicio, dia, horaInicio, horaFin, fechaFin);
			log.trace ("Insercion de horario: " + idHorario + ": " + tuplasInsertadas + " tuplas insertadas");

			tx.commit();


			return new Horarios(idHorario, dia, horaInicio, horaFin, duracion, fechaInicio, idServicio, fechaFin);
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

	public Horarios adicionarHorarioMantenimiento( long idHorario, String duracion, Long idServicio, Timestamp fechaInicio, String dia, String horaInicio, String horaFin, Timestamp fechaFin)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		try
		{
			long tuplasInsertadas = sqlHorario.adicionarHorario(pm, idHorario, duracion, idServicio, fechaInicio, dia, horaInicio, horaFin, fechaFin);
			log.trace ("Insercion de horario: " + idHorario + ": " + tuplasInsertadas + " tuplas insertadas");



			return new Horarios(idHorario, dia, horaInicio, horaFin, duracion, fechaInicio, idServicio, fechaFin);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}

	}

	public Horarios adicionarHorarioServiciosConvencion( long idHorario, String duracion, long idServicio, Timestamp fechaInicio, String dia, String horaInicio, String horaFin, Timestamp fechaFin)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		try
		{
			long tuplasInsertadas = sqlHorario.adicionarHorario(pm, idHorario, duracion, idServicio, fechaInicio, dia, horaInicio, horaFin, fechaFin);
			log.trace ("Insercion de horario: " + idHorario + ": " + tuplasInsertadas + " tuplas insertadas");



			return new Horarios(idHorario, dia, horaInicio, horaFin, duracion, fechaInicio, idServicio, fechaFin);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}

	}

	public Hoteles adicionarHotel( long idHotel, String nombre, String direccion, String ciudad, int estrellas, long idCadenaHotelera) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlHotel.adicionarHotel(pm, idHotel, nombre, direccion, ciudad, estrellas, idCadenaHotelera);
			log.trace ("Insercion de hotel: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
			tx.commit();


			return new Hoteles(idHotel, nombre, direccion, ciudad, estrellas, idCadenaHotelera);
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
	public Planes adicionarPlan( long idPlan, String tipo, double costo, double descuentoAlojamiento, Timestamp fecha) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlPlan.adicionarPlan(pm, idPlan, tipo, costo, descuentoAlojamiento, fecha);
			log.trace ("Insercion de plan: " + idPlan + ": " + tuplasInsertadas + " tuplas insertadas");
			tx.commit();


			return new Planes(idPlan, tipo, costo, descuentoAlojamiento, fecha);
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

	public Planes adicionarPlanConvencion( long idPlan, String tipo, double costo, double descuentoAlojamiento, Timestamp fecha) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		try
		{

			long tuplasInsertadas = sqlPlan.adicionarPlan(pm, idPlan, tipo, costo, descuentoAlojamiento, fecha);
			log.trace ("Insercion de plan: " + idPlan + ": " + tuplasInsertadas + " tuplas insertadas");

			return new Planes(idPlan, tipo, costo, descuentoAlojamiento, fecha);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
	}


	public Productos adicionarProducto( long idProducto, double precio, String nombre, int cantidad, int duracion, String categoria, long idVentaProducto) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlProducto.adicionarProducto(pm, idProducto, precio, nombre, cantidad, duracion, categoria, idVentaProducto);
			log.trace ("Insercion de producto: " + idProducto + ": " + tuplasInsertadas + " tuplas insertadas");

			tx.commit();


			return new Productos(idProducto, nombre, precio, cantidad, duracion, categoria, idVentaProducto);
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

	public Reservas adicionarReserva(long numReserva, Long idEstadia, long idServicio, long idHorario, Long idConsumo, long idConvencion, long capacidad) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlReserva.adicionarReserva(pm, numReserva, idEstadia, idServicio, idHorario, idConsumo, idConvencion, capacidad);
			log.trace ("Insercion de plan: " + numReserva + ": " + tuplasInsertadas + " tuplas insertadas");
			tx.commit();


			return new Reservas(numReserva, idConsumo, idEstadia, idServicio, idHorario, idConvencion, capacidad);
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

	public Salones adicionarSalon(long idServicio, int capacidad, double costoPorHora, String tipo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlSalon.adicionarSalon(pm, idServicio, capacidad, costoPorHora, tipo);
			log.trace ("Insercion del salon: " + idServicio + ": " + tuplasInsertadas + " tuplas insertadas");

			tx.commit();


			return new Salones(idServicio, capacidad, costoPorHora, tipo);
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

	public Servicios adicionarServicio(long idServicio, String nombreServicio) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlServicio.adicionarServicio(pm, idServicio, nombreServicio);
			log.trace ("Insercion del servicio: " + nombreServicio + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Servicios(idServicio, nombreServicio);
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

	public ServiciosAdicionales adicionarServicioAdicional(long idServicioAdicional, int capacidad) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlServicioAdicional.adicionarServicioAdicional(pm, idServicioAdicional, capacidad);
			log.trace ("Insercion del servicio adicional: " + idServicioAdicional + ": " + tuplasInsertadas + " tuplas insertadas");

			tx.commit();


			return new ServiciosAdicionales(idServicioAdicional, capacidad);
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

	public TiposHabitacion adicionarTipoHabitacion(long idTipoHabitacion, String descripcion, int capacidad, int precioNoche, long idHotel) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlTipoHabitacion.adicionarTipoHabitacion(pm, idTipoHabitacion, descripcion, capacidad, precioNoche, idHotel);
			log.trace ("Insercion del tipo usuario: " + idTipoHabitacion + ": " + tuplasInsertadas + " tuplas insertadas");

			tx.commit();


			return new TiposHabitacion(idTipoHabitacion, descripcion, capacidad, precioNoche, idHotel);
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

	public Usuarios adicionarUsuario(long numeroDocumento, String tipoDocumento, String nombre, String correoElectronico, long idTipoUsuario) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlUsuario.adicionarUsuario(pm, numeroDocumento, tipoDocumento, nombre, correoElectronico, idTipoUsuario);
			tx.commit();

			log.trace ("Insercion del servicio: " + numeroDocumento + ": " + tuplasInsertadas + " tuplas insertadas");

			return new Usuarios(numeroDocumento, tipoDocumento, nombre, correoElectronico, idTipoUsuario);
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
	public VentaProductos adicionarVentaProducto(long idServicio, int capacidad, String estilo, String tipo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlVentaProducto.adicionarVentaProducto(pm, idServicio, capacidad, estilo, tipo);
			tx.commit();

			log.trace ("Insercion de la venta producto: " + idServicio + ": " + tuplasInsertadas + " tuplas insertadas");

			return new VentaProductos(idServicio, estilo, capacidad, tipo);	
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

	public Convenciones adicionarConvencion(long idConvencion, String nombre, int capacidad, Timestamp fechaInicio, Timestamp fechaFin, long idOrganizador, long idPlan, int pago) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		try
		{
			long tuplasInsertadas = sqlConvencion.adicionarConvencion(pm, idConvencion, nombre, capacidad, fechaInicio, fechaFin, idOrganizador, idPlan, pago);
			log.trace ("Insercion de la convencion: " + idConvencion + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Convenciones(idConvencion, nombre, capacidad, fechaInicio, fechaFin, idOrganizador, idPlan, pago);
		}	
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
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

	public List<CadenasHoteleras> darCadenasHoteleras ()
	{
		return sqlCadenaHotelera.darCadenasHoteleras(pmf.getPersistenceManager());
	}

	public CadenasHoteleras darCadenaHoteleraPorId (long idCadena)
	{
		return sqlCadenaHotelera.darCadenaHoteleraPorId(pmf.getPersistenceManager(), idCadena);
	}

	public List<CaracteristicasAdicionales> darCaracrteristicasAdicionales(){
		return sqlCaracteristicaAdicional.darCaracteristicasAdicionales(pmf.getPersistenceManager());
	}

	public CaracteristicasAdicionales darCaracteristicaAdicionalPorId(long idCaracteristicaAdicional){
		return sqlCaracteristicaAdicional.darCaracteristicaAdicionalPorId(pmf.getPersistenceManager(), idCaracteristicaAdicional);
	}

	public List<Descuentos> darDescuentos(){
		return sqlDescuento.darDescuentos(pmf.getPersistenceManager());
	}

	public Descuentos darDescuentoPorId(long idDescuento){
		return sqlDescuento.darDescuentoPorId(pmf.getPersistenceManager(), idDescuento);
	}

	public List<Dotaciones> darDotaciones(){
		return sqlDotacion.darDotaciones(pmf.getPersistenceManager());
	}

	public Dotaciones darDotacionPorId(long idDotacion){
		return sqlDotacion.darDotacionesPorId(pmf.getPersistenceManager(), idDotacion);
	}

	public List<DotacionSalon> darDotacionesSalon(){
		return sqlDotacionSalon.darDotacionSalones(pmf.getPersistenceManager());
	}

	public DotacionSalon darDotacionSalonPorId(long idDotacionSalon){
		return sqlDotacionSalon.darDotacionSalonPorId(pmf.getPersistenceManager(), idDotacionSalon);
	}

	public List<Estadias> darEstadias(){
		return sqlEstadia.darEstadias(pmf.getPersistenceManager());
	}

	public Estadias darEstadiaPorId(long id){
		return sqlEstadia.buscarEstadiaPorId(pmf.getPersistenceManager(), id);
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


	public List<Facturas> darFacturas()
	{
		return sqlFactura.darFacturas(pmf.getPersistenceManager());
	}

	public Facturas darFacturaPorId(long numFactura){
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

	public long regIdCliente (long idEstadia, long cedCliente)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlEstadia.registrarIdCliente(pm, idEstadia, cedCliente);
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


	public List<Habitaciones> darHabitaciones(){
		return sqlHabitacion.darHabitaciones(pmf.getPersistenceManager());
	}

	public Habitaciones darHabitacionPorId(long numHabitacion){
		return sqlHabitacion.darHabitacionPorNumeroHabitacion(pmf.getPersistenceManager(), numHabitacion);
	}

	public List<Horarios> darHorarios(){
		return sqlHorario.darHorarios(pmf.getPersistenceManager());
	}

	public Horarios darHorarioPorId(long idHorario){
		return sqlHorario.darHorarioPorId(pmf.getPersistenceManager(), idHorario);
	}

	public List<Hoteles> darHoteles(){
		return sqlHotel.darHoteles(pmf.getPersistenceManager());
	}

	public Hoteles darHotelPorId(long idHotel){
		return sqlHotel.darHotelPorId(pmf.getPersistenceManager(), idHotel);
	}

	public List<Planes> darPlanes(){
		return sqlPlan.darPlanes(pmf.getPersistenceManager());
	}

	public Planes darPlan(long idPlan){
		return sqlPlan.darPlanesPorId(pmf.getPersistenceManager(), idPlan);
	}

	public List<Productos> darProductos(){
		return sqlProducto.darProductos(pmf.getPersistenceManager());
	}

	public Productos darProductoPorId(long idProducto){
		return sqlProducto.darProductoPorId(pmf.getPersistenceManager(), idProducto);
	}

	public List<Reservas> darReservas(){
		return sqlReserva.darReservas(pmf.getPersistenceManager());
	}

	public Reservas darReservaPorId(long idReserva){
		return sqlReserva.darReservaPorId(pmf.getPersistenceManager(), idReserva);
	}

	public List<Salones> darSalones(){
		return sqlSalon.darSalones(pmf.getPersistenceManager());
	}

	public Salones darSalonPorId(long idSalon){
		return sqlSalon.darSalonPorId(pmf.getPersistenceManager(), idSalon);
	}

	public List<Servicios> darServicios(){
		return sqlServicio.darServicios(pmf.getPersistenceManager());
	}

	public Servicios darServicio(long idServicio){
		return sqlServicio.darServicioPorId(pmf.getPersistenceManager(), idServicio);
	}

	public List<ServiciosAdicionales> darServiciosAdicionales(){
		return sqlServicioAdicional.darServiciosAdicionales(pmf.getPersistenceManager());
	}

	public ServiciosAdicionales darServicioAdicionalPorId(long idServicioAdicional){
		return sqlServicioAdicional.darServicioAdicionalPorId(pmf.getPersistenceManager(), idServicioAdicional);
	}

	public List<TiposHabitacion> darTiposHabitacion(){
		return sqlTipoHabitacion.darTiposHabitacion(pmf.getPersistenceManager());
	}

	public TiposHabitacion darTipoHabitacionPorId(long idTipoHabitacion){
		return sqlTipoHabitacion.darTipoHabitacionPorId(pmf.getPersistenceManager(), idTipoHabitacion);
	}

	public List<Usuarios> darUsuarios(){
		return sqlUsuario.darUsuarios(pmf.getPersistenceManager());
	}


	public List<VentaProductos> darVentasProducto(){
		return sqlVentaProducto.darVentasProducto(pmf.getPersistenceManager());
	}

	public VentaProductos darVentaProductoPorId(long idVentaProducto){
		return sqlVentaProducto.darVentaProductoPorId(pmf.getPersistenceManager(), idVentaProducto);
	}

	public Usuarios darUsuario(long cedula){
		return sqlUsuario.darUsuarioPorCedula(pmf.getPersistenceManager(), cedula);
	}

	public List dineroServiciosPorHabitacion(){
		List<long []> resp = new LinkedList<long []> ();
		String sql = "SELECT IDHABITACION, SUM(PRECIO) AS TOTAL_RECOLECTADO FROM(SELECT IDHABITACION, PRECIO FROM((SELECT * FROM FACTURAS WHERE IDSERVICIO IS NOT NULL AND FECHA > TO_DATE('2019-01-01', 'YYYY-MM-DD'))A INNER JOIN ESTADIAS ON A.IDESTADIA = ESTADIAS.IDESTADIA))GROUP BY IDHABITACION";
		//System.out.println(sql);
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			long [] datosResp = new long [2];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((BigDecimal) tupla [1]).longValue ();
			resp.add (datosResp);
		}
		return resp;		
	}

	public List topPopulares(){
		List<Object []> resp = new LinkedList<> ();
		String sql = "SELECT NOMBRESERVICIO, SUM(PRECIO) AS TOTAL_RECOLECTADO FROM ((SELECT * FROM FACTURAS WHERE IDSERVICIO IS NOT NULL AND FECHA > TO_DATE('2019-01-01', 'YYYY-MM-DD') AND FECHA < TO_DATE('2019-12-31', 'YYYY-MM-DD'))A INNER JOIN SERVICIOS ON A.IDSERVICIO = SERVICIOS.IDSERVICIO) GROUP BY A.IDSERVICIO, NOMBRESERVICIO ORDER BY SUM(PRECIO) DESC FETCH FIRST 20 ROWS ONLY";
		//System.out.println(sql);
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Object [] datosResp = new Object [2];

			datosResp [0] = ((String) tupla [0]).toString();
			datosResp [1] = ((BigDecimal) tupla [1]).longValue ();
			resp.add (datosResp);
		}
		return resp;		

	}

	public List indiceOcupacion(){
		List<long []> resp = new LinkedList<long []> ();
		String sql = "SELECT IDHABITACION, (NUMEROPERSONAS / CAPACIDAD * 100) AS PORCENTAJEOCUPACION FROM (ESTADIAS A INNER JOIN HABITACIONES B ON A.IDHABITACION = B.NUMEROHABITACION) INNER JOIN TIPOSHABITACION D ON TIPOHABITACION = D.IDTIPOHABITACION";
		//System.out.println(sql);
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		List<Object []> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			long [] datosResp = new long [2];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue();
			datosResp [1] = ((BigDecimal) tupla [1]).longValue ();
			resp.add (datosResp);
		}
		return resp;		

	}

	public List consumoPorUsuarioPorFecha(long idCliente, String fechaI, String fechaF){
		List<long []> resp = new LinkedList<>();
		String sql = "SELECT E.IDCLIENTE, SUM(F.PRECIO) AS CONSUMO_PERSONA FROM FACTURAS F INNER JOIN ESTADIAS E ON F.IDESTADIA = E.IDESTADIA WHERE IDSERVICIO IS NOT NULL AND FECHA > TO_DATE(?, 'YYYY-MM-DD') AND FECHA < TO_DATE(?, 'YYYY-MM-DD') AND E.IDCLIENTE = ? GROUP BY (E.IDCLIENTE)";
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(fechaI, fechaF, idCliente);
		List<Object []> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			long [] datosResp = new long [2];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue();
			datosResp [1] = ((BigDecimal) tupla [1]).longValue ();
			resp.add (datosResp);
		}
		return resp;	
	}

	public List serviciosPrecioEnRango(long abajo, long arriba){
		List<Object []> resp = new LinkedList<> ();
		String sql = "SELECT DISTINCT B.IDSERVICIO, B.NOMBRESERVICIO, PRECIO FROM FACTURAS A INNER JOIN SERVICIOS B ON A.IDSERVICIO = B.IDSERVICIO WHERE PRECIO BETWEEN ? AND ?";
		//System.out.println(sql);
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(abajo, arriba);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Object [] datosResp = new Object [3];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((String) tupla [1]).toString();
			datosResp [2] = ((BigDecimal) tupla [2]).longValue ();
			resp.add (datosResp);
		}
		return resp;		
	}

	public List serviciosFechaEnRango(String inicio, String fin){
		List<Object []> resp = new LinkedList<> ();
		String sql = "SELECT DISTINCT B.IDSERVICIO, B.NOMBRESERVICIO FROM FACTURAS A INNER JOIN SERVICIOS B ON A.IDSERVICIO = B.IDSERVICIO WHERE FECHA > TO_DATE(?, 'YYYY-MM-DD') AND FECHA < TO_DATE(?, 'YYYY-MM-DD')";
		//System.out.println(sql);
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(inicio, fin);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Object [] datosResp = new Object [2];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((String) tupla [1]).toString();
			resp.add (datosResp);
		}
		return resp;		
	}

	public List serviciosPorEmpleado(long numDocEmpleado){
		List<Object []> resp = new LinkedList<> ();
		String sql = "SELECT DISTINCT B.IDSERVICIO, B.NOMBRESERVICIO FROM FACTURAS A INNER JOIN SERVICIOS B ON A.IDSERVICIO = B.IDSERVICIO WHERE  NUMDOCEMPLEADO = ?";
		//System.out.println(sql);
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(numDocEmpleado);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Object [] datosResp = new Object [2];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((String) tupla [1]).toString();
			resp.add (datosResp);
		}
		return resp;	
	}

	public List verBuenosClientes(){
		List<Object []> resp = new LinkedList<> ();
		String sql = "SELECT D.IDCLIENTE, E.NOMBRE, TOTALCONSUMIDO, TOTALDIAS FROM ( ((SELECT  IDCLIENTE, SUM (PRECIO) AS TOTALCONSUMIDO FROM( (SELECT * FROM FACTURAS INNER JOIN ESTADIAS ON FACTURAS.IDESTADIA = ESTADIAS.IDESTADIA)) c GROUP BY C.IDCLIENTE, IDCLIENTE )D FULL OUTER JOIN" +  
				"(SELECT *  FROM( SELECT IDCLIENTE, SUM(TOTAL) AS TOTALDIAS, A.NOMBRE FROM( SELECT IDCLIENTE, (FECHASALIDA - FECHALLEGADA ) as TOTAL, USUARIOS.NOMBRE FROM ESTADIAS, USUARIOS WHERE ESTADIAS.IDCLIENTE IS NOT NULL AND ESTADIAS.IDCLIENTE = USUARIOS.NUMERODOCUMENTO)A GROUP BY IDCLIENTE, A.NOMBRE) )E ON E.IDCLIENTE = D.IDCLIENTE))" + 
				"WHERE (TOTALCONSUMIDO > 1500000 OR TOTALDIAS > 7)";
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Object [] datosResp = new Object [4];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((String) tupla [1]).toString();
			datosResp [2] = ((BigDecimal) tupla [2]).longValue ();
			datosResp [3] = ((BigDecimal) tupla [3]).longValue ();
			resp.add (datosResp);
		}
		return resp;		

	}

	public List serviciosPocaDemanda(){
		List<Object []> resp = new LinkedList<> ();
		String sql = "SELECT IDSERVICIO, NOMBRESERVICIO, MAX (TOTFACTURAS) AS TOTALFACTURASSEMANA FROM( SELECT SERVICIOS.IDSERVICIO, SERVICIOS.NOMBRESERVICIO , to_char(FECHA - 7/24,'IYYY') AS ANIO, to_char(FECHA - 7/24,'IW') AS SEMANA , COUNT(NUMFACTURA) AS TOTFACTURAS FROM FACTURAS FULL OUTER JOIN SERVICIOS ON SERVICIOS.IDSERVICIO = FACTURAS.IDSERVICIO " +
				" GROUP BY SERVICIOS.IDSERVICIO, SERVICIOS.NOMBRESERVICIO, to_char(FECHA - 7/24,'IYYY'), to_char(FECHA - 7/24,'IW') " +
				" HAVING  (to_char(FECHA - 7/24,'IYYY') IS NULL OR to_char(FECHA - 7/24,'IYYY') = 2019) ) "	+
				" GROUP BY IDSERVICIO, NOMBRESERVICIO HAVING MAX (TOTFACTURAS) < 3 ";
		System.out.println(sql);
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Object [] datosResp = new Object [3];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((String) tupla [1]).toString();
			datosResp [2] = ((BigDecimal) tupla [2]).longValue ();
			resp.add (datosResp);
		}
		return resp;	
	}

	public List masNumeroDeVecesServicioSemana(long idServicio){
		List<Object []> resp = new LinkedList<> ();
		String sql = "SELECT * FROM( SELECT IDSERVICIO, to_char(FECHA - 7/24,'IYYY')AS ANIO, to_char(FECHA - 7/24,'IW') AS SEMANA ,COUNT(IDSERVICIO)AS VECES, SUM(PRECIO)AS DINERO FROM ( SELECT FECHA, PRECIO, IDSERVICIO, TIPOHABITACION FROM ( SELECT *  FROM (FACTURAS LEFT OUTER JOIN ESTADIAS ON FACTURAS.IDESTADIA = ESTADIAS.IDESTADIA) )A LEFT OUTER JOIN HABITACIONES ON A.IDHABITACION = HABITACIONES.NUMEROHABITACION WHERE IDSERVICIO = ?) B GROUP BY IDSERVICIO, to_char(FECHA - 7/24,'IYYY'), to_char(FECHA - 7/24,'IW') ORDER BY VECES DESC FETCH FIRST 1 ROWS WITH TIES)Z";
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(idServicio);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Object [] datosResp = new Object [5];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((String) tupla [1]).toString ();
			datosResp [2] = ((String) tupla [2]).toString ();
			datosResp [3] = ((BigDecimal) tupla [3]).longValue ();
			datosResp [4] = ((BigDecimal) tupla [4]).longValue ();
			resp.add (datosResp);
		}
		return resp;	
	}

	public List mayorGananciaServicioSemana(long idServicio){
		List<Object []> resp = new LinkedList<> ();
		String sql = "SELECT IDSERVICIO, to_char(FECHA - 7/24,'IYYY')AS ANIO, to_char(FECHA - 7/24,'IW') AS SEMANA ,COUNT(IDSERVICIO)AS VECES,SUM(PRECIO)AS DINERO FROM ( SELECT FECHA, PRECIO, IDSERVICIO, TIPOHABITACION FROM ( SELECT * FROM (FACTURAS LEFT OUTER JOIN ESTADIAS ON FACTURAS.IDESTADIA = ESTADIAS.IDESTADIA) )A LEFT OUTER JOIN HABITACIONES ON A.IDHABITACION = HABITACIONES.NUMEROHABITACION WHERE IDSERVICIO = ? ) B GROUP BY IDSERVICIO, to_char(FECHA - 7/24,'IYYY'), to_char(FECHA - 7/24,'IW')  ORDER BY DINERO DESC FETCH FIRST 1 ROWS WITH TIES";
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(idServicio);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Object [] datosResp = new Object [5];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((String) tupla [1]).toString();
			datosResp [2] = ((String) tupla [2]).toString ();
			datosResp [3] = ((BigDecimal) tupla [3]).longValue ();
			datosResp [4] = ((BigDecimal) tupla [4]).longValue ();
			resp.add (datosResp);
		}
		return resp;	
	}

	public List menorGananciaServicioSemana(long idServicio){
		List<Object []> resp = new LinkedList<> ();
		String sql = "SELECT IDSERVICIO, to_char(FECHA - 7/24,'IYYY')AS ANIO, to_char(FECHA - 7/24,'IW') AS SEMANA , COUNT(IDSERVICIO)AS VECES, SUM(PRECIO)AS DINERO FROM ( SELECT FECHA, PRECIO, IDSERVICIO, TIPOHABITACION FROM ( SELECT *  FROM (FACTURAS LEFT OUTER JOIN ESTADIAS ON FACTURAS.IDESTADIA = ESTADIAS.IDESTADIA) )A LEFT OUTER JOIN HABITACIONES ON A.IDHABITACION = HABITACIONES.NUMEROHABITACION WHERE IDSERVICIO = ? ) B group by IDSERVICIO, to_char(FECHA - 7/24,'IYYY'), to_char(FECHA - 7/24,'IW') ORDER BY DINERO  FETCH FIRST 1 ROWS WITH TIES"; 
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(idServicio);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Object [] datosResp = new Object [5];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((String) tupla [1]).toString ();
			datosResp [2] = ((String) tupla [2]).toString ();
			datosResp [3] = ((BigDecimal) tupla [3]).longValue ();
			datosResp [4] = ((BigDecimal) tupla [4]).longValue ();
			resp.add (datosResp);
		}
		return resp;
	}

	public List masNumeroDeVecesHabitacionSemana(long idTipoHabitacion){
		List<Object []> resp = new LinkedList<> ();
		String sql = "SELECT * FROM( SELECT TIPOHABITACION, to_char(FECHA - 7/24,'IYYY')AS ANIO, to_char(FECHA - 7/24,'IW') AS SEMANA ,COUNT(IDSERVICIO)AS VECES, SUM(PRECIO)AS DINERO FROM ( SELECT FECHA, PRECIO, IDSERVICIO, TIPOHABITACION FROM (  SELECT *  FROM (FACTURAS LEFT OUTER JOIN ESTADIAS ON FACTURAS.IDESTADIA = ESTADIAS.IDESTADIA) )A LEFT OUTER JOIN HABITACIONES ON A.IDHABITACION = HABITACIONES.NUMEROHABITACION WHERE TIPOHABITACION = ? ) B GROUP BY TIPOHABITACION, to_char(FECHA - 7/24,'IYYY'), to_char(FECHA - 7/24,'IW') ORDER BY VECES DESC FETCH FIRST 1 ROWS WITH TIES )Z";
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(idTipoHabitacion);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Object [] datosResp = new Object [5];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((String) tupla [1]).toString ();
			datosResp [2] = ((String) tupla [2]).toString ();
			datosResp [3] = ((BigDecimal) tupla [3]).longValue ();
			datosResp [4] = ((BigDecimal) tupla [4]).longValue ();
			resp.add (datosResp);
		}
		return resp;	
	}

	public List mayorGananciaHabitacionSemana(long idServicio){
		List<Object []> resp = new LinkedList<> ();
		String sql = "SELECT TIPOHABITACION, to_char(FECHA - 7/24,'IYYY')AS ANIO, to_char(FECHA - 7/24,'IW') AS SEMANA ,COUNT(IDSERVICIO)AS VECES,SUM(PRECIO)AS DINERO FROM ( SELECT FECHA, PRECIO, IDSERVICIO, TIPOHABITACION FROM ( SELECT *  FROM (FACTURAS LEFT OUTER JOIN ESTADIAS ON FACTURAS.IDESTADIA = ESTADIAS.IDESTADIA) )A LEFT OUTER JOIN HABITACIONES ON A.IDHABITACION = HABITACIONES.NUMEROHABITACION WHERE TIPOHABITACION = ? ) B GROUP BY TIPOHABITACION, to_char(FECHA - 7/24,'IYYY'), to_char(FECHA - 7/24,'IW') ORDER BY DINERO DESC FETCH FIRST 1 ROWS WITH TIES";
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(idServicio);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Object [] datosResp = new Object [5];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((String) tupla [1]).toString();
			datosResp [2] = ((String) tupla [2]).toString ();
			datosResp [3] = ((BigDecimal) tupla [3]).longValue ();
			datosResp [4] = ((BigDecimal) tupla [4]).longValue ();
			resp.add (datosResp);
		}
		return resp;	
	}

	public List menorGananciaHabitacionSemana(long idTipoHabitacion){
		List<Object []> resp = new LinkedList<> ();
		String sql = " SELECT TIPOHABITACION, to_char(FECHA - 7/24,'IYYY')AS ANIO, to_char(FECHA - 7/24,'IW') AS SEMANA , COUNT(IDSERVICIO)AS VECES, SUM(PRECIO)AS DINERO FROM ( SELECT FECHA, PRECIO, IDSERVICIO, TIPOHABITACION FROM ( SELECT *  FROM (FACTURAS LEFT OUTER JOIN ESTADIAS ON FACTURAS.IDESTADIA = ESTADIAS.IDESTADIA) )A LEFT OUTER JOIN HABITACIONES ON A.IDHABITACION = HABITACIONES.NUMEROHABITACION WHERE TIPOHABITACION = ? ) B GROUP BY TIPOHABITACION, to_char(FECHA - 7/24,'IYYY'), to_char(FECHA - 7/24,'IW') ORDER BY DINERO  FETCH FIRST 1 ROWS WITH TIES";
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(idTipoHabitacion);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Object [] datosResp = new Object [5];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((String) tupla [1]).toString ();
			datosResp [2] = ((String) tupla [2]).toString ();
			datosResp [3] = ((BigDecimal) tupla [3]).longValue ();
			datosResp [4] = ((BigDecimal) tupla [4]).longValue ();
			resp.add (datosResp);
		}
		return resp;	
	}

	public List masNumeroDeVecesServicioMes(long id){
		List<Object []> resp = new LinkedList<> ();
		String sql = "SELECT * FROM( SELECT IDSERVICIO, to_char(FECHA - 7/24,'IYYY')AS ANIO, to_char(FECHA,'YYYY-MM') AS MES ,COUNT(IDSERVICIO)AS VECES, SUM(PRECIO)AS DINERO FROM ( SELECT FECHA, PRECIO, IDSERVICIO, TIPOHABITACION FROM ( SELECT * FROM (FACTURAS LEFT OUTER JOIN ESTADIAS ON FACTURAS.IDESTADIA = ESTADIAS.IDESTADIA) )A LEFT OUTER JOIN HABITACIONES ON A.IDHABITACION = HABITACIONES.NUMEROHABITACION WHERE IDSERVICIO = ? ) B GROUP BY IDSERVICIO, to_char(FECHA - 7/24,'IYYY'), to_char(FECHA,'YYYY-MM') ORDER BY VECES DESC FETCH FIRST 1 ROWS WITH TIES )Z ";
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(id);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Object [] datosResp = new Object [5];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((String) tupla [1]).toString ();
			datosResp [2] = ((String) tupla [2]).toString ();
			datosResp [3] = ((BigDecimal) tupla [3]).longValue ();
			datosResp [4] = ((BigDecimal) tupla [4]).longValue ();
			resp.add (datosResp);
		}
		return resp;
	}

	public List mayorGananciaServicioMes(long idServicio){
		List<Object []> resp = new LinkedList<> ();
		String sql = "SELECT IDSERVICIO, to_char(FECHA - 7/24,'IYYY')AS ANIO, to_char(FECHA,'YYYY-MM') AS MES ,COUNT(IDSERVICIO)AS VECES,SUM(PRECIO)AS DINERO FROM ( SELECT FECHA, PRECIO, IDSERVICIO, TIPOHABITACION FROM ( SELECT *   FROM (FACTURAS LEFT OUTER JOIN ESTADIAS ON FACTURAS.IDESTADIA = ESTADIAS.IDESTADIA) )A LEFT OUTER JOIN HABITACIONES ON A.IDHABITACION = HABITACIONES.NUMEROHABITACION WHERE IDSERVICIO = ? ) B group by IDSERVICIO, to_char(FECHA - 7/24,'IYYY'), to_char(FECHA,'YYYY-MM')  ORDER BY DINERO DESC FETCH FIRST 1 ROWS WITH TIES";
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(idServicio);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Object [] datosResp = new Object [5];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((String) tupla [1]).toString();
			datosResp [2] = ((String) tupla [2]).toString ();
			datosResp [3] = ((BigDecimal) tupla [3]).longValue ();
			datosResp [4] = ((BigDecimal) tupla [4]).longValue ();
			resp.add (datosResp);
		}
		return resp;	
	}

	public List menorGananciaServicioMes(long idServicio){
		List<Object []> resp = new LinkedList<> ();
		String sql = "SELECT IDSERVICIO, to_char(FECHA - 7/24,'IYYY')AS ANIO, to_char(FECHA,'YYYY-MM') AS MES , COUNT(IDSERVICIO)AS VECES, SUM(PRECIO)AS DINERO FROM ( SELECT FECHA, PRECIO, IDSERVICIO, TIPOHABITACION  FROM ( SELECT *  FROM (FACTURAS LEFT OUTER JOIN ESTADIAS ON FACTURAS.IDESTADIA = ESTADIAS.IDESTADIA) )A  LEFT OUTER JOIN HABITACIONES ON A.IDHABITACION = HABITACIONES.NUMEROHABITACION WHERE IDSERVICIO = ? ) B  group by IDSERVICIO, to_char(FECHA - 7/24,'IYYY'), to_char(FECHA,'YYYY-MM') ORDER BY DINERO  FETCH FIRST 1 ROWS WITH TIES";     
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(idServicio);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Object [] datosResp = new Object [5];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((String) tupla [1]).toString ();
			datosResp [2] = ((String) tupla [2]).toString ();
			datosResp [3] = ((BigDecimal) tupla [3]).longValue ();
			datosResp [4] = ((BigDecimal) tupla [4]).longValue ();
			resp.add (datosResp);
		}
		return resp;
	}

	public List mayorGananciaHabitacionMes(long idServicio){
		List<Object []> resp = new LinkedList<> ();
		String sql = "SELECT TIPOHABITACION, to_char(FECHA - 7/24,'IYYY')AS ANIO, to_char(FECHA,'YYYY-MM') AS MES ,COUNT(IDSERVICIO)AS VECES,SUM(PRECIO)AS DINERO FROM ( SELECT FECHA, PRECIO, IDSERVICIO, TIPOHABITACION FROM ( SELECT *  FROM (FACTURAS LEFT OUTER JOIN ESTADIAS ON FACTURAS.IDESTADIA = ESTADIAS.IDESTADIA) )A LEFT OUTER JOIN HABITACIONES ON A.IDHABITACION = HABITACIONES.NUMEROHABITACION WHERE TIPOHABITACION = ? ) B group by TIPOHABITACION, to_char(FECHA - 7/24,'IYYY'), to_char(FECHA,'YYYY-MM') ORDER BY DINERO DESC FETCH FIRST 1 ROWS WITH TIES";
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(idServicio);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Object [] datosResp = new Object [5];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((String) tupla [1]).toString();
			datosResp [2] = ((String) tupla [2]).toString ();
			datosResp [3] = ((BigDecimal) tupla [3]).longValue ();
			datosResp [4] = ((BigDecimal) tupla [4]).longValue ();
			resp.add (datosResp);
		}
		return resp;	
	}

	public List masNumeroDeVecesHabitacionMes(long idTipoHabitacion){
		List<Object []> resp = new LinkedList<> ();
		String sql = "SELECT * FROM(  SELECT TIPOHABITACION, to_char(FECHA - 7/24,'IYYY')AS ANIO, to_char(FECHA,'YYYY-MM') AS MES ,COUNT(IDSERVICIO)AS VECES, SUM(PRECIO)AS DINERO FROM ( SELECT FECHA, PRECIO, IDSERVICIO, TIPOHABITACION FROM (  SELECT *  FROM (FACTURAS  LEFT OUTER JOIN ESTADIAS ON FACTURAS.IDESTADIA = ESTADIAS.IDESTADIA) )A LEFT OUTER JOIN HABITACIONES ON A.IDHABITACION = HABITACIONES.NUMEROHABITACION WHERE TIPOHABITACION = ? ) B group by TIPOHABITACION, to_char(FECHA - 7/24,'IYYY'), to_char(FECHA,'YYYY-MM')  ORDER BY VECES DESC FETCH FIRST 1 ROWS WITH TIES  )Z ";
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(idTipoHabitacion);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Object [] datosResp = new Object [5];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((String) tupla [1]).toString ();
			datosResp [2] = ((String) tupla [2]).toString ();
			datosResp [3] = ((BigDecimal) tupla [3]).longValue ();
			datosResp [4] = ((BigDecimal) tupla [4]).longValue ();
			resp.add (datosResp);
		}
		return resp;	
	}

	public List menorGananciaHabitacionMes(long idTipoHabitacion){
		List<Object []> resp = new LinkedList<> ();
		String sql = "  SELECT TIPOHABITACION, to_char(FECHA - 7/24,'IYYY')AS ANIO, to_char(FECHA,'YYYY-MM') AS MES , COUNT(IDSERVICIO)AS VECES, SUM(PRECIO)AS DINERO FROM ( SELECT FECHA, PRECIO, IDSERVICIO, TIPOHABITACION FROM ( SELECT *  FROM (FACTURAS LEFT OUTER JOIN ESTADIAS ON FACTURAS.IDESTADIA = ESTADIAS.IDESTADIA) )A LEFT OUTER JOIN HABITACIONES ON A.IDHABITACION = HABITACIONES.NUMEROHABITACION  WHERE TIPOHABITACION = ? ) B group by TIPOHABITACION, to_char(FECHA - 7/24,'IYYY'), to_char(FECHA,'YYYY-MM') ORDER BY DINERO  FETCH FIRST 1 ROWS WITH TIES" ;
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(idTipoHabitacion);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Object [] datosResp = new Object [5];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((String) tupla [1]).toString ();
			datosResp [2] = ((String) tupla [2]).toString ();
			datosResp [3] = ((BigDecimal) tupla [3]).longValue ();
			datosResp [4] = ((BigDecimal) tupla [4]).longValue ();
			resp.add (datosResp);
		}
		return resp;	
	}

	public List darFacturaPorIdEstadia(long idEstadia){
		List<Object []> resp = new LinkedList<> ();
		String sql = "SELECT * FROM FACTURAS WHERE IDESTADIA = ?";
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		//q.setResultClass(Facturas.class);
		q.setParameters(idEstadia);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Object [] datosResp = new Object [11];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = new Date(((Timestamp) tupla [1]).getYear(), ((Timestamp) tupla [1]).getMonth(), ((Timestamp) tupla [1]).getDate());
			datosResp [2] = ((String) tupla [2]).toString();
			datosResp [3] = ((BigDecimal) tupla [3]).longValue ();
			if(tupla [4] == null){

				datosResp [4] = null;
			}
			else{
				datosResp [4] = ((BigDecimal) tupla [4]).longValue ();
			}
			datosResp [5] = ((BigDecimal) tupla [5]).longValue ();
			datosResp [6] = ((BigDecimal) tupla [6]).longValue ();
			datosResp [7] = ((BigDecimal) tupla [7]).longValue ();
			datosResp [8] = ((BigDecimal) tupla [8]).longValue ();
			if(tupla [9] == null){

				datosResp [9] = null;
			}
			else{
				datosResp [9] = ((BigDecimal) tupla [9]).longValue ();
			}
			if(tupla [10] == null){

				datosResp [10] = null;
			}
			else{
				datosResp [10] = ((BigDecimal) tupla [10]).longValue ();

			}
			resp.add (datosResp);
		}
		return resp;	
	}

	public List darHabitacionesLibresPorTipo(Timestamp fechaInicio, Timestamp fechaFin, Long idTipo){
		List<Object []> resp = new LinkedList<> ();
		String sql = "SELECT * FROM HABITACIONES WHERE NUMEROHABITACION NOT IN ( SELECT IDHABITACION FROM ESTADIAS  WHERE ((FECHALLEGADA < ? AND FECHASALIDA > ?) OR (FECHALLEGADA BETWEEN  ? AND ?) OR(FECHASALIDA BETWEEN ? AND ?))  )  AND TIPOHABITACION = ? ";
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(fechaInicio, fechaFin, fechaInicio, fechaFin, fechaInicio, fechaFin, idTipo);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Object [] datosResp = new Object [2];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((BigDecimal) tupla [1]).longValue ();
			resp.add (datosResp);
		}
		return resp;	
	}

	public List<Object []> darHabitacionesLibresEnFechas(Timestamp fechaInicio, Timestamp fechaFin, Long idHabitacion){
		List<Object []> resp = new LinkedList<> ();
		String sql = "SELECT IDESTADIA, FECHALLEGADA, FECHASALIDA, IDPLAN FROM ESTADIAS WHERE IDHABITACION = ? AND ((FECHALLEGADA < ? AND FECHASALIDA > ?) OR (FECHALLEGADA BETWEEN  ? AND ?) OR(FECHASALIDA BETWEEN ? AND ?)) ";
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(idHabitacion, fechaInicio, fechaFin, fechaInicio, fechaFin, fechaInicio, fechaFin);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Object [] datosResp = new Object [4];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = new Date(((Timestamp) tupla [1]).getYear(), ((Timestamp) tupla [1]).getMonth(), ((Timestamp) tupla [1]).getDate());
			datosResp [2] = new Date(((Timestamp) tupla [2]).getYear(), ((Timestamp) tupla [2]).getMonth(), ((Timestamp) tupla [2]).getDate());
			datosResp [3] = ((BigDecimal) tupla [3]).longValue ();
			resp.add (datosResp);
		}
		return resp;
	}

	public List<Object []> darReservasEnFechas(Timestamp fechaInicio, Timestamp fechaFin, long idServicio){
		List<Object []> resp = new LinkedList<> ();
		String sql = "SELECT NUMRESERVA, IDESTADIA FROM RESERVAS INNER JOIN HORARIOS ON RESERVAS.IDHORARIO = HORARIOS.IDHORARIO WHERE RESERVAS.IDSERVICIO = ? AND ((FECHAINICIO < ? AND FECHAFIN> ?) OR (FECHAINICIO BETWEEN  ? AND ?) OR(FECHAFIN BETWEEN ? AND ?))";
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(idServicio, fechaInicio, fechaFin, fechaInicio, fechaFin, fechaInicio, fechaFin);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Object [] datosResp = new Object [2];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((BigDecimal) tupla [1]).longValue ();
			resp.add (datosResp);
		}
		return resp;	
	}

	public List<Object []> darReservasEnFechasRF12(Timestamp fechaInicio, Timestamp fechaFin, long idServicio){
		List<Object []> resp = new LinkedList<> ();
		String sql = "SELECT NUMRESERVA, IDESTADIA, CAPACIDAD FROM RESERVAS INNER JOIN HORARIOS ON RESERVAS.IDHORARIO = HORARIOS.IDHORARIO WHERE RESERVAS.IDSERVICIO = ? AND ((FECHAINICIO < ? AND FECHAFIN> ?) OR (FECHAINICIO BETWEEN  ? AND ?) OR(FECHAFIN BETWEEN ? AND ?))";
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(idServicio, fechaInicio, fechaFin, fechaInicio, fechaFin, fechaInicio, fechaFin);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Object [] datosResp = new Object [3];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((BigDecimal) tupla [1]).longValue ();
			datosResp [2] = ((Double) tupla [2]).doubleValue ();
			resp.add (datosResp);
		}
		return resp;	
	}

	public List<Object []> darVentasProductorf12(long idServicio){
		List<Object []> resp = new LinkedList<> ();
		String sql = "SELECT IDSERVICIO, CAPACIDAD FROM VENTAPRODUCTOS WHERE IDSERVICIO = ?";
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(idServicio);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Object [] datosResp = new Object [2];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((BigDecimal) tupla [1]).doubleValue ();
			resp.add (datosResp);
		}
		return resp;	
	}

	public List<Long []> darSalonesrf12(long idServicio){
		List<Long []> resp = new LinkedList<> ();
		String sql = "SELECT IDSERVICIO, CAPACIDAD FROM SALONES WHERE IDSERVICIO = ?";
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(idServicio);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Long [] datosResp = new Long [2];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((BigDecimal) tupla [1]).longValue();
			resp.add (datosResp);
		}
		return resp;	
	}

	public List<Long []> darPrecioMayor(Long idTipoHab){
		List<Long []> resp = new LinkedList<> ();
		String sql = "SELECT PRECIOPORNOCHE, IDTIPOHABITACION FROM TIPOSHABITACION WHERE IDTIPOHABITACION = ?";
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(idTipoHab);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Long [] datosResp = new Long [2];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((BigDecimal) tupla [1]).longValue();
			resp.add (datosResp);
		}
		return resp;	
	}

	public List<Long []> listaTipoMayor(Long precio){
		List<Long []> resp = new LinkedList<> ();
		String sql = "SELECT IDTIPOHABITACION, PRECIOPORNOCHE FROM TIPOSHABITACION WHERE PRECIOPORNOCHE > ? ORDER BY PRECIOPORNOCHE ";
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(precio);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Long [] datosResp = new Long [2];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((BigDecimal) tupla [1]).longValue();
			resp.add (datosResp);
		}
		return resp;	

	}

	public List<Object []> darServiciosAdicionalesrf12(long idServicio){
		List<Object []> resp = new LinkedList<> ();
		String sql = "SELECT IDSERVICIO, CAPACIDAD FROM SERVICIOSADICIONALES WHERE IDSERVICIO = ?";
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(idServicio);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Object [] datosResp = new Object [2];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((BigDecimal) tupla [1]).doubleValue ();
			resp.add (datosResp);
		}
		return resp;	
	}


	public long darTotalCapacidad(Timestamp fechaInicio, Timestamp fechaFin, long idServicio){
		String sql = "SELECT NUMRESERVA, CAPACIDAD FROM RESERVAS INNER JOIN HORARIOS ON RESERVAS.IDHORARIO = HORARIOS.IDHORARIO WHERE RESERVAS.IDSERVICIO = ? AND ((FECHAINICIO < ? AND FECHAFIN> ?) OR (FECHAINICIO BETWEEN  ? AND ?) OR(FECHAFIN BETWEEN ? AND ?))";
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(idServicio, fechaInicio, fechaFin, fechaInicio, fechaFin, fechaInicio, fechaFin);
		List<Object[]> tuplas = q.executeList();
		long suma = 0;
		for ( Object [] tupla : tuplas)
		{
			Object [] datosResp = new Object [2];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((BigDecimal) tupla [1]).longValue ();
			suma += ((BigDecimal) tupla [1]).longValue ();
		}
		return suma;	
	}

	public BigDecimal selectMax(){

		return sqlEstadia.selectMax(pmf.getPersistenceManager());
	}

	public BigDecimal selectMaxHorario(){
		return sqlHorario.selectMaxHorario(pmf.getPersistenceManager());
	}

	public BigDecimal selectMaxReserva(){
		return sqlReserva.selectMaxReserva(pmf.getPersistenceManager());
	}

	public void rf12(long idPlan, String tipo, double costo, double descuentoAlojamiento, Timestamp fecha, long idDescuento, long idServicio, Long idProducto, long valor, int limiteVeces, 
			long idConvencion, String nombre, int capacidad, Timestamp fechaInicio, Timestamp fechaFin, long idOrganizador, String[] rpta, String[] rpta2) throws Exception
	{
		Transaction tx=pmf.getPersistenceManager().currentTransaction();
		tx.begin();
		try{
			adicionarPlanConvencion(idPlan, tipo, costo, descuentoAlojamiento, fecha);
			adicionarDescuentoConvencion(idDescuento, idPlan, idServicio, idProducto, valor, limiteVeces);
			adicionarConvencion(idConvencion, nombre, capacidad, fechaInicio, fechaFin, idOrganizador, idPlan, 0);
			for (int i = 0; i < rpta.length; i+=2) {
				String tipoHabitacion = rpta[i];
				long tipoHab = Long.parseLong(tipoHabitacion);
				String cantidadHabitaciones = rpta[i+1];
				int cantHab = Integer.parseInt(cantidadHabitaciones);
				List<Object []> listica = darHabitacionesLibresPorTipo(fechaInicio, fechaFin, tipoHab);
				if(cantHab <= listica.size())
				{
					int j = 0;
					for ( Object [] tupla : listica)
					{
						j++;
						long maximo = selectMax().longValue() + 1;
						long idHab =  ((Long) tupla [0]).longValue ();
						adicionarEstadiaConvencion(maximo, fechaInicio, fechaFin, 1, idPlan, idHab, 0, 0, null, idConvencion);
						maximo++;
						if(j == cantHab){
							break;
						}
					}
				}
				else {
					throw new Exception("No fue posible agregar la convencion: No hay habitaciones suficientes");	
				}
			}
			for(int j = 0; j<rpta2.length; j+=4)
			{
				String tipoServicio = rpta2[j];
				String cantidadP = rpta2[j+1];
				String fechaIni = rpta2[j+2];
				String fechaFini = rpta2[j+3];
				long idServicios = Long.parseLong(tipoServicio);
				int cantidadPersonas = Integer.parseInt(cantidadP);
				Timestamp fechaInicioServicio;
				if(fechaIni == null || fechaIni == "")
				{
					fechaInicioServicio = null;
				}
				else
				{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					try
					{
						Timestamp ts = new Timestamp(((java.util.Date)sdf.parse(fechaIni)).getTime());
						fechaInicioServicio = ts;
					}
					catch (Exception e)
					{
						fechaInicioServicio = null;
					}

				}

				Timestamp fechaFinServicio;
				if(fechaFini == null || fechaFini == "")
				{
					fechaFinServicio = null;
				}
				else
				{
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
					try
					{
						Timestamp ts = new Timestamp(((java.util.Date)sdf.parse(fechaFini)).getTime());
						fechaFinServicio = ts;
					}
					catch (Exception e)
					{
						fechaFinServicio = null;
					}
				}
				List<Long []> salones = darSalonesrf12(idServicios);
				List<Object []> ventaProductos = darVentasProductorf12(idServicios);
				List<Object[]> serviciosAdicionales = darServiciosAdicionalesrf12(idServicios);
				if(salones.isEmpty() && ventaProductos.isEmpty()&& serviciosAdicionales.isEmpty() )
				{
					throw new Exception("el servicio indicado no existe");
				}
				else if(!salones.isEmpty())
				{
					for ( Long [] tupla : salones)
					{
						Long [] datosResp = new Long [2];

						datosResp [1] = (tupla [1]);

						if(cantidadPersonas > (tupla [1])){

							throw new Exception("El servicio no tiene la capacidad requerida");
						}
						else if(!darReservasEnFechasRF12(fechaInicio, fechaFin, idServicios).isEmpty())
						{
							throw new Exception("Ya hay una reserva para ese salon");
						}
					}


				}
				else if(!ventaProductos.isEmpty())
				{
					long cantidad = darTotalCapacidad(fechaInicioServicio, fechaFinServicio, idServicios);
					for ( Object [] tupla : salones)
					{
						Object [] datosResp = new Object [2];

						datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
						datosResp [1] = ((Double) tupla [1]).doubleValue();

						if(((Double) tupla [1]).doubleValue() - cantidad < cantidadPersonas)
						{
							throw new Exception ("no hay sillas disponibles para ese servicio");
						}
					}


				}
				else if (!serviciosAdicionales.isEmpty())
				{
					for ( Object [] tupla : serviciosAdicionales)
					{
						Object [] datosResp = new Object [2];

						datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
						datosResp [1] = ((Double) tupla [1]).doubleValue();
						if(cantidadPersonas > ((Double) tupla [1]).doubleValue()){

							throw new Exception("El servicio no tiene la capacidad requerida");
						}
						else if(!darReservasEnFechasRF12(fechaInicio, fechaFin, idServicios).isEmpty())
						{
							throw new Exception("Ya hay una reserva para ese servicio Adicional");
						}

					}

				}
				long maxHorario = selectMaxHorario().longValue() + 1;
				long maxNumReserva = selectMaxReserva().longValue() + 1;
				adicionarHorarioServiciosConvencion(maxHorario, null, idServicios, fechaInicioServicio, null, null, null, fechaFinServicio);
				adicionarReserva(maxNumReserva, null, idServicios, maxHorario, null, idConvencion, Long.valueOf(cantidadPersonas).longValue());
				maxNumReserva++;
				maxHorario++;

			}

			tx.commit();
		}
		catch(Exception e){
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			throw e;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();;
			}
			pmf.getPersistenceManager().close();
			System.out.println("hola");
		}

	}

	public void rollback(){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		if(tx.isActive()){
			tx.rollback();	}
	}
	


	public void commit(){
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		if(tx.isActive()){



			tx.commit();}
	}

	public List<Long []> darReservasPorConvencion(long idReserva, long idConvencion){
		List<Long []> resp = new LinkedList<> ();
		String sql = "SELECT NUMRESERVA,IDCONVENCION FROM RESERVAS  WHERE NUMRESERVA = ? AND IDCONVENCION = ?";
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(idReserva, idConvencion);
		List<Object[]> tuplas = q.executeList();
		for ( Object [] tupla : tuplas)
		{
			Long [] datosResp = new Long [2];

			datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
			datosResp [1] = ((BigDecimal) tupla [1]).longValue();
			resp.add (datosResp);
		}
		return resp;	
	}

	@SuppressWarnings("rawtypes")
	public void rf13(Long idConvencion, int desReservas, String[] idsServicios) throws Exception{
		Transaction tx=pmf.getPersistenceManager().currentTransaction();
		tx.begin();
		try{
			List<Object []> resp = new LinkedList<>();
			String sql = "SELECT IDESTADIA, NUMEROPERSONAS FROM ESTADIAS  WHERE IDCONVENCION = ?";
			Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
			q.setParameters(idConvencion);
			List<Object []> tuplas = q.executeList();
			for ( Object[] tupla : tuplas)
			{
				Object [] datosResp = new Object [2];

				datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
				datosResp [1] = ((BigDecimal) tupla [1]).longValue ();
				resp.add (datosResp);
			}
			if(desReservas > resp.size()){
				tx.rollback();
				throw new Exception("No se pudo desreservar 1");
			}

			for (int i = 0; i < desReservas; i++)
			{
				Long cant =  (Long) resp.get(i)[0];
				sqlEstadia.eliminarEstadia(pmf.getPersistenceManager(), cant);
			}
			if(idsServicios.length > 0)
			{	
				for (int i = 0; i < idsServicios.length; i++) {
					Long idReserva = Long.parseLong(idsServicios[i]);
					List<Long []> listica = darReservasPorConvencion(idReserva, idConvencion);
					if(listica.isEmpty())
					{
						throw new Exception("No se pudo encontrar la reserva");
					}
					else{
						for ( Long [] tupla : listica)
						{
							Long [] datosResp = new Long [2];

							datosResp [0] = (tupla [0]);

							sqlReserva.eliminarReserva(pmf.getPersistenceManager(), datosResp [0]);
						}

					}
				}
			}
			tx.commit();

		}
		catch(Exception e){
			tx.rollback();
			throw new Exception("No se pudo desreservar 2");
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();;
			}
			pmf.getPersistenceManager().close();
		}



	}

	public void rf14(Long idConvencion) throws Exception{
		Transaction tx=pmf.getPersistenceManager().currentTransaction();
		tx.begin();
		try{
			List<Object []> resp = new LinkedList<>();
			String sql = "SELECT NUMFACTURA, FUEPAGADA FROM FACTURAS WHERE IDCONVENCION = ?  AND FUEPAGADA = 0";
			Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
			q.setParameters(idConvencion);
			List<Object []> tuplas = q.executeList();
			if(!tuplas.isEmpty()){
				for ( Object[] tupla : tuplas)
				{
					Object [] datosResp = new Object [2];

					datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
					datosResp [1] = ((String) tupla [1]).toString ();
					resp.add (datosResp);
				}
				for (int i = 0; i < tuplas.size(); i++)
				{
					Long id =  (Long) resp.get(i)[0];
					sqlFactura.cambiarAPagada(pmf.getPersistenceManager(), id);
				}
			}
			List<Object []> resp1 = new LinkedList<>();
			String sql1 = "SELECT IDESTADIA, NUMEROPERSONAS FROM ESTADIAS WHERE IDCONVENCION = ? AND PAGADO = 0 AND CHECKIN = 1";
			Query q1 = pmf.getPersistenceManager().newQuery(SQL, sql1);
			q1.setParameters(idConvencion);
			List<Object []> tuplas1 = q1.executeList();
			if(!tuplas1.isEmpty()){
				for ( Object[] tupla : tuplas1)
				{
					Object [] datosResp = new Object [2];

					datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
					datosResp [1] = ((BigDecimal) tupla [1]).longValue();
					resp1.add (datosResp);
				}
				for (int i = 0; i < resp1.size(); i++)
				{
					Long id =  (Long) resp1.get(i)[0];
					List facturas = darFacturaPorIdEstadia(id);

					for(Object l: facturas)
					{

						cambiarFacturasAPagada(((BigDecimal) l).longValue());
					}
					cambiarEstadiaAPagada(id);
				}
			}
			sqlConvencion.cambiarAPagada(pmf.getPersistenceManager(), idConvencion);
			tx.commit();

		}
		catch(Exception e){
			tx.rollback();
			throw new Exception("No se pudo finalizar la convencion");
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();;
			}
			pmf.getPersistenceManager().close();
		}

	}

	public void rf15Servicios(Long idMantenimiento, String causa, String[] arregloIds, Timestamp fechaInicio, Timestamp fechaFin) {
		Transaction tx=pmf.getPersistenceManager().currentTransaction();
		tx.begin();
		try {
			for (int i = 0; i < arregloIds.length; i++) {
				String idsito = arregloIds[i];
				Long idServicio = Long.parseLong(idsito);
				Long max = selectMaxHorario().longValue() + 1;
				adicionarHorarioMantenimiento(max, null, idServicio, fechaInicio, null, null, null, fechaFin);
				sqlMantenimiento.adicionarMantenimiento(pmf.getPersistenceManager(), idMantenimiento, causa, max, idServicio, null, 0);
				max++;
				idMantenimiento++;
				List<Object []> reservas = darReservasEnFechas(fechaInicio, fechaFin, idServicio);
				for ( Object[] tupla : reservas)
				{
					sqlReserva.eliminarReserva(pmf.getPersistenceManager(), ((Long) tupla [0]));
				}
			}

			tx.commit();
		}
		catch (Exception e) {
			throw e;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();

			}
			pmf.getPersistenceManager().close();
		}

	}

	
	public Long darDescuentoPlan(Long idPlan){
		String sql = "SELECT DESCUENTOALOJAMIENTO FROM PLANES WHERE IDPLAN = ?";
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(idPlan);
		return (Long) q.executeUnique();
	}

	
	public void rf15Habitaciones(Long idMantenimiento, String causa, String[] arregloIds, Timestamp fechaInicio, Timestamp fechaFin) throws Exception {
		Transaction tx=pmf.getPersistenceManager().currentTransaction();
		tx.begin();
		try {
			for (int i = 0; i < arregloIds.length; i++) {
				String idsito = arregloIds[i];
				Long idHabitacion = Long.parseLong(idsito);
				Long max = selectMaxHorario().longValue() + 1;
				adicionarHorarioMantenimiento(max, null, null, fechaInicio, null, null, null, fechaFin);
				sqlMantenimiento.adicionarMantenimiento(pmf.getPersistenceManager(), idMantenimiento, causa, max, null, idHabitacion, 0);
				List<Object []> listica = darHabitacionesLibresEnFechas(fechaInicio, fechaFin, idHabitacion);
				if(!listica.isEmpty()){
					Long tipoHab = sqlHabitacion.darTipoHabitacionDeHabitacion(pmf.getPersistenceManager(), idHabitacion);
					for ( Object[] tupla : listica)
					{
						List<Object []> habitacionesLibres = darHabitacionesLibresPorTipo(fechaInicio, fechaFin, tipoHab);
						Long idEstadia = ((Long) tupla[0]).longValue ();
						if(!habitacionesLibres.isEmpty()){
							Object [] habitacionLibre = habitacionesLibres.get(0);
							Long idHab = ((Long) habitacionLibre[0]).longValue();
							cambiarNumHabEstadia(idHab, idEstadia);

						}
						else
						{
							List<Long []> precio = darPrecioMayor(tipoHab);
							Long precioOriginal = precio.get(0)[0];
							boolean encontradaHab = false;
							while(!encontradaHab)
							{
								//Buscamos el precio de ese tipo de habitacion
								List<Long []> precios = darPrecioMayor(tipoHab);
								Long precioMayor = precios.get(0)[0];
								//Buscamos el primer tipo de habitacion con un precio inmediatamente mayor
								List<Long []> listaTipoMayor = listaTipoMayor(precioMayor);
								if(!listaTipoMayor.isEmpty())
								{ 
									habitacionesLibres = darHabitacionesLibresPorTipo(fechaInicio, fechaFin, tipoHab);
									//BuscarHabitacionesLibresDeEsaCategoria
									if(!habitacionesLibres.isEmpty())
									{
										Object [] habitacionLibre = habitacionesLibres.get(0);
										Long idHab = ((Long) habitacionLibre[0]).longValue();
										Long idPlan = ((Long) listica.get(0)[3]).longValue ();
										if(idPlan != 0){
											Long descuento = darDescuentoPlan(idPlan);
											Double op = Math.floor(100 * ((listaTipoMayor.get(0) [1]) -  (precioOriginal*descuento/100)/(listaTipoMayor.get(0) [1]) ));
											Long nuevoDescuento = op.longValue();
											String sql1 = "UPDATE PLANES SET DESCUENTOALOJAMIENTO = ? WHERE IDPLAN = ?";
											Query q1 = pmf.getPersistenceManager().newQuery(SQL, sql1);
											q1.setParameters(nuevoDescuento, idPlan);
											q1.executeUnique();
											
										}
										else{
											Double op = Math.floor(100 * ((listaTipoMayor.get(0) [1]) -  (precioOriginal)/(listaTipoMayor.get(0) [1]) ));
											Long nuevoDescuento = op.longValue();
											sqlPlan.adicionarPlan(pmf.getPersistenceManager(), idPlan, "PLAN MANTENIMIENTO", 0, nuevoDescuento, null);
											//agragarle nuevo plan
										}
										encontradaHab = true;
										cambiarNumHabEstadia(idHab, idEstadia);
									}
									else{
										tipoHab = listaTipoMayor.get(0)[0];
										
									}
									
								}
								else{
									throw new Exception("No hay habitaciones libres para reacomodar al cliente");
								}
							}
						}
						
					}

				}
				max++;
				idMantenimiento++;
			}
			tx.commit();
		}
		catch (Exception e) {
			System.out.println("holaaaaaaa");
			throw e;
			
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();

			}
			pmf.getPersistenceManager().close();
		}
	}
	
	public void cambiarNumHabEstadia(Long idHabitacion, Long idEstadia){
		String sql = "UPDATE ESTADIAS SET IDHABITACION = ? WHERE IDESTADIA = ?";
		Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
		q.setParameters(idHabitacion, idEstadia);
		q.executeUnique();
	}



	public void rf16Servicio(String[] resp) throws Exception {
		Transaction tx=pmf.getPersistenceManager().currentTransaction();
		tx.begin();
		try {
			for (int i = 0; i < resp.length; i++) {
				List<Object []> listica = new LinkedList<>();
				String sql = "SELECT IDMANTENIMIENTO, IDHORARIO  FROM MANTENIMIENTO WHERE IDSERVICIO = ? AND FINALIZADO = 0";
				Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
				Long idServicio = Long.parseLong(resp[i]);
				q.setParameters(idServicio);
				List<Object []> tuplas = q.executeList();
				if(!tuplas.isEmpty()){
					for ( Object[] tupla : tuplas)
					{
						Object [] datosResp = new Object [2];

						datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
						datosResp [1] = ((BigDecimal) tupla [1]).longValue ();
						listica.add (datosResp);
					}
					for (int j = 0; j < tuplas.size(); j++)
					{
						Long idMantenimiento =  (Long) listica.get(j)[0];
						Long idHorario = (Long) listica.get(j)[1];
						sqlMantenimiento.actualizarAFinalizado(pmf.getPersistenceManager(), idMantenimiento);
						sqlHorario.actualizarHorario(pmf.getPersistenceManager(), idHorario);
					}
				}
				else
				{
					throw new Exception ("No hay mantenimientos en ese servicio para finalizar");
				}			
			}
			tx.commit();
		}
		catch (Exception e) {
			throw e;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();

			}
			pmf.getPersistenceManager().close();
		}
	}

	public void rf16Habitacion(String[] resp) throws Exception {
		Transaction tx=pmf.getPersistenceManager().currentTransaction();
		tx.begin();
		try {
			for (int i = 0; i < resp.length; i++) {
				List<Object []> listica = new LinkedList<>();
				String sql = "SELECT IDMANTENIMIENTO, IDHORARIO  FROM MANTENIMIENTO WHERE NUMHABITACION = ? AND FINALIZADO = 0";
				Query q = pmf.getPersistenceManager().newQuery(SQL, sql);
				Long idHabitacion = Long.parseLong(resp[i]);
				q.setParameters(idHabitacion);
				List<Object []> tuplas = q.executeList();
				if(!tuplas.isEmpty()){
					for ( Object[] tupla : tuplas)
					{
						Object [] datosResp = new Object [2];

						datosResp [0] = ((BigDecimal) tupla [0]).longValue ();
						datosResp [1] = ((BigDecimal) tupla [1]).longValue ();
						listica.add (datosResp);
					}
					for (int j = 0; j < tuplas.size(); j++)
					{
						Long idMantenimiento =  (Long) listica.get(j)[0];
						Long idHorario = (Long) listica.get(j)[1];
						sqlMantenimiento.actualizarAFinalizado(pmf.getPersistenceManager(), idMantenimiento);
						System.out.println("Hola");
						sqlHorario.actualizarHorario(pmf.getPersistenceManager(), idHorario);
					}
				}
				else
				{
					throw new Exception ("No hay mantenimientos en ese servicio para finalizar");
				}			
			}
			tx.commit();
		}
		catch (Exception e) {
			throw e;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();

			}
			pmf.getPersistenceManager().close();
		}
		
	}






}
