package facturacion;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;


public class CodigoFactura implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo;
	public CodigoFactura(String codigo){
		this.setCodigo(codigo);
	}
	public static CodigoFactura crearCodigoFactura(){
		SecureRandom aleatorio = new SecureRandom();
		String valor = new BigInteger(130, aleatorio).toString(32).toUpperCase();
		return new CodigoFactura(valor);
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
