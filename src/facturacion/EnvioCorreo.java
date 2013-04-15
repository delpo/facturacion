package facturacion;

import java.io.Serializable;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnvioCorreo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final String username = "al151990@uji.es";
	final String password = "uji1991uji";
	
	public void envia(Email destinatario, Factura factura) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.googlemail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

	    try {
	        MimeMessage msg = new MimeMessage(session);
	        msg.setFrom(new InternetAddress(username));
	        msg.setRecipients(Message.RecipientType.TO, destinatario.getEmailenString());
	        msg.setSubject("Factura");
	        msg.setSentDate(new Date());
	        String texto = "Factura emitida el día: "+factura.getFecha()+"\n"+"Tiempo facturado (en segundos): "+factura.segundos+
	        		"\n"+"Tarifa contratada: "+factura.tarifa.getNombre()+"\n"+"Periodo de facturación: del día "+factura.periodo.getFecha_inicio()
	        		+" al día "+factura.periodo.getFecha_fin()+"\n"+"Coste total (con IVA): "+factura.importe+" €";
	        msg.setText(texto);
	        System.out.println("Inicio de envío");
	        Transport.send(msg);
	        System.out.println("Correo enviado.");
	    } catch (MessagingException mex) {
	        System.out.println("send failed, exception: " + mex);
	    }
	}
}
