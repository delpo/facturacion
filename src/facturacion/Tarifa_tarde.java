package facturacion;

import java.util.Calendar;

public class Tarifa_tarde extends TarifaconOfertas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final double precio = 0.05;
	
	public Tarifa_tarde(Tarifa tarifa, double coste){
		super(tarifa, coste);
	}
	
	@Override
	public double getCoste(Calendar fecha, int hora) {		
		//SI ES POR LA TARDE Y NO ES DOMINGO
		if((hora >= 16) && (hora < 20) && fecha.get(Calendar.DAY_OF_WEEK) != 1){
					return precio;
				//si no es domingo
		}else{ //SI NO ES POR LA TARDE
					return tarifa.getCoste(fecha, hora);
		}
	}

	@Override
	public String getNombre() {
		return tarifa.getNombre() + " + Tarifa de tarde";
	}
}
