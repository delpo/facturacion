package terminal;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.InputMismatchException;
//import java.util.Locale;
import java.util.Scanner;

//import java.util.TimeZone;

import terminal.MenuIncidencia.OpcionesIncidencia;
import terminal.MenuTarifa.OpcionesTarifa;

import facturacion.Averia;
import facturacion.Cliente;
import facturacion.CodigoFactura;
import facturacion.CodigoIncidencia;
import facturacion.CodigoLlamada;
import facturacion.Direccion;
import facturacion.Email;
import facturacion.Empresa;
import facturacion.ExcepcionEmailNoValido;
import facturacion.ExcepcionNIFnoValido;
import facturacion.Factura;
import facturacion.Impago;
import facturacion.Incidencia;
import facturacion.Llamada;
import facturacion.NIF;
import facturacion.Operador_telefonia;
import facturacion.Particular;
import facturacion.Periodo_facturacion;
import facturacion.SolicitudPortabilidad;
import facturacion.Tarifa;

public class ManejoInputs implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Cliente inputParticular(){
		Scanner scanner = new Scanner(System.in);
		Cliente cliente = null;
		String nombre; String apellidos; NIF nif = null; Direccion direccion; Email email = null;
		System.out.print("Introduce nombre: ");
		nombre = scanner.nextLine();
		System.out.print("Introduce apellidos: ");
		apellidos = scanner.nextLine();
		boolean ok_nif = false;
		do{
			try {
				System.out.print("Introduce DNI: ");
				String elDNI = scanner.nextLine();
				nif = new NIF(elDNI);
				ok_nif = true;
			} catch (ExcepcionNIFnoValido e) {
				System.out.println("NIF NO VÁLIDO");
			}
		}while(!ok_nif);
		System.out.print("Introduce código postal: ");
		int codigo_postal=0;
		try{
			codigo_postal = scanner.nextInt();
		}catch(InputMismatchException e){
			System.out.println("No has introducido un número válido.");
		}
		Scanner scanner2 = new Scanner(System.in);
		System.out.print("Introduce población: ");
		String poblacion = scanner2.nextLine();
		Scanner scanner3 = new Scanner(System.in);
		System.out.print("Introduce provincia: ");
		String provincia = scanner3.nextLine();
		direccion = new Direccion(codigo_postal, provincia, poblacion);
		boolean ok_email = false;
		do{
			try {
				System.out.print("Introduce email: ");
				Scanner scanner4 = new Scanner(System.in);
				String elEmail = scanner4.nextLine();
				email = new Email(elEmail);
				ok_email = true;
			} catch (ExcepcionEmailNoValido e) {
				System.out.println("EMAIL NO VÁLIDO");
			}
		}while(!ok_email);
		//tarifa básica es -1
		cliente = new Particular(nombre, apellidos, nif, direccion, email, -1);
		return cliente;
	}
	
	private static Cliente inputEmpresa(){
		Scanner scanner = new Scanner(System.in);
		Cliente cliente = null;
		String nombre; NIF nif = null; Direccion direccion; Email email = null;
		System.out.print("Introduce nombre de empresa: ");
		nombre = scanner.nextLine();
		boolean ok_nif = false;
		do{
			try {
				System.out.print("Introduce DNI: ");
				String elDNI = scanner.nextLine();
				nif = new NIF(elDNI);
				ok_nif = true;
			} catch (ExcepcionNIFnoValido e) {
				System.out.println("NIF NO VÁLIDO");
			}
		}while(!ok_nif);
		System.out.print("Introduce código postal: ");
		int codigo_postal=0;
		try{
			codigo_postal = scanner.nextInt();
		}catch(InputMismatchException e){
			System.out.println("No has introducido un número válido.");
		}
		Scanner scanner2 = new Scanner(System.in);
		System.out.print("Introduce población: ");
		String poblacion = scanner2.nextLine();
		Scanner scanner3 = new Scanner(System.in);
		System.out.print("Introduce provincia: ");
		String provincia = scanner3.nextLine();
		direccion = new Direccion(codigo_postal, provincia, poblacion);
		boolean ok_email = false;
		do{
			try {
				System.out.print("Introduce email: ");
				Scanner scanner4 = new Scanner(System.in);
				String elEmail = scanner4.nextLine();
				email = new Email(elEmail);
				ok_email = true;
			} catch (ExcepcionEmailNoValido e) {
				System.out.println("EMAIL NO VÁLIDO");
			}
		}while(!ok_email);
		//tarifa básica es -1
		cliente = new Empresa(nombre, nif, direccion, email, -1);
		return cliente;
	}
	
	public static Cliente pedirCliente(){
		Scanner scanner = new Scanner(System.in);
		boolean ok = false;
		Cliente cliente = null;
		do{
			System.out.println("¿El cliente es particular o empresa?");
			System.out.println("1) Particular");
			System.out.println("2) Empresa");
			System.out.print("Introduce opción: ");
			int op = scanner.nextInt();
			if(op == 1){
				ok = true;
				cliente = inputParticular();
			}else if(op == 2){
				ok = true;
				cliente = inputEmpresa();
			}else{
				System.out.println("ERROR.");
			}
		}while(!ok);
		return cliente;	
	}
	
	public static int pedirTarifa(){
		OpcionesTarifa opcionTarifa = MenuTarifa.Menu();
		switch(opcionTarifa) {
			case OPCION0:
				return 0;
			case OPCION1:
				return 1;
		}
		return -1;
	}

	public static NIF pedirNIF() {
		NIF nif = null;
		Scanner scanner = new Scanner(System.in);
		boolean ok = false;
		do{
			try {
				System.out.print("Introduce NIF: ");
				String elNif = scanner.nextLine();
				nif = new NIF(elNif);
				ok = true;
			} catch (ExcepcionNIFnoValido e) {
				System.out.println("NIF NO VÁLIDO.");
			}
		}while(!ok);
		return nif;
	}

	public static int pedirCP() {
		int cp = 0;
		Scanner scanner = new Scanner(System.in);
		boolean ok = false;
		do{
			try {
				System.out.print("Introduce CP: ");
				int elcp = scanner.nextInt();
				cp = elcp;
				ok = true;
			} catch (InputMismatchException e) {
				System.out.println("NIF NO VÁLIDO.");
			}
		}while(!ok);
		return cp;
	}
	
	public static CodigoFactura pedirCodigoFactura() {
		CodigoFactura cod = null;
		Scanner scanner = new Scanner(System.in);
		boolean ok = false;
		String elcod = null;
		do{
			try {
				try{
					System.out.print("Introduce código de factura: ");
					elcod = scanner.nextLine();
				}catch(java.lang.NullPointerException excp){
					System.out.println("ERROR AL AÑADIR CÓDIGO.");
				}
				cod = new CodigoFactura(elcod);
				ok = true;
			} catch (InputMismatchException e) {
				System.out.println("CÓDIGO DE FACTURA NO VÁLIDO.");
			}
		}while(!ok);
		return cod;
	}
	
	public static Calendar pedirFecha() {
		/*TimeZone tzESP = TimeZone.getTimeZone("GMT+2"); 
		Calendar fecha = Calendar.getInstance ( tzESP, new Locale("es_ES") );*/ 
		Calendar fecha = new GregorianCalendar();
		int dia = 0; int mes = 0; int anyo = 0;
		Scanner scanner = new Scanner(System.in);
		boolean ok = false;
		do{
			try {
				System.out.print("Introduce día: ");
				int eldia = scanner.nextInt();
				dia = eldia;
				System.out.print("Introduce mes: ");
				int elmes = scanner.nextInt();
				mes = elmes;
				System.out.print("Introduce año: ");
				int elanyo = scanner.nextInt();
				anyo = elanyo;
				ok = true;
			} catch (InputMismatchException e) {
				System.out.println("FECHA NO VÁLIDA.");
			}
		}while(!ok);
		fecha.set(anyo, mes, dia);
		return fecha;
	}
	
	public static Factura pedirFactura(NIF nif, Operador_telefonia op, HashMap<CodigoLlamada, Llamada> llamadas) {
		Factura factura = null;
		System.out.println("-Introduzca fecha de emisión-");
		Calendar fecha_emision = pedirFecha();
		
		Tarifa tarifa = null;
		Periodo_facturacion periodo = null;
		
		boolean ok = false;
		do{
			try {
				Cliente cliente = op.obtenerCliente(nif);
				tarifa = cliente.getTarifa();
				System.out.println("-Fecha inicio de periodo de facturación-");
				Calendar fecha_inicio = null;
				fecha_inicio = pedirFecha();
				System.out.println("-Fecha fin de periodo de facturación-");
				Calendar fecha_fin = null;
				fecha_fin = pedirFecha();
				periodo = new Periodo_facturacion(fecha_inicio, fecha_fin);
				ok = true;
			} catch (InputMismatchException e) {
				System.out.println("DATOS DE FACTURA NO VÁLIDOS");
			}
		}while(!ok);
		factura = new Factura(fecha_emision, tarifa, periodo, llamadas);
		return factura;
	}
	
	
	public static Incidencia pedirIncidencia(){
		Incidencia incidencia = null;
		OpcionesIncidencia opcionIncidencia = MenuIncidencia.Menu();
		switch(opcionIncidencia) {
			case OPCION0:
				incidencia = new SolicitudPortabilidad(pedirFecha());
				break;
			case OPCION1:
				incidencia = new Averia(pedirFecha());
				break;
			case OPCION2:
				incidencia = new Impago(pedirFecha());
				break;
		}
		return incidencia;
	}

	public static CodigoIncidencia pedirCodigoIncidencia() {
		CodigoIncidencia cod = null;
		Scanner scanner = new Scanner(System.in);
		boolean ok = false;
		String elcod = null;
		do{
			try {
				try{
					System.out.print("Introduce código de incidencia: ");
					elcod = scanner.nextLine();
				}catch(java.lang.NullPointerException excp){
					System.out.println("ERROR AL AÑADIR CÓDIGO.");
				}
				cod = new CodigoIncidencia(elcod);
				ok = true;
			} catch (InputMismatchException e) {
				System.out.println("CÓDIGO DE FACTURA NO VÁLIDO.");
			}
		}while(!ok);
		return cod;
	}
	
	public static Llamada pedirLlamada(){
		Llamada llamada = null;
		Scanner scanner = new Scanner(System.in);
		boolean ok = false;
		
		int telefono = 0;
		Calendar fecha_llamada;
		int duracion = 0; //en segundos
		
		do{
			try{
				System.out.print("Introduce teléfono: ");
				telefono = scanner.nextInt();
			}catch(InputMismatchException e){
				System.out.println("TELÉFONO NO VÁLIDO.");
			}
			ok = true;
		}while(!ok);
		
		fecha_llamada = pedirFechaConHora();
		
		boolean ok2 = false;
		do{
			try{
				System.out.print("Introduce duración (en segundos): ");
				duracion = scanner.nextInt(); 
			}catch(InputMismatchException e){
				System.out.println("TELÉFONO NO VÁLIDO.");
			}
			ok2 = true;
		}while(!ok2);
		llamada = new Llamada(telefono, fecha_llamada, duracion);
		return llamada;
	}
	
	public static Calendar pedirFechaConHora() {
		Calendar fecha = new GregorianCalendar(); 
		int dia = 0; int mes = 0; int anyo = 0; int hora = 0; int minutos = 0;
		Scanner scanner = new Scanner(System.in);
		boolean ok = false;
		do{
			try {
				System.out.print("Introduce día: ");
				int eldia = scanner.nextInt();
				dia = eldia;
				System.out.print("Introduce mes: ");
				int elmes = scanner.nextInt();
				mes = elmes;
				System.out.print("Introduce año: ");
				int elanyo = scanner.nextInt();
				anyo = elanyo;
				System.out.print("Introduce hora: ");
				int lahora = scanner.nextInt();
				hora = lahora;
				System.out.print("Introduce minuto: ");
				int elminuto = scanner.nextInt();
				minutos = elminuto;
				ok = true;
			} catch (InputMismatchException e) {
				System.out.println("FECHA NO VÁLIDA.");
			}
		}while(!ok);
		fecha.set(anyo, mes-1, dia, hora, minutos);
		if(fecha.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
			System.out.println("ES UNA FECHA DE DOMINGO");
		}else{
			System.out.println("NO ES UNA FECHA DE DOMINGO");
		}
		return fecha;
	}

	public static HashMap<CodigoLlamada, Llamada> pedirLlamadas() {
		HashMap<CodigoLlamada, Llamada> llamadas = new HashMap<CodigoLlamada, Llamada>();
		boolean parar = false;
		System.out.println("-Introducir llamadas para emitir factura-");
		int contador = 1;
		Scanner scanner = new Scanner(System.in);
		while(!parar){
			System.out.println("::Llamada nº"+contador+"::");
			Llamada llamada = pedirLlamada();
			CodigoLlamada cod = new CodigoLlamada();
			llamadas.put(cod.crearCodigoLlamada(), llamada);
			System.out.print("¿Introducir más llamadas? (Sí = s, No = cualquier otra tecla): ");
			String op = scanner.next();
			if(!op.equals("s") || !op.equals("S")){
				parar = true;
			}
			contador++;
		}
		return llamadas;
	}
}
