package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import facturacion.Factura;
import facturacion.NIF;
import facturacion.Operador;

public class EscuchadorBotonEmitirFacturaOK implements ActionListener {
	JFrame ventana;
	Operador op;
	NIF nif;
	Factura factura;
	
	public EscuchadorBotonEmitirFacturaOK(JFrame ventana, Operador op, NIF nif, Factura factura) {
		this.ventana = ventana;
		this.op = op;
		this.nif = nif;
		this.factura = factura;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		ventana.dispose();
		System.out.println(factura.getPeriodoFacturacionTexto());
		op.emitirFactura(nif, factura);
	}

}
