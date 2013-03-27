package facturacion;

public class ExcepcionFacturaNoEncontrada extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ExcepcionFacturaNoEncontrada(){
		super("No se encontró factura.");
	}

}
