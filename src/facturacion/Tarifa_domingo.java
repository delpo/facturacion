package facturacion;

public class Tarifa_domingo extends TarifaconOfertas{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final double coste = 0;
	
	public Tarifa_domingo(Tarifa tarifa){
		super(tarifa);
	}
	
	@Override
	public double getCoste() {
		return coste;
	}

	@Override
	public String getNombre() {
		return "Tarifa de domingo";
	}
}
