package facturacion;

import java.io.Serializable;

public abstract class Info implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Fecha fecha_emision;
	
	public Fecha getFecha() {
		return fecha_emision;
	}

	public void setFecha(Fecha fecha) {
		this.fecha_emision = fecha;
	}
}
