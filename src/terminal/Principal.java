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
	private static Operador_telefonia vodafone = new Operador_telefonia();

	public static void main(String[] args) {
		System.out.println("Bienvenido al programa de gestión de clientela de Vodafone.");
		Datos.recuperarDatos();
		MenuTerminal.OpcionesMenu opcionMenu = MenuTerminal.Menu();
		boolean salir = false;
		do{
			switch(opcionMenu) {
			case OPCION0:
				salir = true;
				break;
			case OPCION1:
				//añadir cliente
				getVodafone().darAlta(ManejoInputs.pedirCliente());
				System.out.println("Tarea completada.");
				break;
			case OPCION2:
				//borrar cliente
				getVodafone().borrarCliente(ManejoInputs.pedirNIF());
				System.out.println("Tarea completada.");
				break;
			case OPCION3:
				//cambiar tarifa a un cliente
				getVodafone().cambiarTarifa(ManejoInputs.pedirNIF(), ManejoInputs.pedirTarifa());
				System.out.println("Tarea completada.");
				break;
			case OPCION4:
				//Obtener datos de cliente sabiendo su NIF.
				getVodafone().obtenerDatos(ManejoInputs.pedirNIF());
				System.out.println("Tarea completada.");
				break;
			case OPCION5:
				//Mostrar todos los clientes
				getVodafone().listarClientes();
				System.out.println("Tarea completada.");
				break;
			case OPCION6:
				//Mostrar clientes con el código postal a introducir
				getVodafone().listarClientesporCP(ManejoInputs.pedirCP());
				System.out.println("Tarea completada.");
				break;
			case OPCION7:
				//Emitir factura de un cliente
				NIF nif = ManejoInputs.pedirNIF();
				getVodafone().emitirFactura(nif, ManejoInputs.pedirFactura(nif));
				System.out.println("Tarea completada.");
				break;
			case OPCION8:
				//Mostrar factura de cliente
				CodigoFactura codigo = ManejoInputs.pedirCodigoFactura();
				try {
					getVodafone().obtenerFactura(codigo).mostrarenTerminal();
				} catch (ExcepcionFacturaNoEncontrada e) {
					System.out.println("No se encontró factura. No se pudo efectuar operación.");
				}
				System.out.println("Tarea completada.");
				break;
			case OPCION9:
				//Borrar factura
				try {
					getVodafone().borrarFactura(ManejoInputs.pedirCodigoFactura());
				} catch (ExcepcionFacturaNoEncontrada e) {
					System.out.println("No se encontró factura. No se pudo efectuar operación.");
				}
				System.out.println("Tarea completada.");
				break;
			case OPCION10:
				//Mostrar todas las facturas de un cliente
				getVodafone().listarFacturasCliente(ManejoInputs.pedirNIF());
				System.out.println("Tarea completada.");
				break;
			case OPCION11:
				//Incidencias de un cliente entre dos fechas
				getVodafone().darDeAltaIncidencia(ManejoInputs.pedirNIF(), ManejoInputs.pedirIncidencia(), ManejoInputs.pedirFecha());
				System.out.println("Tarea completada.");
				break;
			case OPCION12:
				//Todas las incidencias de un cliente
				getVodafone().listarIncidencias(ManejoInputs.pedirNIF());
				System.out.println("Tarea completada.");
				break;
			case OPCION13:	
				//Borrar una incidencia de un cliente
				getVodafone().borrarIncidencia(ManejoInputs.pedirCodigoIncidencia());
				System.out.println("Tarea completada.");
				break;
			case OPCION14:
				//Mostrar facturas de un cliente emitidas entre dos fechas
				getVodafone().mostrarFacturasentreDosFechas(ManejoInputs.pedirNIF());
				System.out.println("Tarea completada.");
				break;
			case OPCION15:
				//Mostrar facturas emitidas para un mismo código postal
				getVodafone().mostrarFacturasparaCP(ManejoInputs.pedirCP());
				System.out.println("Tarea completada.");
				break;
			case OPCION16:
				//Mostrar facturas emitidas entre dos fechas
				getVodafone().mostrarFacturasentreDosFechas();
				System.out.println("Tarea completada.");
				break;
			case OPCION17:
				//Mostrar facturas emitidas entre dos fechas y para el mismo código postal
				getVodafone().mostrarFacturasparaCPentreDosFechas(ManejoInputs.pedirCP());
				break;
			case OPCION18:
				//Mostrar incidencias de un cliente entre dos fechas
				getVodafone().mostrarIncidenciasentreDosFechas(ManejoInputs.pedirNIF());
				break;
			case OPCION19:
				//Mostrar incidencias para el mismo código postal
				getVodafone().mostrarIncidenciasparaCP(ManejoInputs.pedirCP());
				break;
			case OPCION20:
				//Mostrar incidencias entre dos fechas
				getVodafone().mostrarIncidenciasentreDosFechas();
				break;
			case OPCION21:
				//Mostrar incidencias para el mismo código postal y entre dos fechas
				getVodafone().mostrarIncidenciasparaCPentreDosFechas(ManejoInputs.pedirCP());
				break;
			}
			Datos.almacenarDatos();
			if(!salir){
				getVodafone().hashCode();
				opcionMenu = MenuTerminal.Menu();
			}
		}while(!salir);
		System.out.println("¡Hasta luego!");

	}

	public static Operador_telefonia getVodafone() {
		return vodafone;
	}

	public static void setVodafone(Operador_telefonia vodafone) {
		Principal.vodafone = vodafone;
	}

}
