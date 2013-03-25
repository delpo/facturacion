package terminal;

import java.util.Scanner;

public class MenuTerminal {
	public enum OpcionesMenu{
		//opciones
		//SALIR
		OPCION0("Salir del programa."),
		//OPERACIONES CON CLIENTES
		OPCION1("Añadir cliente."),
		OPCION2("Borrar cliente."),
		OPCION3("Cambiar tarifa a un cliente."),
		OPCION4("Obtener datos de cliente sabiendo su NIF."),
		OPCION5("Mostrar listado de todos los clientes."),
		OPCION6("Mostrar clientes con el código postal a introducir."),
		//OPERACIONES CON FACTURAS
		OPCION7("Emitir factura para un cliente."),
		OPCION8("Obtener datos de factura sabiendo su código."),
		OPCION9("Borrar factura sabiendo su código."),
		OPCION10("Mostrar todas las facturas de un cliente."),
		//OPERACIONES CON INCIDENCIAS
		OPCION11("Dar de alta una incidencia de un cliente."),
		OPCION12("Recuperar todas las incidencias de un cliente."),
		OPCION13("Borrar una incidencia de un cliente.");
		
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
		}while(opcion>13 || opcion<0);
		OpcionesMenu opcionMenu = OpcionesMenu.getOpcion(opcion);
		return opcionMenu;
	}
}
