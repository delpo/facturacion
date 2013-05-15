package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Map.Entry;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
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

public class EscuchadorBotonIncidencia implements ActionListener {
	JFrame ventana;
	Operador op;
	JTextField nif;
	int eleccion = 0;
	JSpinner picker;
	Calendar fecha = new GregorianCalendar();

	public EscuchadorBotonIncidencia(JFrame ventana, Operador op, JTextField nif) {
		this.ventana = ventana;
		this.op = op;
		this.nif = nif;

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ventana.dispose();
		//COMPROBAR DNI VÁLIDO Y DE CLIENTE
		boolean todo_ok = true;
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

			//crear incidencia y añadirla
			
			JFrame ventana = new JFrame("Añadir incidencia a cliente (paso 2 de 2)");

			String html = "<html>" +
					"<b>Añadir incidencia a cliente.</b><br/>" +
					" <i>Selecciona el tipo de incidencia que</i><br/>" +
					" <i>se va a añadir al cliente seleccionado.</i><br/>" +
					" ----------------------------------------------<br/>" +
					"</html>";
			JLabel etiqueta = new JLabel(html);
			ventana.getContentPane().add(etiqueta, BorderLayout.NORTH);
			ventana.setAlwaysOnTop(true);
			JRadioButton aButton = new JRadioButton("Impago");
			JRadioButton bButton = new JRadioButton("Avería");
			JRadioButton cButton = new JRadioButton("Solicitud de portabilidad");

			//Create a ButtonGroup object, add buttons to the group
			ButtonGroup myButtonGroup = new ButtonGroup();
			myButtonGroup.add(aButton);
			myButtonGroup.add(bButton);
			myButtonGroup.add(cButton);
			
			ActionListener action = new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	System.out.println("Valor antes: "+eleccion);
	                JRadioButton button = (JRadioButton) e.getSource();
	                if(button.getText().equals("Impago")){
	                	eleccion = 1;
	                }else if(button.getText().equals("Avería")){
	                	eleccion = 2;
	                }else{
	                	eleccion = 3;
	                }
	            	System.out.println("Valor despues: "+eleccion);
	            }
	        };
	        aButton.addActionListener(action);
	        bButton.addActionListener(action);
	        cButton.addActionListener(action);
			//Display radio buttons
			ventana.setLayout(new FlowLayout());
			ventana.add(aButton);
			ventana.add(bButton);
			ventana.add(cButton);
			JLabel fecha_emision = new JLabel("Fecha de incidencia: ");
			ventana.add(fecha_emision, BorderLayout.WEST);
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
		           fecha.setTime(((SpinnerDateModel)dateModel).getDate());
		        }
		    });
			ventana.add(picker, BorderLayout.EAST);
			JButton boton_aceptar = new JButton("Aceptar");
			boton_aceptar.addActionListener(new EscuchadorBotonIncidenciaOK(ventana, op, nif_valido, eleccion, fecha));//Registro escuchador
			ventana.getContentPane().add(boton_aceptar, BorderLayout.SOUTH);
			ventana.setResizable(false);
			ventana.setLocationRelativeTo(null);
			ventana.pack();
			ventana.setVisible(true);
			ventana.setSize(400, 170);

		}else{
			JOptionPane.showMessageDialog(null, "ERROR: El NIF/NIE no es válido.");
		}

	}
}
