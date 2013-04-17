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
	HashMap<CodigoFactura, Factura> facturas = new HashMap<CodigoFactura, Factura>();
	HashMap<CodigoIncidencia, Incidencia> incidencias = new HashMap<CodigoIncidencia, Incidencia>();	
	
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
			this.tarifa = new Tarifa_basica();
			break;
		case 0:
			this.tarifa = new Tarifa_tarde(this.tarifa);
			break;
		case 1:
			this.tarifa = new Tarifa_domingo(this.tarifa);
			break;
		}
	}
	public String getApellidos(){
		return null;
	}
	public void emitirFactura(Factura factura){
		//se sobreescribe tanto en Particular como en Empresa
	}
	
	public void listarCliente(){
		//se sobreescribe tanto en Particular como en Empresa
	}
	public double getIVA() {
		//se sobreescribe tanto en Particular como en Empresa
		return 0.0;
	}
	
	public void reportarIncidencia(Incidencia incidencia, Fecha fecha){
		incidencias.put(CodigoIncidencia.crearCodigoIncidencia(), incidencia);
	}
}