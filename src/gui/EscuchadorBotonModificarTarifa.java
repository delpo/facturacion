package gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Map.Entry;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import facturacion.Cliente;
import facturacion.ExcepcionNIFnoValido;
import facturacion.NIF;
import facturacion.Operador;

public class EscuchadorBotonModificarTarifa implements ActionListener {
	JFrame ventana_anterior;
	Operador op;
	JTextField nif;
	JCheckBox tarde;
	JCheckBox domingo;

	public EscuchadorBotonModificarTarifa(JFrame ventana_anterior, Operador op, JTextField nif) {
		this.ventana_anterior = ventana_anterior;
		this.op = op;
		this.nif = nif;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ventana_anterior.dispose();
		boolean todo_ok = true;
		NIF nif_valido = null;
		try {
			nif_valido = new NIF(nif.getText());
		} catch (ExcepcionNIFnoValido e) {
			JOptionPane.showMessageDialog(null, "NIF/NIE NO VÁLIDO.");
			todo_ok = false;
		}
		if(todo_ok){
			String tarifa_actual = null;
			for(Entry<NIF, Cliente> cliente : op.getClientes().entrySet()){
				System.out.println(nif_valido.toString());
				if(cliente.getKey().toString().equals(nif_valido.toString())){
					tarifa_actual = cliente.getValue().getTarifa().getNombre(); //me lo guardo para mostrar tarifas actuales
				}
			}
			if(tarifa_actual == null){
				JOptionPane.showMessageDialog(null, "No se encontró cliente con el NIF/NIE introducido.");
			}else{
				JFrame ventana_selector = new JFrame("Añadir tarifa");
				Container contenedor = ventana_selector.getContentPane();
				contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
				String html = "<html>" +
		                "<b>Paso 2: </b><br/>" +
		                " <i>Seleccionar tarifa.</i><br/>--------------------------" +
		                "</html>";
		    	JLabel etiqueta = new JLabel(html);
		    	ventana_selector.getContentPane().add(etiqueta, BorderLayout.NORTH);
		    	ventana_selector.setAlwaysOnTop(true);
		    	JLabel tarifas_actuales = new JLabel("<html>"+"Tarifas actuales: "+tarifa_actual+"<br/>--------------------------"+"</html>",
		    	        SwingConstants.CENTER);
		    	ventana_selector.getContentPane().add(tarifas_actuales);
		    	//do stuff
		    	tarde = new JCheckBox("Tarifa de tarde");
		    	domingo = new JCheckBox("Tarifa de domingo");
		    	ManejadorEventos manejador= new ManejadorEventos(nif_valido);
		    	tarde.addItemListener(manejador);
		    	domingo.addItemListener(manejador);
		    	JPanel tarifas = new JPanel();
		    	tarifas.setLayout(new BoxLayout(tarifas, BoxLayout.PAGE_AXIS));
		    	contenedor.add(tarifas);//Cambiamos el panel contenedor
		    	tarifas.add(new JLabel("Elije tarifas a añadir:"));
		    	tarifas.add(tarde);
		    	tarifas.add(domingo);
		    	
		    	//Botón de aceptar
		    	JButton ok = new JButton("Aceptar");
		    	ok.addActionListener(new EscuchadorBotonModificarTarifaOK(ventana_selector));

		    	ventana_selector.getContentPane().add(ok);
		    	
		    	
		    	ventana_selector.setSize(370, 500);
		    	ventana_selector.setResizable(false);
		    	ventana_selector.setLocationRelativeTo(null);
		    	ventana_selector.pack();
		    	ventana_selector.setVisible(true);
			}
		}else{
			JOptionPane.showMessageDialog(null, "ERROR: DNI NO VÁLIDO.");
		}
	}
	
	private class ManejadorEventos implements ItemListener {
		NIF nif_valido;
		public ManejadorEventos(NIF nif_valido){
			this.nif_valido = nif_valido;
		}
		
		public void itemStateChanged(ItemEvent arg0) {
			if(tarde.isSelected()){
				op.anyadirTarifa(nif_valido,0);
			}
			if(domingo.isSelected()){
				op.anyadirTarifa(nif_valido,1);
			}
		}
	}

}
