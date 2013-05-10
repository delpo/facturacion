package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import facturacion.ExcepcionNIFnoValido;
import facturacion.NIF;
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
		ventana.dispose();
		boolean todo_ok = true;
		NIF nif_valido = null;
		try {
			nif_valido = new NIF(nif.getText());
		} catch (ExcepcionNIFnoValido e) {
			JOptionPane.showMessageDialog(null, "NIF NO VÁLIDO.");
			todo_ok = false;
		}
		if(todo_ok){
			JFrame ventana = new JFrame("Añadir tarifa");
			Container contenedor = ventana.getContentPane();
			contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
			String html = "<html>" +
	                "<b>Paso 2: </b><br/>" +
	                " <i>Seleccionar tarifa.</i><br/>--------------------------" +
	                "</html>";
	    	JLabel etiqueta = new JLabel(html);
	    	ventana.getContentPane().add(etiqueta, BorderLayout.NORTH);
	    	ventana.setAlwaysOnTop(true);
			
	    	//do stuff
	    	JCheckBox tarde = new JCheckBox("Tarifa de tarde");
	    	JCheckBox domingo = new JCheckBox("Tarifa de domingo");
	    	JPanel tarifas = new JPanel();
	    	tarifas.setLayout(new BoxLayout(tarifas, BoxLayout.PAGE_AXIS));
	    	contenedor.add(tarifas);//Cambiamos el panel contenedor
	    	tarifas.add(new JLabel("Elije tarifas a añadir:"));
	    	tarifas.add(tarde);
	    	tarifas.add(domingo);
	    	ventana.pack();
	    	ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    	ventana.setVisible(true);
	    	
	    	//Botón de aceptar
	    	JButton ok = new JButton("Aceptar");

	    	//ok.addActionListener(new EscuchadorBotonEmpresaOK(ventana, op, nombre, nif, cp, poblacion, provincia, email));

	    	ventana.getContentPane().add(ok);
	    	
	    	
			ventana.setSize(370, 500);
		    ventana.setResizable(false);
		    ventana.setLocationRelativeTo(null);
			ventana.pack();
			ventana.setVisible(true);
		}else{
			JOptionPane.showMessageDialog(null, "ERROR: DNI NO VÁLIDO.");
		}
	}

}