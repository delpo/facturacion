package facturacion;

public abstract class TarifaconOfertas extends Tarifa{
	protected Tarifa tarifa;
	
	public TarifaconOfertas(Tarifa tarifa){
		super();
		this.tarifa = tarifa;
	}
}
