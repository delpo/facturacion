package facturacion;

import java.io.Serializable;
import java.util.Calendar;

public abstract class Info implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Calendar fecha_emision;
	
	public Calendar getFecha() {
		return fecha_emision;
	}

	public void setFecha(Calendar fecha) {
		this.fecha_emision = fecha;
	}
}
