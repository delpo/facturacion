package facturacion;

import java.io.Serializable;

public class ExcepcionNIFnoValido extends Exception implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExcepcionNIFnoValido(){
		super("El NIF introducido no es válido");
	}
}
