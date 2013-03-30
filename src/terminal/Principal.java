package terminal;

import java.io.Serializable;

import facturacion.*;
import terminal.ManejoInputs;
import terminal.MenuTerminal;
//import es.uji.www.GeneradorDatosINE;

public class Principal implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		System.out.println("Bienvenido al programa de gestión de clientela de Vodafone.");
		Operador_telefonia vodafone = new Operador_telefonia();
		vodafone = Datos.recuperarDatos();
		MenuTerminal.OpcionesMenu opcionMenu = MenuTerminal.Menu();
		boolean salir = false;
		do{
			switch(opcionMenu) {
			case OPCION0:
				salir = true;
				break;
			case OPCION1:
				//añadir cliente
				vodafone.darAlta(ManejoInputs.pedirCliente());
				System.out.println("Tarea completada.");
				break;
			case OPCION2:
				//borrar cliente
				vodafone.borrarCliente(ManejoInputs.pedirNIF());
				System.out.println("Tarea completada.");
				break;
			case OPCION3:
				//cambiar tarifa a un cliente
				vodafone.cambiarTarifa(ManejoInputs.pedirNIF(), ManejoInputs.pedirTarifa());
				System.out.println("Tarea completada.");
				break;
			case OPCION4:
				//Obtener datos de cliente sabiendo su NIF.
				vodafone.obtenerDatos(ManejoInputs.pedirNIF());
				System.out.println("Tarea completada.");
				break;
			case OPCION5:
				//Mostrar todos los clientes
				vodafone.listarClientes();
				System.out.println("Tarea completada.");
				break;
			case OPCION6:
				//Mostrar clientes con el código postal a introducir
				vodafone.listarClientesporCP(ManejoInputs.pedirCP());
				System.out.println("Tarea completada.");
				break;
			case OPCION7:
				//Emitir factura de un cliente
				NIF nif = ManejoInputs.pedirNIF();
				vodafone.emitirFactura(nif, ManejoInputs.pedirFactura(nif));
				System.out.println("Tarea completada.");
				break;
			case OPCION8:
				//Mostrar factura de cliente
				CodigoFactura codigo = ManejoInputs.pedirCodigoFactura();
				try {
					vodafone.obtenerFactura(codigo).mostrarenTerminal();
				} catch (ExcepcionFacturaNoEncontrada e) {
					System.out.println("No se encontró factura. No se pudo efectuar operación.");
				}
				System.out.println("Tarea completada.");
				break;
			case OPCION9:
				//Borrar factura
				try {
					vodafone.borrarFactura(ManejoInputs.pedirCodigoFactura());
				} catch (ExcepcionFacturaNoEncontrada e) {
					System.out.println("No se encontró factura. No se pudo efectuar operación.");
				}
				System.out.println("Tarea completada.");
				break;
			case OPCION10:
				//Mostrar todas las facturas de un cliente
				vodafone.listarFacturasCliente(ManejoInputs.pedirNIF());
				System.out.println("Tarea completada.");
				break;
			case OPCION11:
				//Incidencias de un cliente entre dos fechas
				vodafone.darDeAltaIncidencia(ManejoInputs.pedirNIF(), ManejoInputs.pedirIncidencia(), ManejoInputs.pedirFecha());
				System.out.println("Tarea completada.");
				break;
			case OPCION12:
				//Todas las incidencias de un cliente
				vodafone.listarIncidencias(ManejoInputs.pedirNIF());
				System.out.println("Tarea completada.");
				break;
			case OPCION13:	
				//Borrar una incidencia de un cliente
				vodafone.borrarIncidencia(ManejoInputs.pedirCodigoIncidencia());
				System.out.println("Tarea completada.");
				break;
			case OPCION14:
				//Mostrar facturas de un cliente emitidas entre dos fechas
				vodafone.mostrarFacturasentreDosFechas(ManejoInputs.pedirNIF());
				System.out.println("Tarea completada.");
				break;
			case OPCION15:
				//Mostrar facturas emitidas para un mismo código postal
				vodafone.mostrarFacturasparaCP(ManejoInputs.pedirCP());
				System.out.println("Tarea completada.");
				break;
			case OPCION16:
				//Mostrar facturas emitidas entre dos fechas
				vodafone.mostrarFacturasentreDosFechas();
				System.out.println("Tarea completada.");
				break;
			case OPCION17:
				//Mostrar facturas emitidas entre dos fechas y para el mismo código postal
				vodafone.mostrarFacturasparaCPentreDosFechas(ManejoInputs.pedirCP());
				break;
			case OPCION18:
				//Mostrar incidencias de un cliente entre dos fechas
				vodafone.mostrarIncidenciasentreDosFechas(ManejoInputs.pedirNIF());
				break;
			case OPCION19:
				//Mostrar incidencias para el mismo código postal
				vodafone.mostrarIncidenciasparaCP(ManejoInputs.pedirCP());
				break;
			case OPCION20:
				//Mostrar incidencias entre dos fechas
				vodafone.mostrarIncidenciasentreDosFechas();
				break;
			case OPCION21:
				//Mostrar incidencias para el mismo código postal y entre dos fechas
				vodafone.mostrarIncidenciasparaCPentreDosFechas(ManejoInputs.pedirCP());
				break;
			}
			Datos.almacenarDatos(vodafone);
			if(!salir){
				vodafone.hashCode();
				opcionMenu = MenuTerminal.Menu();
			}
		}while(!salir);
		System.out.println("¡Hasta luego!");

	}

}