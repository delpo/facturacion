package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultFormatter;

import facturacion.Cliente;
import facturacion.ExcepcionNIFnoValido;
import facturacion.NIF;
import facturacion.Operador;

public class EscuchadorBotonEmitirFactura implements ActionListener {

	JFrame ventana_paso1;
	JTextField nif;
	Operador op;
	JSpinner picker;
	JSpinner picker1;
	JSpinner picker2;
	Calendar emision = new GregorianCalendar();
	Calendar inicio = new GregorianCalendar();
	Calendar fin = new GregorianCalendar();

	public EscuchadorBotonEmitirFactura(JFrame ventana_paso1, Operador op, JTextField nif){
		this.ventana_paso1 = ventana_paso1;
		this.op = op;
		this.nif = nif;
		emision.setTime(new Date());
		inicio.setTime(new Date());
		fin.setTime(new Date());
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		boolean todo_ok = true;
		ventana_paso1.dispose();
		NIF nif_valido = null;
		try {
			nif_valido = new NIF(nif.getText());
			System.out.println("NIF válido.");
			todo_ok = false;
			for(Entry<NIF, Cliente> cliente : op.getClientes().entrySet()){
				if(cliente.getKey().toString().equals(nif_valido.toString())){
					todo_ok = true;
				}
			}
		} catch (ExcepcionNIFnoValido e) {
			todo_ok = false;
		}
		if(todo_ok){
			JFrame ventana = new JFrame("Emitir factura (paso 2 de 4)");

			String html = "<html>" +
					"<b>Seleccione fechas:</b><br/>" +
					" <i>A partir de estas fechas se calculará el</i><br/>" +
					" <i>coste de las llamadas en el siguiente paso.</i><br/>" +
					" ----------------------------------------------<br/>" +
					"</html>";
			JLabel etiqueta = new JLabel(html);
			ventana.getContentPane().add(etiqueta, BorderLayout.NORTH);
			ventana.setAlwaysOnTop(true);
			JPanel panel = new JPanel();
			JLabel fecha_emision = new JLabel("Fecha de emisión: ");
			panel.add(fecha_emision, BorderLayout.WEST);
			
			final JSpinner picker = new JSpinner( new SpinnerDateModel() );
			JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(picker, "dd-MM-yyyy");
			picker.setEditor(timeEditor);
			picker.setValue(new Date()); // will only show the current time
			JComponent comp = picker.getEditor();
		    JFormattedTextField field = (JFormattedTextField) comp.getComponent(0);
		    DefaultFormatter formatter = (DefaultFormatter) field.getFormatter();
		    formatter.setCommitsOnValidEdit(true);
		    picker.addChangeListener(new ChangeListener() {

		        @Override
		        public void stateChanged(ChangeEvent e) {
		           SpinnerModel dateModel = picker.getModel();
		           emision.setTime(((SpinnerDateModel)dateModel).getDate());
		        }
		    });
			panel.add(picker, BorderLayout.EAST);
			
			JLabel fecha_inicio = new JLabel("Fecha de inicio: ");
			panel.add(fecha_inicio, BorderLayout.WEST);
			final JSpinner picker1 = new JSpinner( new SpinnerDateModel() );
			JSpinner.DateEditor timeEditor1 = new JSpinner.DateEditor(picker1, "dd-MM-yyyy");
			picker1.setEditor(timeEditor1);
			picker1.setValue(new Date()); // will only show the current time
			JComponent comp1 = picker1.getEditor();
		    JFormattedTextField field1 = (JFormattedTextField) comp1.getComponent(0);
		    DefaultFormatter formatter1 = (DefaultFormatter) field1.getFormatter();
		    formatter1.setCommitsOnValidEdit(true);
		    picker1.addChangeListener(new ChangeListener() {

		        @Override
		        public void stateChanged(ChangeEvent f) {
		           SpinnerModel dateModel1 = picker1.getModel();
		           inicio.setTime(((SpinnerDateModel)dateModel1).getDate());
		        }
		    });
			panel.add(picker1, BorderLayout.EAST);
			
			JLabel fecha_fin = new JLabel("Fecha final: ");
			panel.add(fecha_fin, BorderLayout.WEST);
			final JSpinner picker2 = new JSpinner( new SpinnerDateModel() );
			JSpinner.DateEditor timeEditor2 = new JSpinner.DateEditor(picker2, "dd-MM-yyyy");
			picker2.setEditor(timeEditor2);
			picker2.setValue(new Date()); // will only show the current time
			JComponent comp2 = picker1.getEditor();
		    JFormattedTextField field2 = (JFormattedTextField) comp2.getComponent(0);
		    DefaultFormatter formatter2 = (DefaultFormatter) field2.getFormatter();
		    formatter2.setCommitsOnValidEdit(true);
		    picker2.addChangeListener(new ChangeListener() {

		        @Override
		        public void stateChanged(ChangeEvent g) {
		           SpinnerModel dateModel2 = picker2.getModel();
		           fin.setTime(((SpinnerDateModel)dateModel2).getDate());
		        }
		    });
			panel.add(picker2, BorderLayout.EAST);
			ventana.getContentPane().add(panel);
			JButton boton_aceptar = new JButton("Aceptar");
			boton_aceptar.addActionListener(new EscuchadorBotonEmitirFacturaFechas(ventana, op, nif_valido, emision,
					inicio, fin));//Registro escuchador
			ventana.getContentPane().add(boton_aceptar, BorderLayout.SOUTH);
			ventana.setResizable(false);
			ventana.setLocationRelativeTo(null);
			ventana.pack();
			ventana.setVisible(true);
			ventana.setSize(300, 230);
		}else{
			JOptionPane.showMessageDialog(null, "El NIF/NIE no es de un cliente existente o no es válido.");
		}
	}
}
