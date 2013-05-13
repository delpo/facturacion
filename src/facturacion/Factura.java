package facturacion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
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
	
	public Factura(Calendar fecha_emision, Tarifa tarifa, Periodo_facturacion periodo_facturacion, HashMap<CodigoLlamada, Llamada> llamadas){
		super.setFecha(fecha_emision);
		this.segundos = totalSegundosLlamadas();
		this.tarifa = tarifa;
		this.periodo = periodo_facturacion;
		this.llamadas = llamadas;
	}
	
	public void setIVA(double iva){
		this.iva = iva;
		}
	
	public BigDecimal calcularCoste(){
		double coste_total_sin_iva = 0.0;
		//CALCULAR COSTE TOTAL DE LAS LLAMADAS (SIN IVA)
		coste_total_sin_iva = totalCosteLlamadas();
		//COSTE TOTAL SIN IVA
		double min = Math.ceil(totalSegundosLlamadas()/60.0);
		System.out.println("Minutos: "+min);
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
			System.out.println("Calculando coste de la llamada: "+llamada.getKey().getCodigo());
			Calendar fecha = llamada.getValue().getFecha_llamada();
			System.out.println("Día del mes de la llamada: "+fecha.get(Calendar.DAY_OF_MONTH));
			System.out.println("Domingo es: "+Calendar.SUNDAY);
			System.out.println("Día de la semana de la llamada: "+fecha.get(Calendar.DAY_OF_WEEK));
			System.out.println("hora A COMPROBAR: "+llamada.getValue().getFecha_llamada().get(Calendar.HOUR_OF_DAY)+":"+llamada.getValue().getFecha_llamada().get(Calendar.MINUTE));
			double minutos = 0;
			minutos = Math.ceil((llamada.getValue().getDuracion()) / 60.0);
			double coste = tarifa.getCoste(fecha, llamada.getValue().getFecha_llamada().get(Calendar.HOUR_OF_DAY));
			System.out.println("Coste por minuto: "+coste);
			System.out.println("minutos: "+minutos);
			coste_total += (minutos * coste);
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
		System.out.println("Factura emitida el día: "+super.getFecha().get(Calendar.DAY_OF_MONTH)+"/"+super.getFecha().get(Calendar.MONTH)+"/"+super.getFecha().get(Calendar.YEAR));
		System.out.println("Tiempo facturado (en segundos): "+totalSegundosLlamadas());
		System.out.println("Tarifa contratada: "+tarifa.getNombre());
		System.out.println("Periodo de facturación: del día "+
				periodo.getFecha_inicio().get(Calendar.DAY_OF_MONTH)+"/"+periodo.getFecha_inicio().get(Calendar.MONTH)+"/"+periodo.getFecha_inicio().get(Calendar.YEAR)
				+" al día "+periodo.getFecha_fin().get(Calendar.DAY_OF_MONTH)+"/"+periodo.getFecha_fin().get(Calendar.MONTH)+"/"+periodo.getFecha_fin().get(Calendar.YEAR));
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
	
	public String getPeriodoFacturacionTexto(){
		return periodo.getFecha_inicio().get(Calendar.DAY_OF_MONTH)+"/"+(periodo.getFecha_inicio().get(Calendar.MONTH)+1)
				+"/"+periodo.getFecha_inicio().get(Calendar.YEAR)+" - "+periodo.getFecha_fin().get(Calendar.DAY_OF_MONTH)
				+"/"+(periodo.getFecha_fin().get(Calendar.MONTH)+1)+"/"+periodo.getFecha_fin().get(Calendar.YEAR);
	}
	
	public int getSegundos(){
		return totalSegundosLlamadas();
	}
	
}
