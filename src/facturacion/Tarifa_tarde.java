package facturacion;

public class Tarifa_tarde extends TarifaconOfertas {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Tarifa_tarde(Tarifa tarifa){
		super(tarifa);
	}
	
	@Override
	public double getCoste() {
		return 0.05;
	}

	@Override
	public String getNombre() {
		return tarifa.getNombre() + " + Tarifa de tarde";
	}
}
