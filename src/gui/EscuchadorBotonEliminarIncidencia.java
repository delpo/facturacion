package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import facturacion.CodigoIncidencia;
import facturacion.Operador;

public class EscuchadorBotonEliminarIncidencia implements ActionListener {
	JFrame ventana;
	Operador op;
	JTextField cod;
	
	public EscuchadorBotonEliminarIncidencia(JFrame ventana, Operador op, JTextField cod) {
		this.ventana = ventana;
		this.op = op;
		this.cod = cod;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ventana.dispose();
		CodigoIncidencia codigo = new CodigoIncidencia(cod.getText());
		op.borrarIncidencia(codigo);
		JOptionPane.showMessageDialog(null, "Operación completada con éxito.");
	}

}
