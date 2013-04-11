package facturacion;

import java.io.Serializable;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

public class Email implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String email;
	
	public Email(String email) throws ExcepcionEmailNoValido{
		if(isValidEmailAddress(email)){
			this.email = email;
		}else{
			//lanzar excepción EMAIL NO VÁLIDO
			throw new ExcepcionEmailNoValido();
		}
	}

	//empleando la API de Mail
	public static boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
		}
	public String toString(){
		return "Email : "+email;
	}
	
	public String getEmailenString(){
		return email;
	}
}
