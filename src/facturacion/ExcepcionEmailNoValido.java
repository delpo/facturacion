package facturacion;

import java.io.Serializable;

public class ExcepcionEmailNoValido extends Exception implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ExcepcionEmailNoValido(){
		super("El email introducido no es válido");
	}
}
