package facturacion;

import java.math.BigInteger;
import java.security.SecureRandom;


public class CodigoFactura{
	private String codigo;
	CodigoFactura(String codigo){
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
