package facturacion;

import java.io.Serializable;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
	
	public void envia(final Email destinatario, final Factura factura) {
		

	    final Runnable stuffToDo = new Thread() {
			@Override
		    public void run() {
		    	try {
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
					System.out.println("ok");
				} catch (MessagingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    }
		    
		};
		final ExecutorService executor = Executors.newSingleThreadExecutor();
		final Future<?> future = executor.submit(stuffToDo);
		executor.shutdown(); // This does not cancel the already-scheduled task.
		try { future.get(30, TimeUnit.SECONDS); }
		catch (InterruptedException ie) { /* Handle the interruption. Or ignore it. */ }
		catch (ExecutionException ee) { /* Handle the error. Or ignore it. */ }
		catch (TimeoutException te) { /* Handle the timeout. Or ignore it. */ }
		if (!executor.isTerminated()){
			executor.shutdownNow(); // If you want to stop the code that hasn't finished.
			System.out.println("Correo no enviado.");
		}else{
		    System.out.println("Correo enviado.");
		}
	}
}
