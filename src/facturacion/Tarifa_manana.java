package facturacion;

import java.io.Serializable;

public class Tarifa_manana implements Tarifa ,Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	double coste = 0.05;
	
	public Tarifa_manana(){
	}
	
	@Override
	public double getCoste() {
		return coste;
	}

	@Override
	public String getNombre() {
		return "Tarifa de mañana";
	}

}
