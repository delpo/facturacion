package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import facturacion.Operador;

public class PanelFacturas extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Operador op;
	private JTable tabla;
	private DefaultTableModel modelo;
	private Object columnNames[] = {"Código de factura", "Nombre", "Apellidos", "NIF", "Tarifa", "Periodo de factuación", "Importe"};
	private JScrollPane scrollPane;
	
	public PanelFacturas(Operador op){
		this.op = op;
		crearPanel();
	}
	
	public void recargarModelo(DefaultTableModel modelo){
		ModeloTablaFacturas mod = new ModeloTablaFacturas();
		modelo = new DefaultTableModel(mod.llenarTabla(op), columnNames);
	}
	
	public DefaultTableModel getModelo(){
		return modelo;
	}
	
	public void regenerarModelo(){
		remove(scrollPane);
		crearPanel();
	}
	
	public void crearPanel(){
		ModeloTablaFacturas mod = new ModeloTablaFacturas();
		modelo = new DefaultTableModel(mod.llenarTabla(op), columnNames);
		tabla = new JTable(modelo);
		tabla.setVisible(true);
		tabla.setEnabled(false); //no editable
		tabla.setPreferredScrollableViewportSize(new Dimension(990, 480));
		scrollPane = new JScrollPane(tabla);
		setLayout(new BorderLayout());
		add(scrollPane, BorderLayout.CENTER);
	}

}
