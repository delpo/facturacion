package terminal;

import java.io.Serializable;
import java.util.Scanner;

import facturacion.CodigoFactura;
import facturacion.ExcepcionFacturaNoEncontrada;
import facturacion.NIF;

public class MenuFacturas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum OpcionesMenu{
		//opciones
		//SALIR
		OPCION0("Volver al menú principal."),
		//OPERACIONES CON FACTURAS
		OPCION1("Emitir factura para un cliente."),
		OPCION2("Obtener datos de factura sabiendo su código."),
		OPCION3("Borrar factura sabiendo su código."),
		OPCION4("Mostrar todas las facturas de un cliente."),
		OPCION5("Mostrar facturas de un cliente emitidas entre dos fechas"),
		OPCION6("Mostrar facturas emitidas para un mismo código postal"),
		OPCION7("Mostrar facturas emitidas entre dos fechas"),
		OPCION8("Mostrar facturas emitidas entre dos fechas y para el mismo código postal");

		private String descripcion;

		private OpcionesMenu(String descripcion) {
			this.descripcion = descripcion;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public static String getMenu() {
			StringBuilder sb = new StringBuilder();
			System.out.println("\n");
			for(OpcionesMenu opcion: OpcionesMenu.values()) {
				sb.append(opcion.ordinal());
				sb.append(".- ");
				sb.append(opcion.getDescripcion());
				sb.append("\n");
			}
			return sb.toString();
		}

		public static OpcionesMenu getOpcion(int posicion) {
			return values()[posicion];
		}
	}

	public static OpcionesMenu Menu(){
		System.out.println(OpcionesMenu.getMenu());
		Scanner scanner = new Scanner(System.in);
		byte opcion = 99;
		do{
			System.out.print("\nElije una opción:");
			opcion = scanner.nextByte();
		}while(opcion>4 || opcion<0);
		OpcionesMenu opcionMenu = OpcionesMenu.getOpcion(opcion);
		return opcionMenu;
	}

	public MenuFacturas(){
		super();
	}

	public void actuar(facturacion.Operador_telefonia operador, OpcionesMenu select){
		boolean salir = false;
		do{
			switch(select) {
			case OPCION0:
				salir = true;
				break;
			case OPCION1:
				//Emitir factura de un cliente
				NIF nif = ManejoInputs.pedirNIF();
				operador.emitirFactura(nif, ManejoInputs.pedirFactura(nif, operador));
				System.out.println("Tarea completada.");
				break;
			case OPCION2:
				//Mostrar factura de cliente
				CodigoFactura codigo = ManejoInputs.pedirCodigoFactura();
				try {
					operador.obtenerFactura(codigo).mostrarenTerminal();
				} catch (ExcepcionFacturaNoEncontrada e) {
					System.out.println("No se encontró factura. No se pudo efectuar operación.");
				}
				System.out.println("Tarea completada.");
				break;
			case OPCION3:
				//Borrar factura
				try {
					operador.borrarFactura(ManejoInputs.pedirCodigoFactura());
				} catch (ExcepcionFacturaNoEncontrada e) {
					System.out.println("No se encontró factura. No se pudo efectuar operación.");
				}
				System.out.println("Tarea completada.");
				break;
			case OPCION4:
				//Mostrar todas las facturas de un cliente
				operador.listarFacturasCliente(ManejoInputs.pedirNIF());
				System.out.println("Tarea completada.");
				break;
			case OPCION5:
				//Mostrar facturas de un cliente emitidas entre dos fechas
				operador.mostrarFacturasentreDosFechas(ManejoInputs.pedirNIF());
				System.out.println("Tarea completada.");
				break;
			case OPCION6:
				//Mostrar facturas emitidas para un mismo código postal
				operador.mostrarFacturasparaCP(ManejoInputs.pedirCP());
				System.out.println("Tarea completada.");
				break;
			case OPCION7:
				//Mostrar facturas emitidas entre dos fechas
				operador.mostrarFacturasentreDosFechas();
				System.out.println("Tarea completada.");
				break;
			case OPCION8:
				//Mostrar facturas emitidas entre dos fechas y para el mismo código postal
				operador.mostrarFacturasparaCPentreDosFechas(ManejoInputs.pedirCP());
				break;
			}
			if(!salir){
				operador.hashCode();
				select = Menu();
			}
		}while(!salir);
	}
	
}
