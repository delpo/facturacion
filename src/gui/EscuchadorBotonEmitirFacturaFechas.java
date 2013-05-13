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
	Calendar calendar_fecha_emision;
	Calendar calendar_fecha_inicio;
	Calendar calendar_fecha_fin;
	
	public EscuchadorBotonEmitirFacturaFechas(JFrame ventana, Operador op, NIF nif, Calendar calendar_fecha_emision,
			Calendar calendar_fecha_inicio, Calendar calendar_fecha_fin) {
		this.ventana = ventana;
		this.op = op;
		this.calendar_fecha_emision = calendar_fecha_emision;
		this.calendar_fecha_inicio = calendar_fecha_inicio;
		this.calendar_fecha_fin = calendar_fecha_fin;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Fecha emisión: "+calendar_fecha_emision.toString());
		System.out.println("Fecha inicio: "+calendar_fecha_inicio.toString());
		System.out.println("Fecha fin: "+calendar_fecha_fin.toString());
	}

}
