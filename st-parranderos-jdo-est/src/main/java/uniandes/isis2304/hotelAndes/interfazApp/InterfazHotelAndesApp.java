package uniandes.isis2304.hotelAndes.interfazApp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.hotelAndes.negocio.CaracteristicasAdicionales;
import uniandes.isis2304.hotelAndes.negocio.Convenciones;
import uniandes.isis2304.hotelAndes.negocio.Descuentos;
import uniandes.isis2304.hotelAndes.negocio.DotacionSalon;
import uniandes.isis2304.hotelAndes.negocio.Estadias;
import uniandes.isis2304.hotelAndes.negocio.Facturas;
import uniandes.isis2304.hotelAndes.negocio.Habitaciones;
import uniandes.isis2304.hotelAndes.negocio.Horarios;
import uniandes.isis2304.hotelAndes.negocio.HotelAndes;
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
 * Clase principal de la interfaz
 */
@SuppressWarnings("serial")

public class InterfazHotelAndesApp extends JFrame implements ActionListener
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazHotelAndesApp.class.getName());

	/**
	 * Ruta al archivo de configuración de la interfaz PARA LOS CLIENTES
	 */
	private static final String CONFIG_INTERFAZ_CLIENTES = "./src/main/resources/config/interfaceConfigAppCliente.json"; 
	private static final String CONFIG_INTERFAZ_DEMO = "./src/main/resources/config/interfaceDemo.json"; 


	/**
	 * Ruta al archivo de configuración de la interfaz PARA LOS USUARIOS DE PERSONAL
	 */
	private static final String CONFIG_INTERFAZ_EMPLEADO = "./src/main/resources/config/interfaceConfigAppEmpleado.json"; 

	private static final String CONFIG_INTERFAZ_RECEPCIONISTA = "./src/main/resources/config/interfaceConfigRecepcionista.json";

	private static final String CONFIG_INTERFAZ_GERENTE = "./src/main/resources/config/interfaceConfigAppGerente.json"; 



	/**
	 * Ruta al archivo de configuración de la interfaz PARA EL ADMINISTRADOR DE DATOS
	 */
	private static final String CONFIG_INTERFAZ_ADMIN_DATOS = "./src/main/resources/config/interfaceConfigAppAndes.json"; 

	private static final String CONFIG_INTERFAZ_ORGANIZADOR_EVENTOS = "./src/main/resources/config/interfaceConfigAppOrganizadorEventos.json"; 


	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD.json"; 


	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
	 */
	private JsonObject tableConfig;

	/**
	 * Asociación a la clase principal del negocio.
	 */
	private HotelAndes hotelAndes;

	/* ****************************************************************
	 * 			Atributos de interfaz
	 *****************************************************************/
	/**
	 * Objeto JSON con la configuración de interfaz de la app.
	 */
	private JsonObject guiConfig;

	/**
	 * Panel de despliegue de interacción para los requerimientos
	 */
	private PanelDatos panelDatos;

	/**
	 * Menú de la aplicación
	 */
	private JMenuBar menuBar;

	/**
	 * En caso de ser un cliente, se representa con su identificador
	 */
	private long identificacionUsuario;

	/**
	 * Representa el tipo de persona que esta usando la app en su ejecucion 6= Organizador de Eventos, 5 = cliente, 4 = empleado, 3 = recepccionista, 2 = gerente, 1 = Administrador de datos}
	 */
	private int estadoAplicacion;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Construye la ventana principal de la aplicación. <br>
	 * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
	 */
	public InterfazHotelAndesApp( )
	{
		tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
		hotelAndes = new HotelAndes (tableConfig);

		Object[] options = {"Cliente", "Empleado", "Recepcionista", "Gerente", "Administrador De Datos", "Organizador Eventos", "Demo"};
		int n = JOptionPane.showOptionDialog(this,"¿Cómo desea ingresar?","HotelAndes",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null,options,options[2]);
		if(n == 0)
		{
			String ced = JOptionPane.showInputDialog(this, "Digite su cedula", "HotelAndes", JOptionPane.PLAIN_MESSAGE);
			long cedula = Long.parseLong(ced);

			if(ced.isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Por favor digite una cedula valida", "HotelAndes", JOptionPane.PLAIN_MESSAGE);
				System.exit(0);
			}
			if(hotelAndes.darUsuario(cedula) == null  || hotelAndes.darUsuario(cedula).getIdTipoUsuario() != 5)
			{
				JOptionPane.showMessageDialog(this, "Lo sentimos, su cedula no se encuentra en nuestros registros", "HotelAndes", JOptionPane.PLAIN_MESSAGE);
				System.exit(0);
			}
			else
			{
				estadoAplicacion = 5;
				identificacionUsuario = cedula;
				guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_CLIENTES);
			}
		}
		else if(n == 1)
		{
			String ced = JOptionPane.showInputDialog(this, "Digite su cedula", "HotelAndes", JOptionPane.PLAIN_MESSAGE);
			long cedula = Long.parseLong(ced);
			if(ced.isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Por favor ingrese una cedula valido", "HotelAndes", JOptionPane.PLAIN_MESSAGE);
				System.exit(0);
			}
			if(hotelAndes.darUsuario(cedula) == null|| hotelAndes.darUsuario(cedula).getIdTipoUsuario() != 4)
			{
				JOptionPane.showMessageDialog(this, "Lo sentimos, su cedula no se encuentra en nuestros registros", "HotelAndes", JOptionPane.PLAIN_MESSAGE);
				System.exit(0);
			}
			else
			{
				estadoAplicacion = 4;
				identificacionUsuario = cedula;
				guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_EMPLEADO);
			}
		}
		else if(n == 2)
		{
			String ced = JOptionPane.showInputDialog(this, "Digite su cedula", "HotelAndes", JOptionPane.PLAIN_MESSAGE);
			long cedula = Long.parseLong(ced);


			if(hotelAndes.darUsuario(cedula) == null || hotelAndes.darUsuario(cedula).getIdTipoUsuario() != 3)
			{
				JOptionPane.showMessageDialog(this, "Lo sentimos, su cedula no se encuentra en nuestros registros", "HotelAndes", JOptionPane.PLAIN_MESSAGE);
				System.exit(0);
			}
			else
			{
				estadoAplicacion = 3;
				identificacionUsuario = cedula;
				guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_RECEPCIONISTA);
			}
		}
		else if(n == 3)
		{

			String ced = JOptionPane.showInputDialog(this, "Digite su cedula", "HotelAndes", JOptionPane.PLAIN_MESSAGE);
			long cedula = Long.parseLong(ced);


			if(hotelAndes.darUsuario(cedula) == null || hotelAndes.darUsuario(cedula).getIdTipoUsuario() != 2)
			{
				JOptionPane.showMessageDialog(this, "Lo sentimos, su cedula no se encuentra en nuestros registros", "HotelAndes", JOptionPane.PLAIN_MESSAGE);
				System.exit(0);
			}
			else
			{
				estadoAplicacion = 2;
				identificacionUsuario = cedula;
				//TODO cambiar a interfaz GERENTE
				guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_GERENTE);
			}
		}
		else if(n == 4)
		{

			String ced = JOptionPane.showInputDialog(this, "Digite su cedula", "HotelAndes", JOptionPane.PLAIN_MESSAGE);
			Long cedula = Long.parseLong(ced);


			if(hotelAndes.darUsuario(cedula) == null || hotelAndes.darUsuario(cedula).getIdTipoUsuario() != 1)
			{
				JOptionPane.showMessageDialog(this, "Esta opcion es solo para acceso administrativo", "HotelAndes", JOptionPane.WARNING_MESSAGE);
				System.exit(0);
			}
			else
			{
				estadoAplicacion = 1;
				identificacionUsuario = cedula;
				guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_ADMIN_DATOS);
			}
		}
		else if(n == 5)
		{

			String ced = JOptionPane.showInputDialog(this, "Digite su cedula", "HotelAndes", JOptionPane.PLAIN_MESSAGE);
			long cedula = Long.parseLong(ced);


			if(hotelAndes.darUsuario(cedula) == null || hotelAndes.darUsuario(cedula).getIdTipoUsuario() != 6)
			{
				JOptionPane.showMessageDialog(this, "Lo sentimos, su cedula no se encuentra en nuestros registros", "HotelAndes", JOptionPane.PLAIN_MESSAGE);
				System.exit(0);
			}
			else
			{
				estadoAplicacion = 6;
				identificacionUsuario = cedula;
				//TODO cambiar a interfaz Organizador Eventos
				guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_ORGANIZADOR_EVENTOS);
			}
		}
		else{
			estadoAplicacion = 7;
			//TODO cambiar a interfaz Organizador Eventos
			guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_DEMO);
		}

		// Configura la apariencia del frame que contiene la interfaz gráfica
		configurarFrame ( );
		if (guiConfig != null) 	   
		{
			crearMenu( guiConfig.getAsJsonArray("menuBar") );
		}

		String path = guiConfig.get("bannerPath").getAsString();
		panelDatos = new PanelDatos ( );

		setLayout (new BorderLayout());
		add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
		add( panelDatos, BorderLayout.CENTER );        
	}

	/* ****************************************************************
	 * 			Métodos de configuración de la interfaz
	 *****************************************************************/
	/**
	 * Lee datos de configuración para la aplicació, a partir de un archivo JSON o con valores por defecto si hay errores.
	 * @param tipo - El tipo de configuración deseada
	 * @param archConfig - Archivo Json que contiene la configuración
	 * @return Un objeto JSON con la configuración del tipo especificado
	 * 			NULL si hay un error en el archivo.
	 */
	private JsonObject openConfig (String tipo, String archConfig)
	{
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
			//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "HotelAndes App", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}

	/**
	 * Método para configurar el frame principal de la aplicación
	 */
	private void configurarFrame(  )
	{
		int alto = 0;
		int ancho = 0;
		String titulo = "";	

		if ( guiConfig == null )
		{
			log.info ( "Se aplica configuración por defecto" );			
			titulo = "Hotel Andes APP Default";
			alto = 300;
			ancho = 500;
		}
		else
		{
			log.info ( "Se aplica configuración indicada en el archivo de configuración" );
			titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
		}

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLocation (50,50);
		setResizable( true );
		setBackground( Color.WHITE );

		setTitle( titulo );
		setSize ( ancho, alto);        
	}

	/**
	 * Método para crear el menú de la aplicación con base em el objeto JSON leído
	 * Genera una barra de menú y los menús con sus respectivas opciones
	 * @param jsonMenu - Arreglo Json con los menùs deseados
	 */
	private void crearMenu(  JsonArray jsonMenu )
	{    	
		// Creación de la barra de menús
		menuBar = new JMenuBar();       
		for (JsonElement men : jsonMenu)
		{
			// Creación de cada uno de los menús
			JsonObject jom = men.getAsJsonObject(); 

			String menuTitle = jom.get("menuTitle").getAsString();        	
			JsonArray opciones = jom.getAsJsonArray("options");

			JMenu menu = new JMenu( menuTitle);

			for (JsonElement op : opciones)
			{       	
				// Creación de cada una de las opciones del menú
				JsonObject jo = op.getAsJsonObject(); 
				String lb =   jo.get("label").getAsString();
				String event = jo.get("event").getAsString();

				JMenuItem mItem = new JMenuItem( lb );
				mItem.addActionListener( this );
				mItem.setActionCommand(event);

				menu.add(mItem);
			}       
			menuBar.add( menu );
		}        
		setJMenuBar ( menuBar );	
	}




	/* ****************************************************************
	 * 			Métodos de la Interacción
	 *****************************************************************/

	/**
	 * Limpia todas las tuplas de todas las tablas de la base de datos de hotelandes
	 * Muestra en el panel de datos el número de tuplas eliminadas de cada tabla
	 */
	//	public void limpiarBD ()
	//	{
	//		try 
	//		{
	//			// Ejecución de la demo y recolección de los resultados
	//			//long eliminados [] = hotelAndes.limpiarhotelandes();
	//
	//			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
	//			String resultado = "\n\n************ Limpiando la base de datos ************ \n";
	//			resultado += "Se limpiaron " + eliminados.length + " tablas";
	//			panelDatos.actualizarInterfaz(resultado);
	//		} 
	//		catch (Exception e) 
	//		{
	//			e.printStackTrace();
	//		}
	//	}

	public void registrarTipoUsuario(){

		String ids = JOptionPane.showInputDialog(this, "Ingrese el id del tipo de usuario");
		long id = Long.parseLong(ids);
		String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del tipo de usuario");
		TipoUsuario tipoUsuario = hotelAndes.adicionarTipoUsuario(id, nombre);
		if(tipoUsuario == null)
		{
			JOptionPane.showMessageDialog(this, "No fue posible agregar al tipo de usuario","HotelAndes", JOptionPane.PLAIN_MESSAGE);
			return;
		}
		String rta = "Se agrego el usuario: \n";
		rta += tipoUsuario.toString();
		panelDatos.actualizarInterfaz(rta);
	}

	public void registrarUsuario(){
		String ids = JOptionPane.showInputDialog(this, "Ingrese el documento de usuario");
		long id = Long.parseLong(ids);
		String tipoId = JOptionPane.showInputDialog(this, "Ingrese el tipo de documento de usuario");
		String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del usuario");
		String correo = JOptionPane.showInputDialog(this, "Ingrese el correo electronico del usuario");
		String tipo = JOptionPane.showInputDialog(this, "Ingrese el id del tipo del usuario");
		long idTipo = Long.parseLong(tipo);
		Usuarios usuario = hotelAndes.adicionarUsuario(id, tipoId, nombre, correo, idTipo);
		if(usuario == null)
		{
			JOptionPane.showMessageDialog(this, "No fue posible agregar al usuario","hotelandes", JOptionPane.PLAIN_MESSAGE);
			return;
		}
		String rta = "Se agrego el usuario: \n";
		rta += usuario.toString();
		panelDatos.actualizarInterfaz(rta);
	}

	public void registrarTipoHabitacion(){
		String ids = JOptionPane.showInputDialog(this, "Ingrese el id del tipo de habitacion");
		long id = Long.parseLong(ids);
		String descripcion = JOptionPane.showInputDialog(this, "Ingrese una descripcion de la habitacion");
		String cap = JOptionPane.showInputDialog(this, "Ingrese una capacidad para la habitacion");
		int capacidad = Integer.parseInt(cap);
		String precio = JOptionPane.showInputDialog(this, "Ingrese el precio por persona por noche");
		int precioPorPersonaPorNoche = Integer.parseInt(precio);
		String idH = JOptionPane.showInputDialog(this, "Ingrese el id del hotel");
		long idHotel = Long.parseLong(idH);

		TiposHabitacion tipo = hotelAndes.adicionarTipoHabitacion(id, descripcion, capacidad, precioPorPersonaPorNoche, idHotel);	
		if(tipo == null){
			JOptionPane.showMessageDialog(this, "No fue posible agregar al usuario","hotelandes", JOptionPane.PLAIN_MESSAGE);
			return;
		}
		String rta = "Se agrego el usuario: \n";
		rta += tipo.toString();
		panelDatos.actualizarInterfaz(rta);
	}

	public void registrarHabitacion(){
		String id = JOptionPane.showInputDialog(this, "Ingrese el numero de la habitacion");
		long numHabitacion = Long.parseLong(id);
		String idTipo = JOptionPane.showInputDialog(this, "Ingrese el id del tipo de habitacion");
		long idTipoHabitacion = Long.parseLong(idTipo);
		Habitaciones habitacion = hotelAndes.adicionarHabitacion(numHabitacion, idTipoHabitacion);
		if(habitacion == null){
			JOptionPane.showMessageDialog(this, "No fue posible agregar al usuario","hotelandes", JOptionPane.PLAIN_MESSAGE);
			return;
		}
		String rta = "Se agrego el usuario: \n";
		rta += habitacion.toString();
		panelDatos.actualizarInterfaz(rta);
	}

	public void registrarServicios(){
		String id = JOptionPane.showInputDialog(this, "Ingrese el id del servicio");
		long idServicio = Long.parseLong(id);
		String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre del servicio");
		Servicios serv = hotelAndes.adicionarServicio(idServicio, nombre);
		if(serv == null){
			JOptionPane.showMessageDialog(this, "No fue posible agregar al usuario","hotelandes", JOptionPane.PLAIN_MESSAGE);
			return;
		}
		String rta = "Se agrego el servicio: \n";
		rta += serv.toString();
		panelDatos.actualizarInterfaz(rta);

		String rpta = JOptionPane.showInputDialog(this, "¿Que tipo de servicio deseas agregar?");
		if(rpta.equalsIgnoreCase("Servicio Adicional")){
			String capa = JOptionPane.showInputDialog(this, "Ingresa la capacidad del servicio");
			int capacidad = Integer.parseInt(capa);
			ServiciosAdicionales servicioAdicional = hotelAndes.adicionarServicioAdicional(idServicio, capacidad);
			if(servicioAdicional == null){
				JOptionPane.showMessageDialog(this, "No fue posible agregar al usuario","hotelandes", JOptionPane.PLAIN_MESSAGE);
				return;
			}
			String idCaracteristica = JOptionPane.showInputDialog(this, "Ingrese el id de la caracteristica adicional");
			long idCaracteristicaAdicional = Long.parseLong(idCaracteristica);
			String nombreCar = JOptionPane.showInputDialog(this, "Ingrese el nombre de la carcteristica adicional");
			String val = JOptionPane.showInputDialog(this, "Ingrese el valor de la caracteristica");
			double valor = Double.parseDouble(val);
			CaracteristicasAdicionales car = hotelAndes.adicionarCaracteristicaAdicional(idCaracteristicaAdicional, nombreCar, valor, idServicio);
			if(car == null){
				JOptionPane.showMessageDialog(this, "No fue posible agregar al servicio","hotelandes", JOptionPane.PLAIN_MESSAGE);
				return;
			}
			//TODO PONER OPCIÖN DE AGREGAR HORARIO
			/*			String resp = JOptionPane.showInputDialog(this, "Ingrese que dias esta abierto");
			String horaApertura = JOptionPane.showInputDialog(this, "Ingrese el horario de apertura");
			String horaCierre = JOptionPane.showInputDialog(this, "Ingrese la hora de cierre");
			String idHor = JOptionPane.showInputDialog(this, "Ingrese el id del horario");
			long idHorario = Long.parseLong(idHor);
			Horario hor = hotelAndes.adicionarHorario(idHorario, null, idServicio, null, resp, horaApertura, horaCierre);
			if(hor == null){
				JOptionPane.showMessageDialog(this, "No fue posible agregar al usuario","hotelandes", JOptionPane.PLAIN_MESSAGE);
				return;
			}*/

		}
		else if(rpta.equalsIgnoreCase("Venta Producto")){
			String idVP = JOptionPane.showInputDialog(this, "Ingrese el id de venta del producto");
			String cap= JOptionPane.showInputDialog(this, "Ingrese la capacidad");
			int capacidad = Integer.parseInt(cap);
			String estilo = JOptionPane.showInputDialog(this, "Ingrese el estilo");
			String tipo = JOptionPane.showInputDialog(this, "Ingresa el tipo de servicio");

			long idVentaP = Long.parseLong(idVP);
			VentaProductos ventaP = hotelAndes.adicionarVentaProducto(idVentaP, capacidad, estilo, tipo);
			if(ventaP == null){
				JOptionPane.showMessageDialog(this, "No fue posible hacer el servicio","hotelandes", JOptionPane.PLAIN_MESSAGE);
				return;
			}

			String idProd = JOptionPane.showInputDialog(this, "Ingrese el id del producto");
			long idProducto = Long.parseLong(idProd);
			String nombreProd = JOptionPane.showInputDialog(this, "Ingrese el nombre del producto");
			String precio = JOptionPane.showInputDialog(this, "Ingrese el precio");
			String cantidadProducto = JOptionPane.showInputDialog(this, "Ingrese la cantidad del producto");
			int cant = Integer.parseInt(cantidadProducto);
			String categoriaProducto = JOptionPane.showInputDialog(this, "Ingrese la categoria del producto");
			String duracion = JOptionPane.showInputDialog(this, "Ingrese la duracion. Ingrese cero si no tiene duración");
			int dur = Integer.parseInt(duracion);

			double prec = Double.parseDouble(precio);

			Productos p = hotelAndes.adicionarProducto(idProducto, prec, nombreProd, cant, dur, categoriaProducto, idVentaP); 
			if(p == null){
				JOptionPane.showMessageDialog(this, "No fue posible agregar al usuario","hotelandes", JOptionPane.PLAIN_MESSAGE);
				return;
			}
		}
		else if(rpta.equalsIgnoreCase("Salon")){
			String cSalon = JOptionPane.showInputDialog(this, "Ingrese la capacidad del salon");
			int capacidadSalon = Integer.parseInt(cSalon);
			String precio = JOptionPane.showInputDialog(this, "Ingrese el costo por hora");
			double precioSalon = Double.parseDouble(precio);
			String tipo = JOptionPane.showInputDialog(this, "Ingrese el tipo de salon (CONFERENCIA o REUNION");
			Salones salon = hotelAndes.adicionarSalon(idServicio, capacidadSalon, precioSalon, tipo);
			if(salon == null){
				JOptionPane.showMessageDialog(this, "No fue posible agregar al usuario","hotelandes", JOptionPane.PLAIN_MESSAGE);
				return;
			}

			String idDot = JOptionPane.showInputDialog(this, "Ingrese la dotacion del salon");
			long idDotacion = Long.parseLong(idDot);
			String nombreDot = JOptionPane.showInputDialog(this, "Ingrese el nombre de la dotacion del salon");
			String val = JOptionPane.showInputDialog(this, "Ingrese el valor de la dotacion");
			double valor = Double.parseDouble(val);
			DotacionSalon ds = hotelAndes.adicionarDotacionSalon(idDotacion, nombreDot, valor, idServicio);
			if(ds == null){
				JOptionPane.showMessageDialog(this, "No fue posible agregar al usuario","hotelandes", JOptionPane.PLAIN_MESSAGE);
				return;
			}

		}
		else
		{
			JOptionPane.showMessageDialog(this, "Tipo incorrecto","hotelandes", JOptionPane.WARNING_MESSAGE);

		}
	}

	public void registrarPlan(){
		String costo = JOptionPane.showInputDialog(this, "Ingrese el costo del plan");
		double costoPlan = Double.parseDouble(costo);
		String desc = JOptionPane.showInputDialog(this, "Ingrese el descuento en el alojamiento del plan");
		double descuento = Double.parseDouble(desc);

		String fecha = JOptionPane.showInputDialog("Ingrese la fecha de vencimiento del plan, si tiene. " + "\n"
				+ "Ingrese la fecha en formato: yyyy-mm-dd");
		Timestamp fechaVencimiento;
		if(fecha == null || fecha == "")
		{
			fechaVencimiento = null;
		}
		else
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try
			{
				Timestamp ts = new Timestamp(((java.util.Date)sdf.parse(fecha)).getTime());
				fechaVencimiento = ts;
			}
			catch (Exception e)
			{
				fechaVencimiento = null;
			}

		}

		String idPlan = JOptionPane.showInputDialog(this, "Ingrese el id del plan");
		long idP = Long.parseLong(idPlan);
		String tipo = JOptionPane.showInputDialog("Ingrese el tipo del plan");
		Planes plan = hotelAndes.adicionarPlan(idP, tipo, costoPlan, descuento, fechaVencimiento);
		if(plan == null){
			JOptionPane.showMessageDialog(this, "No fue posible agregar al usuario","hotelandes", JOptionPane.PLAIN_MESSAGE);
			return;
		}

		String idDescuento = JOptionPane.showInputDialog(this, "Ingrese el id del descuento");
		long idDescu = Long.parseLong(idDescuento);
		String idServ = JOptionPane.showInputDialog(this, "Ingrese el id del servicio al que pertenece el desuento");
		Long idServicio;
		if(idServ.equals(""))
		{
			idServicio = null;
		}
		else
		{
			idServicio = Long.valueOf(idServ);
		}
		String idProd = JOptionPane.showInputDialog(this, "Ingrese el id del producto. No ingrese nada si no hay");
		Long idProducto;
		if(idProd.equals(""))
		{
			idProducto = null;
		}
		else
		{
			idProducto = Long.valueOf(idProd);
		}

		String numVeces = JOptionPane.showInputDialog(this, "Ingrese el numero de veces que se puede aplicar. Ingrese -1 si no hay.");
		int cantVeces = Integer.parseInt(numVeces);
		String valorDesc = JOptionPane.showInputDialog(this, "Ingrese el porcentaje de descuento (Numero entre 1 y 100)");
		Long descuentito = Long.valueOf( valorDesc);
		Descuentos descu = hotelAndes.adicionarDescuento(idDescu, idP, idServicio, idProducto, descuentito, cantVeces);
		if(descu == null){
			JOptionPane.showMessageDialog(this, "No fue posible agregar al usuario","hotelandes", JOptionPane.PLAIN_MESSAGE);
			return;
		}

	}

	public void reservarEstadia()
	{
		String ced = JOptionPane.showInputDialog(this, "Digite su cedula");
		long cedula = Long.parseLong(ced);
		String fechaLlegada = JOptionPane.showInputDialog("Ingrese la fecha de llegada" + "\n"
				+ "Ingrese la fecha en formato: yyyy-mm-dd");
		Timestamp fechaL;
		if(fechaLlegada == null || fechaLlegada == "")
		{
			fechaL = null;
		}
		else
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try
			{
				Timestamp ts = new Timestamp(((java.util.Date)sdf.parse(fechaLlegada)).getTime());
				fechaL = ts;
			}
			catch (Exception e)
			{
				fechaL = null;
			}

		}
		String fechaSalida = JOptionPane.showInputDialog("Ingrese la fecha de salida. " + "\n"
				+ "Ingrese la fecha en formato: yyyy-mm-dd");
		Timestamp fechaS;
		if(fechaSalida == null || fechaSalida == "")
		{
			fechaS = null;
		}
		else
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try
			{
				Timestamp ts = new Timestamp(((java.util.Date)sdf.parse(fechaSalida)).getTime());
				fechaS = ts;
			}
			catch (Exception e)
			{
				fechaS = null;
			}

		}
		String idE = hotelAndes.selectMax().toString();
		long idEstadia = Long.parseLong(idE);
		idEstadia++;
		String numH = JOptionPane.showInputDialog(this, "Ingrese el numero de habitacion que desea");
		long numHabitacion = Long.parseLong(numH);
		String numPersonas = JOptionPane.showInputDialog(this, "Ingrese el numero de personas con quienes viene a hotelandes");
		int cantPersonas = Integer.parseInt(numPersonas);
		String idPla = JOptionPane.showInputDialog(this, "Ingrese el id de su plan");
		long idPlan = Long.parseLong(idPla);

		String idConvencion = JOptionPane.showInputDialog(this, "Ingrese el id de la convencion");
		Long idConv;
		if(idConvencion == null || idConvencion.equals(""))
		{
			idConv = null;
		}
		else{
			idConv = Long.parseLong(idConvencion);
		}
		try{
			hotelAndes.adicionarEstadia(idEstadia, fechaL, fechaS, cantPersonas, idPlan, numHabitacion, 0, 0, cedula, idConv);}
		catch (Exception e) {
			e.printStackTrace();
		}


	}

	public void reservarServicios() {
		String ced = JOptionPane.showInputDialog(this, "Ingrese el id de su estadia");
		String x = JOptionPane.showInputDialog(this, "Diga el id del servicio que desea reservar");
		long id = Long.parseLong(x);
		long idEstadia = Long.parseLong(ced);
		String fechaIni = JOptionPane.showInputDialog("Ingrese la fecha  inicial de la reserva. " + "\n"
				+ "Ingrese la fecha en formato: yyyy-mm-dd");
		Timestamp fechaInicio;
		if(fechaIni == null || fechaIni == "")
		{
			fechaInicio = null;
		}
		else
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try
			{
				Timestamp ts = new Timestamp(((java.util.Date)sdf.parse(fechaIni)).getTime());
				fechaInicio = ts;
			}
			catch (Exception e)
			{
				fechaInicio = null;
			}

		}
		String fechaFini = JOptionPane.showInputDialog("Ingrese la fecha final de la reserva " + "\n"
				+ "Ingrese la fecha en formato: yyyy-mm-dd");
		Timestamp fechaFin;
		if(fechaFini == null || fechaFini == "")
		{
			fechaFin = null;
		}
		else
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try
			{
				Timestamp ts = new Timestamp(((java.util.Date)sdf.parse(fechaFini)).getTime());
				fechaFin = ts;
			}
			catch (Exception e)
			{
				fechaFin = null;
			}

		}
		String duracion = JOptionPane.showInputDialog(this, "Ingrese la duracion de su servicio");

		Horarios hor = hotelAndes.adicionarHorario(idEstadia, duracion, id, fechaInicio, null, null, null, fechaFin);

		String conv = JOptionPane.showInputDialog(this, "Ingrese el id de la convencion");
		long idConvencion = Long.parseLong(conv);
		String cap = JOptionPane.showInputDialog(this, "¿Para cuantas personas es la reserva?");
		long capacidad = Long.parseLong(cap);

		Reservas res = hotelAndes.adicionarReserva(idEstadia, idEstadia, id, hor.getIdHorario(), 0, idConvencion, capacidad);
		if(res == null){
			JOptionPane.showMessageDialog(this, "No fue posible agregar al usuario","hotelandes", JOptionPane.PLAIN_MESSAGE);
			return;
		}
	}

	public void registrarLlegada(){
		String ced = JOptionPane.showInputDialog(this, "Ingrese la cedula del cliente");
		long cedula = Long.parseLong(ced);
		String num = JOptionPane.showInputDialog(this, "Ingrese el numero de reserva de la estadia");
		long numReserva = Long.parseLong(num);
		Estadias est = hotelAndes.darEstadiaPorId(numReserva);
		if(est == null){
			JOptionPane.showMessageDialog(this, "No fue posible registrar la llegada del cliente","hotelandes", JOptionPane.PLAIN_MESSAGE);
			return;
		}
		//hotelAndes.regIdCliente(numReserva, cedula);
		hotelAndes.checkInCliente(numReserva);
		JOptionPane.showMessageDialog(this, "¡Check in realizado exitosamente!");

	}

	public void registrarConsumo(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date today = new Date();
		Timestamp hoy = Timestamp.valueOf(sdf.format(today));
		String s = JOptionPane.showInputDialog(this, "Ingrese el id del consumo realizado");
		long idConsumo = Long.parseLong(s);
		String d = JOptionPane.showInputDialog(this, "Ingrese el id del servicio realizado");
		long idServ = Long.parseLong(d);
		String ced = JOptionPane.showInputDialog(this, "Ingrese la cedula del cliente");
		long idEstadia = Long.parseLong(ced);
		String ceduEmp = JOptionPane.showInputDialog(this, "Ingrese su cedula");
		long numDocEmpleado = Long.parseLong(ceduEmp);
		String precio = JOptionPane.showInputDialog(this, "Ingrese el valor del consumo");
		long valor = Long.parseLong(precio);
		String conv = JOptionPane.showInputDialog(this, "Ingrese el id de la convencion");
		long idConvencion = Long.parseLong(conv);
		String idDotacionSalon = JOptionPane.showInputDialog(this, "Ingrese el id de la dotacion del salon a facturar");
		long idDotacionS = Long.parseLong(idDotacionSalon);
		String idDotacion = JOptionPane.showInputDialog(this, "Ingrese el id de la dotacion a facturar");
		long idDot = Long.parseLong(idDotacion);
		VOFacturas fac = hotelAndes.adicionarFactura(numFactura(), hoy, 0, valor, idDot, idServ, idEstadia, numDocEmpleado, idConsumo, idConvencion, idDotacionS);
		if(fac == null){
			JOptionPane.showMessageDialog(this, "No fue posible registrar la facturae","hotelandes", JOptionPane.PLAIN_MESSAGE);
			return;
		}
	}

	public void registrarSalida(){
		String ced = JOptionPane.showInputDialog(this, "Ingrese el id de la estadia");
		long id = Long.parseLong(ced);
		List<Object []> facturas = hotelAndes.darFacturasPorIdEstadia(id);
		String a = "Las facturas del cliente son: \n";			
		a += listarFacturas(facturas);	
		JOptionPane.showMessageDialog(this, a);
		String resp = JOptionPane.showInputDialog(this, "¿Desea pagar las facturas?");
		Estadias est = hotelAndes.darEstadiaPorId(id);
		if(resp.equalsIgnoreCase("si"))
		{
			List<Long> lista = pagarFactura(facturas); 
			for(Long l: lista)
			{
				hotelAndes.cambiarFacturaAPagada(l);
			}
			hotelAndes.cambiarEstadiaAPagada(est.getIdEstadia());
			JOptionPane.showMessageDialog(this, "Check-out realizado exitosamente");
		}
		else{
			JOptionPane.showMessageDialog(this, "Lo sentimos, no se pudo realizar check-out");
		}


	}

	private String listarFacturas(List<Object []> list){
		String resp = "";
		int i = 1;
		for ( Object [] tupla : list)
		{
			Object [] datos = tupla;
			String resp1 = i++ + ". " + "[";
			resp1 += "Num factura: " + datos [0] + ", ";
			resp1 += "Fecha: " + datos [1];
			resp1 += "Pagada: " + datos [2] + ", ";
			resp1 += "Precio: " + datos [3] + ", ";
			resp1 += "Id dotacion: " + datos [4] + ", ";
			resp1 += "Id servicio: " + datos [5] + ", ";
			resp1 += "Id estadia: " + datos [6] + ", ";
			resp1 += "Num doc empleado: " + datos [7] + ", ";
			resp1 += "Id producto: " + datos [8] + ", ";
			resp1 += "Id convencion: " + datos [9] + ", ";
			resp1 += "Id dotacion salon: " + datos [10] + ", ";
			resp1 += "]";
			resp += resp1 + "\n";
		}
		panelDatos.actualizarInterfaz(resp);
		return resp;
	}

	private List<Long> pagarFactura(List<Object []> list){
		LinkedList<Long> numFactura = new LinkedList<>();
		for ( Object [] tupla : list)
		{
			Object [] datos = tupla;
			numFactura.add(((Long) datos[0]).longValue ());
		}
		return numFactura;
	}

	public long numFactura(){

		return (long) Math.random();
	}



	/**
	 * Muestra la documentación Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
		mostrarArchivo ("doc/index.html");
	}

	/**
	 * Muestra la información acerca del desarrollo de esta apicación
	 */
	public void acercaDe ()
	{
		String resultado = "Proyecto #2 Sistemas Transaccionales \n"
				+ "Daniel Serrano - 201731047 \n"
				+ "Sofia Alvarez - 201729031";

		panelDatos.actualizarInterfaz(resultado);		
	}

	public String dineroServiciosPorHabitacion(){
		String resp = "Las habitaciones y el total de dinero recolectado son:\n";
		int i = 1;
		for ( long [] tupla : hotelAndes.dineroServiciosPorHabitacion())
		{
			long [] datos = tupla;
			String resp1 = i++ + ". " + "[";
			resp1 += "idHab: " + datos [0] + ", ";
			resp1 += "total: " + datos [1];
			resp1 += "]";
			resp += resp1 + "\n";
		}
		panelDatos.actualizarInterfaz(resp);
		return resp;
	}

	public String topPopulares(){
		String resp = "Los 20 servicios más populares son:\n";
		int i = 1;
		for ( Object [] tupla : hotelAndes.topPopulares())
		{
			Object [] datos = tupla;
			String resp1 = i++ + ". " + "[";
			resp1 += "Servicio: " + datos [0] + ", ";
			resp1 += "Total recolectado: " + datos [1];
			resp1 += "]";
			resp += resp1 + "\n";
		}
		panelDatos.actualizarInterfaz(resp);
		return resp;

	}

	public String indiceOcupacion() {
		String resp = "Indice ocupacion:\n";
		int i = 1;
		for ( long [] tupla : hotelAndes.indiceOcupacion())
		{
			long [] datos = tupla;
			String resp1 = i++ + ". " + "[";
			resp1 += "Id habitacion: " + datos [0] + ", ";
			resp1 += "Porcentaje de ocupacion: " + datos [1];
			resp1 += "%]";
			resp += resp1 + "\n";
		}
		panelDatos.actualizarInterfaz(resp);
		return resp;

	}

	public void serviciosCaracteristica(){
		String solicitud = JOptionPane.showInputDialog(this, "Digite el numero de la opcion a consultar: \n"
				+ "1. Mostrar servicios con precio en rango. \n"
				+ "2. Mostrar servicios en rango de tiempo. \n"
				+ "3. Mostrar servicios registrados por cierto empleado.");
		if(solicitud.equals("1")){
			String down = JOptionPane.showInputDialog(this, "Ingrese el limite inferior del rango");
			long abajo = Long.parseLong(down);
			String up = JOptionPane.showInputDialog(this, "Ingrese el limite superior del rango");
			long arriba = Long.parseLong(up);
			String resp = "Servicios con precio en rango " + abajo + "-" + arriba + "\n";
			int i = 1;
			for ( Object [] tupla : hotelAndes.serviciosPrecioEnRango(abajo, arriba))
			{
				Object [] datos = tupla;
				String resp1 = i++ + ". " + "[";
				resp1 += "Id servicio: " + datos [0] + ", ";
				resp1 += "Nombre servicio: " + datos [1] + ", ";
				resp1 += "Precio: " + datos[2];
				resp1 += "]";
				resp += resp1 + "\n";
			}
			panelDatos.actualizarInterfaz(resp);
		}
		else if(solicitud.equals("2")){
			String inicio = JOptionPane.showInputDialog(this, "Ingrese la fecha de inicio en formato yyyy-mm-dd");
			String fin = JOptionPane.showInputDialog(this, "Ingrese la fecha de fin en formato yyyy-mm-dd");
			String resp = "Servicios con fecha en rango " + inicio + "-" + fin + "\n";
			int i = 1;
			for ( Object [] tupla : hotelAndes.serviciosFechaEnRango(inicio, fin))
			{
				Object [] datos = tupla;
				String resp1 = i++ + ". " + "[";
				resp1 += "Id servicio: " + datos [0] + ", ";
				resp1 += "Nombre servicio: " + datos [1];
				resp1 += "]";
				resp += resp1 + "\n";
			}
			panelDatos.actualizarInterfaz(resp);
		}
		else if(solicitud.equals("3")){
			String idEmp = JOptionPane.showInputDialog(this, "Ingrese el documento del empleado");
			long cedulaEmpleado = Long.parseLong(idEmp);
			String resp = "Servicios registrados por el empleado " + cedulaEmpleado + "\n";
			int i = 1;
			for ( Object [] tupla : hotelAndes.serviciosPorEmpleado(cedulaEmpleado))
			{
				Object [] datos = tupla;
				String resp1 = i++ + ". " + "[";
				resp1 += "Id servicio: " + datos [0] + ", ";
				resp1 += "Nombre servicio: " + datos [1];
				resp1 += "]";
				resp += resp1 + "\n";
			}
			panelDatos.actualizarInterfaz(resp);
		}	
	}

	public String consumoPorUsuarioPorFecha() {
		String id = JOptionPane.showInputDialog(this, "Ingrese el id del cliente");
		long idCliente = Long.parseLong(id);
		String fechaInicio = JOptionPane.showInputDialog(this, "Ingrese la fecha de inicio en formato yyyy-mm-dd");
		String fechaFin = JOptionPane.showInputDialog(this, "Ingrese la fecha de fin en formato yyyy-mm-dd");
		String resp = "Consumo del usuario " + idCliente + " entre " + fechaInicio + " y " + fechaFin + "\n";
		int i = 1;
		for ( long [] tupla : hotelAndes.consumoPorUsuarioPorFecha(idCliente, fechaInicio, fechaFin))
		{
			long [] datos = tupla;
			String resp1 = i++ + ". " + "[";
			resp1 += "Id cliente: " + datos [0] + ", ";
			resp1 += "Consumo cliente: " + datos [1];
			resp1 += "]";
			resp += resp1 + "\n";
		}
		panelDatos.actualizarInterfaz(resp);
		return resp;

	}


	public void operacionHotelAndes(){
		String unidad = JOptionPane.showInputDialog("Digite el numero de la unidad de tiempo para analizar la operacion de HotelAndes: \n"
				+ "1. Semana \n" +
				"2. Mes");
		if(unidad.equals("1")){
			String opcion = JOptionPane.showInputDialog("Digite 1 para consultar analisis de operacion de servicio, 2 para consultar habitacion de HotelAndes: \n"
					+ "1. Servicio \n" +
					"2. Habitacion");
			if(opcion.equalsIgnoreCase("1")){
				String id = JOptionPane.showInputDialog("Digite el id del servicio a analizar");
				long idServicio = Long.parseLong(id);
				String resp = "Semana de mayor numero de veces de consumo para el servicio " + idServicio + "\n";
				int i = 1;
				for ( Object [] tupla : hotelAndes.masNumeroDeVecesServicioSemana(idServicio))
				{
					Object [] datos = tupla;
					String resp1 = i++ + ". " + "[";
					resp1 += "Id servicio: " + datos [0] + ", ";
					resp1 += "Anio: " + datos [1] + ", ";
					resp1 += "Semana: " + datos [2] + ", ";
					resp1 += "Veces consumido: " + datos [3] + ", ";
					resp1 += "Dinero recaudado: " + datos [4];
					resp1 += "]";
					resp += resp1 + "\n";
				}
				resp += "Semana de mayor consumo para el servicio " + idServicio + "\n";
				for ( Object [] tupla : hotelAndes.mayorGananciaServicioSemana(idServicio))
				{
					Object [] datos = tupla;
					String resp1 = i++ + ". " + "[";
					resp1 += "Id servicio: " + datos [0] + ", ";
					resp1 += "Anio: " + datos [1] + ", ";
					resp1 += "Semana: " + datos [2] + ", ";
					resp1 += "Veces consumido: " + datos [3] + ", ";
					resp1 += "Dinero recaudado: " + datos [4];
					resp1 += "]";
					resp += resp1 + "\n";
				}
				resp += "Semana de menor consumo para el servicio " + idServicio + "\n";
				for ( Object [] tupla : hotelAndes.menorGananciaServicioSemana(idServicio))
				{
					Object [] datos = tupla;
					String resp1 = i++ + ". " + "[";
					resp1 += "Id servicio: " + datos [0] + ", ";
					resp1 += "Anio: " + datos [1] + ", ";
					resp1 += "Semana: " + datos [2] + ", ";
					resp1 += "Veces consumido: " + datos [3] + ", ";
					resp1 += "Dinero recaudado: " + datos [4];
					resp1 += "]";
					resp += resp1 + "\n";
				}
				panelDatos.actualizarInterfaz(resp);
			}
			else if(opcion.equalsIgnoreCase("2")){
				String id = JOptionPane.showInputDialog("Digite el id del tipo de habitacion a analizar");
				long idServicio = Long.parseLong(id);
				String resp = "Semana de mayor consumo para el tipo de habitacion " + idServicio + "\n";
				int i = 1;
				for ( Object [] tupla : hotelAndes.masNumeroDeVecesHabitacionSemana(idServicio))
				{
					Object [] datos = tupla;
					String resp1 = i++ + ". " + "[";
					resp1 += "Id tipo habitacion: " + datos [0] + ", ";
					resp1 += "Anio: " + datos [1] + ", ";
					resp1 += "Semana: " + datos [2] + ", ";
					resp1 += "Veces consumido: " + datos [3] + ", ";
					resp1 += "Dinero recaudado: " + datos [4];
					resp1 += "]";
					resp += resp1 + "\n";
				}
				resp += "Semana de mayor consumo para el tipo de habitacion " + idServicio + "\n";
				for ( Object [] tupla : hotelAndes.mayorGananciaHabitacionSemana(idServicio))
				{
					Object [] datos = tupla;
					String resp1 = i++ + ". " + "[";
					resp1 += "Id tipo habitacion: " + datos [0] + ", ";
					resp1 += "Anio: " + datos [1] + ", ";
					resp1 += "Semana: " + datos [2] + ", ";
					resp1 += "Veces consumido: " + datos [3] + ", ";
					resp1 += "Dinero recaudado: " + datos [4];
					resp1 += "]";
					resp += resp1 + "\n";
				}
				resp += "Semana de menor consumo para el tipo de habitacion" + idServicio + "\n";
				for ( Object [] tupla : hotelAndes.menorGananciaHabitacionSemana(idServicio))
				{
					Object [] datos = tupla;
					String resp1 = i++ + ". " + "[";
					resp1 += "Id tipo habitacion: " + datos [0] + ", ";
					resp1 += "Anio: " + datos [1] + ", ";
					resp1 += "Semana: " + datos [2] + ", ";
					resp1 += "Veces consumido: " + datos [3] + ", ";
					resp1 += "Dinero recaudado: " + datos [4];
					resp1 += "]";
					resp += resp1 + "\n";
				}
				panelDatos.actualizarInterfaz(resp);				
			}

		}
		else if(unidad.equals("2")){
			String opcion = JOptionPane.showInputDialog("Digite 1 para consultar analisis de operacion de servicio, 2 para consultar habitacion de HotelAndes: \n"
					+ "1. Servicio \n" +
					"2. Habitacion");
			if(opcion.equalsIgnoreCase("1")){
				String id = JOptionPane.showInputDialog("Digite el id del servicio a analizar");
				long idServicio = Long.parseLong(id);
				String resp = "Mes de mayor numero de veces de consumo para el servicio " + idServicio + "\n";
				int i = 1;
				for ( Object [] tupla : hotelAndes.masNumeroDeVecesServicioMes(idServicio))
				{
					Object [] datos = tupla;
					String resp1 = i++ + ". " + "[";
					resp1 += "Id servicio: " + datos [0] + ", ";
					resp1 += "Anio: " + datos [1] + ", ";
					resp1 += "Mes: " + datos [2] + ", ";
					resp1 += "Veces consumido: " + datos [3] + ", ";
					resp1 += "Dinero recaudado: " + datos [4];
					resp1 += "]";
					resp += resp1 + "\n";
				}
				resp += "Mes de mayor consumo para el servicio " + idServicio + "\n";
				for ( Object [] tupla : hotelAndes.mayorGananciaServicioMes(idServicio))
				{
					Object [] datos = tupla;
					String resp1 = i++ + ". " + "[";
					resp1 += "Id servicio: " + datos [0] + ", ";
					resp1 += "Anio: " + datos [1] + ", ";
					resp1 += "Mes: " + datos [2] + ", ";
					resp1 += "Veces consumido: " + datos [3] + ", ";
					resp1 += "Dinero recaudado: " + datos [4];
					resp1 += "]";
					resp += resp1 + "\n";
				}
				resp += "Mes de menor consumo para el servicio " + idServicio + "\n";
				for ( Object [] tupla : hotelAndes.menorGananciaServicioMes(idServicio))
				{
					Object [] datos = tupla;
					String resp1 = i++ + ". " + "[";
					resp1 += "Id servicio: " + datos [0] + ", ";
					resp1 += "Anio: " + datos [1] + ", ";
					resp1 += "Mes: " + datos [2] + ", ";
					resp1 += "Veces consumido: " + datos [3] + ", ";
					resp1 += "Dinero recaudado: " + datos [4];
					resp1 += "]";
					resp += resp1 + "\n";
				}
				panelDatos.actualizarInterfaz(resp);
			}
			else if(opcion.equalsIgnoreCase("2")){
				String id = JOptionPane.showInputDialog("Digite el id del tipo de habitacion a analizar");
				long idServicio = Long.parseLong(id);
				String resp = "Mes de mayor consumo para el tipo de habitacion " + idServicio + "\n";
				int i = 1;
				for ( Object [] tupla : hotelAndes.masNumeroDeVecesHabitacionMes(idServicio))
				{
					Object [] datos = tupla;
					String resp1 = i++ + ". " + "[";
					resp1 += "Id tipo habitacion: " + datos [0] + ", ";
					resp1 += "Anio: " + datos [1] + ", ";
					resp1 += "Mes: " + datos [2] + ", ";
					resp1 += "Veces consumido: " + datos [3] + ", ";
					resp1 += "Dinero recaudado: " + datos [4];
					resp1 += "]";
					resp += resp1 + "\n";
				}
				resp += "Mes de mayor consumo para el tipo de habitacion " + idServicio + "\n";
				for ( Object [] tupla : hotelAndes.mayorGananciaHabitacionMes(idServicio))
				{
					Object [] datos = tupla;
					String resp1 = i++ + ". " + "[";
					resp1 += "Id tipo habitacion: " + datos [0] + ", ";
					resp1 += "Anio: " + datos [1] + ", ";
					resp1 += "Mes: " + datos [2] + ", ";
					resp1 += "Veces consumido: " + datos [3] + ", ";
					resp1 += "Dinero recaudado: " + datos [4];
					resp1 += "]";
					resp += resp1 + "\n";
				}
				resp += "Mes de menor consumo para el tipo de habitacion" + idServicio + "\n";
				for ( Object [] tupla : hotelAndes.menorGananciaHabitacionMes(idServicio))
				{
					Object [] datos = tupla;
					String resp1 = i++ + ". " + "[";
					resp1 += "Id tipo habitacion: " + datos [0] + ", ";
					resp1 += "Anio: " + datos [1] + ", ";
					resp1 += "Mes: " + datos [2] + ", ";
					resp1 += "Veces consumido: " + datos [3] + ", ";
					resp1 += "Dinero recaudado: " + datos [4];
					resp1 += "]";
					resp += resp1 + "\n";
				}
				panelDatos.actualizarInterfaz(resp);				
			}	
		}

	}

	public String verBuenosClientes(){
		String resp = "Los buenos clientes son:\n";
		int i = 1;
		for ( Object [] tupla : hotelAndes.verBuenosClientes())
		{
			Object [] datos = tupla;
			String resp1 = i++ + ". " + "[";
			resp1 += "Id cliente: " + datos [0] + ", ";
			resp1 += "Nombre cliente: " + datos [1] + ", ";
			resp1 += "Total consumido: " + datos [2] + ", ";
			resp1 += "Total dias en HotelAndes: " + datos [3];
			resp1 += "]";
			resp += resp1 + "\n";
		}
		panelDatos.actualizarInterfaz(resp);
		return resp;		
	}

	public String serviciosPocaDemanda(){
		String resp = "Los buenos clientes son:\n";
		int i = 1;
		for ( Object [] tupla : hotelAndes.verServiciosPocaDemanda())
		{
			Object [] datos = tupla;
			String resp1 = i++ + ". " + "[";
			resp1 += "Id servicio: " + datos [0] + ", ";
			resp1 += "Nombre servicio: " + datos [1] + ", ";
			resp1 += "Total facturas por semana: " + datos [2];
			resp1 += "]";
			resp += resp1 + "\n";
		}
		panelDatos.actualizarInterfaz(resp);
		return resp;	

	}

	public void crearPlanConvencion(){

	}

	public void crearConvencion(){
		String id = JOptionPane.showInputDialog(this, "Ingrese el id de la convencion");
		long idConvencion = Long.parseLong(id);
		String nombre = JOptionPane.showInputDialog(this, "Ingrese el nombre de la convencion");
		String cap = JOptionPane.showInputDialog(this, "Ingrese la capacidad de la convencion");
		int capacidad = Integer.parseInt(cap);
		String fechaIni = JOptionPane.showInputDialog("Ingrese la fecha  inicial de la reserva. " + "\n"
				+ "Ingrese la fecha en formato: yyyy-mm-dd");
		Timestamp fechaInicio;
		if(fechaIni == null || fechaIni == "")
		{
			fechaInicio = null;
		}
		else
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try
			{
				Timestamp ts = new Timestamp(((java.util.Date)sdf.parse(fechaIni)).getTime());
				fechaInicio = ts;
			}
			catch (Exception e)
			{
				fechaInicio = null;
			}

		}
		String fechaFini = JOptionPane.showInputDialog("Ingrese la fecha final de la reserva " + "\n"
				+ "Ingrese la fecha en formato: yyyy-mm-dd");
		Timestamp fechaFin;
		if(fechaFini == null || fechaFini == "")
		{
			fechaFin = null;
		}
		else
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try
			{
				Timestamp ts = new Timestamp(((java.util.Date)sdf.parse(fechaFini)).getTime());
				fechaFin = ts;
			}
			catch (Exception e)
			{
				fechaFin = null;
			}

		}
		String idOrg = JOptionPane.showInputDialog(this, "Ingrese su id");
		long idOrganizador = Long.parseLong(idOrg);
		String costo = JOptionPane.showInputDialog(this, "Ingrese el costo del plan");
		double costoPlan = Double.parseDouble(costo);
		String desc = JOptionPane.showInputDialog(this, "Ingrese el descuento en el alojamiento del plan");
		double descuento = Double.parseDouble(desc);

		String idPlan = JOptionPane.showInputDialog(this, "Ingrese el id del plan");
		long idP = Long.parseLong(idPlan);
		String tipo = JOptionPane.showInputDialog("Ingrese el tipo del plan");

		String idDescuento = JOptionPane.showInputDialog(this, "Ingrese el id del descuento");
		long idDescu = Long.parseLong(idDescuento);
		String idServ = JOptionPane.showInputDialog(this, "Ingrese el id del servicio al que pertenece el desuento. No ingrese nada si no hay");
		Long idServicio;
		if(idServ.equals(""))
		{
			idServicio = null;
		}
		else
		{
			idServicio = Long.valueOf(idServ);
		}
		String idProd = JOptionPane.showInputDialog(this, "Ingrese el id del producto. No ingrese nada si no hay");
		Long idProducto;
		if(idProd.equals(""))
		{
			idProducto = null;
		}
		else
		{
			idProducto = Long.valueOf(idProd);
		}

		String numVeces = JOptionPane.showInputDialog(this, "Ingrese el numero de veces que se puede aplicar. Ingrese -1 si no hay.");
		int cantVeces = Integer.parseInt(numVeces);
		String valorDesc = JOptionPane.showInputDialog(this, "Ingrese el porcentaje de descuento (Numero entre 1 y 100)");
		long descuentito = Long.valueOf( valorDesc);

		String cant = JOptionPane.showInputDialog(this, "¿Cuantas habitaciones desea reservar? \n Separe los tipos y las cantidades con comas \n Ej: 1,2,2,3 Busca 2 habitaciones tipo 1 y 3 tipo 2 ");
		String[] rpta = cant.split(",");

		String cant2 = JOptionPane.showInputDialog(this, "¿Cuantos servicios desea reservar? \n Separe los tipos y las cantidades y las horas con comas \n Ej: 20,2,2019-03-03 11:00:00,2019-03-03 12:00:00 hace una reserva para el servicio 20 para 2 personas desde las 11 hasta las 12 el 3 de marzo del 2019 ");
		String[] rpta2 = cant2.split(",");
		try{
			hotelAndes.rf12(idP, tipo, costoPlan, descuento, fechaFin, idDescu, idServicio, idProducto, descuentito, cantVeces, idConvencion, nombre, capacidad, fechaInicio, fechaFin, idOrganizador, rpta, rpta2);
			JOptionPane.showMessageDialog(this, "La convencion se pudo registrar exitosamente");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	public void cancelarConvencion(){
		String idC = JOptionPane.showInputDialog(this, "Digite el id de la convencion");
		Long idConvencion = Long.parseLong(idC);
		String desres = JOptionPane.showInputDialog(this, "Digite la cantidad de habitaciones que desea desreservar");
		int desReservas = Integer.parseInt(desres);
		String servs = JOptionPane.showInputDialog(this, "Digite los ids de las reservas que desea desreservar. \n Ej: 2,5");
		String[] idsServicios = servs.split(",");		
		try{
			hotelAndes.rf13(idConvencion, desReservas, idsServicios);
			JOptionPane.showMessageDialog(this, "Se desreservo exitosamente");
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	public void finalizarConvencion(){
		String idC = JOptionPane.showInputDialog(this, "Digite el id de la convencion");
		Long idConvencion = Long.parseLong(idC);
		try{
			hotelAndes.rf14(idConvencion);
			JOptionPane.showMessageDialog(this, "Se ha logrado finalizar la convencion");
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	public void mantenimientoHabitaciones() {
		String idm = JOptionPane.showInputDialog(this, "Ingrese el id del mantenimiento");
		Long idMantenimiento = Long.parseLong(idm);
		String causa = JOptionPane.showInputDialog(this, "Ingrese la causa del mantenimiento");
		String ids = JOptionPane.showInputDialog(this, "Ingrese los ids de las habitaciones a mantener \n. Separe los ids con comas. \n Ejemplo: 28,7");
		String[] arregloIds = ids.split(",");
		String fechaIni = JOptionPane.showInputDialog("Ingrese la fecha inicial del mantenimiento. " + "\n"
				+ "Ingrese la fecha en formato: yyyy-mm-dd");
		Timestamp fechaInicio;
		if(fechaIni == null || fechaIni == "")
		{
			fechaInicio = null;
		}
		else
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try
			{
				Timestamp ts = new Timestamp(((java.util.Date)sdf.parse(fechaIni)).getTime());
				fechaInicio = ts;
			}
			catch (Exception e)
			{
				fechaInicio = null;
			}

		}
		String fechaFini = JOptionPane.showInputDialog("Ingrese la fecha final del mantenimiento " + "\n"
				+ "Ingrese la fecha en formato: yyyy-mm-dd");
		Timestamp fechaFin;
		if(fechaFini == null || fechaFini == "")
		{
			fechaFin = null;
		}
		else
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try
			{
				Timestamp ts = new Timestamp(((java.util.Date)sdf.parse(fechaFini)).getTime());
				fechaFin = ts;
			}
			catch (Exception e)
			{
				fechaFin = null;
			}
		}
		try{
			hotelAndes.rf15Habitaciones(idMantenimiento, causa, arregloIds, fechaInicio, fechaFin);
			JOptionPane.showMessageDialog(this, "Se entraron a mantenimiento las habitaciones");}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	public void mantenimientoServicios(){
		String idm = JOptionPane.showInputDialog(this, "Ingrese el id del mantenimiento");
		Long idMantenimiento = Long.parseLong(idm);
		String causa = JOptionPane.showInputDialog(this, "Ingrese la causa del mantenimiento");
		String ids = JOptionPane.showInputDialog(this, "Ingrese los ids de servicios a mantener \n. Separe los ids con comas. \n Ejemplo: 2,11,3");
		String[] arregloIds = ids.split(",");
		String fechaIni = JOptionPane.showInputDialog("Ingrese la fecha  inicial del mantenimiento " + "\n"
				+ "Ingrese la fecha en formato: yyyy-mm-dd");
		Timestamp fechaInicio;
		if(fechaIni == null || fechaIni == "")
		{
			fechaInicio = null;
		}
		else
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try
			{
				Timestamp ts = new Timestamp(((java.util.Date)sdf.parse(fechaIni)).getTime());
				fechaInicio = ts;
			}
			catch (Exception e)
			{
				fechaInicio = null;
			}

		}
		String fechaFini = JOptionPane.showInputDialog("Ingrese la fecha final del mantenimiento " + "\n"
				+ "Ingrese la fecha en formato: yyyy-mm-dd");
		Timestamp fechaFin;
		if(fechaFini == null || fechaFini == "")
		{
			fechaFin = null;
		}
		else
		{
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			try
			{
				Timestamp ts = new Timestamp(((java.util.Date)sdf.parse(fechaFini)).getTime());
				fechaFin = ts;
			}
			catch (Exception e)
			{
				fechaFin = null;
			}
		}
		try{
			hotelAndes.rf15Servicios(idMantenimiento, causa, arregloIds, fechaInicio, fechaFin);
			JOptionPane.showMessageDialog(this, "Se entraron a mantenimiento las habitaciones");
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

	}

	public void finMantenimientoHabitaciones(){
		String servicito = JOptionPane.showInputDialog(this, "Digite el id de las habitaciones a sacar de mantenimiento, separados por comas. ej (20,11)");
		if(servicito == null || servicito.equals("")){
			JOptionPane.showMessageDialog(this, "No hay mantenimientos a finalizar");
		}
		String[] resp = servicito.split(",");
		try{
			hotelAndes.rf16Habitacion(resp);
			JOptionPane.showMessageDialog(this, "Se finalizaron correctamente los mantenimientos");
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

	}

	public void finMantenimientoServicios(){
		String servicito = JOptionPane.showInputDialog(this, "Digite el id de los servicios a sacar de mantenimiento, separados por comas. ej (20,11)");
		String[] resp = servicito.split(",");
		try{
			hotelAndes.rf16Servicio(resp);
			JOptionPane.showMessageDialog(this, "Mantenimientos finalizados exitosamente");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

	}

	/**
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo (String nombreArchivo)
	{
		try
		{
			Desktop.getDesktop().open(new File(nombreArchivo));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	/**
	 * Método para la ejecución de los eventos que enlazan el menú con los métodos de negocio
	 * Invoca al método correspondiente según el evento recibido
	 * @param pEvento - El evento del usuario
	 */
	@Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );		
		try 
		{
			Method req = InterfazHotelAndesApp.class.getMethod ( evento );			
			req.invoke ( this );
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}

	public void rf12(){
		long idP = 200;
		String tipo = "Plan feria";
		 double costo = 300000;
		 double descuentoAlojamiento = 10;
		 Timestamp fecha = null;
		 long idDescuento = 300;
		 long idServicio = 4;
		 Long idProducto = 2L;
		 long valor = 70;
		 int limiteVeces = 2;
		long idConvencion = 100;
		String nombre = "FERIA";
		int capacidad = 300;
		Timestamp fechaInicio = new Timestamp(2021, 10, 1, 0, 0, 0, 0);
		Timestamp fechaFin = new Timestamp(2021, 10, 5, 0, 0, 0, 0);
		long idOrganizador = 19135679;
		String [] rpta = {"1", "2", "2", "3"};
		String[] rpta2 = {"20", "5", "04/10/2021 11:00", "04/10/2021 12:00"};
		
		try{
			hotelAndes.rf12(idP, tipo, costo, descuentoAlojamiento, fechaFin, idDescuento, idServicio, idProducto, valor, limiteVeces, idConvencion, nombre, capacidad, fechaInicio, fechaFin, idOrganizador, rpta, rpta2);
			JOptionPane.showMessageDialog(this, "La convencion se pudo registrar exitosamente");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	public void rf13(){
		long idConvencion = 100;
		int desReservas = 2;
		String[] idsServicios = {"5"};
		
		
		try{
			hotelAndes.rf13(idConvencion, desReservas, idsServicios);
			JOptionPane.showMessageDialog(this, "Se desreservo exitosamente");
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
		
	}

	public void rf14(){
		Long idConvencion = Long.parseLong("100");
		try{
			hotelAndes.rf14(idConvencion);
			JOptionPane.showMessageDialog(this, "Se ha logrado finalizar la convencion.");
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}
	}

	public void rf15Servicios(){
		Long idMantenimiento = Long.parseLong("300");
		String causa = "APAGON";
		String[] arregloIds = {"11","20"};
		Timestamp fechaInicio = new Timestamp(2021, 3, 2, 10, 0, 0, 0);
		Timestamp fechaFin = new Timestamp(2021, 3, 2, 11, 0, 0, 0);
		try{
			hotelAndes.rf15Servicios(idMantenimiento, causa, arregloIds, fechaInicio, fechaFin);
			JOptionPane.showMessageDialog(this, "Se entraron a mantenimiento los servicios");
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

	}

	public void rf15Habitaciones(){
		Long idMantenimiento = Long.parseLong("100");
		String causa = "PLAGAS";
		String [] arregloIds = {"17","28"};
		Timestamp fechaInicio = new Timestamp(2021, 4, 7, 10, 0, 0, 0);
		Timestamp fechaFin = new Timestamp(2021, 3, 4, 8, 11, 0, 0);

		try{
			hotelAndes.rf15Habitaciones(idMantenimiento, causa, arregloIds, fechaInicio, fechaFin);
			JOptionPane.showMessageDialog(this, "Se entraron a mantenimiento las habitaciones");}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

	}

	public void rf16Servicios(){
		String[] resp = {"11", "20"};
		
		try{
			hotelAndes.rf16Servicio(resp);
			JOptionPane.showMessageDialog(this, "Mantenimientos finalizados exitosamente");
		}
		catch(Exception e){
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

	}

	public void rf16Habitaciones(){
		String[] resp = {"17", "28"};
		
		try{
			hotelAndes.rf16Habitacion(resp);
			JOptionPane.showMessageDialog(this, "Se finalizaron correctamente los mantenimientos");
		}
		catch (Exception e) {
			JOptionPane.showMessageDialog(this, e.getMessage());
		}

	}


	/* ****************************************************************
	 * 			Programa principal
	 *****************************************************************/
	/**
	 * Este método ejecuta la aplicación, creando una nueva interfaz
	 * @param args Arreglo de argumentos que se recibe por línea de comandos
	 */
	public static void main( String[] args )
	{
		try
		{
			// Unifica la interfaz para Mac y para Windows.
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
			InterfazHotelAndesApp interfaz = new InterfazHotelAndesApp( );
			interfaz.setVisible( true );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}
}
