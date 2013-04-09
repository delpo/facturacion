package facturacion;

import java.io.Serializable;

public class Impago extends Incidencia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Impago(Fecha fecha){
		super.setFecha(fecha);
	}
	
	public String getNombreIncidencia() {
		return "Impago";
	}
	
}
