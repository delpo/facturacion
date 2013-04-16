package facturacion;

public class Tarifa_tarde extends TarifaconOfertas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final double coste = 0.05;
	
	public Tarifa_tarde(Tarifa tarifa){
		super(tarifa);
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
