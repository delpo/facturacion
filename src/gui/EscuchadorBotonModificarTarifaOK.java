package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import facturacion.NIF;
import facturacion.Operador;

public class EscuchadorBotonModificarTarifaOK implements ActionListener{
	JFrame ventana_anterior;
	Operador op;
	NIF nif;
	boolean tarde_activado;
	boolean domingo_activado;
	
	public EscuchadorBotonModificarTarifaOK(JFrame ventana_selector, Operador op, NIF nif, boolean tarde_activado, boolean domingo_activado){
		this.ventana_anterior = ventana_selector;
		this.op = op;
		this.nif = nif;
		this.tarde_activado = tarde_activado;
		this.domingo_activado = domingo_activado;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ventana_anterior.dispose();
		//-1 normal (siempre está añadida)
		//0 tarde
		//1 domingo
	}

}
