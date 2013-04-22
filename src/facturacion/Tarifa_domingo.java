package facturacion;

import java.util.Calendar;

public class Tarifa_domingo extends TarifaconOfertas{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public Tarifa_domingo(Tarifa tarifa, double coste){
		super(tarifa, coste);
	}
	
	@Override
	public double getCoste(Calendar fecha) {
		//si es en domingo
		if(fecha.get(Calendar.DAY_OF_WEEK) == 1)
			return Math.min(super.getCoste(fecha), tarifa.getCoste(fecha));
		//si no es domingo
		else	
			return tarifa.getCoste(fecha);
	}

	@Override
	public String getNombre() {
		return tarifa.getNombre() + " + Tarifa de domingo";
	}
}
