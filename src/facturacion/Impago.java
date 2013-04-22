package facturacion;

import java.io.Serializable;
import java.util.Calendar;

public class Impago extends Incidencia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Impago(Calendar fecha){
		super.setFecha(fecha);
	}
	
	public String getNombreIncidencia() {
		return "Impago";
	}
	
}
