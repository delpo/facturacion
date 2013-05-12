package facturacion;

import java.io.Serializable;
import java.util.HashMap;

public abstract class Cliente implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String nombre;
	private NIF nif;
	private Direccion direccion;
	private Email email;
	private Tarifa tarifa;
	private HashMap<CodigoFactura, Factura> facturas = new HashMap<CodigoFactura, Factura>();
	private HashMap<CodigoIncidencia, Incidencia> incidencias = new HashMap<CodigoIncidencia, Incidencia>();	
	
	//GETTERS Y SETTERS
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public NIF getNif() {
		return nif;
	}
	public void setNif(NIF nif) {
		this.nif = nif;
	}
	public Direccion getDireccion() {
		return direccion;
	}
	public void setDireccion(Direccion direccion) {
		this.direccion = direccion;
	}
	public Email getEmail() {
		return email;
	}
	public void setEmail(Email email) {
		this.email = email;
	}
	public Tarifa getTarifa() {
		return tarifa;
	}
	public void setTarifa(int i) {
		switch(i){
		case -1: 
			this.tarifa = FabricaTarifas.creaTarifa(this.tarifa, -1, 0.15); //tarifa normal
			break;
		case 0:
			this.tarifa = FabricaTarifas.creaTarifa(this.tarifa, 0, 0.05); //tarifa de tarde
			break;
		case 1:
			this.tarifa = FabricaTarifas.creaTarifa(this.tarifa, 1, 0); //tarifa de domingo
			break;
		}
	}
	public String getApellidos(){
		return null;
	}
	public CodigoFactura emitirFactura(Factura factura){
		//se sobreescribe tanto en Particular como en Empresa
		return null;
	}
	
	public void setApellidos(String apellidos){
		//se sobreescribe en Particular
	}
	
	public void listarCliente(){
		//se sobreescribe tanto en Particular como en Empresa
	}
	public double getIVA() {
		//se sobreescribe tanto en Particular como en Empresa
		return 0.0;
	}
	
	public void reportarIncidencia(Incidencia incidencia){
		incidencias.put(CodigoIncidencia.crearCodigoIncidencia(), incidencia);
	}
	public HashMap<CodigoFactura, Factura> getFacturas() {
		return facturas;
	}
	public void setFacturas(HashMap<CodigoFactura, Factura> facturas) {
		this.facturas = facturas;
	}
	
	public HashMap<CodigoIncidencia, Incidencia> getIncidencias() {
		return incidencias;
	}
	public void setIncidencias(HashMap<CodigoIncidencia, Incidencia> incidencias) {
		this.incidencias = incidencias;
	}
}