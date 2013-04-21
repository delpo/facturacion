package terminal;

import java.io.Serializable;
import java.util.Scanner;

public class MenuSecciones implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public enum OpcionesMenu{
		//SALIR
		OPCION0("Salir del programa."),
		//OPERACIONES CON CLIENTES
		OPCION1("Manejar clientes."),
		//OPERACIONES CON FACTURAS
		OPCION2("Manejar facturas."),
		//OPERACIONES CON INCIDENCIAS
		OPCION3("Manejar incidencias.");
		
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
		}while(opcion>3 || opcion<0);
		OpcionesMenu opcionMenu = OpcionesMenu.getOpcion(opcion);
		return opcionMenu;
	}

}
