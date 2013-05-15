package gui;

import java.util.Map.Entry;

import facturacion.Cliente;
import facturacion.NIF;
import facturacion.Operador;

public class ModeloTablaClientes{

	private String columnNames[] = {"Nombre", "Apellidos", "NIF", "Población", "Código postal", "Provincia", "Email", "Tarifa"};

	
	public ModeloTablaClientes(){
	}
	
	public String[] getColumnNames(){
		return columnNames;
	}

	public Object[][] llenarTabla(Operador op) {
		int fila = 0;
		Object[][] rowData;
		//"Nombre", "Apellidos", "NIF", "Dirección", "Email", "Tarifa"
		rowData = new Object[ op.getClientes().entrySet().size()][columnNames.length];
			for(Entry<NIF, Cliente> cliente : op.getClientes().entrySet()){
				rowData[fila][0] = cliente.getValue().getNombre();
				rowData[fila][1] = cliente.getValue().getApellidos();
				rowData[fila][2] = cliente.getKey().toString();
				rowData[fila][3] = cliente.getValue().getDireccion().getPoblacion();
				rowData[fila][4] = cliente.getValue().getDireccion().getCodigo_postal();
				rowData[fila][5] = cliente.getValue().getDireccion().getProvincia();
				rowData[fila][6] = cliente.getValue().getEmail().getEmailenString();
				rowData[fila][7] = cliente.getValue().getTarifa().getNombre();
				fila++;
		}
		return rowData;
	}
	
	public String obtenerDNI(Operador op, int fila){
		int fila_contada = 0;
		for(Entry<NIF, Cliente> cliente : op.getClientes().entrySet()){
			if(fila_contada == fila){
					return cliente.getKey().toString();
				}else{
					fila_contada++;					
				}
		}
		return null;
		
	}
	
	public NIF obtenerDNICompleto(Operador op, int fila){
		int fila_contada = 0;
		for(Entry<NIF, Cliente> cliente : op.getClientes().entrySet()){
			if(fila_contada == fila){
					return cliente.getKey();
				}else{
					fila_contada++;					
				}
		}
		return null;
	}
}
