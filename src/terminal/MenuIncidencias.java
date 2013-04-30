package terminal;

import java.io.Serializable;
import java.util.Scanner;

import facturacion.Operador;

public class MenuIncidencias implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum OpcionesMenu{
		//opciones
		//SALIR
		OPCION0("Salir del programa."),
		//OPERACIONES CON INCIDENCIAS
		OPCION1("Dar de alta una incidencia de un cliente."),
		OPCION2("Recuperar todas las incidencias de un cliente."),
		OPCION3("Borrar una incidencia de un cliente."),
		OPCION4("Mostrar incidencias de un cliente entre dos fechas"),
		OPCION5("Mostrar incidencias para el mismo código postal"),
		OPCION6("Mostrar incidencias entre dos fechas"),
		OPCION7("Mostrar incidencias para el mismo código postal y entre dos fechas");
		
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
		}while(opcion>7 || opcion<0);
		OpcionesMenu opcionMenu = OpcionesMenu.getOpcion(opcion);
		return opcionMenu;
	}
	
	public MenuIncidencias(){
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
				//Incidencias de un cliente entre dos fechas
				operador.darDeAltaIncidencia(ManejoInputs.pedirNIF(), ManejoInputs.pedirIncidencia());
				System.out.println("Tarea completada.");
				break;
			case OPCION2:
				//Todas las incidencias de un cliente
				operador.listarIncidencias(ManejoInputs.pedirNIF());
				System.out.println("Tarea completada.");
				break;
			case OPCION3:	
				//Borrar una incidencia de un cliente
				operador.borrarIncidencia(ManejoInputs.pedirCodigoIncidencia());
				System.out.println("Tarea completada.");
				break;
			case OPCION4:
				//Mostrar incidencias de un cliente entre dos fechas
				operador.mostrarIncidenciasentreDosFechas(ManejoInputs.pedirNIF());
				break;
			case OPCION5:
				//Mostrar incidencias para el mismo código postal
				operador.mostrarIncidenciasparaCP(ManejoInputs.pedirCP());
				break;
			case OPCION6:
				//Mostrar incidencias entre dos fechas
				operador.mostrarIncidenciasentreDosFechas();
				break;
			case OPCION7:
				//Mostrar incidencias para el mismo código postal y entre dos fechas
				operador.mostrarIncidenciasparaCPentreDosFechas(ManejoInputs.pedirCP());
				break;
			}
			if(!salir){
				operador.hashCode();
				select = Menu();
			}
		}while(!salir);
	}
}
