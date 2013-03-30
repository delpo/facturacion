package facturacion;

import java.io.Serializable;

public class Tarifa_tarde implements Tarifa, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double coste = 0.04;
	
	public Tarifa_tarde(){
	}
	
	@Override
	public double getCoste() {
		return coste;
	}

	@Override
	public String getNombre() {
		return "Tarifa de tarde";
	}
}
