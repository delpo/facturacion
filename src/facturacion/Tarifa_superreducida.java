package facturacion;

import java.io.Serializable;

public class Tarifa_superreducida implements Tarifa, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double coste = 0.01;
	
	public Tarifa_superreducida(){
	}
	
	@Override
	public double getCoste() {
		return coste;
	}
	
	@Override
	public String getNombre() {
		return "Tarifa superreducida";
	}
}
