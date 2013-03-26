package facturacion;

public class Factura {
	Fecha fecha_emision;
	int segundos;
	Tarifa tarifa;
	Periodo_facturacion periodo;
	
	public Factura(Fecha fecha_emision, int segundos, Tarifa tarifa, Periodo_facturacion periodo_facturacion){
		this.fecha_emision = fecha_emision;
		this.segundos = segundos;
		this.tarifa = tarifa;
		this.periodo = periodo_facturacion;
	}
	
	public void mostrarenTerminal(){
		System.out.println("Factura emitida el día: "+fecha_emision);
		System.out.println("Tiempo facturado (en segundos): "+segundos);
		System.out.println("Tarifa contratada: "+tarifa.toString());
		System.out.println("Periodo de facturación: del día "+periodo.getFecha_inicio()+" al día "+periodo.getFecha_fin());
	}
	
}
