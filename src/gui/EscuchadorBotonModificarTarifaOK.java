package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import facturacion.NIF;
import facturacion.Operador;

public class EscuchadorBotonModificarTarifaOK implements ActionListener{
	JFrame ventana_anterior;
	Operador op;
	NIF nif;
	boolean tarde_activado;
	boolean domingo_activado;
	
	public EscuchadorBotonModificarTarifaOK(JFrame ventana_selector){
		this.ventana_anterior = ventana_selector;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ventana_anterior.dispose();
		JOptionPane.showMessageDialog(null, "Tarifas del cliente actualizadas");
	}

}
