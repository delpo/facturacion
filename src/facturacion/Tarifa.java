package facturacion;

import java.io.Serializable;

public abstract class Tarifa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String descripcion;
	double coste;
	
	public Tarifa(){
		super();
	}

	public Tarifa(String descripcion, double coste){
		super();
		this.descripcion = descripcion;
		this.coste = coste;
	}
	
	public String getNombre() {
		return descripcion;
	}

	public double getCoste() {
		return coste;
	}
	
}
