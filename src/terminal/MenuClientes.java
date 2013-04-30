package terminal;

import java.io.Serializable;
import java.util.Scanner;

import facturacion.Operador;

public class MenuClientes implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum OpcionesMenu{
		//opciones
		//SALIR
		OPCION0("Volver al menú principal."),
		//OPERACIONES CON CLIENTES
		OPCION1("Añadir cliente."),
		OPCION2("Borrar cliente."),
		OPCION3("Añadir tarifa a un cliente."),
		OPCION4("Obtener datos de cliente sabiendo su NIF."),
		OPCION5("Mostrar listado de todos los clientes."),
		OPCION6("Mostrar clientes con el código postal a introducir.");
		
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
		}while(opcion>6 || opcion<0);
		OpcionesMenu opcionMenu = OpcionesMenu.getOpcion(opcion);
		return opcionMenu;
	}
	
	public MenuClientes(){
		super();
	}
	
	public void actuar(Operador operador, OpcionesMenu select){
		boolean salir = false;
		do{
			switch(select) {
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
				operador.anyadirTarifa(ManejoInputs.pedirNIF(), ManejoInputs.pedirTarifa());
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
			}
			if(!salir){
				operador.hashCode();
				select = Menu();
			}
		}while(!salir);
	}
}
