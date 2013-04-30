package facturacion;

public abstract class TarifaconOfertas extends Tarifa{
	/**
	 * 
	 */
	
	private double coste;
	
	private static final long serialVersionUID = 1L;
	protected Tarifa tarifa;
	
	public TarifaconOfertas(Tarifa tarifa, double coste){
		super();
		this.tarifa = tarifa;
		this.coste = coste;
	}
	

	//TODO
	public double getCoste(Llamada llamada){
		return coste;
	}
}
