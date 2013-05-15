package gui;

import java.util.Map.Entry;

import facturacion.Cliente;
import facturacion.CodigoIncidencia;
import facturacion.Incidencia;
import facturacion.NIF;
import facturacion.Operador;

public class ModeloTablaIncidencias {
	private String columnNames[] = {"Código de incidencia", "Nombre", "Apellidos", "Tipo de incidencia", "Estado"};


	public ModeloTablaIncidencias(){
	}

	public String[] getColumnNames(){
		return columnNames;
	}

	public Object[][] llenarTabla(Operador op) {
		int fila = 0;
		Object[][] rowData;
		//"Código de incidencia", "Nombre", "Apellidos", "NIF", "Tarifa", "Periodo de factuación", "Importe"
		int tamanyo = 0;
		for(Entry<NIF, Cliente> cliente : op.getClientes().entrySet()){
			tamanyo += cliente.getValue().getIncidencias().size();
		}
		rowData = new Object[tamanyo][columnNames.length];

		for(Entry<NIF, Cliente> cliente : op.getClientes().entrySet()){
			for(Entry<CodigoIncidencia, Incidencia> incidencia : cliente.getValue().getIncidencias().entrySet()){
				rowData[fila][0] = incidencia.getKey().getCodigo();
				rowData[fila][1] = cliente.getValue().getNombre();
				rowData[fila][2] = cliente.getValue().getApellidos();
				rowData[fila][3] = incidencia.getValue().getNombreIncidencia();
				rowData[fila][4] = incidencia.getValue().estaResuelta();
				fila++;
			}
		}

		return rowData;
	}
	
	public String obtenerCodigo(Operador op, int fila){
		int fila_contada = 0;
		for(Entry<NIF, Cliente> cliente : op.getClientes().entrySet()){
			for(Entry<CodigoIncidencia, Incidencia> incidencia : cliente.getValue().getIncidencias().entrySet()){
				if(fila_contada == fila){
					return incidencia.getKey().getCodigo();
				}else{
					fila_contada++;					
				}
			}
		}
		return null;
		
	}
	
	public CodigoIncidencia obtenerCodigoCompleto(Operador op, int fila){
		int fila_contada = 0;
		for(Entry<NIF, Cliente> cliente : op.getClientes().entrySet()){
			for(Entry<CodigoIncidencia, Incidencia> incidencia : cliente.getValue().getIncidencias().entrySet()){
				if(fila_contada == fila){
					return incidencia.getKey();
				}else{
					fila_contada++;					
				}
			}
		}
		return null;
		
	}
	
}
