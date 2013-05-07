
package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import facturacion.Cliente;
import facturacion.Direccion;
import facturacion.Email;
import facturacion.ExcepcionEmailNoValido;
import facturacion.ExcepcionNIFnoValido;
import facturacion.NIF;
import facturacion.Operador;
import facturacion.Particular;

public class EscuchadorBotonParticularOK implements ActionListener, Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	JFrame ventana_paso_2;
	JTextField nombre;
	JTextField apellidos;
	JTextField nif;
	JTextField cp;
	JTextField poblacion;
	JTextField provincia;
	JTextField email;
	Operador op;


	public EscuchadorBotonParticularOK(JFrame ventana_paso_2, Operador op, JTextField nombre, JTextField apellidos, JTextField nif, JTextField cp, JTextField poblacion, JTextField provincia, JTextField email){
		this.ventana_paso_2 = ventana_paso_2;
		this.op = op;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.nif = nif;
		this.cp = cp;
		this.poblacion = poblacion;
		this.provincia = provincia;
		this.email = email;
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		boolean todo_ok = true;
		ventana_paso_2.dispose();
		NIF nif_valido = null;
		try {
			nif_valido = new NIF(nif.getText());
		} catch (ExcepcionNIFnoValido e) {
			JOptionPane.showMessageDialog(null, "NIF NO VÁLIDO.");
			todo_ok = false;
		}
		Email email_valido = null;
		try {
			email_valido = new Email(email.getText());
		} catch (ExcepcionEmailNoValido e) {
			JOptionPane.showMessageDialog(null, "EMAIL NO VÁLIDO.");
			todo_ok = false;
		}
		if(todo_ok){
			Cliente cliente = new Particular(nombre.getText(), apellidos.getText(), nif_valido, new Direccion(Integer.parseInt(cp.getText()), provincia.getText(), poblacion.getText()), email_valido, -1);
			op.darAlta(cliente);
			JOptionPane.showMessageDialog(null, "Cliente añadido satisfactoriamente.");


		}else{
			JOptionPane.showMessageDialog(null, "ERROR: NO SE PUDO AÑADIR CLIENTE.");
		}
	}

}

