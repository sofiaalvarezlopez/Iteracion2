package uniandes.isis2304.hotelAndes.negocio;


import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

import uniandes.isis2304.hotelAndes.persistencia.*;;



public class HotelAndes {
	
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecuciÃ³n
	 */
	private static Logger log = Logger.getLogger(HotelAndes.class.getName());
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaHotelAndes pp;
	
	/* ****************************************************************
	 * 			MÃ©todos
	 *****************************************************************/
	/**
	 * El constructor por defecto
	 */
	public HotelAndes ()
	{
		pp = PersistenciaHotelAndes.getInstance ();
	}
	
	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public HotelAndes (JsonObject tableConfig)
	{
		pp = PersistenciaHotelAndes.getInstance (tableConfig);
	}
	
	/**
	 * Cierra la conexiÃ³n con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia ()
	{
		pp.cerrarUnidadPersistencia ();
	}
	/* ****************************************************************
	 * 			Métodos para manejar los TIPOS DE USUARIO
	 ********************************************************/
	
	public Usuarios darUsuario(long cedula){
		log.info ("Dando Cliente :  " + cedula);
		Usuarios clienEmp = pp.darUsuario(cedula);
		log.info ("Dando Cliente :  " + clienEmp);
		return clienEmp;
	}
	
	public List <TipoUsuario> darTiposUsuarios(){
		log.info("Consultando Tipos de Usuario");
		List <TipoUsuario> tiposUsuarios = pp.darTiposUsuario();
		log.info("Conultando tipos de usuario " + tiposUsuarios.size() + " existentes");
		return tiposUsuarios;
	}
	public List <VOTipoUsuario> darVOTiposUsuarios(){
		log.info("Consultando los VO de Tipos de Usuario");
		List <VOTipoUsuario> tiposUsuarios = new LinkedList<VOTipoUsuario>();
		for(TipoUsuario tu : pp.darTiposUsuario())
		{
			tiposUsuarios.add(tu);
		}
		log.info("Conultando tipos de usuario " + tiposUsuarios.size() + " existentes");
		return tiposUsuarios;
	}
	public TipoUsuario darTipoUsuarioPorID(long id)
	{
		log.info("Buscando tipo de usuario por id: " + id);
		return pp.darTipoUsuarioPorId(id);
	}
	
	public TipoUsuario adicionarTipoUsuario(long idTipoUsuario, String nombreTipoUsuario){
		log.info ("Adicionando Usuario " + nombreTipoUsuario);
		TipoUsuario usuario = pp.adicionarTipoUsuario(idTipoUsuario, nombreTipoUsuario);
        log.info ("Adicionando bebida: " + usuario);
        return usuario;
	}
	
	
	
	/* ****************************************************************
	 * 			Métodos para manejar los  USUARIOS
	 ********************************************************/
	public Usuarios adicionarUsuario (long numeroDocumento, String tipoDocumento, String nombre, String correoElectronico, long idTipoUsuario)
	{
		log.info ("Adicionando Usuario " + nombre);
		Usuarios usuario = pp.adicionarUsuario(numeroDocumento, tipoDocumento, nombre, correoElectronico, idTipoUsuario);
        log.info ("Adicionando bebida: " + usuario);
        return usuario;
	}
	public List<Usuarios> darUsuarios()
	{
		log.info("Conusltando Usuarios");
		List <Usuarios> usuarios = pp.darUsuarios();
		log.info("Consultando Usuarios: " + usuarios.size() + " usuarios");
		return usuarios;
	}
	public Usuarios darUsuarioPorID(long idUsuario)
	{
		log.info("Buscando usuario por ID: "  + idUsuario);
		return pp.darUsuario(idUsuario);

	}
	
