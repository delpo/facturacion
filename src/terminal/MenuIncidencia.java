package terminal;

import java.io.Serializable;
import java.util.Scanner;

public class MenuIncidencia implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum OpcionesIncidencia {
		//TARIFAS
		OPCION0("Solicitud de portabilidad"),
		OPCION1("Avería"),
		OPCION2("Impago");
		
		private String descripcion;

		private OpcionesIncidencia(String descripcion) {
			this.descripcion = descripcion;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public static String getMenu() {
			System.out.println("\n-Elija entre las siguientes incidencias-");
			StringBuilder sb = new StringBuilder();
			for(OpcionesIncidencia opcion: OpcionesIncidencia.values()) {
				sb.append(opcion.ordinal());
				sb.append(".- ");
				sb.append(opcion.getDescripcion());
				sb.append("\n");
			}
			return sb.toString();
		}

		public static OpcionesIncidencia getOpcion(int posicion) {
			return values()[posicion];
		}
	}

	public static OpcionesIncidencia Menu(){
		System.out.println(OpcionesIncidencia.getMenu());
		Scanner scanner = new Scanner(System.in);
		byte opcion = 99;
		do{
			System.out.print("\nElije una opción:");
			opcion = scanner.nextByte();
		}while(opcion>2 || opcion<0);
		OpcionesIncidencia opcionTarifa = OpcionesIncidencia.getOpcion(opcion);
		return opcionTarifa;
	}
}
