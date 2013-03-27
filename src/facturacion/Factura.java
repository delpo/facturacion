package facturacion;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Factura {
	Fecha fecha_emision;
	int segundos;
	Tarifa tarifa;
	Periodo_facturacion periodo;
	BigDecimal importe; //CON IVA de la tarifa correspondiente
	double iva;
	
	public Factura(Fecha fecha_emision, int segundos, Tarifa tarifa, Periodo_facturacion periodo_facturacion){
		this.fecha_emision = fecha_emision;
		this.segundos = segundos;
		this.tarifa = tarifa;
		this.periodo = periodo_facturacion;
		this.importe = calcularCoste();
	}
	
	public void setIVA(double iva){
		this.iva = iva;
		}
	
	public BigDecimal calcularCoste(){
		double coste_total_sin_iva = 0.0;
		coste_total_sin_iva = (segundos/60) *  tarifa.getCoste();
		double coste_total = coste_total_sin_iva+(coste_total_sin_iva*iva);
		String val = coste_total+"";
	    BigDecimal big = new BigDecimal(val);
	    big = big.setScale(2, RoundingMode.HALF_UP);
		return big;
	}
	
	public void mostrarenTerminal(){
		System.out.println("Factura emitida el día: "+fecha_emision);
		System.out.println("Tiempo facturado (en segundos): "+segundos);
		System.out.println("Tarifa contratada: "+tarifa.getNombre());
		System.out.println("Periodo de facturación: del día "+periodo.getFecha_inicio()+" al día "+periodo.getFecha_fin());
		System.out.println("Coste total (con IVA): "+importe+" €");
	}
	
	public BigDecimal getImporte(){
		return importe;
	}
	
}
