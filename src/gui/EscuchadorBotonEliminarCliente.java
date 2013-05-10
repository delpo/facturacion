package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import facturacion.ExcepcionNIFnoValido;
import facturacion.NIF;
import facturacion.Operador;

public class EscuchadorBotonEliminarCliente implements ActionListener {

	JFrame ventana_paso_2;
	JTextField nif;
	Operador op;
	
	public EscuchadorBotonEliminarCliente(JFrame ventana_paso_2, Operador op, JTextField nif){
		this.ventana_paso_2 = ventana_paso_2;
		this.op = op;
		this.nif = nif;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		boolean todo_ok = true;
		ventana_paso_2.dispose();
		NIF nif_valido = null;
		try {
			System.out.println("Dato obtenido: "+nif.getText());
			nif_valido = new NIF(nif.getText());
		} catch (ExcepcionNIFnoValido e) {
			JOptionPane.showMessageDialog(null, "NIF NO VÁLIDO.");
			todo_ok = false;
		}
		if(todo_ok){
			op.borrarCliente(nif_valido);
			JOptionPane.showMessageDialog(null, "Operación realizada con éxito.");
		}
	}

}
