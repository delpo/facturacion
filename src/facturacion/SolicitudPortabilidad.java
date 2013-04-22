package facturacion;

import java.io.Serializable;
import java.util.Calendar;

public class SolicitudPortabilidad extends Incidencia implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SolicitudPortabilidad(Calendar fecha){
		super.setFecha(fecha);
	}
	
	public String getNombreIncidencia() {
		return "Solicitud de portabilidad";
	}
	
}
