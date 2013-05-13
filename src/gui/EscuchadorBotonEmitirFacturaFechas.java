package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JFrame;
import facturacion.NIF;
import facturacion.Operador;

public class EscuchadorBotonEmitirFacturaFechas implements ActionListener {

	JFrame ventana;
	Operador op;
	NIF nif;
	Calendar picker;
	Calendar picker1;
	Calendar picker2;
	
	public EscuchadorBotonEmitirFacturaFechas(JFrame ventana, Operador op, NIF nif, Calendar picker,
			Calendar picker1, Calendar picker2) {
		this.ventana = ventana;
		this.op = op;
		this.picker = picker;
		this.picker1 = picker1;
		this.picker2 = picker2;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Fecha emisión: "+picker.get(Calendar.DAY_OF_MONTH));
		System.out.println("Fecha inicio: "+picker1.get(Calendar.DAY_OF_MONTH));
		System.out.println("Fecha fin: "+picker2.get(Calendar.DAY_OF_MONTH));
	}

}
