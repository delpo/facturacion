package facturacion;

import java.io.Serializable;

public class ExcepcionFacturaNoEncontrada extends Exception implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExcepcionFacturaNoEncontrada(){
		super("No se encontró factura.");
	}

}
