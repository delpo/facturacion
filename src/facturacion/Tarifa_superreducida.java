package facturacion;

public class Tarifa_superreducida implements Tarifa {
	double coste = 0.01;
	
	public Tarifa_superreducida(){
	}
	
	@Override
	public double getCoste() {
		return coste;
	}
	
	@Override
	public String getNombre() {
		return "Tarifa superreducida";
	}
}
