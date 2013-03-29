package facturacion;

import java.util.Calendar;

public class Fecha {
	private int dia;
	private int mes;
	private int anyo;
	
	public Fecha(int dia, int mes, int anyo){
		this.dia = dia;
		this.mes = mes;
		this.anyo = anyo;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAnyo() {
		return anyo;
	}

	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}
	@Override
	public String toString(){
		return this.getDia()+"/"+this.getMes()+"/"+this.getAnyo();
	}
	
	public static Fecha hoy(){
		return new Fecha(Calendar.DAY_OF_MONTH, Calendar.MONTH, Calendar.YEAR);
	}
	
}
