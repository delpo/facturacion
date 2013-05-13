package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		ventana.dispose();
		JFrame ventana = new JFrame("Emitir factura (paso 3 de 4)");

		String html = "<html>" +
				"<b>Añadir llamadas:</b><br/>" +
				" <i>Introduzca las llamadas a facturar. Cuando</i><br/>" +
				" <i>haya introducido todas, pulse Aceptar.</i><br/>" +
				" ----------------------------------------------<br/>" +
				"</html>";
		JLabel etiqueta = new JLabel(html);
		ventana.getContentPane().add(etiqueta, BorderLayout.NORTH);
		ventana.setAlwaysOnTop(true);
		JPanel panel = new JPanel();
		//cosas en el panel
		ventana.getContentPane().add(panel);
		JButton boton_aceptar = new JButton("Aceptar");
		//boton_aceptar.addActionListener(new EscuchadorBotonEmitirFacturaFechas(ventana, op, nif_valido, picker,
				//picker1, picker2));//Registro escuchador
		ventana.getContentPane().add(boton_aceptar, BorderLayout.SOUTH);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.pack();
		ventana.setVisible(true);
		ventana.setSize(300, 230);
	}

}
