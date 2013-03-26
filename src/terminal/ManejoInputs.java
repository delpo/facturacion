package terminal;

import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

import terminal.MenuTarifa.OpcionesTarifa;

import facturacion.Cliente;
import facturacion.CodigoFactura;
import facturacion.Direccion;
import facturacion.Email;
import facturacion.Empresa;
import facturacion.ExcepcionEmailNoValido;
import facturacion.ExcepcionNIFnoValido;
import facturacion.Factura;
import facturacion.Fecha;
import facturacion.NIF;
import facturacion.Particular;
import facturacion.Periodo_facturacion;
import facturacion.Tarifa;
import facturacion.Tarifa_manana;
import facturacion.Tarifa_reducida;
import facturacion.Tarifa_superreducida;
import facturacion.Tarifa_tarde;

public class ManejoInputs {

	private static Cliente inputParticular(){
		Scanner scanner = new Scanner(System.in);
		Cliente cliente = null;
		String nombre; String apellidos; NIF nif = null; Direccion direccion; Email email = null; Tarifa tarifa = null;
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
		OpcionesTarifa opc = MenuTarifa.Menu();
		switch(opc) {
			case OPCION0:
				tarifa = new Tarifa_manana();
				break;
			case OPCION1:
				tarifa = new Tarifa_tarde();
				break;
			case OPCION2:
				tarifa = new Tarifa_reducida();
				break;
			case OPCION3:
				tarifa = new Tarifa_superreducida();
				break;
		}
		cliente = new Particular(nombre, apellidos, nif, direccion, email, tarifa);
		return cliente;
	}
	
	private static Cliente inputEmpresa(){
		Scanner scanner = new Scanner(System.in);
		Cliente cliente = null;
		String nombre; NIF nif = null; Direccion direccion; Email email = null; Tarifa tarifa = null;
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
		OpcionesTarifa opcionTarifa = MenuTarifa.Menu();
		switch(opcionTarifa) {
			case OPCION0:
				tarifa = new Tarifa_manana();
				break;
			case OPCION1:
				tarifa = new Tarifa_tarde();
				break;
			case OPCION2:
				tarifa = new Tarifa_reducida();
				break;
			case OPCION3:
				tarifa = new Tarifa_superreducida();
				break;
		}
		cliente = new Empresa(nombre, nif, direccion, email, tarifa);
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
	
	public static Tarifa pedirTarifa(){
		Tarifa tarifa = null;
		OpcionesTarifa opcionTarifa = MenuTarifa.Menu();
		switch(opcionTarifa) {
			case OPCION0:
				tarifa = new Tarifa_manana();
				break;
			case OPCION1:
				tarifa = new Tarifa_tarde();
				break;
			case OPCION2:
				tarifa = new Tarifa_reducida();
				break;
			case OPCION3:
				tarifa = new Tarifa_superreducida();
				break;
		}
		return tarifa;
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
	
	@SuppressWarnings("null")
	public static CodigoFactura pedirCodigoFactura() {
		CodigoFactura cod = null;
		Scanner scanner = new Scanner(System.in);
		boolean ok = false;
		do{
			try {
				System.out.print("Introduce código de factura: ");
				String elcod = scanner.nextLine();
				cod.setCodigo(elcod);
				ok = true;
			} catch (InputMismatchException e) {
				System.out.println("CÓDIGO DE FACTURA NO VÁLIDO.");
			}
		}while(!ok);
		return cod;
	}
	
	public static Fecha pedirFecha() {
		Fecha fecha = null;
		int dia = 0, mes = 0, anyo = 0;
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
		fecha = new Fecha(dia, mes, anyo);
		return fecha;
	}
	
	@SuppressWarnings("null")
	public static Factura pedirFactura() {
		Factura factura = null;
		Fecha fecha_emision = new Fecha(Calendar.DAY_OF_MONTH, Calendar.MONTH, Calendar.YEAR);
		
		int segundos = 0;
		Tarifa tarifa = null;
		Periodo_facturacion periodo = null;
		
		Scanner scanner = new Scanner(System.in);
		boolean ok = false;
		do{
			try {
				System.out.print("Introduce segundos: ");
				int seg = scanner.nextInt();
				segundos = seg;
				tarifa = pedirTarifa();
				periodo.setFecha_inicio(pedirFecha());
				periodo.setFecha_fin(pedirFecha());
				ok = true;
			} catch (InputMismatchException e) {
				System.out.println("DATOS DE FACTURA NO VÁLIDOS");
			}
		}while(!ok);
		factura = new Factura(fecha_emision, segundos, tarifa, periodo);
		return factura;
	}
}
