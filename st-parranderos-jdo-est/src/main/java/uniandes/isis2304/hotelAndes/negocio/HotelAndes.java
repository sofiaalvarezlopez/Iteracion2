package uniandes.isis2304.hotelAndes.negocio;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;

import com.google.gson.JsonObject;

import uniandes.isis2304.hotelAndes.persistencia.*;


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
	public CadenasHoteleras adicionarCadenaHotelera (long idCadena, String nombre)
	{
		log.info ("Adicionando CadenaHotelera " + nombre);
		CadenasHoteleras ch = pp.adicionarCadenaHotelera(idCadena, nombre);
        log.info ("Adicionando Cadena Hotelera: " + ch);
        return ch;
	}
	public List<CadenasHoteleras> darCadenasHoteleras()
	{
		log.info("Conusltando Cadenas Hoteleras");
		List <CadenasHoteleras> ch = pp.darCadenasHoteleras();
		log.info("Consultando Cadenas Hoteleras: " + ch.size() + " cadenas");
		return ch;
	}
	public CadenasHoteleras darCadenaHoteleraPorID(long id)
	{
		log.info("Buscando Cadena Hotelera por ID: "  + id);
		return pp.darCadenaHoteleraPorId(id);
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los  Hoteles
	 ********************************************************/
	public Hoteles adicionarHotel (long idHotel, String nombre, String direccion, String ciudad, int estrellas, long idCadenaHotelera)
	{
		log.info ("Adicionando Hotel " + nombre);
		Hoteles hotel = pp.adicionarHotel(idHotel,  nombre,  direccion, ciudad, estrellas, idCadenaHotelera);
        log.info ("Adicionando Hotel: " + hotel);
        return hotel;
	}
	public List<Hoteles> darHoteles()
	{
		log.info("Conusltando Hoteles");
		List <Hoteles> hoteles = pp.darHoteles();
		log.info("Consultando Hoteles: " + hoteles.size() + " hoteles");
		return hoteles;
	}
	public Hoteles darHotelPorID(long id)
	{
		log.info("Buscando hotel por ID: "  + id);
		return pp.darHotelPorId(id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar los  Tipos de Habitacion
	 ********************************************************/
	public TiposHabitacion adicionarTipoHabitacion (long idTipoHabitacion, String descripcion, int capacidad, int precioNoche, long idHotel)
	{
		log.info ("Adicionando Habitacion " + descripcion);
		TiposHabitacion th = pp.adicionarTipoHabitacion(idTipoHabitacion, descripcion,  capacidad,  precioNoche, idHotel);
        log.info ("Adicionando tipoHabitacion: " + th);
        return th;
	}
	public List<TiposHabitacion> darTiposHabitaciones()
	{
		log.info("Conusltando Tipos Habitaciones");
		List <TiposHabitacion> th = pp.darTiposHabitacion();
		log.info("Consultando Tipos Habitaciones: " + th.size() + " tipos habitaciones");
		return th;
	}
	public TiposHabitacion darTipoHabitacionPorID(long id)
	{
		log.info("Buscando tipo Habitacion por ID: "  + id);
		return pp.darTipoHabitacionPorId(id);
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar Habitacion
	 ********************************************************/
	public Habitaciones adicionarHabitacion (long numHabitacion, long idTipoHabitaciones)
	{
		log.info ("Adicionando Habitacion " + numHabitacion);
		Habitaciones th = pp.adicionarHabitacion(numHabitacion, idTipoHabitaciones);
        log.info ("Adicionando Habitacion: " + th);
        return th;
	}
	public List<Habitaciones> darHabitaciones()
	{
		log.info("Conusltando  Habitaciones");
		List <Habitaciones> h = pp.darHabitaciones();
		log.info("Consultando Habitaciones: " + h.size() + " habitaciones");
		return h;
	}
	public Habitaciones darHabitacionPorID(long id)
	{
		log.info("Buscando Habitacion por ID: "  + id);
		return pp.darHabitacionPorId(id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar Dotacion
	 ********************************************************/
	public Dotaciones adicionarDotacion (long idDotacion, String nombre, double precio, long idTipoHabitacion)
	{
		log.info ("Adicionando Dotacion " + nombre);
		Dotaciones d = pp.adicionarDotacion( idDotacion,  nombre,  precio,idTipoHabitacion);
        log.info ("Adicionando Dotacion: " + d);
        return d;
	}
	public List<Dotaciones> darDotaciones()
	{
		log.info("Conusltando Dotaciones ");
		List <Dotaciones> d = pp.darDotaciones();
		log.info("Consultando Dotaciones" + d.size() + " dotaciones");
		return d;
	}
	public Dotaciones darDotacionPorID(long id)
	{
		log.info("Buscando Dotacion por ID: "  + id);
		return pp.darDotacionPorId(id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar Servicio
	 ********************************************************/
	public Servicios adicionarServicio(long idServicio, String nombreServicio)
	{
		log.info ("Adicionando Servicio " + nombreServicio);
		Servicios s = pp.adicionarServicio( idServicio, nombreServicio);
        log.info ("Adicionando Servicio: " + s);
        return s;
	}
	public List<Servicios> darServicios()
	{
		log.info("Conusltando Servicios ");
		List <Servicios> s = pp.darServicios();
		log.info("Consultando Servicios" + s.size() + " servicios");
		return s;
	}
	public Servicios darServicioPorID(long id)
	{
		log.info("Buscando Servicio por ID: "  + id);
		return pp.darServicio(id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar VentaProducto
	 ********************************************************/
	public VentaProductos adicionarVentaProducto (long idServicio, int capacidad, String estilo, String tipo)
	{
		log.info ("Adicionando VentaProducto de tipo" + tipo);
		VentaProductos d = pp.adicionarVentaProducto(idServicio, capacidad, estilo, tipo);
        log.info ("Adicionando VEnta Producto: " + d);
        return d;
	}
	public List<VentaProductos> darVentasProductos()
	{
		log.info("Conusltando VentasProductos ");
		return pp.darVentasProducto();

	}
	public VentaProductos darVentaProductoPorID(long id)
	{
		log.info("Buscando VentaProducto por ID: "  + id);
		return pp.darVentaProductoPorId(id);

	}
	/* ****************************************************************
	 * 			Métodos para manejar Salon
	 ********************************************************/
	public Salones adicionarSalon (long idServicio, int capacidad, double costoPorHora, String tipo)
	{
		log.info ("Adicionando Salon ");
		Salones s = pp.adicionarSalon( idServicio,  capacidad,  costoPorHora, tipo);
        log.info ("Adicionando Dotacion: " + s);
        return s;
	}
	public List<Salones> darSalones()
	{
		log.info("Conusltando Salones ");
		List <Salones> s = pp.darSalones();
		log.info("Consultando Salones" + s.size() + " salones");
		return s;
	}
	public Salones darSalonPorID(long id)
	{
		log.info("Buscando Salon por ID: "  + id);
		return pp.darSalonPorId(id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar ServicioAdicional
	 ********************************************************/
	public ServiciosAdicionales adicionarServicioAdicional ( long idServicioAdicional, int capacidad)
	{
		log.info ("Adicionndo ServicioAdicional " );
		ServiciosAdicionales sa = pp.adicionarServicioAdicional(idServicioAdicional, capacidad);
        log.info ("Adicionando ServicioAdicional: " + sa);
        return sa;
	}
	public List<ServiciosAdicionales> darServiciosAdicionales()
	{
		log.info("Conusltando Servicios Adicionales ");
		List <ServiciosAdicionales> sa = pp.darServiciosAdicionales();
		log.info("Consultando Servicios Adicionales" + sa.size() + " Servicios Adicionales");
		return sa;
	}
	public ServiciosAdicionales darServicioAdicionalPorID(long id)
	{
		log.info("Buscando ServicioAdicional por ID: "  + id);
		return pp.darServicioAdicionalPorId(id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar Producto
	 ********************************************************/
	public Productos adicionarProducto(long idProducto, double precio, String nombre, int cantidad, int duracion, String categoria, long idVentaProducto)
	{
		log.info ("Adicionando Producto " + nombre);
		Productos p = pp.adicionarProducto(idProducto, precio, nombre, cantidad, duracion, categoria, idVentaProducto);
        log.info ("Adicionando Producto: " + p);
        return p;
	}
	public List<Productos> darProducto()
	{
		log.info("Conusltando Productoes ");
		List <Productos>  p = pp.darProductos();
		log.info("ConsultandoProductos" + p.size() + " Productos");
		return p;
	}
	public Productos darProductoPorID(long id)
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
	public CaracteristicasAdicionales adicionarCaracteristicaAdicional(long idCaracteristicaAdicional, String nombre, double valor, long idServicioAdicional)
	{
		log.info ("Adicionando CaracteristicaAdicional " + nombre);
		CaracteristicasAdicionales ca = pp.adicionarCaracteristicaAdicional(idCaracteristicaAdicional, nombre, valor, idServicioAdicional);
        log.info ("Adicionando CaracteristicaAdicional: " + ca);
        return ca;
	}
	public List<CaracteristicasAdicionales> darCaracteristicasAdicionales()
	{
		log.info("Conusltando Caracteristicas Adicionales ");
		List <CaracteristicasAdicionales>  ca = pp.darCaracrteristicasAdicionales();
		log.info("Consultando  CaracteristicaAdicionales" + ca.size() + " Caracteristicas Adicionales");
		return ca;
	}
	public CaracteristicasAdicionales darCaracteristicaAdicionalPorID(long id)
	{
		log.info("Buscando CaracteristicaAdicional por ID: "  + id);
		return pp.darCaracteristicaAdicionalPorId(id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar Horario
	 ********************************************************/
	public Horarios adicionarHorario(long idHorario,String duracion, long idServicio, Timestamp fechaInicio, String dia, String  horaInicio,String horaFin, Timestamp fechaFin)
	{
		log.info ("Adicionando Horario " );
		Horarios h = pp.adicionarHorario(idHorario, duracion, idServicio, fechaInicio, dia, horaInicio, horaFin, fechaFin);
        log.info ("Adicionando Horario: " + h);
        return h;
	}
	public List<Horarios> darHorarios()
	{
		log.info("Conusltando Horarios");
		List <Horarios>  h = pp.darHorarios();
		log.info("Consultando  Horarios" + h.size() + " Horarios");
		return h;
	}
	public Horarios darHorarioPorID(long id)
	{
		log.info("Buscando Horario por ID: "  + id);
		return pp.darHorarioPorId(id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar Plan
	 ********************************************************/
	public Planes adicionarPlan(long idPlan, String tipo, double costo, double descuentoAlojamiento, Timestamp fecha)
	{
		log.info ("Adicionando Plan " + tipo);
		Planes p = pp.adicionarPlan(idPlan, tipo, costo, descuentoAlojamiento, fecha);
        log.info ("Adicionando Plan: " + p);
        return p;
	}
	
	public Planes adicionarPlanConvencion(long idPlan, String tipo, double costo, double descuentoAlojamiento, Timestamp fecha)
	{
		log.info ("Adicionando Plan " + tipo);
		Planes p = pp.adicionarPlanConvencion(idPlan, tipo, costo, descuentoAlojamiento, fecha);
        log.info ("Adicionando Plan: " + p);
        return p;
	}
	public List<Planes> darPlanes()
	{
		log.info("Consultando Plan");
		List <Planes>  p = pp.darPlanes();
		log.info("Consultando  Planes" + p.size() + " Planes");
		return p;
	}
	public Planes darPlanPorID(long id)
	{
		log.info("Buscando Plan por ID: "  + id);
		return pp.darPlan(id);		
	}
	/* ****************************************************************
	 * 			Métodos para manejar Descuento
	 ********************************************************/
	public Descuentos adicionarDescuento(long idDescuento,long idPlan,long idServicio,long idProducto, long valor, int limiteVeces)
	{
		log.info ("Adicionando Descuento ");
		Descuentos d = pp.adicionarDescuento(idDescuento, idPlan, idServicio, idProducto, valor, limiteVeces);
        log.info ("Adicionando Descuento: " + d);
        return d;
	}
	
	public Descuentos adicionarDescuentoConvencion(long idDescuento,long idPlan,long idServicio,long idProducto, long valor, int limiteVeces)
	{
		log.info ("Adicionando Descuento ");
		Descuentos d = pp.adicionarDescuentoConvencion(idDescuento, idPlan, idServicio, idProducto, valor, limiteVeces);
        log.info ("Adicionando Descuento: " + d);
        return d;
	}
	public List<Descuentos> darDescuentos()
	{
		log.info("Consultando Descuentos");
		List <Descuentos>  d = pp.darDescuentos();
		log.info("Consultando  Descuentos" + d.size() + " Descuentos");
		return d;
	}
	public Descuentos darDescuentoPorID(long id)
	{
		log.info("Buscando Descuento por ID: "  + id);
		return pp.darDescuentoPorId(id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar Estadia
	 ********************************************************/
	public Estadias adicionarEstadia(long idEstadia, Timestamp fechaLlegada,Timestamp  fechaSalida,int numPersonas,long  idPlan, long idHabitacion, int checkIn, int pago, long numDoc, long idConvencion)
	{
		log.info ("Adicionando Estadia ");
		Estadias e = pp.adicionarEstadia(idEstadia, fechaLlegada, fechaSalida, numPersonas, idPlan, idHabitacion, checkIn, pago, numDoc, idConvencion);
        log.info ("Adicionando Estadia: " + e);
        return e;
	}
	
	public Estadias adicionarEstadiaConvencion(long idEstadia, Timestamp fechaLlegada,Timestamp  fechaSalida,int numPersonas,long  idPlan, long idHabitacion, int checkIn, int pago, Long numDoc, long idConvencion)
	{
		log.info ("Adicionando Estadia ");
		Estadias e = pp.adicionarEstadiaConvencion(idEstadia, fechaLlegada, fechaSalida, numPersonas, idPlan, idHabitacion, checkIn, pago, numDoc, idConvencion);
        log.info ("Adicionando Estadia: " + e);
        return e;
	}
	public List<Estadias> darEstadias()
	{
		log.info("Consultando Estadias");
		List <Estadias>  e = pp.darEstadias();
		log.info("Consultando  Estadias" + e.size() + " Estadias");
		return e;
	}
	
	public Estadias darEstadiaPorId(long id){
		Estadias e = pp.darEstadiaPorId(id);
		return e;
	}
	
	public long cambiarEstadiaAPagada(long idEstadia)
	{
		return pp.cambiarEstadiaAPagada(idEstadia);
	}
	
	public long checkInCliente(long idEstadia)
	{
		return pp.checkInCliente(idEstadia);
	}
	// UPDATE
	/* ****************************************************************
	 * 			Métodos para manejar Reserva
	 ********************************************************/
	public Reservas adicionarReserva(long numReserva, long idEstadia, long idServicio, long idHorario, long idConsumo, long idConvencion, long capacidad)
	{
		log.info ("Adicionando Reserva ");
		Reservas r = pp.adicionarReserva(numReserva, idEstadia, idServicio, idHorario, idConsumo, idConvencion, capacidad);
        log.info ("Adicionando Reserva: " + r);
        return r;
	}
	

	
	
	public List<Reservas> darReservas()
	{
		log.info("Consultando Reservas");
		List <Reservas>  r = pp.darReservas();
		log.info("Consultando  Reservas" + r.size() + " Reservas");
		return r;
	}
	public Reservas darReservaPorID(long id)
	{
		log.info("Buscando Reserva por ID: "  + id);
		return pp.darReservaPorId(id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar Factura
	 ********************************************************/
	public VOFacturas adicionarFactura(long numFactura, Timestamp fecha, int pagada, long precio, long idDotacion, long idServicio, long idEstadia, long numDocEmpleado, long idConsumo, long idConvencion, long idDotacionSalon)
	{
		log.info ("Adicionando Factura ");
		VOFacturas f = pp.adicionarFactura(numFactura, fecha, pagada, precio, idDotacion, idServicio, idEstadia, numDocEmpleado, idConsumo, idConvencion, idDotacionSalon);
        log.info ("Adicionando Factura: " + f);
        return f;
	}
	public List<Facturas> darFacturas()
	{
		log.info("Consultando Facturas");
		List <Facturas>  f = pp.darFacturas();
		log.info("Consultando  Facturas" + f.size() + " Facturas");
		return f;
	}
	public Facturas darFacturaPorID(long id)
	{
		log.info("Buscando Factura por ID: "  + id);
		return pp.darFacturaPorId(id);
	}
	public long cambiarFacturaAPagada(long idFactura)
	{
		return pp.cambiarFacturasAPagada(idFactura);
	}
	
	public Convenciones adicionarConvencion(long idConvencion, String nombre, int capacidad, Timestamp fechaInicio, Timestamp fechaFin, long idOrganizador, long idPlan, int pago){
		Convenciones c = pp.adicionarConvencion(idConvencion, nombre, capacidad, fechaInicio, fechaFin, idOrganizador, idPlan, pago);
		return c;
	}
	
	
	
	public List<long []> dineroServiciosPorHabitacion ()
	{

        log.info ("Listando dinero servicios por habitacion");
        List tuplas = pp.dineroServiciosPorHabitacion();
        log.info ("Listando dinero servicios por habitacion: Listo!");
        return tuplas;
	}
	
	public List<Object []> topPopulares(){
		log.info ("Listando top 20");
        List tuplas = pp.topPopulares();
        log.info ("Listando top 20: Listo!");
        return tuplas;
	}
	
	public List<long []> indiceOcupacion(){
		log.info ("Mostrando indice ocupacion");
        List tuplas = pp.indiceOcupacion();
        log.info ("Mostrado indice ocupacion");
        return tuplas;
	}
	
	public List<long []> consumoPorUsuarioPorFecha(long idCliente, String fechaI, String fechaF){
		log.info ("Mostrando consumo por usuario por fecha");
        List tuplas = pp.consumoPorUsuarioPorFecha(idCliente, fechaI, fechaF);
        log.info ("Mostrado consumo por usuario por fecha");
        return tuplas;
	}
	
	public List<Object []> serviciosPrecioEnRango(long abajo, long arriba){
		log.info ("Mostrando servicios en rango " + abajo + "-" + arriba);
        List tuplas = pp.serviciosPrecioEnRango(abajo, arriba);
        log.info ("Mostraddo servicios en rango " + abajo + "-" + arriba);
        return tuplas;
	}
	
	public List<Object []> serviciosFechaEnRango(String inicio, String fin){
		log.info ("Mostrando servicios en rango " + inicio + "-" + fin);
        List tuplas = pp.serviciosFechaEnRango(inicio, fin);
        log.info ("Mostraddo servicios en rango " + inicio + "-" + fin);
        return tuplas;
	}
	
	public List<Object []> serviciosPorEmpleado(long idEmpleado){
		log.info ("Mostrando servicios del empleado: " + idEmpleado + "\n");
        List tuplas = pp.serviciosPorEmpleado(idEmpleado);
        log.info ("Mostrado servicios del empleado: " + idEmpleado + "\n");
        return tuplas;
	}
	
	public List<Object []> verBuenosClientes(){
		log.info ("Mostrando buenos clientes");
        List tuplas = pp.verBuenosClientes();
        log.info ("Mostrado buenos clientes");
        return tuplas;
	}
	
	public List<Object []> verServiciosPocaDemanda(){
		log.info("Mostrando servicios poca demanda");
		List tuplas = pp.serviciosPocaDemanda();
		log.info("Mostrado servicios poca demanda");
		return tuplas;

	}
	
	public List<Object []> mayorGananciaServicioSemana(long id){
		log.info("Mostrando servicio con mayor ganancia semanal");
		List tuplas = pp.mayorGananciaServicioSemana(id);
		log.info("Mostrado servicio con mayor ganancia semanal");
		return tuplas;
	}
	
	public List<Object []> masNumeroDeVecesServicioSemana(long id){
		log.info("Mostrando servicios mas numero de veces por semana");
		List tuplas = pp.masNumeroDeVecesServicioSemana(id);
		log.info("Mostrado servicios mas numero de veces por semana");
		return tuplas;
	}
	
	public List<Object []> menorGananciaServicioSemana(long id){
		log.info("Mostrando servicios con menor ganancia semanal");
		List tuplas = pp.menorGananciaServicioSemana(id);
		log.info("Mostrado servicios con menor ganancia semanal");
		return tuplas;
	}
	
	public List<Object []> mayorGananciaHabitacionSemana(long id){
		log.info("Mostrando servicio con mayor ganancia semanal");
		List tuplas = pp.mayorGananciaHabitacionSemana(id);
		log.info("Mostrado servicio con mayor ganancia semanal");
		return tuplas;
	}
	
	public List<Object []> masNumeroDeVecesHabitacionSemana(long id){
		log.info("Mostrando servicios mas numero de veces por semana");
		List tuplas = pp.masNumeroDeVecesHabitacionSemana(id);
		log.info("Mostrado servicios mas numero de veces por semana");
		return tuplas;
	}
	
	public List<Object []> menorGananciaHabitacionSemana(long id){
		log.info("Mostrando servicios con menor ganancia semanal");
		List tuplas = pp.menorGananciaHabitacionSemana(id);
		log.info("Mostrado servicios con menor ganancia semanal");
		return tuplas;
	}
	
	public List<Object []> mayorGananciaServicioMes(long id){
		log.info("Mostrando servicio con mayor ganancia mensual");
		List tuplas = pp.mayorGananciaServicioMes(id);
		log.info("Mostrado servicio con mayor ganancia mensual");
		return tuplas;
	}
	
	public List<Object []> masNumeroDeVecesServicioMes(long id){
		log.info("Mostrando servicios mas numero de veces por mes");
		List tuplas = pp.masNumeroDeVecesServicioMes(id);
		log.info("Mostrado servicios mas numero de veces por mes");
		return tuplas;
	}
	
	public List<Object []> menorGananciaServicioMes(long id){
		log.info("Mostrando servicios con menor ganancia mes");
		List tuplas = pp.menorGananciaServicioMes(id);
		log.info("Mostrado servicios con menor ganancia mes");
		return tuplas;
	}
	
	public List<Object []> mayorGananciaHabitacionMes(long id){
		log.info("Mostrando servicio con mayor ganancia mensual");
		List tuplas = pp.mayorGananciaHabitacionMes(id);
		log.info("Mostrado servicio con mayor ganancia mensual");
		return tuplas;
	}
	
	public List<Object []> masNumeroDeVecesHabitacionMes(long id){
		log.info("Mostrando servicios mas numero de veces por mes");
		List tuplas = pp.masNumeroDeVecesHabitacionMes(id);
		log.info("Mostrado servicios mas numero de veces por semana");
		return tuplas;
	}
	
	public List<Object []> menorGananciaHabitacionMes(long id){
		log.info("Mostrando servicios con menor ganancia semanal");
		List tuplas = pp.menorGananciaHabitacionMes(id);
		log.info("Mostrado servicios con menor ganancia semanal");
		return tuplas;
	}
	
	public List<Object []> darFacturasPorIdEstadia(long id){
		log.info ("Generando las facturas");        
        List tuplas = pp.darFacturaPorIdEstadia(id);
        log.info ("Generando las facturas de la estadia" + id + " existentes");
        return tuplas;
		
	}
	
	public List<Object []> darHabitacionesLibresPorTipo(Timestamp fechaInicio, Timestamp fechaFin, long idTipo){
        List tuplas = pp.darHabitacionesLibresPorTipo(fechaInicio, fechaFin, idTipo);
        return tuplas;
		
	}
	
	public BigDecimal selectMax(){
		return pp.selectMax();
	}
	
	public void rollback(){
		pp.rollback();
	}
	
	public void commit(){
		pp.commit();
	}
	
	public void rf12(long idPlan, String tipo, double costo, double descuentoAlojamiento, Timestamp fecha, long idDescuento, long idServicio, Long idProducto, long valor, int limiteVeces, 
			long idConvencion, String nombre, int capacidad, Timestamp fechaInicio, Timestamp fechaFin, long idOrganizador, String [] rpta) throws Exception
	{
		pp.rf12(idPlan, tipo, costo, descuentoAlojamiento, fecha, idDescuento, idServicio, idProducto, valor, limiteVeces, idConvencion, nombre, capacidad, fechaInicio, fechaFin, idOrganizador, rpta);
	}

	public void rf13(Long idConvencion, int desReservas) throws Exception {
		pp.rf13(idConvencion, desReservas);
	}

	public void rf14(Long idConvencion) throws Exception{
		pp.rf14(idConvencion);
		
	}

	public void rf15Servicios(Long idMantenimiento, String causa, String[] arregloIds, Timestamp fechaInicio, Timestamp fechaFin) {
		pp.rf15Servicios(idMantenimiento, causa, arregloIds, fechaInicio, fechaFin);
		
	}
	
	
	
	

}