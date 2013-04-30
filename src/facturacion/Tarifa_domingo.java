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
	public double getCoste(Calendar fecha, int hora) {
		//si es en domingo
		if(fecha.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY){
			double coste = tarifa.getCoste(fecha, hora);
			return coste;
		//si no es domingo
		}else	
			return tarifa.getCoste(fecha, hora);
	}

	@Override
	public String getNombre() {
		return tarifa.getNombre() + " + Tarifa de domingo";
	}
}
