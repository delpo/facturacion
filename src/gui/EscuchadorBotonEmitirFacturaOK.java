package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
		JFrame ventana = new JFrame("Emitir factura (paso 4 de 4)");
		String html = "<html>" +
				"<b>Factura emitida:</b><br/>" +
				" <i>-Se ha enviado un e-mail al cliente con la factura.</i><br/>" +
				" <i>-Se ha actualizado la tabla de facturas con la nueva.</i><br/>" +
				" -------------------------------------------------------------------<br/>" +
				"</html>";
		JLabel etiqueta = new JLabel(html);
		ventana.getContentPane().add(etiqueta, BorderLayout.NORTH);
		ventana.setAlwaysOnTop(true);
		JPanel panel = new JPanel();
		JLabel fin = new JLabel("Tarea completada con éxito. No olvide guardar los cambios si está conforme.");
		panel.add(fin, BorderLayout.CENTER);
		ventana.getContentPane().add(panel);
		ventana.setResizable(false);
		ventana.setLocationRelativeTo(null);
		ventana.pack();
		ventana.setVisible(true);
		ventana.setSize(550, 130);
	}

}
