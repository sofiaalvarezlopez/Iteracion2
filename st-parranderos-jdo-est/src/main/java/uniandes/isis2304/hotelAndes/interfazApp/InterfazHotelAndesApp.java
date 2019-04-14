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
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.hotelAndes.negocio.CaracteristicaAdicional;
import uniandes.isis2304.hotelAndes.negocio.Descuento;
import uniandes.isis2304.hotelAndes.negocio.DotacionSalon;
import uniandes.isis2304.hotelAndes.negocio.Estadia;
import uniandes.isis2304.hotelAndes.negocio.Factura;
import uniandes.isis2304.hotelAndes.negocio.Habitacion;
import uniandes.isis2304.hotelAndes.negocio.Horario;
import uniandes.isis2304.hotelAndes.negocio.HotelAndes;
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

	/**
	 * Ruta al archivo de configuración de la interfaz PARA LOS USUARIOS DE PERSONAL
	 */
	private static final String CONFIG_INTERFAZ_EMPLEADO = "./src/main/resources/config/interfaceConfigAppEmpleado.json"; 

	private static final String CONFIG_INTERFAZ_RECEPCIONISTA = "./src/main/resources/config/interfaceConfigRecepcionista.json"; 


	/**
	 * Ruta al archivo de configuración de la interfaz PARA LOS USUARIOS DE hotelandes
	 */
	private static final String CONFIG_INTERFAZ_ADMIN_DATOS = "./src/main/resources/config/interfaceConfigAppAndes.json"; 

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

		Object[] options = {"Cliente", "Empleado", "Recepcionista", "Gerente", "administradorDeDatos", "Organizador Eventos"};
		int n = JOptionPane.showOptionDialog(this,"¿Cómo desea ingresar?","HotelAndes",JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,null,options,options[2]);
		if(n == 0)
		{
			String ced = JOptionPane.showInputDialog(this, "Digite su cedula", "HotelAndes", JOptionPane.PLAIN_MESSAGE);
			String cedula = String.valueOf(Long.parseLong(ced));

			if(ced.isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Por favor digite una cedula valida", "HotelAndes", JOptionPane.PLAIN_MESSAGE);
				System.exit(0);
			}
			if(hotelAndes.darUsuario(cedula) == null  || hotelAndes.darUsuario(cedula).getid_tipo_usuario() != 5)
			{
				JOptionPane.showMessageDialog(this, "Lo sentimos, su cedula no se encuentra en nuestros registros", "HotelAndes", JOptionPane.PLAIN_MESSAGE);
				System.exit(0);
			}
			else
			{
				estadoAplicacion = 5;
				identificacionUsuario = Long.parseLong(cedula);
				guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_CLIENTES);
			}
		}
		else if(n == 1)
		{
			String ced = JOptionPane.showInputDialog(this, "Digite su cedula", "HotelAndes", JOptionPane.PLAIN_MESSAGE);
			String cedula = ced;
			if(ced.isEmpty())
			{
				JOptionPane.showMessageDialog(this, "Por favor ingrese una cedula valido", "HotelAndes", JOptionPane.PLAIN_MESSAGE);
				System.exit(0);
			}
			if(hotelAndes.darUsuario(cedula) == null|| hotelAndes.darUsuario(cedula).getid_tipo_usuario() != 4)
			{
				JOptionPane.showMessageDialog(this, "Lo sentimos, su cedula no se encuentra en nuestros registros", "HotelAndes", JOptionPane.PLAIN_MESSAGE);
				System.exit(0);
			}
			else
			{
				estadoAplicacion = 4;
				identificacionUsuario = Long.parseLong(cedula);
				guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_EMPLEADO);
			}
		}
		else if(n == 2)
		{
			String ced = JOptionPane.showInputDialog(this, "Digite su cedula", "HotelAndes", JOptionPane.PLAIN_MESSAGE);
			String cedula = ced;


				if(hotelAndes.darUsuario(cedula) == null || hotelAndes.darUsuario(cedula).getid_tipo_usuario() != 3)
				{
					JOptionPane.showMessageDialog(this, "Lo sentimos, su cedula no se encuentra en nuestros registros", "HotelAndes", JOptionPane.PLAIN_MESSAGE);
					System.exit(0);
				}
				else
				{
					estadoAplicacion = 3;
					identificacionUsuario = Long.parseLong(cedula);
					guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_RECEPCIONISTA);
				}
		}
		else if(n == 3)
		{
			
			String ced = JOptionPane.showInputDialog(this, "Digite su cedula", "HotelAndes", JOptionPane.PLAIN_MESSAGE);
			String cedula = String.valueOf(Long.parseLong(ced));


				if(hotelAndes.darUsuario(cedula) == null || hotelAndes.darUsuario(cedula).getid_tipo_usuario() != 2)
				{
					JOptionPane.showMessageDialog(this, "Lo sentimos, su cedula no se encuentra en nuestros registros", "HotelAndes", JOptionPane.PLAIN_MESSAGE);
					System.exit(0);
				}
				else
				{
					estadoAplicacion = 2;
					identificacionUsuario = Long.parseLong(cedula);
					//TODO cambiar a interfaz GERENTE
					guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_RECEPCIONISTA);
				}
		}
		else if(n == 4)
		{
			
			String ced = JOptionPane.showInputDialog(this, "Digite su cedula", "HotelAndes", JOptionPane.PLAIN_MESSAGE);
			String cedula = ced;
			guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_ADMIN_DATOS);
			
