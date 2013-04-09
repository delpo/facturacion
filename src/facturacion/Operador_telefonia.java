package facturacion;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map.Entry;

import terminal.ManejoInputs;

public class Operador_telefonia implements Serializable {

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
	
	public void darDeAltaIncidencia(NIF nif, Incidencia incidencia, Fecha fecha){
		boolean ok = false;
		for (Entry<NIF, Cliente> entry : clientes.entrySet()) {
			if(entry.getKey().toString().equals(nif.toString())){
				ok = true;
				entry.getValue().reportarIncidencia(incidencia, fecha); //emplea el método de la clase Cliente
			}
		}
		if(!ok) System.out.println("Cliente no encontrado.");
	}

	public void listarIncidencias(NIF nif) {
		boolean ok = false;
		for (Entry<NIF, Cliente> entry : clientes.entrySet()) {
			if(entry.getKey().toString().equals(nif.toString())){
				ok = true;
				//recorrer incidencias en entry.getValue().incidencias
				for(Entry<CodigoIncidencia, Incidencia> entry2 : entry.getValue().incidencias.entrySet()){
					for(Entry<CodigoIncidencia, Incidencia> entry3 : entry2.getValue().entrySet()){
						System.out.println("Código: "+entry3.getKey().getCodigo());
						System.out.println("Incidencia: "+entry3.getValue().getNombreIncidencia());
						System.out.println("\n");
					}
				}
			}
		}
		if(!ok) System.out.println("Cliente no encontrado.");
	}
	
	public void borrarIncidencia(CodigoIncidencia codigo) {
		boolean ok = false;
		for (Entry<NIF, Cliente> entry : clientes.entrySet()) {
				//recorrer incidencias en entry.getValue().incidencias
				for(Entry<CodigoIncidencia, Incidencia> entry2 : entry.getValue().incidencias.entrySet()){
					for(Entry<CodigoIncidencia, Incidencia> entry3 : entry2.getValue().entrySet()){
						if(entry3.getKey().getCodigo().equals(codigo.getCodigo())){
							entry2.getValue().entrySet().remove(entry3);
							ok = true;
						}
						if(ok) break;
					}
					if(ok) break;
				}
				if(ok) break;
		}
		if(!ok) System.out.println("Cliente no encontrado.");
	}
	
	//Genericidad
	//FACTURAS
	
	public HashMap<CodigoFactura, Factura> facturasEntreDosFechas(Fecha fecha1, Fecha fecha2){
		HashMap<CodigoFactura, Factura> datos = new HashMap<CodigoFactura, Factura>();
		//Ordeno fechas
		Fecha fecha_inicio = fecha1;
		Fecha fecha_fin = fecha2;
		if(fecha1.compareTo(fecha2) > 0){
			fecha_inicio = fecha2;
			fecha_fin = fecha1;
		}
		for (Entry<NIF, Cliente> cliente : clientes.entrySet()) {
			for(Entry<CodigoFactura, Factura> factura: cliente.getValue().facturas.entrySet()){
				if((factura.getValue().fecha_emision.compareTo(fecha_inicio) >= 0)
						&& (factura.getValue().fecha_emision.compareTo(fecha_fin) <= 0)){
					datos.put(factura.getKey(), factura.getValue());
				}
			}
		}
		return datos;
	}
	
	public HashMap<CodigoFactura, Factura> facturasEntreDosFechas(NIF nif, Fecha fecha1, Fecha fecha2){
		HashMap<CodigoFactura, Factura> datos = new HashMap<CodigoFactura, Factura>();
		//Ordeno fechas
		Fecha fecha_inicio = fecha1;
		Fecha fecha_fin = fecha2;
		if(fecha1.compareTo(fecha2) > 0){
			fecha_inicio = fecha2;
			fecha_fin = fecha1;
		}
		for (Entry<NIF, Cliente> cliente_listado : clientes.entrySet()) {
			if(cliente_listado.getKey().NIF.equals(nif.NIF)){
				for(Entry<CodigoFactura, Factura> factura: cliente_listado.getValue().facturas.entrySet()){
					if((factura.getValue().fecha_emision.compareTo(fecha_inicio) >= 0)
							&& (factura.getValue().fecha_emision.compareTo(fecha_fin) <= 0)){
						datos.put(factura.getKey(), factura.getValue());
					}
				}
				break;
			}
		}
		return datos;
	}
	
