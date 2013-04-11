package facturacion;

import java.io.Serializable;
import java.math.BigInteger;
import java.security.SecureRandom;


public class CodigoIncidencia implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String codigo;
	public CodigoIncidencia(String codigo){
		this.setCodigo(codigo);
	}
	public static CodigoIncidencia crearCodigoIncidencia(){
		SecureRandom aleatorio = new SecureRandom();
		String valor = new BigInteger(130, aleatorio).toString(32).toUpperCase();
		return new CodigoIncidencia(valor);
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
}
