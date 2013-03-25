package terminal;

import java.util.InputMismatchException;
import java.util.Scanner;

import terminal.MenuTarifa.OpcionesTarifa;

import facturacion.Cliente;
import facturacion.Direccion;
import facturacion.Email;
import facturacion.Empresa;
import facturacion.ExcepcionEmailNoValido;
import facturacion.ExcepcionNIFnoValido;
import facturacion.NIF;
import facturacion.Particular;
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
}