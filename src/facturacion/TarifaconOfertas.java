package facturacion;

public abstract class TarifaconOfertas extends Tarifa{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Tarifa tarifa;
	
	public TarifaconOfertas(Tarifa tarifa){
		super();
		this.tarifa = tarifa;
	}
}
