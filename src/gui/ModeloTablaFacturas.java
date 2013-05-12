package gui;

import java.util.Map.Entry;

import facturacion.Cliente;
import facturacion.CodigoFactura;
import facturacion.Factura;
import facturacion.NIF;
import facturacion.Operador;

public class ModeloTablaFacturas {
private String columnNames[] = {"Código de factura", "Nombre", "Apellidos", "NIF", "Tarifa", "Periodo de factuación", "Importe"};

	
	public ModeloTablaFacturas(){
	}
	
	public String[] getColumnNames(){
		return columnNames;
	}

	public Object[][] llenarTabla(Operador op) {
		int fila = 0;
		Object[][] rowData;
		//"Código de factura", "Nombre", "Apellidos", "NIF", "Tarifa", "Periodo de factuación", "Importe"
		rowData = new Object[ op.getClientes().entrySet().size()][columnNames.length];
			for(Entry<NIF, Cliente> cliente : op.getClientes().entrySet()){
				for(Entry<CodigoFactura, Factura> factura : cliente.getValue().getFacturas().entrySet()){
					rowData[fila][0] = factura.getKey().getCodigo();
					rowData[fila][1] = cliente.getValue().getNombre();
					rowData[fila][2] = cliente.getValue().getApellidos();
					rowData[fila][3] = cliente.getKey().toString();
					rowData[fila][4] = cliente.getValue().getTarifa().getNombre();
					rowData[fila][5] = factura.getValue().getPeriodoFacturacionTexto();
					rowData[fila][6] = factura.getValue().getImporte();
					fila++;
				}
		}
		return rowData;
	}
}
