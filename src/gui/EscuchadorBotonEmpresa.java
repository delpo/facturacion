package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;

import javax.swing.JFrame;

public class EscuchadorBotonEmpresa implements ActionListener, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JFrame ventana_paso_1;
	
	public EscuchadorBotonEmpresa(JFrame ventana_paso_1){
		this.ventana_paso_1 = ventana_paso_1;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ventana_paso_1.dispose();
		System.out.println("Seleccionaste cliente empresa.");
	}
}