	/* ****************************************************************
	 * 			Métodos para manejar la Cadena Hotelera
	 ********************************************************/
	public CadenaHotelera adicionarCadenaHotelera (long idCadena, String nombre)
	{
		log.info ("Adicionando CadenaHotelera " + nombre);
		CadenaHotelera ch = pp.adicionarCadenaHotelera(idCadena, nombre);
        log.info ("Adicionando Cadena Hotelera: " + ch);
        return ch;
	}
	public List<CadenaHotelera> darCadenasHoteleras()
	{
		log.info("Conusltando Cadenas Hoteleras");
		List <CadenaHotelera> ch = pp.darCadenasHoteleras();
		log.info("Consultando Cadenas Hoteleras: " + ch.size() + " cadenas");
		return ch;
	}
	public CadenaHotelera darCadenaHoteleraPorID(long id)
	{
		log.info("Buscando Cadena Hotelera por ID: "  + id);
		return pp.darCadenaHoteleraPorId(id);
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los  Hoteles
	 ********************************************************/
	public Hotel adicionarHotel (long idHotel, String nombre, String direccion, String ciudad, int estrellas, long idCadenaHotelera)
	{
		log.info ("Adicionando Hotel " + nombre);
		Hotel hotel = pp.adicionarHotel(idHotel,  nombre,  direccion, ciudad, estrellas, idCadenaHotelera);
        log.info ("Adicionando Hotel: " + hotel);
        return hotel;
	}
	public List<Hotel> darHoteles()
	{
		log.info("Conusltando Hoteles");
		List <Hotel> hoteles = pp.darHoteles();
		log.info("Consultando Hoteles: " + hoteles.size() + " hoteles");
		return hoteles;
	}
	public Hotel darHotelPorID(long id)
	{
		log.info("Buscando hotel por ID: "  + id);
		return pp.darHotelPorId(id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar los  Tipos de Habitacion
	 ********************************************************/
	public TipoHabitacion adicionarTipoHabitacion (long idTipoHabitacion, String descripcion, int capacidad, int precioNoche, long idHotel)
	{
		log.info ("Adicionando Habitacion " + descripcion);
		TipoHabitacion th = pp.adicionarTipoHabitacion(idTipoHabitacion, descripcion,  capacidad,  precioNoche, idHotel);
        log.info ("Adicionando tipoHabitacion: " + th);
        return th;
	}
	public List<TipoHabitacion> darTiposHabitaciones()
	{
		log.info("Conusltando Tipos Habitaciones");
		List <TipoHabitacion> th = pp.darTiposHabitacion();
		log.info("Consultando Tipos Habitaciones: " + th.size() + " tipos habitaciones");
		return th;
	}
	public TipoHabitacion darTipoHabitacionPorID(long id)
	{
		log.info("Buscando tipo Habitacion por ID: "  + id);
		return pp.darTipoHabitacionPorId(id);
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar Habitacion
	 ********************************************************/
	public Habitacion adicionarHabitacion (long numHabitacion, long idTipoHabitaciones)
	{
		log.info ("Adicionando Habitacion " + numHabitacion);
		Habitacion th = pp.adicionarHabitacion(numHabitacion, idTipoHabitaciones);
        log.info ("Adicionando Habitacion: " + th);
        return th;
	}
	public List<Habitacion> darHabitaciones()
	{
		log.info("Conusltando  Habitaciones");
		List <Habitacion> h = pp.darHabitaciones();
		log.info("Consultando Habitaciones: " + h.size() + " habitaciones");
		return h;
	}
	public Habitacion darHabitacionPorID(long id)
	{
		log.info("Buscando Habitacion por ID: "  + id);
		return pp.darHabitacionPorId(id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar Dotacion
	 ********************************************************/
	public Dotacion adicionarDotacion (long idDotacion, String nombre, double precio, long idTipoHabitacion)
	{
		log.info ("Adicionando Dotacion " + nombre);
		Dotacion d = pp.adicionarDotacion( idDotacion,  nombre,  precio,idTipoHabitacion);
        log.info ("Adicionando Dotacion: " + d);
        return d;
	}
	public List<Dotacion> darDotaciones()
	{
		log.info("Conusltando Dotaciones ");
		List <Dotacion> d = pp.darDotaciones();
		log.info("Consultando Dotaciones" + d.size() + " dotaciones");
		return d;
	}
	public Dotacion darDotacionPorID(long id)
	{
		log.info("Buscando Dotacion por ID: "  + id);
		return pp.darDotacionPorId(id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar Servicio
	 ********************************************************/
	public Servicio adicionarServicio(long idServicio, String nombreServicio)
	{
		log.info ("Adicionando Servicio " + nombreServicio);
		Servicio s = pp.adicionarServicio( idServicio, nombreServicio);
        log.info ("Adicionando Servicio: " + s);
        return s;
	}
	public List<Servicio> darServicios()
	{
		log.info("Conusltando Servicios ");
		List <Servicio> s = pp.darServicios();
		log.info("Consultando Servicios" + s.size() + " servicios");
		return s;
	}
	public Servicio darServicioPorID(long id)
	{
		log.info("Buscando Servicio por ID: "  + id);
		return pp.darServicio(id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar VentaProducto
	 ********************************************************/
	public VentaProducto adicionarVentaProducto (long idServicio, int capacidad, String estilo, String tipo)
	{
		log.info ("Adicionando VentaProducto de tipo" + tipo);
		VentaProducto d = pp.adicionarVentaProducto(idServicio, capacidad, estilo, tipo);
        log.info ("Adicionando VEnta Producto: " + d);
        return d;
	}
	public List<VentaProducto> darVentasProductos()
	{
		log.info("Conusltando VentasProductos ");
		return pp.darVentasProducto();

	}
	public VentaProducto darVentaProductoPorID(long id)
	{
		log.info("Buscando VentaProducto por ID: "  + id);
		return pp.darVentaProductoPorId(id);

	}
	/* ****************************************************************
	 * 			Métodos para manejar Salon
	 ********************************************************/
	public Salon adicionarSalon (long idServicio, int capacidad, double costoPorHora, String tipo)
	{
		log.info ("Adicionando Salon ");
		Salon s = pp.adicionarSalon( idServicio,  capacidad,  costoPorHora, tipo);
        log.info ("Adicionando Dotacion: " + s);
        return s;
	}
	public List<Salon> darSalones()
	{
		log.info("Conusltando Salones ");
		List <Salon> s = pp.darSalones();
		log.info("Consultando Salones" + s.size() + " salones");
		return s;
	}
	public Salon darSalonPorID(long id)
	{
		log.info("Buscando Salon por ID: "  + id);
		return pp.darSalonPorId(id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar ServicioAdicional
	 ********************************************************/
	public ServicioAdicional adicionarServicioAdicional ( long idServicioAdicional, int capacidad)
	{
		log.info ("Adicionndo ServicioAdicional " );
		ServicioAdicional sa = pp.adicionarServicioAdicional(idServicioAdicional, capacidad);
        log.info ("Adicionando ServicioAdicional: " + sa);
        return sa;
	}
	public List<ServicioAdicional> darServiciosAdicionales()
	{
		log.info("Conusltando Servicios Adicionales ");
		List <ServicioAdicional> sa = pp.darServiciosAdicionales();
		log.info("Consultando Servicios Adicionales" + sa.size() + " Servicios Adicionales");
		return sa;
	}
	public ServicioAdicional darServicioAdicionalPorID(long id)
	{
		log.info("Buscando ServicioAdicional por ID: "  + id);
		return pp.darServicioAdicionalPorId(id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar Producto
	 ********************************************************/
	public Producto adicionarProducto(long idProducto, double precio, String nombre, int cantidad, int duracion, String categoria, long idVentaProducto)
	{
		log.info ("Adicionando Producto " + nombre);
		Producto p = pp.adicionarProducto(idProducto, precio, nombre, cantidad, duracion, categoria, idVentaProducto);
        log.info ("Adicionando Producto: " + p);
        return p;
	}
	public List<Producto> darProducto()
	{
		log.info("Conusltando Productoes ");
		List <Producto>  p = pp.darProductos();
		log.info("ConsultandoProductos" + p.size() + " Productos");
		return p;
	}
	public Producto darProductoPorID(long id)
	{
		log.info("Buscando Producto por ID: "  + id);
		return pp.darProductoPorId(id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar DotacionSalon
	 ********************************************************/
	public DotacionSalon adicionarDotacionSalon(long idDotacion, String nombre, double valor, long idSalon)
	{
		log.info ("Adicionando DotacionSalon " + nombre);
		DotacionSalon ds = pp.adicionarDotacionSalon(idDotacion, nombre, valor, idSalon);
        log.info ("Adicionando DotacionSalon: " + ds);
        return ds;
	}
	public List<DotacionSalon> darDotacionSalones()
	{
		log.info("Conusltando DotacionSalones ");
		return pp.darDotacionesSalon();
	}
	public DotacionSalon darDotacionSalonPorID(long id)
	{
		log.info("Buscando DotacionSalon por ID: "  + id);
		return  pp.darDotacionSalonPorId(id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar CaracteristicaAdicional
	 ********************************************************/
	public CaracteristicaAdicional adicionarCaracteristicaAdicional(long idCaracteristicaAdicional, String nombre, double valor, long idServicioAdicional)
	{
		log.info ("Adicionando CaracteristicaAdicional " + nombre);
		CaracteristicaAdicional ca = pp.adicionarCaracteristicaAdicional(idCaracteristicaAdicional, nombre, valor, idServicioAdicional);
        log.info ("Adicionando CaracteristicaAdicional: " + ca);
        return ca;
	}
	public List<CaracteristicaAdicional> darCaracteristicasAdicionales()
	{
		log.info("Conusltando Caracteristicas Adicionales ");
		List <CaracteristicaAdicional>  ca = pp.darCaracrteristicasAdicionales();
		log.info("Consultando  CaracteristicaAdicionales" + ca.size() + " Caracteristicas Adicionales");
		return ca;
	}
	public CaracteristicaAdicional darCaracteristicaAdicionalPorID(long id)
	{
		log.info("Buscando CaracteristicaAdicional por ID: "  + id);
		return pp.darCaracteristicaAdicionalPorId(id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar Horario
	 ********************************************************/
	public Horario adicionarHorario(long idHorario,String duracion, long idServicio, Timestamp fechaInicio, String dia, String  horaInicio,String horaFin, Timestamp fechaFin)
	{
		log.info ("Adicionando Horario " );
		Horario h = pp.adicionarHorario(idHorario, duracion, idServicio, fechaInicio, dia, horaInicio, horaFin, fechaFin);
        log.info ("Adicionando Horario: " + h);
        return h;
	}
	public List<Horario> darHorarios()
	{
		log.info("Conusltando Horarios");
		List <Horario>  h = pp.darHorarios();
		log.info("Consultando  Horarios" + h.size() + " Horarios");
		return h;
	}
	public Horario darHorarioPorID(long id)
	{
		log.info("Buscando Horario por ID: "  + id);
		return pp.darHorarioPorId(id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar Plan
	 ********************************************************/
	public Plan adicionarPlan(long idPlan, String tipo, double costo, double descuentoAlojamiento, Timestamp fecha)
	{
		log.info ("Adicionando Plan " + tipo);
		Plan p = pp.adicionarPlan(idPlan, tipo, costo, descuentoAlojamiento, fecha);
        log.info ("Adicionando Plan: " + p);
        return p;
	}
	public List<Plan> darPlanes()
	{
		log.info("Consultando Plan");
		List <Plan>  p = pp.darPlanes();
		log.info("Consultando  Planes" + p.size() + " Planes");
		return p;
	}
	public Plan darPlanPorID(long id)
	{
		log.info("Buscando Plan por ID: "  + id);
		return pp.darPlan(id);		
	}
	/* ****************************************************************
	 * 			Métodos para manejar Descuento
	 ********************************************************/
	public Descuento adicionarDescuento(long idDescuento,long idPlan,long idServicio,long idProducto, long valor, int limiteVeces)
	{
		log.info ("Adicionando Descuento ");
		Descuento d = pp.adicionarDescuento(idDescuento, idPlan, idServicio, idProducto, valor, limiteVeces);
        log.info ("Adicionando Descuento: " + d);
        return d;
	}
	public List<Descuento> darDescuentos()
	{
		log.info("Consultando Descuentos");
		List <Descuento>  d = pp.darDescuentos();
		log.info("Consultando  Descuentos" + d.size() + " Descuentos");
		return d;
	}
	public Descuento darDescuentoPorID(long id)
	{
		log.info("Buscando Descuento por ID: "  + id);
		return pp.darDescuentoPorId(id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar Estadia
	 ********************************************************/
	public Estadia adicionarEstadia(long idEstadia, Timestamp fechaLlegada,Timestamp  fechaSalida,int numPersonas,long  idPlan, long idHabitacion, int checkIn, int pago, String tipoDoc, long numDoc)
	{
		log.info ("Adicionando Estadia ");
		Estadia e = pp.adicionarEstadia(idEstadia, fechaLlegada, fechaSalida, numPersonas, idPlan, idHabitacion, checkIn, pago, tipoDoc, numDoc);
        log.info ("Adicionando Estadia: " + e);
        return e;
	}
	public List<Estadia> darEstadias()
	{
		log.info("Consultando Estadias");
		List <Estadia>  e = pp.darEstadias();
		log.info("Consultando  Estadias" + e.size() + " Estadias");
		return e;
	}
	public Estadia darEstadiaPorID(long id)
	{
		log.info("Buscando Estadia por ID: "  + id);
		return pp.darEstadiaPorId(id);
	}
	public long cambiarEstadiaAPagada(long idEstadia)
	{
		return pp.cambiarEstadiaAPagada(idEstadia);
	}
	
	public long checkInCliente(long idEstadia)
	{
		return checkInCliente(idEstadia);
	}
	// UPDATE
	/* ****************************************************************
	 * 			Métodos para manejar Reserva
	 ********************************************************/
	public Reserva adicionarReserva(long numReserva, long idEstadia, long idServicio, long idHorario, long idConsumo)
	{
		log.info ("Adicionando Reserva ");
		Reserva r = pp.adicionarReserva(numReserva, idEstadia, idServicio, idHorario, idConsumo);
        log.info ("Adicionando Reserva: " + r);
        return r;
	}
	public List<Reserva> darReservas()
	{
		log.info("Consultando Reservas");
		List <Reserva>  r = pp.darReservas();
		log.info("Consultando  Reservas" + r.size() + " Reservas");
		return r;
	}
	public Reserva darReservaPorID(long id)
	{
		log.info("Buscando Reserva por ID: "  + id);
		return pp.darReservaPorId(id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar Factura
	 ********************************************************/
	public Factura adicionarFactura(long numFactura, Timestamp fecha, int pagada, long precio, long idDotacion, long idServicio, String  tipoDocEmpleado, long idEstadia, long numDocEmpleado, long idConsumo)
	{
		log.info ("Adicionando Factura ");
		Factura f = pp.adicionarFactura(numFactura, fecha, pagada, precio, idDotacion, idServicio, tipoDocEmpleado, idEstadia, numDocEmpleado, idConsumo);
        log.info ("Adicionando Factura: " + f);
        return f;
	}
	public List<Factura> darFacturas()
	{
		log.info("Consultando Facturas");
		List <Factura>  f = pp.darFacturas();
		log.info("Consultando  Facturas" + f.size() + " Facturas");
		return f;
	}
	public Factura darFacturaPorID(long id)
	{
		log.info("Buscando Factura por ID: "  + id);
		return pp.darFacturaPorId(id);
	}
	public long cambiarFacturaAPagada(long idFactura)
	{
		return pp.cambiarFacturasAPagada(idFactura);
	}
	
	
	
	
	
	
	

	

}