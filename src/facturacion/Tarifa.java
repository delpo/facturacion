package facturacion;

import java.io.Serializable;
import java.util.Calendar;

public abstract class Tarifa implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String descripcion;
	private double coste;
	
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

	public double getCoste(Calendar fecha) {
		return coste;
	}
	
}
