package facturacion;

public class Tarifa_tarde implements Tarifa {
	double coste = 0.04;
	
	public Tarifa_tarde(){
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
