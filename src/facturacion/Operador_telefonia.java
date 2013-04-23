package facturacion;

import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map.Entry;

import terminal.ManejoInputs;

public class Operador_telefonia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	HashMap<NIF, Cliente> clientes = new HashMap<NIF, Cliente>();
		
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

	public void anyadirTarifa(NIF nif, int tarifa) {
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
		if(!clientes.entrySet().isEmpty()){
			System.out.println("Listado de clientes:");
			for (Entry<NIF, Cliente> entry : clientes.entrySet()) {
				listaElemento(entry);
				System.out.println("=============================");
			}
		}else{
			System.out.println("No hay clientes registrados.");
		}
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
	

	public Cliente obtenerCliente(NIF nif) {
		boolean ok = false;
		for (Entry<NIF, Cliente> entry : clientes.entrySet()) {
			if(entry.getKey().toString().equals(nif.toString())){
				ok = true;
				return entry.getValue();
			}
		}
		if(!ok) System.out.println("Cliente no encontrado.");
		return null; //excepción
	}
	

	public boolean claveValida(CodigoFactura clave){
		Boolean valida = true;
		for(NIF nif: clientes.keySet()){
			if(clientes.get(nif).facturas.containsKey(clave.getCodigo())) valida = false;
		}
		return valida;
	}
	

	public Factura obtenerFactura(CodigoFactura codigo_factura) throws ExcepcionFacturaNoEncontrada{
		System.out.println("Código a buscar: "+codigo_factura.getCodigo());
		boolean encontrado = false;
		for (Entry<NIF, Cliente> cliente : clientes.entrySet()) {
			for(Entry<CodigoFactura, Factura> factura: cliente.getValue().facturas.entrySet()){
				if(factura.getKey().getCodigo().equals(codigo_factura.getCodigo())){
					encontrado = true;
					System.out.println("Factura encontrada.");
					return factura.getValue();
				}
				if(encontrado) break;
			}
			if(encontrado) break;
		}
		if(!encontrado){
			//lanzar excepción
			throw new ExcepcionFacturaNoEncontrada();
		}
		return null;
	}
	

	public CodigoFactura emitirFactura(NIF nif, Factura factura){
		boolean ok = false;
		for (Entry<NIF, Cliente> entry : clientes.entrySet()) {
			if(entry.getKey().toString().equals(nif.toString())){
				ok = true;
				return entry.getValue().emitirFactura(factura); //emplea el método de la clase Cliente
			}
		}
		if(!ok) System.out.println("Cliente no encontrado.");
		return null;
	}
	


	public void borrarFactura(CodigoFactura codigo_factura) throws ExcepcionFacturaNoEncontrada {
		boolean encontrado = false;
		for (Entry<NIF, Cliente> cliente : clientes.entrySet()) {
			for(Entry<CodigoFactura, Factura> factura: cliente.getValue().facturas.entrySet()){
				if(factura.getKey().getCodigo().equals(codigo_factura.getCodigo())){
					encontrado = true;
					cliente.getValue().facturas.entrySet().remove(factura);
				}
				if(encontrado) break;
			}
			if(encontrado) break;
		}
		if(!encontrado){
			//lanzar excepción
			throw new ExcepcionFacturaNoEncontrada();
		}
	}
	

	public void listarFacturasCliente(NIF nif){
		boolean encontrado = false;
		for (Entry<NIF, Cliente> cliente : clientes.entrySet()) {
			for(Entry<CodigoFactura, Factura> factura: cliente.getValue().facturas.entrySet()){
				System.out.println("Código de factura: "+factura.getKey().getCodigo());
				factura.getValue().mostrarenTerminal();
				System.out.println("=====================");
			}
			if(encontrado) break;
		}
		if(!encontrado){
			//lanzar excepción
		}
	}
	

	public void darDeAltaIncidencia(NIF nif, Incidencia incidencia){
		boolean ok = false;
		for (Entry<NIF, Cliente> entry : clientes.entrySet()) {
			if(entry.getKey().toString().equals(nif.toString())){
				ok = true;
				entry.getValue().reportarIncidencia(incidencia); //emplea el método de la clase Cliente
			}
		}
		if(!ok) System.out.println("Cliente no encontrado.");
	}


	public void listarIncidencias(NIF nif) {
		boolean ok = false;
		for (Entry<NIF, Cliente> entry : clientes.entrySet()) {
			if(entry.getKey().toString().equals(nif.toString())){
				ok = true;
				mostrarIncidencias(entry.getValue().incidencias);
			}
		}
		if(!ok) System.out.println("Cliente no encontrado.");
	}
	

	public void borrarIncidencia(CodigoIncidencia codigo) {
		boolean ok = false;
		for (Entry<NIF, Cliente> entry : clientes.entrySet()) {
				//recorrer incidencias en entry.getValue().incidencias
				for(Entry<CodigoIncidencia, Incidencia> entry2 : entry.getValue().incidencias.entrySet()){
					if(entry2.getKey().getCodigo().equals(codigo.getCodigo())){
						entry.getValue().incidencias.entrySet().remove(entry2);
						ok = true;
					}
					if(ok) break;
				}
			if(ok) break;
		}
		if(!ok) System.out.println("Cliente no encontrado.");
	}
	
	//Genericidad
	
	//LISTADO ENTRE DOS FECHAS DE INCIDENCIAS O FACTURAS
	public <S,T extends Info> HashMap<S, T> entreDosFechas(HashMap<S, T> listado, Calendar fecha1, Calendar fecha2){
		HashMap<S, T> datos = new HashMap<S, T>();
		//Ordeno fechas
		Calendar fecha_inicio = fecha1;
		Calendar fecha_fin = fecha2;
		ordenarFechas(fecha_inicio, fecha_fin);
		for(Entry<S, T> elemento: listado.entrySet()){
			if((elemento.getValue().getFecha().compareTo(fecha_inicio) >= 0) && (elemento.getValue().getFecha().compareTo(fecha_fin) <= 0)){
				datos.put(elemento.getKey(), elemento.getValue());
			}
		}
		return datos;
	}
	
	public HashMap<CodigoFactura, Factura> facturasEntreDosFechas(Calendar fecha1, Calendar fecha2){
		HashMap<CodigoFactura, Factura> datos = new HashMap<CodigoFactura, Factura>();
		//Ordeno fechas
		Calendar fecha_inicio = fecha1;
		Calendar fecha_fin = fecha2;
		ordenarFechas(fecha_inicio, fecha_fin);
		for (Entry<NIF, Cliente> cliente_listado : clientes.entrySet()) {
			datos.putAll(entreDosFechas(cliente_listado.getValue().facturas, fecha1, fecha2));
		}
		return datos;
	}
	
	public HashMap<CodigoIncidencia, Incidencia> incidenciasEntreDosFechas(Calendar fecha1, Calendar fecha2){
		HashMap<CodigoIncidencia, Incidencia> datos = new HashMap<CodigoIncidencia, Incidencia>();
		//Ordeno fechas
		Calendar fecha_inicio = fecha1;
		Calendar fecha_fin = fecha2;
		ordenarFechas(fecha_inicio, fecha_fin);
		for (Entry<NIF, Cliente> cliente_listado : clientes.entrySet()) {
			datos.putAll(entreDosFechas(cliente_listado.getValue().incidencias, fecha1, fecha2));
		}
		return datos;
	}
	
	public HashMap<CodigoFactura, Factura> facturasEntreDosFechas(NIF nif, Calendar fecha1, Calendar fecha2){
		HashMap<CodigoFactura, Factura> datos = new HashMap<CodigoFactura, Factura>();
		//Ordeno fechas
		Calendar fecha_inicio = fecha1;
		Calendar fecha_fin = fecha2;
		ordenarFechas(fecha_inicio, fecha_fin);
		
		for (Entry<NIF, Cliente> cliente_listado : clientes.entrySet()) {
			if(cliente_listado.getKey().NIF.equals(nif.NIF)){
				datos.putAll(entreDosFechas(cliente_listado.getValue().facturas, fecha1, fecha2));
				break;
			}
		}
		return datos;
	}
	
	public void mostrarFacturasentreDosFechas(){
		System.out.println("Se buscarán facturas entre la fecha 1 y fecha 2 a introducir.");
		System.out.println("-Fecha 1-");
		Calendar fecha1 = ManejoInputs.pedirFecha();
		System.out.println("-Fecha 2-");
		Calendar fecha2 = ManejoInputs.pedirFecha();
		//Obtengo facturas
		HashMap<CodigoFactura, Factura> facturas = new HashMap<CodigoFactura, Factura>();
		facturas = facturasEntreDosFechas(fecha1, fecha2);
		//Muestro facturas
		for(Entry<CodigoFactura, Factura> factura: facturas.entrySet()){
				System.out.println("Código: "+factura.getKey().getCodigo());
				factura.getValue().mostrarenTerminal();
		}	
	}
	
	public void mostrarFacturasentreDosFechas(NIF nif){
		System.out.println("Se buscarán facturas entre la fecha 1 y fecha 2 a introducir.");
		System.out.println("-Fecha 1-");
		Calendar fecha1 = ManejoInputs.pedirFecha();
		System.out.println("-Fecha 2-");
		Calendar fecha2 = ManejoInputs.pedirFecha();
		//Obtengo facturas
		HashMap<CodigoFactura, Factura> facturas = new HashMap<CodigoFactura, Factura>();
		facturas = facturasEntreDosFechas(nif, fecha1, fecha2);
		//Muestro facturas
		for(Entry<CodigoFactura, Factura> factura: facturas.entrySet()){
				System.out.println("Código: "+factura.getKey().getCodigo());
				factura.getValue().mostrarenTerminal();
		}

	}
	
	public HashMap<CodigoIncidencia, Incidencia> incidenciasEntreDosFechas(NIF nif, Calendar fecha1, Calendar fecha2){
		HashMap<CodigoIncidencia, Incidencia> datos = new HashMap<CodigoIncidencia, Incidencia>();
		//Ordeno fechas
		Calendar fecha_inicio = fecha1;
		Calendar fecha_fin = fecha2;
		ordenarFechas(fecha_inicio, fecha_fin);
		
		for (Entry<NIF, Cliente> cliente_listado : clientes.entrySet()) {
			if(cliente_listado.getKey().NIF.equals(nif.NIF)){
				datos.putAll(entreDosFechas(cliente_listado.getValue().incidencias, fecha1, fecha2));
				break;
			}
		}
		return datos;
	}
	
	public void mostrarIncidenciasentreDosFechas(NIF nif){
		System.out.println("Se buscarán incidencias entre la fecha 1 y fecha 2 a introducir.");
		System.out.println("-Fecha 1-");
		Calendar fecha1 = ManejoInputs.pedirFecha();
		System.out.println("-Fecha 2-");
		Calendar fecha2 = ManejoInputs.pedirFecha();
		//Obtengo incidencias
		HashMap<CodigoIncidencia, Incidencia> incidencias = new HashMap<CodigoIncidencia, Incidencia>();
		incidencias = incidenciasEntreDosFechas(nif, fecha1, fecha2);
		//Muestro incidencias
		mostrarIncidencias(incidencias);
		
	}
	
	public void mostrarIncidenciasentreDosFechas(){
		System.out.println("Se buscarán incidencias entre la fecha 1 y fecha 2 a introducir.");
		System.out.println("-Fecha 1-");
		Calendar fecha1 = ManejoInputs.pedirFecha();
		System.out.println("-Fecha 2-");
		Calendar fecha2 = ManejoInputs.pedirFecha();
		//Obtengo incidencias
		HashMap<CodigoIncidencia, Incidencia> incidencias = new HashMap<CodigoIncidencia, Incidencia>();
		incidencias = incidenciasEntreDosFechas(fecha1, fecha2);
		//Muestro incidencias
		mostrarIncidencias(incidencias);

	}
	
	public <S,T extends Info> HashMap<S, T> listar(HashMap<S, T> listado){
		HashMap<S, T> datos = new HashMap<S, T>();
		for(Entry<S, T> elemento: listado.entrySet()){
			datos.put(elemento.getKey(), elemento.getValue());
		}
		return datos;
	}
	
	private HashMap<CodigoFactura, Factura> facturasPorCP(int cp) {
		HashMap<CodigoFactura, Factura> datos = new HashMap<CodigoFactura, Factura>();
		for (Entry<NIF, Cliente> cliente_listado : clientes.entrySet()) {
			if(cliente_listado.getValue().getDireccion().getCodigo_postal() == cp){
				datos.putAll(listar(cliente_listado.getValue().facturas));
			}
			break;
		}
		return datos;
	}
	
	public void mostrarFacturasparaCP(int cp){
		//Obtengo facturas
		HashMap<CodigoFactura, Factura> facturas = new HashMap<CodigoFactura, Factura>();
		facturas = facturasPorCP(cp);
		//Muestro facturas
		for(Entry<CodigoFactura, Factura> factura: facturas.entrySet()){
				System.out.println("Código: "+factura.getKey().getCodigo());
				factura.getValue().mostrarenTerminal();
		}
	}
	
	public void mostrarIncidenciasparaCP(int cp){
		//Obtengo incidencias
		HashMap<CodigoIncidencia, Incidencia> incidencias = new HashMap<CodigoIncidencia, Incidencia>();
		incidencias = incidenciasPorCP(cp);
		//Muestro incidencias
		mostrarIncidencias(incidencias);
		
	}

	public HashMap<CodigoIncidencia, Incidencia> incidenciasPorCP(int cp) {
		HashMap<CodigoIncidencia, Incidencia> incidencias = new HashMap<CodigoIncidencia, Incidencia>();
		for (Entry<NIF, Cliente> cliente_listado : clientes.entrySet()) {
			if(cliente_listado.getValue().getDireccion().getCodigo_postal() == cp){
				incidencias.putAll(listar(cliente_listado.getValue().incidencias));
			}
			break;
		}
		return incidencias;
	}
	
	public HashMap<CodigoIncidencia, Incidencia> incidenciasPorCP(int cp, Calendar fecha1, Calendar fecha2) {
		HashMap<CodigoIncidencia, Incidencia> datos = new HashMap<CodigoIncidencia, Incidencia>();
		//Ordeno fechas
		Calendar fecha_inicio = fecha1;
		Calendar fecha_fin = fecha2;
		ordenarFechas(fecha_inicio, fecha_fin);
		
		for (Entry<NIF, Cliente> cliente_listado : clientes.entrySet()) {
			if(cliente_listado.getValue().getDireccion().getCodigo_postal() == cp){
				for(Entry<CodigoIncidencia, Incidencia> incidencia: cliente_listado.getValue().incidencias.entrySet()){
					if((incidencia.getValue().getFecha().compareTo(fecha_inicio) >= 0)
							&& (incidencia.getValue().getFecha().compareTo(fecha_fin) <= 0)){
						datos.put(incidencia.getKey(), incidencia.getValue());
					}
				}
			}
		}
		return datos;
	}

	public void mostrarFacturasparaCPentreDosFechas(int cp) {
		System.out.println("Se buscarán facturas entre la fecha 1 y fecha 2 a introducir.");
		System.out.println("-Fecha 1-");
		Calendar fecha1 = ManejoInputs.pedirFecha();
		System.out.println("-Fecha 2-");
		Calendar fecha2 = ManejoInputs.pedirFecha();
		//Obtengo facturas
		HashMap<CodigoFactura, Factura> facturas = new HashMap<CodigoFactura, Factura>();
		facturas = facturasPorCP(cp, fecha1, fecha2);
		//Muestro facturas
		for(Entry<CodigoFactura, Factura> factura: facturas.entrySet()){
				System.out.println("Código: "+factura.getKey().getCodigo());
				factura.getValue().mostrarenTerminal();
		}
	}

	public HashMap<CodigoFactura, Factura> facturasPorCP(int cp, Calendar fecha1, Calendar fecha2) {
		HashMap<CodigoFactura, Factura> datos = new HashMap<CodigoFactura, Factura>();
		//Ordeno fechas
		Calendar fecha_inicio = fecha1;
		Calendar fecha_fin = fecha2;
		ordenarFechas(fecha_inicio, fecha_fin);
				
		for (Entry<NIF, Cliente> cliente_listado : clientes.entrySet()) {
			if(cliente_listado.getValue().getDireccion().getCodigo_postal() == cp){
				for(Entry<CodigoFactura, Factura> factura: cliente_listado.getValue().facturas.entrySet()){
					if((factura.getValue().getFecha().compareTo(fecha_inicio) >= 0)
							&& (factura.getValue().getFecha().compareTo(fecha_fin) <= 0)){
						datos.put(factura.getKey(), factura.getValue());
					}
				}
				break;
			}
		}
		return datos;
	}

	//INCIDENCIAS

	public void mostrarIncidenciasparaCPentreDosFechas(int cp) {
		System.out.println("Se buscarán incidencias entre la fecha 1 y fecha 2 a introducir.");
		System.out.println("-Fecha 1-");
		Calendar fecha1 = ManejoInputs.pedirFecha();
		System.out.println("-Fecha 2-");
		Calendar fecha2 = ManejoInputs.pedirFecha();
		//Obtengo incidencias
		HashMap<CodigoIncidencia, Incidencia> incidencias = new HashMap<CodigoIncidencia, Incidencia>();
		incidencias = incidenciasPorCP(cp, fecha1, fecha2);
		//Muestro incidencias
		mostrarIncidencias(incidencias);
	}
	
	public void mostrarIncidencias(HashMap<CodigoIncidencia, Incidencia> incidencias){
		for(Entry<CodigoIncidencia, Incidencia> incidencia: incidencias.entrySet()){
			System.out.println("Código: "+incidencia.getKey().getCodigo());
			System.out.println("Fecha: "+incidencia.getValue().getFecha().toString());
			System.out.println("Incidencia: "+incidencia.getValue().getNombreIncidencia());
			System.out.println("\n");
		}
	}
	
	public void ordenarFechas(Calendar fecha1, Calendar fecha2){
		//Ordeno fechas
		if(fecha1.compareTo(fecha2) > 0){
			Calendar aux = fecha1;
			fecha1 = fecha2;
			fecha2 = aux;
		}
	}
}