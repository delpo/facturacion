package facturacion;

import java.io.Serializable;

public class Averia extends Incidencia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Averia(){
		
	}
	
	@Override
	public String getNombreIncidencia() {
		return "Avería";
	}
}
