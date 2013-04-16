package terminal;

import java.io.Serializable;
import java.util.Scanner;

public class MenuTarifa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum OpcionesTarifa {
		//TARIFAS
		OPCION0("Tarifa de tarde"),
		OPCION1("Tarifa de domingo");

		private String descripcion;

		private OpcionesTarifa(String descripcion) {
			this.descripcion = descripcion;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public static String getMenu() {
			System.out.println("Además de la tarifa básica se pueden acoplar otras tarifas.");
			System.out.println("\n-Elija entre las siguientes tarifas-");
			StringBuilder sb = new StringBuilder();
			for(OpcionesTarifa opcion: OpcionesTarifa.values()) {
				sb.append(opcion.ordinal());
				sb.append(".- ");
				sb.append(opcion.getDescripcion());
				sb.append("\n");
			}
			return sb.toString();
		}

		public static OpcionesTarifa getOpcion(int posicion) {
			return values()[posicion];
		}
	}

	public static OpcionesTarifa Menu(){
		System.out.println(OpcionesTarifa.getMenu());
		Scanner scanner = new Scanner(System.in);
		byte opcion = 99;
		do{
			System.out.print("\nElije una opción:");
			opcion = scanner.nextByte();
		}while(opcion>3 || opcion<0);
		OpcionesTarifa opcionTarifa = OpcionesTarifa.getOpcion(opcion);
		return opcionTarifa;
	}
}
