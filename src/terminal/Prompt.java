package terminal;

import java.io.Serializable;

import facturacion.Operador_telefonia;

public class Prompt implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Prompt(){
	}

	public void iniciarPrompt(){
		Operador_telefonia operador = new Operador_telefonia();
		System.out.println("Bienvenido al programa de gestión de clientela de operador.");
		Datos dato = new Datos();
		operador = dato.recuperarDatos();
		MenuSecciones.OpcionesMenu opcionMenu = MenuSecciones.Menu();
		boolean salir = false;
		do{
			switch(opcionMenu) {
			case OPCION0:
				salir = true;
				break;
			case OPCION1:
				//CLIENTES
				MenuClientes.OpcionesMenu opcionC = MenuClientes.Menu();
				MenuClientes cl = new MenuClientes();
				cl.actuar(operador, opcionC);
				break;
			case OPCION2:
				//FACTURAS
				MenuFacturas.OpcionesMenu opcionF = MenuFacturas.Menu();
				MenuFacturas fac = new MenuFacturas();
				fac.actuar(operador, opcionF);
				break;
			case OPCION3:
				//INCIDENCIAS
				MenuIncidencias.OpcionesMenu opcionI = MenuIncidencias.Menu();
				MenuIncidencias inc = new MenuIncidencias();
				inc.actuar(operador, opcionI);
				break;
			}
			if(!salir){
				operador.hashCode();
				opcionMenu = MenuSecciones.Menu();
			}
		}while(!salir);
		dato.almacenarDatos(operador);
		System.out.println("¡Hasta luego!");
	}
}