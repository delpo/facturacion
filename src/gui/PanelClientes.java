package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import facturacion.Operador;

public class PanelClientes extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@SuppressWarnings("unused")
	private Operador op;
	private JTable tabla;
	private DefaultTableModel modelo;
	private Object columnNames[] = {"Nombre", "Apellidos", "NIF", "Población", "Código postal", "Provincia", "Email", "Tarifa"};

	
	public PanelClientes(Operador op){
		this.op = op;
		ModeloTablaClientes mod = new ModeloTablaClientes();
		modelo = new DefaultTableModel(mod.llenarTabla(op), columnNames);
		tabla = new JTable(modelo);
		tabla.setVisible(true);
		tabla.setPreferredScrollableViewportSize(new Dimension(990, 480));
		JScrollPane scrollPane = new JScrollPane(tabla);
		setLayout(new BorderLayout());
		add(scrollPane, BorderLayout.CENTER);
	}

	

}
