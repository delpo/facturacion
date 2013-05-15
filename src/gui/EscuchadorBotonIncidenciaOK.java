package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import facturacion.Averia;
import facturacion.Impago;
import facturacion.Incidencia;
import facturacion.NIF;
import facturacion.Operador;
import facturacion.SolicitudPortabilidad;

public class EscuchadorBotonIncidenciaOK implements ActionListener {
	
	JFrame ventana;
	Operador op;
	NIF nif;
	int eleccion;
	Calendar fecha;

	public EscuchadorBotonIncidenciaOK(JFrame ventana, Operador op, NIF nif, int eleccion, Calendar fecha) {
		this.ventana = ventana;
		this.op = op;
		this.nif = nif;
		this.eleccion = eleccion;
		this.fecha = fecha;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ventana.dispose();
        System.out.println("Elección: "+eleccion);
		Incidencia incidencia = null;
		if(eleccion == 0){
			JOptionPane.showMessageDialog(null, "ERROR: No has seleccionado ningún tipo de incidencia.");
		}else if(eleccion == 1){
			//impago
			incidencia = new Impago(fecha);
		}else if(eleccion == 2){
			//avería
			incidencia = new Averia(fecha);
		}else if(eleccion == 3){
			//solicitud de portabilidad
			incidencia = new SolicitudPortabilidad(fecha);
		}
		op.darDeAltaIncidencia(nif, incidencia);
		if(eleccion != 0)
			JOptionPane.showMessageDialog(null, "Operación completada.");
	}

}