/*				if(hotelAndes.darUsuario(cedula) == null || hotelAndes.darUsuario(cedula).getIdTipoUsuario() != 1)
				{
					JOptionPane.showMessageDialog(this, "Esta opcion es solo para acceso administrativo", "HotelAndes", JOptionPane.WARNING_MESSAGE);
					System.exit(0);
				}
				else
				{
					estadoAplicacion = 1;
					identificacionUsuario = Long.parseLong(cedula);
					//TODO cambiar a interfaz clientes
					guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_RECEPCIONISTA);
				}*/
		}
		else if(n == 5)
		{
			
			String ced = JOptionPane.showInputDialog(this, "Digite su cedula", "HotelAndes", JOptionPane.PLAIN_MESSAGE);
			String cedula = ced;


				if(hotelAndes.darUsuario(cedula) == null || hotelAndes.darUsuario(cedula).getid_tipo_usuario() != 6)
				{
					JOptionPane.showMessageDialog(this, "Lo sentimos, su cedula no se encuentra en nuestros registros", "HotelAndes", JOptionPane.PLAIN_MESSAGE);
					System.exit(0);
				}
				else
				{
					estadoAplicacion = 6;
					identificacionUsuario = Long.parseLong(cedula);
					//TODO cambiar a interfaz Organizador Eventos
					guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_RECEPCIONISTA);
				}
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
		Usuario usuario = hotelAndes.adicionarUsuario(id, tipoId, nombre, correo, idTipo);
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

		TipoHabitacion tipo = hotelAndes.adicionarTipoHabitacion(id, descripcion, capacidad, precioPorPersonaPorNoche, idHotel);	
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
		Habitacion habitacion = hotelAndes.adicionarHabitacion(numHabitacion, idTipoHabitacion);
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
		Servicio serv = hotelAndes.adicionarServicio(idServicio, nombre);
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
			ServicioAdicional servicioAdicional = hotelAndes.adicionarServicioAdicional(idServicio, capacidad);
			if(servicioAdicional == null){
				JOptionPane.showMessageDialog(this, "No fue posible agregar al usuario","hotelandes", JOptionPane.PLAIN_MESSAGE);
				return;
			}
			String idCaracteristica = JOptionPane.showInputDialog(this, "Ingrese el id de la caracteristica adicional");
			long idCaracteristicaAdicional = Long.parseLong(idCaracteristica);
			String nombreCar = JOptionPane.showInputDialog(this, "Ingrese el nombre de la carcteristica adicional");
			String val = JOptionPane.showInputDialog(this, "Ingrese el valor de la caracteristica");
			double valor = Double.parseDouble(val);
			CaracteristicaAdicional car = hotelAndes.adicionarCaracteristicaAdicional(idCaracteristicaAdicional, nombreCar, valor, idServicio);
			if(car == null){
				JOptionPane.showMessageDialog(this, "No fue posible agregar al servicio","hotelandes", JOptionPane.PLAIN_MESSAGE);
				return;
			}
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
			VentaProducto ventaP = hotelAndes.adicionarVentaProducto(idVentaP, capacidad, estilo, tipo);
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

			Producto p = hotelAndes.adicionarProducto(idProducto, prec, nombreProd, cant, dur, categoriaProducto, idVentaP); 
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
			Salon salon = hotelAndes.adicionarSalon(idServicio, capacidadSalon, precioSalon, tipo);
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

	public void registrarConsumos(){
		String costo = JOptionPane.showInputDialog(this, "Ingrese el costo del plan");
		double costoPlan = Double.parseDouble(costo);
		String desc = JOptionPane.showInputDialog(this, "Ingrese el descuento del plan");
		double descuento = Double.parseDouble(desc);
		String fecha = JOptionPane.showInputDialog("Ingrese la fecha de vencimiento del plan, si tiene. " + "\n"
				+ "Ingrese la fecha en formato: yyyy-mm-dd");
		Timestamp fechaVencimiento = Timestamp.valueOf(fecha);
		String idPlan = JOptionPane.showInputDialog(this, "Ingrese el id del plan");
		long idP = Long.parseLong(idPlan);
		String tipo = JOptionPane.showInputDialog("Ingrese el tipo del plan");
		Plan plan = hotelAndes.adicionarPlan(idP, tipo, costoPlan, descuento, fechaVencimiento);
		if(plan == null){
			JOptionPane.showMessageDialog(this, "No fue posible agregar al usuario","hotelandes", JOptionPane.PLAIN_MESSAGE);
			return;
		}

		String idDescuento = JOptionPane.showInputDialog(this, "Ingrese el id del descuento");
		long idDescu = Long.parseLong(idDescuento);
		String numVeces = JOptionPane.showInputDialog(this, "Ingrese el numero de veces que se puede aplicar. No ingrese nada si no hay.");
		int cantVeces = Integer.parseInt(numVeces);
		long descuentito = (long) descuento;
		Descuento descu = hotelAndes.adicionarDescuento(idDescu, idP, 0, 0, descuentito, cantVeces);
		if(descu == null){
			JOptionPane.showMessageDialog(this, "No fue posible agregar al usuario","hotelandes", JOptionPane.PLAIN_MESSAGE);
			return;
		}

	}
	
	public void reservarEstadia()
	{
		String fecha1 = JOptionPane.showInputDialog(this, "Ingrese la fecha de llegada en formato yyyy-mm-dd");
		Timestamp fechaLlegada = Timestamp.valueOf(fecha1);
		String fecha2 = JOptionPane.showInputDialog(this, "Ingrese la fecha de salida en formato yyyy-mm-dd");
		Timestamp fechaSalida = Timestamp.valueOf(fecha2);
		String idEst = JOptionPane.showInputDialog(this, "Ingrese su cedula");
		long idEstadia = Long.parseLong(idEst);
		String numH = JOptionPane.showInputDialog(this, "Ingrese el numero de habitacion que desea");
		long numHabitacion = Long.parseLong(numH);
		String numPersonas = JOptionPane.showInputDialog(this, "Ingrese el numero de personas con quienes viene a hotelandes");
		int cantPersonas = Integer.parseInt(numPersonas);
		String idPla = JOptionPane.showInputDialog(this, "Ingrese el id de su plan");
		long idPlan = Long.parseLong(idPla);
		
		String tipoDoc = JOptionPane.showInputDialog(this, "Ingrese el tipo de su documento");
		
		Estadia estadia = hotelAndes.adicionarEstadia(idEstadia, fechaLlegada, fechaSalida, cantPersonas, idPlan, numHabitacion, 0, 0, tipoDoc, idEstadia);
		if(estadia == null){
			JOptionPane.showMessageDialog(this, "No fue posible agregar al usuario","hotelandes", JOptionPane.PLAIN_MESSAGE);
			return;
		}
	
	}
	
	public void reservarServicio() {
		String ced = JOptionPane.showInputDialog(this, "Ingrese su cedula");
		String x = JOptionPane.showInputDialog(this, "Diga el id del servicio que desea reservar");
		long id = Long.parseLong(x);
		
		
		long idEstadia = Long.parseLong(ced);
		String fecha = JOptionPane.showInputDialog(this, "Ingrese la fecha de reserva en formato yyyy-mm-dd ");
		Timestamp fechares = Timestamp.valueOf(fecha);
		String duracion = JOptionPane.showInputDialog(this, "Ingrese la duracion de su servicio");
	
		Horario hor = hotelAndes.adicionarHorario(idEstadia, duracion, id, fechares, null, null, null);
		
		Reserva res = hotelAndes.adicionarReserva(idEstadia, idEstadia, id, hor.getIdHorario(), 0);
		if(res == null){
			JOptionPane.showMessageDialog(this, "No fue posible agregar al usuario","hotelandes", JOptionPane.PLAIN_MESSAGE);
			return;
		}
	}
	
	public void registrarLlegada(){
		String ced = JOptionPane.showInputDialog(this, "Ingrese la cedula del cliente");
		long cedula = Long.parseLong(ced);
		Estadia est = hotelAndes.darEstadiaPorID(cedula);
		if(est == null){
			JOptionPane.showMessageDialog(this, "No fue posible registrar la llegada del cliente","hotelandes", JOptionPane.PLAIN_MESSAGE);
			return;
		}
		hotelAndes.checkInCliente(cedula);
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
		Factura fac = hotelAndes.adicionarFactura(numFactura(), hoy, 0, valor, 0, idServ, "CC", idEstadia, numDocEmpleado, idConsumo);
		if(fac == null){
			JOptionPane.showMessageDialog(this, "No fue posible registrar la facturae","hotelandes", JOptionPane.PLAIN_MESSAGE);
			return;
		}
	}
	
	public void registrarSalida(){
		String ced = JOptionPane.showInputDialog(this, "Ingrese la cedula del cliente");
		long cedula = Long.parseLong(ced);
		List<Factura> facturas = hotelAndes.darFacturas();
		String a;
		for (int i = 0; i < facturas.size(); i++) {
			if(i == 0){
				
			a = "Las facturas del cliente son: ";
			}
			else
			{
				a = "";
			}
			JOptionPane.showMessageDialog(this, a+= facturas.get(i).getNumFactura());
		}
		String resp = JOptionPane.showInputDialog(this, "¿Desea pagar las facturas?");
		Estadia est = hotelAndes.darEstadiaPorID(cedula);
		if(resp.equalsIgnoreCase("si"))
		{
			hotelAndes.cambiarEstadiaAPagada(est.getIdEstadia());
		}
	
		JOptionPane.showInputDialog(this, "Check-out realizado exitosamente");

	
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
		String resultado = "Proyecto #1 Sistemas Transaccionales \n"
				+ "Daniel Serrano - 201731047"
				+ "Sofia Alvarez - 201729031";

		panelDatos.actualizarInterfaz(resultado);		
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
