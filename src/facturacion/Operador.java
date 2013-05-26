package facturacion;

import gui.Vista;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map.Entry;

public interface Operador {

	public abstract void darAlta(Cliente cliente);

	public abstract void borrarCliente(NIF nif);

	public abstract void anyadirTarifa(NIF nif, int tarifa);

	public abstract void listaElemento(Entry<NIF, Cliente> entry);

	public abstract void listarClientes();

	public abstract void listarClientesporCP(int cp);

	public abstract void obtenerDatos(NIF nif);

	public abstract Cliente obtenerCliente(NIF nif);

	public abstract boolean claveValida(CodigoFactura clave);

	public abstract Factura obtenerFactura(CodigoFactura codigo_factura)
			throws ExcepcionFacturaNoEncontrada;

	public abstract CodigoFactura emitirFactura(NIF nif, Factura factura);

	public abstract void borrarFactura(CodigoFactura codigo_factura)
			throws ExcepcionFacturaNoEncontrada;

	public abstract void listarFacturasCliente(NIF nif);

	public abstract void darDeAltaIncidencia(NIF nif, Incidencia incidencia);

	public abstract void listarIncidencias(NIF nif);

	public abstract void borrarIncidencia(CodigoIncidencia codigo);

	//LISTADO ENTRE DOS FECHAS DE INCIDENCIAS O FACTURAS
	public abstract <S, T extends Info> HashMap<S, T> entreDosFechas(
			HashMap<S, T> listado, Calendar fecha1, Calendar fecha2);

	public abstract HashMap<CodigoFactura, Factura> facturasEntreDosFechas(
			Calendar fecha1, Calendar fecha2);

	public abstract HashMap<CodigoIncidencia, Incidencia> incidenciasEntreDosFechas(
			Calendar fecha1, Calendar fecha2);

	public abstract HashMap<CodigoFactura, Factura> facturasEntreDosFechas(
			NIF nif, Calendar fecha1, Calendar fecha2);

	public abstract void mostrarFacturasentreDosFechas();

	public abstract void mostrarFacturasentreDosFechas(NIF nif);

	public abstract HashMap<CodigoIncidencia, Incidencia> incidenciasEntreDosFechas(
			NIF nif, Calendar fecha1, Calendar fecha2);

	public abstract void mostrarIncidenciasentreDosFechas(NIF nif);

	public abstract void mostrarIncidenciasentreDosFechas();

	public abstract <S, T extends Info> HashMap<S, T> listar(
			HashMap<S, T> listado);

	public abstract void mostrarFacturasparaCP(int cp);

	public abstract void mostrarIncidenciasparaCP(int cp);

	public abstract HashMap<CodigoIncidencia, Incidencia> incidenciasPorCP(
			int cp);

	public abstract HashMap<CodigoIncidencia, Incidencia> incidenciasPorCP(
			int cp, Calendar fecha1, Calendar fecha2);

	public abstract void mostrarFacturasparaCPentreDosFechas(int cp);

	public abstract HashMap<CodigoFactura, Factura> facturasPorCP(int cp,
			Calendar fecha1, Calendar fecha2);

	public abstract void mostrarIncidenciasparaCPentreDosFechas(int cp);

	public abstract void mostrarIncidencias(
			HashMap<CodigoIncidencia, Incidencia> incidencias);

	public abstract void ordenarFechas(Calendar fecha1, Calendar fecha2);
	
	public HashMap<NIF, Cliente> getClientes();

	public void setVista(Vista vista);

	public void resolverIncidencia(CodigoIncidencia codigo);
}