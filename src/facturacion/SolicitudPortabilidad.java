package facturacion;

import java.io.Serializable;

public class SolicitudPortabilidad extends Incidencia implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SolicitudPortabilidad(Fecha fecha){
		super.setFecha(fecha);
	}
	
	public String getNombreIncidencia() {
		return "Solicitud de portabilidad";
	}
	
}
