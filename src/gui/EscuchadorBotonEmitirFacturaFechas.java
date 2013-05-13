package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;

import facturacion.NIF;
import facturacion.Operador;

public class EscuchadorBotonEmitirFacturaFechas implements ActionListener {

	JFrame ventana;
	Operador op;
	NIF nif;
	Calendar picker;
	Calendar picker1;
	Calendar picker2;
	JSpinner picker_llamada;
	Calendar llamada = new GregorianCalendar();
	
	public EscuchadorBotonEmitirFacturaFechas(JFrame ventana, Operador op, NIF nif, Calendar picker,
			Calendar picker1, Calendar picker2) {
		this.ventana = ventana;
		this.op = op;
		this.picker = picker;
		this.picker1 = picker1;
		this.picker2 = picker2;
		llamada.setTime(new Date());
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		ventana.dispose();
		JFrame ventana = new JFrame("Emitir factura (paso 3 de 4)");

		String html = "<html>" +
				"<b>Añadir llamadas:</b><br/>" +
				" <i>Introduzca las llamadas a facturar. Cuando</i><br/>" +
				" <i>haya introducido todas, pulse Aceptar.</i><br/>" +
				" ---------------------------------------------------------------------------------------<br/>" +
				"</html>";
		JLabel etiqueta = new JLabel(html);
		ventana.getContentPane().add(etiqueta, BorderLayout.NORTH);
		ventana.setAlwaysOnTop(true);
		JPanel panel = new JPanel();
		//cosas en el panel
		JPanel panel1 = new JPanel();
		panel1.setLayout(new BoxLayout(panel1, BoxLayout.PAGE_AXIS));
		JLabel etiqueta1 = new JLabel("Teléfono");
		JTextField telf = new JTextField(10);
		panel1.add(etiqueta1);
		panel1.add(telf);
		panel.add(panel1);
		JPanel panel2 = new JPanel();
		panel2.setLayout(new BoxLayout(panel2, BoxLayout.PAGE_AXIS));
		JLabel etiqueta2 = new JLabel("Fecha");
		picker_llamada = new JSpinner( new SpinnerDateModel() );
		JSpinner.DateEditor timeEditor1 = new JSpinner.DateEditor(picker_llamada, "dd-MM-yyyy HH:mm");
		picker_llamada.setEditor(timeEditor1);
		picker_llamada.setValue(new Date()); // will only show the current time
		picker_llamada.addChangeListener(timeEditor1);
		panel2.add(etiqueta2);
		panel2.add(timeEditor1);
		JPanel panel3 = new JPanel();
		panel3.setLayout(new BoxLayout(panel3, BoxLayout.PAGE_AXIS));
		JLabel etiqueta3 = new JLabel("Duración");
		JTextField duracion = new JTextField(10);
		panel3.add(etiqueta3);
		panel3.add(duracion);
		JPanel panel0 = new JPanel();
		panel0.setLayout(new BoxLayout(panel0, BoxLayout.PAGE_AXIS));
		JButton boton_llamar = new JButton("Llamar");
		boton_llamar.addActionListener(new EscuchadorBotonAñadirLlamada(ventana, op, nif, picker,
				picker1, picker2, llamada, telf.getText(), duracion.getText()));//Registro escuchador
		panel0.add(boton_llamar);
		panel.add(panel1);
		panel.add(panel2);
		panel.add(panel3);
		panel.add(panel0);
		ventana.getContentPane().add(panel);
		JButton boton_aceptar = new JButton("Aceptar");
		//boton_aceptar.addActionListener(new EscuchadorBotonEmitirFacturaFechas(ventana, op, nif_valido, picker,
				//picker1, picker2));//Registro escuchador
		ventana.getContentPane().add(boton_aceptar, BorderLayout.SOUTH);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.pack();
		ventana.setVisible(true);
		ventana.setSize(600, 180);
	}
	
	public void stateChanged(ChangeEvent e) {
        SpinnerModel dateModel = picker_llamada.getModel();
        if (dateModel instanceof SpinnerDateModel) {
        	llamada.setTime(((SpinnerDateModel)dateModel).getDate());
        }
    }

}
