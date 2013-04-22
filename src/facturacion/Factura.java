package facturacion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map.Entry;

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
	HashMap<CodigoLlamada, Llamada> llamadas = new HashMap<CodigoLlamada, Llamada>();
	
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
		//CALCULAR COSTE TOTAL DE LAS LLAMADAS (SIN IVA)
		coste_total_sin_iva = totalCosteLlamadas();
		//COSTE TOTAL SIN IVA
		System.out.println("Minutos: "+totalSegundosLlamadas()/60);
		System.out.println("Coste total sin iva: "+coste_total_sin_iva);
		System.out.println("IVA: "+iva);
		//COSTE TOTAL CON IVA
		double coste_total = coste_total_sin_iva+(coste_total_sin_iva*iva);
		System.out.println("Coste total con iva: "+coste_total);
		String val = coste_total+"";
	    BigDecimal big = new BigDecimal(val);
	    //REDONDEO
	    big = big.setScale(2, RoundingMode.HALF_UP);
		return big;
	}
	
	private double totalCosteLlamadas() {
		double coste_total = 0.0;
		for(Entry<CodigoLlamada, Llamada> llamada : llamadas.entrySet()){
			Fecha fecha = llamada.getValue().getFecha_llamada();
			int dia_de_la_semana = fecha.diaDeLaSemana();
			Tarifa tarifa = new Tarifa_basica();
			if(dia_de_la_semana == 1){ //ES DOMINGO
				tarifa = new Tarifa_domingo(tarifa);
				coste_total += tarifa.getCoste() * ((llamada.getValue().getDuracion())/60);	
			}else if(fecha.getHora() > 14 && fecha.getHora() < 20){ //ES TARDE
				tarifa = new Tarifa_tarde(tarifa);
				coste_total += tarifa.getCoste() * ((llamada.getValue().getDuracion())/60);	
			}else{ //ES HORARIO NORMAL
				coste_total += tarifa.getCoste() * ((llamada.getValue().getDuracion())/60);	
			}
		}
		return coste_total;
	}
	
	private int totalSegundosLlamadas() {
		int segundos = 0;
		for(Entry<CodigoLlamada, Llamada> llamada : llamadas.entrySet()){
			segundos += llamada.getValue().getDuracion();
		}
		return segundos;
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
	
	public void anyadirLlamada(Llamada llamada){
		CodigoLlamada cod = new CodigoLlamada();
		llamadas.put(cod.crearCodigoLlamada(), llamada);
	}
	
}
