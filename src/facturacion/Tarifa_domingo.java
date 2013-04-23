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
			System.out.println("Día de la semana calculado por tarifa domingo: "+fecha.get(Calendar.DAY_OF_WEEK));
			double coste = tarifa.getCoste(fecha, hora);
			System.out.println("Coste de tarifa domingo: "+coste);
			return 0;
		//si no es domingo
		}else	
			System.out.println("No es una tarifa de domingo.");
			return tarifa.getCoste(fecha, hora);
	}

	@Override
	public String getNombre() {
		return tarifa.getNombre() + " + Tarifa de domingo";
	}
}
