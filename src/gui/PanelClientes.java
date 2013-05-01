package gui;

import javax.swing.JPanel;
import javax.swing.JTable;

import facturacion.Operador;

public class PanelClientes extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Operador op;
	private JTable tabla;
	
	public PanelClientes(Operador op){
		this.op = op;
		tabla = new JTable();
	}

}
