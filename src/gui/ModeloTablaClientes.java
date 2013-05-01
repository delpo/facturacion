package gui;

import java.util.Map.Entry;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import facturacion.Cliente;
import facturacion.NIF;

public class ModeloTablaClientes extends AbstractTableModel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String columnNames[] = {"Nombre", "Apellidos", "NIF", "Población", "Código postal", "Provincia", "Email", "Tarifa"};
	private Vector<Entry<NIF, Cliente>> data;
	
	public ModeloTablaClientes(){
		data = new Vector<Entry<NIF, Cliente>>();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return data.size();
	}

	@Override
	public Object getValueAt(int row, int column) {
		Entry<NIF, Cliente> cliente = (Entry<NIF, Cliente>) data.get(row); 
		if(column == 0){
			return cliente.getValue().getNombre();
		}else if(column == 1){
			return cliente.getValue().getApellidos();
		}else if(column == 2){
			return cliente.getKey().toString();
		}else if(column == 3){
			return cliente.getValue().getDireccion().getPoblacion().toString();
		}else if(column == 4){
			return cliente.getValue().getDireccion().getCodigo_postal();
		}else if(column == 5){
			return cliente.getValue().getDireccion().getProvincia().toString();
		}else if(column == 6){
			return cliente.getValue().getEmail().toString();
		}else if(column == 7){
			return cliente.getValue().getTarifa().getNombre().toString();
		}else{
			return new Object();
		}
	}
	
	@Override
	public String getColumnName(int column){
		return columnNames[column];
	}

}
