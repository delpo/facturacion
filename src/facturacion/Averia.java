package facturacion;

import java.io.Serializable;
import java.util.Calendar;

public class Averia extends Incidencia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Averia(Calendar fecha){
		super.setFecha(fecha);
	}
	
	@Override
	public String getNombreIncidencia() {
		return "Avería";
	}
}
