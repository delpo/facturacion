package facturacion;

import java.util.Calendar;

public class Tarifa_tarde extends TarifaconOfertas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Tarifa_tarde(Tarifa tarifa, double coste){
		super(tarifa, coste);
	}
	
	@Override
	public double getCoste(Calendar fecha) {
		//SI ES POR LA TARDE
		if(fecha.get(Calendar.HOUR_OF_DAY) >= 16 && fecha.get(Calendar.HOUR_OF_DAY) < 20){
			return Math.min(super.getCoste(fecha), tarifa.getCoste(fecha));		
		}else{ //SI NO ES POR LA TARDE
			return tarifa.getCoste(fecha);
		}
		
	}

	@Override
	public String getNombre() {
		return tarifa.getNombre() + " + Tarifa de tarde";
	}
}
