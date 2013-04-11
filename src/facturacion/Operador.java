package facturacion;

import java.util.HashMap;
import java.util.Map.Entry;

public interface Operador {

	/* (non-Javadoc)
	 * @see facturacion.Operador#darAlta(facturacion.Cliente)
	 */
	public abstract void darAlta(Cliente cliente);

	/* (non-Javadoc)
	 * @see facturacion.Operador#borrarCliente(facturacion.NIF)
	 */
	public abstract void borrarCliente(NIF nif);

	/* (non-Javadoc)
	 * @see facturacion.Operador#cambiarTarifa(facturacion.NIF, facturacion.Tarifa)
	 */
	public abstract void cambiarTarifa(NIF nif, Tarifa tarifa);

	/* (non-Javadoc)
	 * @see facturacion.Operador#listaElemento(java.util.Map.Entry)
	 */
	public abstract void listaElemento(Entry<NIF, Cliente> entry);

	/* (non-Javadoc)
	 * @see facturacion.Operador#listarClientes()
	 */
	public abstract void listarClientes();

	/* (non-Javadoc)
	 * @see facturacion.Operador#listarClientesporCP(int)
	 */
	public abstract void listarClientesporCP(int cp);

	/* (non-Javadoc)
	 * @see facturacion.Operador#obtenerDatos(facturacion.NIF)
	 */
	public abstract void obtenerDatos(NIF nif);

	/* (non-Javadoc)
	 * @see facturacion.Operador#obtenerCliente(facturacion.NIF)
	 */
	public abstract Cliente obtenerCliente(NIF nif);

	/* (non-Javadoc)
	 * @see facturacion.Operador#claveValida(facturacion.CodigoFactura)
	 */
	public abstract boolean claveValida(CodigoFactura clave);

	/* (non-Javadoc)
	 * @see facturacion.Operador#obtenerFactura(facturacion.CodigoFactura)
	 */
	public abstract Factura obtenerFactura(CodigoFactura codigo_factura)
			throws ExcepcionFacturaNoEncontrada;

	/* (non-Javadoc)
	 * @see facturacion.Operador#emitirFactura(facturacion.NIF, facturacion.Factura)
	 */
	public abstract void emitirFactura(NIF nif, Factura factura);

	/* (non-Javadoc)
	 * @see facturacion.Operador#borrarFactura(facturacion.CodigoFactura)
	 */
	public abstract void borrarFactura(CodigoFactura codigo_factura)
			throws ExcepcionFacturaNoEncontrada;

	/* (non-Javadoc)
	 * @see facturacion.Operador#listarFacturasCliente(facturacion.NIF)
	 */
	public abstract void listarFacturasCliente(NIF nif);

	/* (non-Javadoc)
	 * @see facturacion.Operador#darDeAltaIncidencia(facturacion.NIF, facturacion.Incidencia, facturacion.Fecha)
	 */
	public abstract void darDeAltaIncidencia(NIF nif, Incidencia incidencia,
			Fecha fecha);

	/* (non-Javadoc)
	 * @see facturacion.Operador#listarIncidencias(facturacion.NIF)
	 */
	public abstract void listarIncidencias(NIF nif);

	/* (non-Javadoc)
	 * @see facturacion.Operador#borrarIncidencia(facturacion.CodigoIncidencia)
	 */
	public abstract void borrarIncidencia(CodigoIncidencia codigo);

	//////ENTRE DOS FECHAS////////////////////////////////////////////////////////////////////////////////
	/* (non-Javadoc)
	 * @see facturacion.Operador#facturasEntreDosFechas(facturacion.Fecha, facturacion.Fecha)
	 */
	public abstract HashMap<CodigoFactura, Factura> facturasEntreDosFechas(
			Fecha fecha1, Fecha fecha2);

	/* (non-Javadoc)
	 * @see facturacion.Operador#facturasEntreDosFechas(facturacion.NIF, facturacion.Fecha, facturacion.Fecha)
	 */
	public abstract HashMap<CodigoFactura, Factura> facturasEntreDosFechas(
			NIF nif, Fecha fecha1, Fecha fecha2);

	/* (non-Javadoc)
	 * @see facturacion.Operador#mostrarFacturasentreDosFechas()
	 */
	public abstract void mostrarFacturasentreDosFechas();

	/* (non-Javadoc)
	 * @see facturacion.Operador#mostrarFacturasentreDosFechas(facturacion.NIF)
	 */
	public abstract void mostrarFacturasentreDosFechas(NIF nif);

	/* (non-Javadoc)
	 * @see facturacion.Operador#mostrarFacturasparaCP(int)
	 */
	public abstract void mostrarFacturasparaCP(int cp);

	/* (non-Javadoc)
	 * @see facturacion.Operador#mostrarFacturasparaCPentreDosFechas(int)
	 */
	public abstract void mostrarFacturasparaCPentreDosFechas(int cp);

	/* (non-Javadoc)
	 * @see facturacion.Operador#facturasPorCP(int, facturacion.Fecha, facturacion.Fecha)
	 */
	public abstract HashMap<CodigoFactura, Factura> facturasPorCP(int cp,
			Fecha fecha1, Fecha fecha2);

	/* (non-Javadoc)
	 * @see facturacion.Operador#mostrarIncidenciasentreDosFechas()
	 */
	public abstract void mostrarIncidenciasentreDosFechas();

	/* (non-Javadoc)
	 * @see facturacion.Operador#mostrarIncidenciasentreDosFechas(facturacion.NIF)
	 */
	public abstract void mostrarIncidenciasentreDosFechas(NIF nif);

	public abstract HashMap<CodigoIncidencia, Incidencia> incidenciasEntreDosFechas(
			Fecha fecha1, Fecha fecha2);

	public abstract HashMap<CodigoIncidencia, Incidencia> incidenciasEntreDosFechas(
			NIF nif, Fecha fecha1, Fecha fecha2);

	/* (non-Javadoc)
	 * @see facturacion.Operador#mostrarIncidenciasparaCP(int)
	 */
	public abstract void mostrarIncidenciasparaCP(int cp);

	/* (non-Javadoc)
	 * @see facturacion.Operador#mostrarIncidenciasparaCPentreDosFechas(int)
	 */
	public abstract void mostrarIncidenciasparaCPentreDosFechas(int cp);

	/* (non-Javadoc)
	 * @see facturacion.Operador#incidenciasPorCP(int, facturacion.Fecha, facturacion.Fecha)
	 */
	public abstract HashMap<CodigoIncidencia, Incidencia> incidenciasPorCP(
			int cp, Fecha fecha1, Fecha fecha2);

	/* (non-Javadoc)
	 * @see facturacion.Operador#mostrarIncidencias(java.util.HashMap)
	 */
	public abstract void mostrarIncidencias(
			HashMap<CodigoIncidencia, Incidencia> incidencias);

	public abstract void ordenarFechas(Fecha fecha1, Fecha fecha2);

}