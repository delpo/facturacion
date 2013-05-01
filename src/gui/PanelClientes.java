package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Map.Entry;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import facturacion.Cliente;
import facturacion.NIF;
import facturacion.Operador;

public class PanelClientes extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private Operador op;
	private JTable tabla;
	private ModeloTablaClientes datos;

	
	public PanelClientes(Operador op){
		this.op = op;
		tabla = new JTable();
		datos = new ModeloTablaClientes();
		tabla = new JTable(datos);
		llenarTabla(tabla, op);
		tabla.setVisible(true);
		tabla.setPreferredScrollableViewportSize(new Dimension(990, 480));
		JScrollPane scrollPane = new JScrollPane(tabla);
		add(scrollPane, BorderLayout.CENTER);
	}

	private void llenarTabla(JTable tabla, Operador op) {
		int fila = 0;
		//"Nombre", "Apellidos", "NIF", "Dirección", "Email", "Tarifa"
		for(Entry<NIF, Cliente> cliente : op.getClientes().entrySet()){
			datos.setValueAt(cliente.getValue().getNombre(), fila, 0);
			datos.setValueAt(cliente.getValue().getApellidos(), fila, 1);
			datos.setValueAt(cliente.getKey().toString(), fila, 2);
			datos.setValueAt(cliente.getValue().getDireccion().toString(), fila, 3);
			datos.setValueAt(cliente.getValue().getEmail().toString(), fila, 4);
			datos.setValueAt(cliente.getValue().getTarifa().getNombre(), fila, 5);
			fila++;
		}
	}

}
