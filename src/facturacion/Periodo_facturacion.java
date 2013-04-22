package facturacion;

import java.io.Serializable;
import java.util.Calendar;

public class Periodo_facturacion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Calendar fecha_inicio;
	private Calendar fecha_fin;
	
	public Periodo_facturacion(Calendar fecha_inicio, Calendar fecha_fin) {
		this.setFecha_inicio(fecha_inicio);
		this.setFecha_fin(fecha_fin);
	}

	public Calendar getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Calendar fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public Calendar getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Calendar fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
}
