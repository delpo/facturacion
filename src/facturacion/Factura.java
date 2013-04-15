package facturacion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class Factura  extends Info implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int segundos;
	Tarifa tarifa;
	Periodo_facturacion periodo;
	BigDecimal importe; //CON IVA de la tarifa correspondiente
	double iva;
	
	public Factura(Fecha fecha_emision, int segundos, Tarifa tarifa, Periodo_facturacion periodo_facturacion){
		super.setFecha(fecha_emision);
		this.segundos = segundos;
		this.tarifa = tarifa;
		this.periodo = periodo_facturacion;
	}
	
	public void setIVA(double iva){
		this.iva = iva;
		}
	
	public BigDecimal calcularCoste(){
		double coste_total_sin_iva = 0.0;
		coste_total_sin_iva = (segundos/60) *  tarifa.getCoste();
		System.out.println("Minutos: "+segundos/60);
		System.out.println("Coste total sin iva: "+coste_total_sin_iva);
		System.out.println("IVA: "+iva);
		double coste_total = coste_total_sin_iva+(coste_total_sin_iva*iva);
		System.out.println("Coste total con iva: "+coste_total);
		String val = coste_total+"";
	    BigDecimal big = new BigDecimal(val);
	    big = big.setScale(2, RoundingMode.HALF_UP);
		return big;
	}
	
	public void mostrarenTerminal(){
		System.out.println("Factura emitida el día: "+super.getFecha());
		System.out.println("Tiempo facturado (en segundos): "+segundos);
		System.out.println("Tarifa contratada: "+tarifa.getNombre());
		System.out.println("Periodo de facturación: del día "+periodo.getFecha_inicio()+" al día "+periodo.getFecha_fin());
		System.out.println("Coste total (con IVA): "+importe+" €");
	}
	
	public BigDecimal getImporte(){
		return importe;
	}

	public double getIVA() {
		return iva;
	}
	
}
