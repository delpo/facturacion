package gui;

import javax.swing.table.AbstractTableModel;

public class ModeloTablaClientes extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final String nombreColumnas[] = {"Nombre", "Apellidos", "NIF", "Dirección", "Email", "Tarifa"};
	private Object datos[][];
	
	public ModeloTablaClientes(){
		super();
	}

	@Override
	public int getColumnCount() {
		return nombreColumnas.length;
	}

	@Override
	public int getRowCount() {
		return datos.length;
	}

	@Override
	public Object getValueAt(int row, int column) {
		return datos[row][column];
	}
	
	@Override
	public String getColumnName(int column){
		return nombreColumnas[column];
	}

}
