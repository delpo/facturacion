package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JTextField;

import facturacion.Operador;

public class EscuchadorBotonModificarTarifa implements ActionListener {
	JFrame ventana;
	Operador op;
	JTextField nif;

	public EscuchadorBotonModificarTarifa(JFrame ventana, Operador op, JTextField nif) {
		this.ventana = ventana;
		this.op = op;
		this.nif = nif;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

}
