package facturacion;

public class FabricaTarifas {
	public static Tarifa creaTarifa(Tarifa tarifa, int tipo, double coste){
		switch(tipo){
		case 0:
			tarifa = new Tarifa_tarde(tarifa, coste);
			break;
		case 1:
			tarifa = new Tarifa_domingo(tarifa, coste);
			break;
		case -1:
			tarifa = new Tarifa_basica();
		}
		return tarifa;
	}
	
}
