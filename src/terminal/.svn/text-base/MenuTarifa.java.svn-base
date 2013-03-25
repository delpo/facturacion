package terminal;

import java.util.Scanner;

public class MenuTarifa {
	public enum OpcionesTarifa {
		//TARIFAS
		OPCION0("Tarifa de mañana"),
		OPCION1("Tarifa de tarde"),
		OPCION2("Tarifa reducida"),
		OPCION3("Tarifa superreducida");

		private String descripcion;

		private OpcionesTarifa(String descripcion) {
			this.descripcion = descripcion;
		}

		public String getDescripcion() {
			return descripcion;
		}

		public static String getMenu() {
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
		}while(opcion>4 || opcion<0);
		OpcionesTarifa opcionTarifa = OpcionesTarifa.getOpcion(opcion);
		return opcionTarifa;
	}
}
