package terminal;

import java.io.Serializable;

import facturacion.Operador;
import facturacion.Operador_telefonia;
import gui.Vista;
import gui.VistaImpl;

public class GraphicUserInterface implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public GraphicUserInterface(){
	}
	
	public void iniciarGUI(){
		//Inicializo datos
		Operador operador = new Operador_telefonia();
		Datos dato = new Datos();
		operador = dato.recuperarDatos();
		//Empiezo a hacer cosas
		Vista vista = new VistaImpl(operador);
		vista.crearGUI();
	}

}
