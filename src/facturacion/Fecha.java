package facturacion;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Fecha implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int dia;
	private int mes;
	private int anyo;
	private int hora;
	private int minutos;
	private int segundos;
	
	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}

	public int getSegundos() {
		return segundos;
	}

	public void setSegundos(int segundos) {
		this.segundos = segundos;
	}

	public Fecha(int dia, int mes, int anyo){
		this.dia = dia;
		this.mes = mes;
		this.anyo = anyo;
	}
	
	public Fecha(int dia, int mes, int anyo, int hora, int minutos, int segundos){
		this.dia = dia;
		this.mes = mes;
		this.anyo = anyo;
		this.hora = hora;
		this.minutos = minutos;
		this.segundos = segundos;
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
	
	public int compareTo(Fecha otraFecha){
		if(this.dia == otraFecha.dia && this.mes == otraFecha.mes && this.anyo == otraFecha.anyo){
			return 0; //son iguales
		}
		
		if(this.anyo == otraFecha.anyo){
			if(this.mes == otraFecha.mes){
				return this.dia - otraFecha.dia; //mismo mes
			}else{
				return this.mes - otraFecha.mes;  //mismo año
			}
		}else{
			return this.anyo - otraFecha.anyo;  //distinto año
		}
	}
	
	public int diaDeLaSemana(){
		int dia_de_la_semana = 0;
	    Calendar cal1 = new GregorianCalendar(this.anyo, this.mes, this.dia);
		dia_de_la_semana = cal1.get(Calendar.DAY_OF_WEEK);
		return dia_de_la_semana;
	}
	
}
