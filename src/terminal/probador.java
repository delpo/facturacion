package terminal;

import facturacion.*;
import terminal.ManejoInputs;
import terminal.MenuTerminal;
//import es.uji.www.GeneradorDatosINE;


public class probador {
	public static void main(String[] args) {
		System.out.println("Bienvenido al programa de gestión de clientela de Vodafone.");
		Operador_telefonia vodafone = new Operador_telefonia();
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
				case OPCION8:
				case OPCION9:
				case OPCION10:
				case OPCION11:
				case OPCION12:
				case OPCION13:	
			}
			if(!salir){
				vodafone.hashCode();
				opcionMenu = MenuTerminal.Menu();
			}
		}while(!salir);
		System.out.println("¡Hasta luego!");

	}

}
