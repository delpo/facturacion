package terminal;

import facturacion.CodigoFactura;
import facturacion.ExcepcionFacturaNoEncontrada;
import facturacion.NIF;
import facturacion.Operador_telefonia;

public class Prompt {
		
	public Prompt(){
	}
	
	public static void iniciarPrompt(){
		Operador_telefonia operador = new Operador_telefonia();
		System.out.println("Bienvenido al programa de gestión de clientela de operador.");
		Datos dato = new Datos();
		dato.recuperarDatos(operador);
		MenuTerminal.OpcionesMenu opcionMenu = MenuTerminal.Menu();
		boolean salir = false;
		do{
			switch(opcionMenu) {
			case OPCION0:
				salir = true;
				break;
			case OPCION1:
				//añadir cliente
				operador.darAlta(ManejoInputs.pedirCliente());
				System.out.println("Tarea completada.");
				break;
			case OPCION2:
				//borrar cliente
				operador.borrarCliente(ManejoInputs.pedirNIF());
				System.out.println("Tarea completada.");
				break;
			case OPCION3:
				//cambiar tarifa a un cliente
				operador.cambiarTarifa(ManejoInputs.pedirNIF(), ManejoInputs.pedirTarifa());
				System.out.println("Tarea completada.");
				break;
			case OPCION4:
				//Obtener datos de cliente sabiendo su NIF.
				operador.obtenerDatos(ManejoInputs.pedirNIF());
				System.out.println("Tarea completada.");
				break;
			case OPCION5:
				//Mostrar todos los clientes
				operador.listarClientes();
				System.out.println("Tarea completada.");
				break;
			case OPCION6:
				//Mostrar clientes con el código postal a introducir
				operador.listarClientesporCP(ManejoInputs.pedirCP());
				System.out.println("Tarea completada.");
				break;
			case OPCION7:
				//Emitir factura de un cliente
				NIF nif = ManejoInputs.pedirNIF();
				operador.emitirFactura(nif, ManejoInputs.pedirFactura(nif, operador));
				System.out.println("Tarea completada.");
				break;
			case OPCION8:
				//Mostrar factura de cliente
				CodigoFactura codigo = ManejoInputs.pedirCodigoFactura();
				try {
					operador.obtenerFactura(codigo).mostrarenTerminal();
				} catch (ExcepcionFacturaNoEncontrada e) {
					System.out.println("No se encontró factura. No se pudo efectuar operación.");
				}
				System.out.println("Tarea completada.");
				break;
			case OPCION9:
				//Borrar factura
				try {
					operador.borrarFactura(ManejoInputs.pedirCodigoFactura());
				} catch (ExcepcionFacturaNoEncontrada e) {
					System.out.println("No se encontró factura. No se pudo efectuar operación.");
				}
				System.out.println("Tarea completada.");
				break;
			case OPCION10:
				//Mostrar todas las facturas de un cliente
				operador.listarFacturasCliente(ManejoInputs.pedirNIF());
				System.out.println("Tarea completada.");
				break;
			case OPCION11:
				//Incidencias de un cliente entre dos fechas
				operador.darDeAltaIncidencia(ManejoInputs.pedirNIF(), ManejoInputs.pedirIncidencia(), ManejoInputs.pedirFecha());
				System.out.println("Tarea completada.");
				break;
			case OPCION12:
				//Todas las incidencias de un cliente
				operador.listarIncidencias(ManejoInputs.pedirNIF());
				System.out.println("Tarea completada.");
				break;
			case OPCION13:	
				//Borrar una incidencia de un cliente
				operador.borrarIncidencia(ManejoInputs.pedirCodigoIncidencia());
				System.out.println("Tarea completada.");
				break;
			case OPCION14:
				//Mostrar facturas de un cliente emitidas entre dos fechas
				operador.mostrarFacturasentreDosFechas(ManejoInputs.pedirNIF());
				System.out.println("Tarea completada.");
				break;
			case OPCION15:
				//Mostrar facturas emitidas para un mismo código postal
				operador.mostrarFacturasparaCP(ManejoInputs.pedirCP());
				System.out.println("Tarea completada.");
				break;
			case OPCION16:
				//Mostrar facturas emitidas entre dos fechas
				operador.mostrarFacturasentreDosFechas();
				System.out.println("Tarea completada.");
				break;
			case OPCION17:
				//Mostrar facturas emitidas entre dos fechas y para el mismo código postal
				operador.mostrarFacturasparaCPentreDosFechas(ManejoInputs.pedirCP());
				break;
			case OPCION18:
				//Mostrar incidencias de un cliente entre dos fechas
				operador.mostrarIncidenciasentreDosFechas(ManejoInputs.pedirNIF());
				break;
			case OPCION19:
				//Mostrar incidencias para el mismo código postal
				operador.mostrarIncidenciasparaCP(ManejoInputs.pedirCP());
				break;
			case OPCION20:
				//Mostrar incidencias entre dos fechas
				operador.mostrarIncidenciasentreDosFechas();
				break;
			case OPCION21:
				//Mostrar incidencias para el mismo código postal y entre dos fechas
				operador.mostrarIncidenciasparaCPentreDosFechas(ManejoInputs.pedirCP());
				break;
			}
			dato.almacenarDatos(operador);
			if(!salir){
				operador.hashCode();
				opcionMenu = MenuTerminal.Menu();
			}
		}while(!salir);
		System.out.println("¡Hasta luego!");
	}
}
