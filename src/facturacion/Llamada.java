package facturacion;

import java.io.Serializable;
import java.util.Calendar;

public class Llamada implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int telefono;
	private Calendar fecha_llamada;
	private int duracion; //en segundos	
	public Llamada(){
		super();
	}
	
	public Llamada(int telefono, Calendar fecha, int duracion){
		this.telefono = telefono;
		this.fecha_llamada = fecha;
		this.duracion = duracion;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public Calendar getFecha_llamada() {
		return fecha_llamada;
	}

	public void setFecha_llamada(Calendar fecha_llamada) {
		this.fecha_llamada = fecha_llamada;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}
}
