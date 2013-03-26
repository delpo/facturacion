package facturacion;

import java.util.HashMap;
import java.util.Map.Entry;

public class Operador_telefonia {

	static HashMap<NIF, Cliente> clientes = new HashMap<NIF, Cliente>();
	
	public Operador_telefonia(){
	}
	
	public void darAlta(Cliente cliente) {
		if(clientes.containsKey(cliente.getNif())){
			System.out.println("El cliente ya existe. No se añadirá.");
		}else{
			clientes.put(cliente.getNif(), cliente);
		}
	}
	public void borrarCliente(NIF nif) {
		for (Entry<NIF, Cliente> entry : clientes.entrySet()) {
			if(entry.getKey().toString().equals(nif.toString()) && clientes.remove(entry.getKey())!=null){
					System.out.println("Borrado completado con éxito.");
			}
		}
	}
	public void cambiarTarifa(NIF nif, Tarifa tarifa) {
		for (Entry<NIF, Cliente> entry : clientes.entrySet()) {
			if(entry.getKey().toString().equals(nif.toString())){
				entry.getValue().setTarifa(tarifa);
			}
		}
	}
	
	public void listaElemento(Entry<NIF, Cliente> entry){
		entry.getValue().listarCliente();
	}
	
	public void listarClientes() {
		System.out.println("Listado de clientes:");
		for (Entry<NIF, Cliente> entry : clientes.entrySet()) {
			listaElemento(entry);
			System.out.println("=============================");
		}
		if(clientes.entrySet().isEmpty()) System.out.println("No hay clientes registrados.");
		
	}
	public void listarClientesporCP(int cp) {
		Boolean sinclientes=true;
		for (Entry<NIF, Cliente> entry : clientes.entrySet()) {
			if(entry.getValue().getDireccion().getCodigo_postal()==cp){
				listaElemento(entry);
				sinclientes=false;
			}
		}
		if(sinclientes) System.out.println("No hay clientes para este código postal.");
	}
	
	public void obtenerDatos(NIF nif) {
		boolean ok = false;
		for (Entry<NIF, Cliente> entry : clientes.entrySet()) {
			if(entry.getKey().toString().equals(nif.toString())){
				ok = true;
				listaElemento(entry);
			}
		}
		if(!ok) System.out.println("Cliente no encontrado.");
	}
	public static boolean claveValida(CodigoFactura clave){
		Boolean valida = true;
		for(NIF nif: clientes.keySet()){
			if(clientes.get(nif).facturas.containsKey(clave.getCodigo())) valida = false;
		}
		return valida;
	}
	
	public Factura obtenerFactura(CodigoFactura codigo_factura){
		boolean encontrado = false;
		for (Entry<NIF, Cliente> cliente : clientes.entrySet()) {
			for(Entry<CodigoFactura, Factura> factura: cliente.getValue().facturas.entrySet()){
				if(factura.getKey().equals(codigo_factura)){
					encontrado = true;
					return factura.getValue();
				}
				if(encontrado) break;
			}
			if(encontrado) break;
		}
		if(!encontrado){
			//lanzar excepción
		}
		return null;
	}
	
	public void emitirFactura(NIF nif, Factura factura){
		boolean ok = false;
		for (Entry<NIF, Cliente> entry : clientes.entrySet()) {
			if(entry.getKey().toString().equals(nif.toString())){
				ok = true;
				entry.getValue().emitirFactura(factura); //emplea el método de la clase Cliente
			}
		}
		if(!ok) System.out.println("Cliente no encontrado.");
	}

	public void borrarFactura(CodigoFactura codigo_factura) {
		boolean encontrado = false;
		for (Entry<NIF, Cliente> cliente : clientes.entrySet()) {
			for(Entry<CodigoFactura, Factura> factura: cliente.getValue().facturas.entrySet()){
				if(factura.getKey().equals(codigo_factura)){
					encontrado = true;
					cliente.getValue().facturas.entrySet().remove(factura);
				}
				if(encontrado) break;
			}
			if(encontrado) break;
		}
		if(!encontrado){
			//lanzar excepción
		}
	}
	
	public void listarFacturasCliente(NIF nif){
		boolean encontrado = false;
		for (Entry<NIF, Cliente> cliente : clientes.entrySet()) {
			for(Entry<CodigoFactura, Factura> factura: cliente.getValue().facturas.entrySet()){
				factura.getValue().mostrarenTerminal();
				System.out.println("=====================");
			}
			if(encontrado) break;
		}
		if(!encontrado){
			//lanzar excepción
		}
	}

}
