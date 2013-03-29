package terminal;

import facturacion.*;
import terminal.ManejoInputs;
import terminal.MenuTerminal;
//import es.uji.www.GeneradorDatosINE;


public class probador {
	public static void main(String[] args) {
		System.out.println("Bienvenido al programa de gestión de clientela de Vodafone.");
		Operador_telefonia vodafone = new Operador_telefonia();
		//DATOS PARA PROBAR
		Cliente cliente_test = null;
		try {
			cliente_test = new Particular("Ángel Carlos", "del Pozo Muela", new NIF("20905219J"),
					new Direccion(12005, "Castellón de la Plana", "Castellón"), new Email("carlosdc360@gmail.com"), new Tarifa_manana());
		} catch (ExcepcionNIFnoValido e) {
			e.printStackTrace();
		} catch (ExcepcionEmailNoValido e) {
			e.printStackTrace();
		}
		vodafone.darAlta(cliente_test);
		try {
			vodafone.emitirFactura(new NIF("20905219J"), new Factura(new Fecha(27, 3, 2013), 3000, new Tarifa_manana(), new Periodo_facturacion(new Fecha(1,1,2013), new Fecha(1,3,2013))));
		} catch (ExcepcionNIFnoValido e) {
			e.printStackTrace();
		}
		//FIN DE DATOS PARA PROBAR
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
			}
			if(!salir){
				vodafone.hashCode();
				opcionMenu = MenuTerminal.Menu();
			}
		}while(!salir);
		System.out.println("¡Hasta luego!");

	}

}
