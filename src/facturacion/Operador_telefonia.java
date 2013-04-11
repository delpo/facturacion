package facturacion;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map.Entry;

import terminal.ManejoInputs;

public class Operador_telefonia implements Serializable, Operador {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	HashMap<NIF, Cliente> clientes = new HashMap<NIF, Cliente>();
		
	public Operador_telefonia(){
		
	}
	
	/* (non-Javadoc)
	 * @see facturacion.Operador#darAlta(facturacion.Cliente)
	 */
	@Override
	public void darAlta(Cliente cliente) {
		if(clientes.containsKey(cliente.getNif())){
			System.out.println("El cliente ya existe. No se añadirá.");
		}else{
			clientes.put(cliente.getNif(), cliente);
		}
	}
	/* (non-Javadoc)
	 * @see facturacion.Operador#borrarCliente(facturacion.NIF)
	 */
	@Override
	public void borrarCliente(NIF nif) {
		for (Entry<NIF, Cliente> entry : clientes.entrySet()) {
			if(entry.getKey().toString().equals(nif.toString()) && clientes.remove(entry.getKey())!=null){
					System.out.println("Borrado completado con éxito.");
			}
		}
	}
	/* (non-Javadoc)
	 * @see facturacion.Operador#cambiarTarifa(facturacion.NIF, facturacion.Tarifa)
	 */
	@Override
	public void cambiarTarifa(NIF nif, Tarifa tarifa) {
		for (Entry<NIF, Cliente> entry : clientes.entrySet()) {
			if(entry.getKey().toString().equals(nif.toString())){
				entry.getValue().setTarifa(tarifa);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see facturacion.Operador#listaElemento(java.util.Map.Entry)
	 */
	@Override
	public void listaElemento(Entry<NIF, Cliente> entry){
		entry.getValue().listarCliente();
	}
	
	/* (non-Javadoc)
	 * @see facturacion.Operador#listarClientes()
	 */
	@Override
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
	/* (non-Javadoc)
	 * @see facturacion.Operador#listarClientesporCP(int)
	 */
	@Override
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
	
	/* (non-Javadoc)
	 * @see facturacion.Operador#obtenerDatos(facturacion.NIF)
	 */
	@Override
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
	
	/* (non-Javadoc)
	 * @see facturacion.Operador#obtenerCliente(facturacion.NIF)
	 */
	@Override
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
	
	/* (non-Javadoc)
	 * @see facturacion.Operador#claveValida(facturacion.CodigoFactura)
	 */
	@Override
	public boolean claveValida(CodigoFactura clave){
		Boolean valida = true;
		for(NIF nif: clientes.keySet()){
			if(clientes.get(nif).facturas.containsKey(clave.getCodigo())) valida = false;
		}
		return valida;
	}
	
	/* (non-Javadoc)
	 * @see facturacion.Operador#obtenerFactura(facturacion.CodigoFactura)
	 */
	@Override
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
	
	/* (non-Javadoc)
	 * @see facturacion.Operador#emitirFactura(facturacion.NIF, facturacion.Factura)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see facturacion.Operador#borrarFactura(facturacion.CodigoFactura)
	 */
	@Override
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
	
	/* (non-Javadoc)
	 * @see facturacion.Operador#listarFacturasCliente(facturacion.NIF)
	 */
	@Override
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
	
	/* (non-Javadoc)
	 * @see facturacion.Operador#darDeAltaIncidencia(facturacion.NIF, facturacion.Incidencia, facturacion.Fecha)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see facturacion.Operador#listarIncidencias(facturacion.NIF)
	 */
	@Override
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
	
	/* (non-Javadoc)
	 * @see facturacion.Operador#borrarIncidencia(facturacion.CodigoIncidencia)
	 */
	@Override
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
	//FACTURAS
	
	
	//////ENTRE DOS FECHAS////////////////////////////////////////////////////////////////////////////////
	/* (non-Javadoc)
	 * @see facturacion.Operador#facturasEntreDosFechas(facturacion.Fecha, facturacion.Fecha)
	 */
	@Override
	public HashMap<CodigoFactura, Factura> facturasEntreDosFechas(Fecha fecha1, Fecha fecha2){
		HashMap<CodigoFactura, Factura> datos = new HashMap<CodigoFactura, Factura>();
		//Ordeno fechas
		Fecha fecha_inicio = fecha1;
		Fecha fecha_fin = fecha2;
		ordenarFechas(fecha_inicio, fecha_fin);
				
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
	
	/* (non-Javadoc)
	 * @see facturacion.Operador#facturasEntreDosFechas(facturacion.NIF, facturacion.Fecha, facturacion.Fecha)
	 */
	@Override
	public HashMap<CodigoFactura, Factura> facturasEntreDosFechas(NIF nif, Fecha fecha1, Fecha fecha2){
		HashMap<CodigoFactura, Factura> datos = new HashMap<CodigoFactura, Factura>();
		//Ordeno fechas
		Fecha fecha_inicio = fecha1;
		Fecha fecha_fin = fecha2;
		ordenarFechas(fecha_inicio, fecha_fin);
		
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
	
	/* (non-Javadoc)
	 * @see facturacion.Operador#mostrarFacturasentreDosFechas()
	 */
	@Override
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
	
	/* (non-Javadoc)
	 * @see facturacion.Operador#mostrarFacturasentreDosFechas(facturacion.NIF)
	 */
	@Override
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
	
	/* (non-Javadoc)
	 * @see facturacion.Operador#mostrarFacturasparaCP(int)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see facturacion.Operador#mostrarFacturasparaCPentreDosFechas(int)
	 */
	@Override
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

	/* (non-Javadoc)
	 * @see facturacion.Operador#facturasPorCP(int, facturacion.Fecha, facturacion.Fecha)
	 */
	@Override
	public HashMap<CodigoFactura, Factura> facturasPorCP(int cp, Fecha fecha1, Fecha fecha2) {
		HashMap<CodigoFactura, Factura> datos = new HashMap<CodigoFactura, Factura>();
		//Ordeno fechas
		Fecha fecha_inicio = fecha1;
		Fecha fecha_fin = fecha2;
		ordenarFechas(fecha_inicio, fecha_fin);
				
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
	
	/* (non-Javadoc)
	 * @see facturacion.Operador#mostrarIncidenciasentreDosFechas()
	 */
	@Override
	public void mostrarIncidenciasentreDosFechas(){
		System.out.println("Se buscarán incidencias entre la fecha 1 y fecha 2 a introducir.");
		System.out.println("-Fecha 1-");
		Fecha fecha1 = ManejoInputs.pedirFecha();
		System.out.println("-Fecha 2-");
		Fecha fecha2 = ManejoInputs.pedirFecha();
		//Obtengo incidencias
		HashMap<CodigoIncidencia, Incidencia> incidencias = new HashMap<CodigoIncidencia, Incidencia>();
		incidencias = incidenciasEntreDosFechas(fecha1, fecha2);
		//Muestro incidencias
		mostrarIncidencias(incidencias);
		
	}
	
	/* (non-Javadoc)
	 * @see facturacion.Operador#mostrarIncidenciasentreDosFechas(facturacion.NIF)
	 */
	@Override
	public void mostrarIncidenciasentreDosFechas(NIF nif){
		System.out.println("Se buscarán incidencias entre la fecha 1 y fecha 2 a introducir.");
		System.out.println("-Fecha 1-");
		Fecha fecha1 = ManejoInputs.pedirFecha();
		System.out.println("-Fecha 2-");
		Fecha fecha2 = ManejoInputs.pedirFecha();
		//Obtengo incidencias
		HashMap<CodigoIncidencia, Incidencia> incidencias = new HashMap<CodigoIncidencia, Incidencia>();
		incidencias = incidenciasEntreDosFechas(nif, fecha1, fecha2);
		//Muestro incidencias
		mostrarIncidencias(incidencias);
		
	}
	
	/* (non-Javadoc)
	 * @see facturacion.Operador#incidenciasEntreDosFechas(facturacion.Fecha, facturacion.Fecha)
	 */
	@Override
	public HashMap<CodigoIncidencia, Incidencia> incidenciasEntreDosFechas(Fecha fecha1, Fecha fecha2){
		HashMap<CodigoIncidencia, Incidencia> datos = new HashMap<CodigoIncidencia, Incidencia>();
		//Ordeno fechas
		Fecha fecha_inicio = fecha1;
		Fecha fecha_fin = fecha2;
		if(fecha1.compareTo(fecha2) > 0){
			fecha_inicio = fecha2;
			fecha_fin = fecha1;
		}
		for (Entry<NIF, Cliente> cliente : clientes.entrySet()) {
			for(Entry<CodigoIncidencia, Incidencia> incidencia: cliente.getValue().incidencias.entrySet()){
				if((incidencia.getValue().getFecha().compareTo(fecha_inicio) >= 0)
						&& (incidencia.getValue().getFecha().compareTo(fecha_fin) <= 0)){
					datos.put(incidencia.getKey(), incidencia.getValue());
				}
			}
		}
		return datos;
	}

	/* (non-Javadoc)
	 * @see facturacion.Operador#incidenciasEntreDosFechas(facturacion.NIF, facturacion.Fecha, facturacion.Fecha)
	 */
	@Override
	public HashMap<CodigoIncidencia, Incidencia> incidenciasEntreDosFechas(NIF nif, Fecha fecha1, Fecha fecha2){
		HashMap<CodigoIncidencia, Incidencia> datos = new HashMap<CodigoIncidencia, Incidencia>();
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
					if((incidencia.getValue().getFecha().compareTo(fecha_inicio) >= 0)
							&& (incidencia.getValue().getFecha().compareTo(fecha_fin) <= 0)){
						datos.put(incidencia.getKey(), incidencia.getValue());
					}
				}
				break;
			}
		}
		return datos;
	}
	
	/* (non-Javadoc)
	 * @see facturacion.Operador#mostrarIncidenciasparaCP(int)
	 */
	@Override
	public void mostrarIncidenciasparaCP(int cp){
		//Obtengo incidencias
		HashMap<CodigoIncidencia, Incidencia> incidencias = new HashMap<CodigoIncidencia, Incidencia>();
		incidencias = incidenciasPorCP(cp);
		//Muestro incidencias
		mostrarIncidencias(incidencias);
		
	}

	private HashMap<CodigoIncidencia, Incidencia> incidenciasPorCP(int cp) {
		HashMap<CodigoIncidencia, Incidencia> incidencias = new HashMap<CodigoIncidencia, Incidencia>();
		for (Entry<NIF, Cliente> cliente_listado : clientes.entrySet()) {
			if(cliente_listado.getValue().getDireccion().getCodigo_postal() == cp){
				for(Entry<CodigoIncidencia, Incidencia> incidencia: incidencias.entrySet()){
					incidencias.put(incidencia.getKey(), incidencia.getValue());
				}
			}
		}
		return incidencias;
	}

	/* (non-Javadoc)
	 * @see facturacion.Operador#mostrarIncidenciasparaCPentreDosFechas(int)
	 */
	@Override
	public void mostrarIncidenciasparaCPentreDosFechas(int cp) {
		System.out.println("Se buscarán incidencias entre la fecha 1 y fecha 2 a introducir.");
		System.out.println("-Fecha 1-");
		Fecha fecha1 = ManejoInputs.pedirFecha();
		System.out.println("-Fecha 2-");
		Fecha fecha2 = ManejoInputs.pedirFecha();
		//Obtengo incidencias
		HashMap<CodigoIncidencia, Incidencia> incidencias = new HashMap<CodigoIncidencia, Incidencia>();
		incidencias = incidenciasPorCP(cp, fecha1, fecha2);
		//Muestro incidencias
		mostrarIncidencias(incidencias);
	}

	/* (non-Javadoc)
	 * @see facturacion.Operador#incidenciasPorCP(int, facturacion.Fecha, facturacion.Fecha)
	 */
	@Override
	public HashMap<CodigoIncidencia, Incidencia> incidenciasPorCP(int cp, Fecha fecha1, Fecha fecha2) {
		HashMap<CodigoIncidencia, Incidencia> datos = new HashMap<CodigoIncidencia, Incidencia>();
		//Ordeno fechas
		Fecha fecha_inicio = fecha1;
		Fecha fecha_fin = fecha2;
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
	
	/* (non-Javadoc)
	 * @see facturacion.Operador#mostrarIncidencias(java.util.HashMap)
	 */
	@Override
	public void mostrarIncidencias(HashMap<CodigoIncidencia, Incidencia> incidencias){
		for(Entry<CodigoIncidencia, Incidencia> incidencia: incidencias.entrySet()){
			System.out.println("Código: "+incidencia.getKey().getCodigo());
			System.out.println("Fecha: "+incidencia.getValue().getFecha().toString());
			System.out.println("Incidencia: "+incidencia.getValue().getNombreIncidencia());
			System.out.println("\n");
		}
	}
	
	/* (non-Javadoc)
	 * @see facturacion.Operador#ordenarFechas(facturacion.Fecha, facturacion.Fecha)
	 */
	@Override
	public void ordenarFechas(Fecha fecha1, Fecha fecha2){
		//Ordeno fechas
		if(fecha1.compareTo(fecha2) > 0){
			Fecha aux = fecha1;
			fecha1 = fecha2;
			fecha2 = aux;
		}
	}
}