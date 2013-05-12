package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import facturacion.Cliente;
import facturacion.ExcepcionNIFnoValido;
import facturacion.NIF;
import facturacion.Operador;

public class EscuchadorBotonEmitirFactura implements ActionListener {

	JFrame ventana_paso1;
	JTextField nif;
	Operador op;
	
	public EscuchadorBotonEmitirFactura(JFrame ventana_paso1, Operador op, JTextField nif){
		this.ventana_paso1 = ventana_paso1;
		this.op = op;
		this.nif = nif;
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
			//TODO Do stuff
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
        	//TODO fechas
        	
    		JButton boton_aceptar = new JButton("Aceptar");
        	//boton_aceptar.addActionListener(new EscuchadorBotonEmitirFactura(ventana, op, nif));//Registro escuchador
        	ventana.getContentPane().add(boton_aceptar, BorderLayout.SOUTH);
        	ventana.setResizable(false);
        	ventana.setLocationRelativeTo(null);
        	ventana.pack();
        	ventana.setVisible(true);
        	ventana.setSize(400, 600);
		}else{
			JOptionPane.showMessageDialog(null, "El NIF/NIE no es de un cliente existente o no es válido.");
		}
	}

}