	public void mostrarFacturasentreDosFechas(){
		System.out.println("Se buscarán facturas entre la fecha 1 y fecha 2 a introducir.");
		System.out.println("-Fecha 1-");
		Fecha fecha1 = ManejoInputs.pedirFecha();
		System.out.println("-Fecha 2-");
		Fecha fecha2 = ManejoInputs.pedirFecha();
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
		Fecha fecha1 = ManejoInputs.pedirFecha();
		System.out.println("-Fecha 2-");
		Fecha fecha2 = ManejoInputs.pedirFecha();
		//Obtengo facturas
		HashMap<CodigoFactura, Factura> facturas = new HashMap<CodigoFactura, Factura>();
		facturas = facturasEntreDosFechas(nif, fecha1, fecha2);
		//Muestro facturas
		for(Entry<CodigoFactura, Factura> factura: facturas.entrySet()){
				System.out.println("Código: "+factura.getKey().getCodigo());
				factura.getValue().mostrarenTerminal();
		}
		
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

	private HashMap<CodigoFactura, Factura> facturasPorCP(int cp) {
		HashMap<CodigoFactura, Factura> datos = new HashMap<CodigoFactura, Factura>();
		for (Entry<NIF, Cliente> cliente_listado : clientes.entrySet()) {
			if(cliente_listado.getValue().getDireccion().getCodigo_postal() == cp){
				for(Entry<CodigoFactura, Factura> factura: cliente_listado.getValue().facturas.entrySet()){
					datos.put(factura.getKey(), factura.getValue());
				}
			}
			break;
		}
		return datos;
	}

	public void mostrarFacturasparaCPentreDosFechas(int cp) {
		System.out.println("Se buscarán facturas entre la fecha 1 y fecha 2 a introducir.");
		System.out.println("-Fecha 1-");
		Fecha fecha1 = ManejoInputs.pedirFecha();
		System.out.println("-Fecha 2-");
		Fecha fecha2 = ManejoInputs.pedirFecha();
		//Obtengo facturas
		HashMap<CodigoFactura, Factura> facturas = new HashMap<CodigoFactura, Factura>();
		facturas = facturasPorCP(cp, fecha1, fecha2);
		//Muestro facturas
		for(Entry<CodigoFactura, Factura> factura: facturas.entrySet()){
				System.out.println("Código: "+factura.getKey().getCodigo());
				factura.getValue().mostrarenTerminal();
		}
	}

	public HashMap<CodigoFactura, Factura> facturasPorCP(int cp, Fecha fecha1, Fecha fecha2) {
		HashMap<CodigoFactura, Factura> datos = new HashMap<CodigoFactura, Factura>();
		//Ordeno fechas
		Fecha fecha_inicio = fecha1;
		Fecha fecha_fin = fecha2;
		if(fecha1.compareTo(fecha2) > 0){
			fecha_inicio = fecha2;
			fecha_fin = fecha1;
		}
		for (Entry<NIF, Cliente> cliente_listado : clientes.entrySet()) {
			if(cliente_listado.getValue().getDireccion().getCodigo_postal() == cp){
				for(Entry<CodigoFactura, Factura> factura: cliente_listado.getValue().facturas.entrySet()){
					if((factura.getValue().fecha_emision.compareTo(fecha_inicio) >= 0)
							&& (factura.getValue().fecha_emision.compareTo(fecha_fin) <= 0)){
						datos.put(factura.getKey(), factura.getValue());
					}
				}
				break;
			}
		}
		return datos;
	}

	//INCIDENCIAS
	public HashMap<Fecha, HashMap<CodigoIncidencia, Incidencia>> incidenciasEntreDosFechas(Fecha fecha1, Fecha fecha2){
		HashMap<Fecha, HashMap<CodigoIncidencia, Incidencia>> datos = new HashMap<Fecha, HashMap<CodigoIncidencia, Incidencia>>();
		//Ordeno fechas
		Fecha fecha_inicio = fecha1;
		Fecha fecha_fin = fecha2;
		if(fecha1.compareTo(fecha2) > 0){
			fecha_inicio = fecha2;
			fecha_fin = fecha1;
		}
		for (Entry<NIF, Cliente> cliente : clientes.entrySet()) {
			for(Entry<CodigoIncidencia, Incidencia> incidencia: cliente.getValue().incidencias.entrySet()){
				if((incidencia.getKey().compareTo(fecha_inicio) >= 0)
						&& (incidencia.getKey().compareTo(fecha_fin) <= 0)){
					datos.put(incidencia.getKey(), incidencia.getValue());
				}
			}
		}
		return datos;
	}
	
	public HashMap<Fecha, HashMap<CodigoIncidencia, Incidencia>> incidenciasEntreDosFechas(NIF nif, Fecha fecha1, Fecha fecha2){
		HashMap<Fecha, HashMap<CodigoIncidencia, Incidencia>> datos = new HashMap<Fecha, HashMap<CodigoIncidencia, Incidencia>>();
		//Ordeno fechas
		Fecha fecha_inicio = fecha1;
		Fecha fecha_fin = fecha2;
		if(fecha1.compareTo(fecha2) > 0){
			fecha_inicio = fecha2;
			fecha_fin = fecha1;
		}
		for (Entry<NIF, Cliente> cliente_listado : clientes.entrySet()) {
			if(cliente_listado.getKey().NIF.equals(nif.NIF)){
				for(Entry<CodigoIncidencia, Incidencia> incidencia: cliente_listado.getValue().incidencias.entrySet()){
					if((incidencia.getKey().compareTo(fecha_inicio) >= 0)
							&& (incidencia.getKey().compareTo(fecha_fin) <= 0)){
						datos.put(incidencia.getKey(), incidencia.getValue());
					}
				}
				break;
			}
		}
		return datos;
	}
	
	public void mostrarIncidenciasentreDosFechas(){
		System.out.println("Se buscarán incidencias entre la fecha 1 y fecha 2 a introducir.");
		System.out.println("-Fecha 1-");
		Fecha fecha1 = ManejoInputs.pedirFecha();
		System.out.println("-Fecha 2-");
		Fecha fecha2 = ManejoInputs.pedirFecha();
		//Obtengo incidencias
		HashMap<Fecha, HashMap<CodigoIncidencia, Incidencia>> incidencias = new HashMap<Fecha, HashMap<CodigoIncidencia, Incidencia>>();
		incidencias = incidenciasEntreDosFechas(fecha1, fecha2);
		//Muestro incidencias
		for(Entry<Fecha, HashMap<CodigoIncidencia, Incidencia>> incidencia: incidencias.entrySet()){
				System.out.println("Fecha: "+incidencia.getKey().toString());
				for(Entry<CodigoIncidencia, Incidencia> incidencia2: incidencia.getValue().entrySet()){
					System.out.println("Código: "+incidencia2.getKey());
					System.out.println("Tipo: "+incidencia2.getValue());
				}
		}
		
	}
	
	public void mostrarIncidenciasentreDosFechas(NIF nif){
		System.out.println("Se buscarán incidencias entre la fecha 1 y fecha 2 a introducir.");
		System.out.println("-Fecha 1-");
		Fecha fecha1 = ManejoInputs.pedirFecha();
		System.out.println("-Fecha 2-");
		Fecha fecha2 = ManejoInputs.pedirFecha();
		//Obtengo incidencias
		HashMap<Fecha, HashMap<CodigoIncidencia, Incidencia>> incidencias = new HashMap<Fecha, HashMap<CodigoIncidencia, Incidencia>>();
		incidencias = incidenciasEntreDosFechas(nif, fecha1, fecha2);
		//Muestro incidencias
		for(Entry<Fecha, HashMap<CodigoIncidencia, Incidencia>> incidencia: incidencias.entrySet()){
			System.out.println("Fecha: "+incidencia.getKey().toString());
			for(Entry<CodigoIncidencia, Incidencia> incidencia2: incidencia.getValue().entrySet()){
				System.out.println("Código: "+incidencia2.getKey());
				System.out.println("Tipo: "+incidencia2.getValue());
			}
		}
		
	}
	
	public void mostrarIncidenciasparaCP(int cp){
		//Obtengo incidencias
		HashMap<Fecha, HashMap<CodigoIncidencia, Incidencia>> incidencias = new HashMap<Fecha, HashMap<CodigoIncidencia, Incidencia>>();
		incidencias = incidenciasPorCP(cp);
		//Muestro incidencias
		for(Entry<Fecha, HashMap<CodigoIncidencia, Incidencia>> incidencia: incidencias.entrySet()){
			System.out.println("Fecha: "+incidencia.getKey().toString());
			for(Entry<CodigoIncidencia, Incidencia> incidencia2: incidencia.getValue().entrySet()){
				System.out.println("Código: "+incidencia2.getKey());
				System.out.println("Tipo: "+incidencia2.getValue());
			}
		}
		
	}

	private HashMap<Fecha, HashMap<CodigoIncidencia, Incidencia>> incidenciasPorCP(int cp) {
		HashMap<Fecha, HashMap<CodigoIncidencia, Incidencia>> incidencias = new HashMap<Fecha, HashMap<CodigoIncidencia, Incidencia>>();
		for (Entry<NIF, Cliente> cliente_listado : clientes.entrySet()) {
			if(cliente_listado.getValue().getDireccion().getCodigo_postal() == cp){
				for(Entry<Fecha, HashMap<CodigoIncidencia, Incidencia>> incidencia: incidencias.entrySet()){
					incidencias.put(incidencia.getKey(), incidencia.getValue());
				}
			}
		}
		return incidencias;
	}

	public void mostrarIncidenciasparaCPentreDosFechas(int cp) {
		System.out.println("Se buscarán incidencias entre la fecha 1 y fecha 2 a introducir.");
		System.out.println("-Fecha 1-");
		Fecha fecha1 = ManejoInputs.pedirFecha();
		System.out.println("-Fecha 2-");
		Fecha fecha2 = ManejoInputs.pedirFecha();
		//Obtengo incidencias
		HashMap<Fecha, HashMap<CodigoIncidencia, Incidencia>> incidencias = new HashMap<Fecha, HashMap<CodigoIncidencia, Incidencia>>();
		incidencias = incidenciasPorCP(cp, fecha1, fecha2);
		//Muestro incidencias
		for(Entry<Fecha, HashMap<CodigoIncidencia, Incidencia>> incidencia: incidencias.entrySet()){
			System.out.println("Fecha: "+incidencia.getKey().toString());
			for(Entry<CodigoIncidencia, Incidencia> incidencia2: incidencia.getValue().entrySet()){
				System.out.println("Código: "+incidencia2.getKey());
				System.out.println("Tipo: "+incidencia2.getValue());
			}
		}
	}

	public HashMap<Fecha, HashMap<CodigoIncidencia, Incidencia>> incidenciasPorCP(int cp, Fecha fecha1, Fecha fecha2) {
		HashMap<Fecha, HashMap<CodigoIncidencia, Incidencia>> datos = new HashMap<Fecha, HashMap<CodigoIncidencia, Incidencia>>();
		//Ordeno fechas
		Fecha fecha_inicio = fecha1;
		Fecha fecha_fin = fecha2;
		if(fecha1.compareTo(fecha2) > 0){
			fecha_inicio = fecha2;
			fecha_fin = fecha1;
		}
		for (Entry<NIF, Cliente> cliente_listado : clientes.entrySet()) {
			if(cliente_listado.getValue().getDireccion().getCodigo_postal() == cp){
				for(Entry<CodigoIncidencia, Incidencia> incidencia: cliente_listado.getValue().incidencias.entrySet()){
					if((incidencia.getKey().compareTo(fecha_inicio) >= 0)
							&& (incidencia.getKey().compareTo(fecha_fin) <= 0)){
						datos.put(incidencia.getKey(), incidencia.getValue());
					}
				}
			}
		}
		return datos;
	}
}