package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import facturacion.CodigoFactura;
import facturacion.ExcepcionFacturaNoEncontrada;
import facturacion.Operador;

public class EscuchadorBotonEliminarFactura implements ActionListener {
	JFrame ventana;
	Operador op;
	JTextField cod;
	
	public EscuchadorBotonEliminarFactura(JFrame ventana, Operador op, JTextField cod) {
		this.ventana = ventana;
		this.op = op;
		this.cod = cod;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ventana.dispose();
		CodigoFactura codigo = new CodigoFactura(cod.getText());
		try {
			op.borrarFactura(codigo);
			JOptionPane.showMessageDialog(null, "Factura eliminada con éxito.");
		} catch (ExcepcionFacturaNoEncontrada e1) {
			JOptionPane.showMessageDialog(null, "ERROR: No se pudo eliminar factura.");
		}
	}

}
