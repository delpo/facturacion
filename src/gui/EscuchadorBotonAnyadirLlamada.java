package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import facturacion.Factura;
import facturacion.Llamada;

public class EscuchadorBotonAnyadirLlamada implements ActionListener {
	Factura factura;
	Calendar picker;
	Calendar picker1;
	Calendar picker2;
	Calendar llamada;
	JTextField telefono;
	JTextField duracion;
	int telefono_integer;
	int duracion_integer;
	boolean todo_ok;
	
	public EscuchadorBotonAnyadirLlamada(Factura factura, Calendar picker, Calendar picker1, Calendar picker2, Calendar llamada, JTextField telefono, JTextField duracion) {

			this.factura = factura;
			this.picker = picker;
			this.picker1 = picker1;
			this.picker2 = picker2;
			this.llamada = llamada;
			this.telefono = telefono;
			this.duracion = duracion;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		todo_ok = true;
		try{
			telefono_integer = Integer.parseInt(telefono.getText());
			duracion_integer = Integer.parseInt(duracion.getText());
		}catch (NumberFormatException e) {
			todo_ok = false;
		}
		if(todo_ok){
			factura.anyadirLlamada(new Llamada(telefono_integer, llamada, duracion_integer));
		}else{
			JOptionPane.showMessageDialog(null, "ERROR: CAMPOS TELÉFONO Y/O DURACIÓN NO VÁLIDOS.");
		}
	}

}
