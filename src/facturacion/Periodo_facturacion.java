package facturacion;

import java.io.Serializable;

public class Periodo_facturacion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Fecha fecha_inicio;
	private Fecha fecha_fin;
	
	public Periodo_facturacion(Fecha fecha_inicio, Fecha fecha_fin) {
		this.setFecha_inicio(fecha_inicio);
		this.setFecha_fin(fecha_fin);
	}

	public Fecha getFecha_inicio() {
		return fecha_inicio;
	}

	public void setFecha_inicio(Fecha fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}

	public Fecha getFecha_fin() {
		return fecha_fin;
	}

	public void setFecha_fin(Fecha fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
}
