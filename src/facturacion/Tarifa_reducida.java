package facturacion;

import java.io.Serializable;

public class Tarifa_reducida implements Tarifa ,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double coste = 0.02;
	
	public Tarifa_reducida(){
	}
	
	@Override
	public double getCoste() {
		return coste;
	}
	
	@Override
	public String getNombre() {
		return "Tarifa reducida";
	}
}
