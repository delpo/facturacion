package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import facturacion.NIF;
import facturacion.Operador;

public class PanelClientes extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Operador op;
	private JTable tabla;
	private DefaultTableModel modelo;
	private Object columnNames[] = {"Nombre", "Apellidos", "NIF", "Población", "Código postal", "Provincia", "Email", "Tarifa"};
	private JScrollPane scrollPane;
	private JPopupMenu popup;
	private Point p;
	
	public PanelClientes(Operador op){
		this.op = op;
		crearPanel();
	}
	
	public void recargarModelo(DefaultTableModel modelo){
		ModeloTablaClientes mod = new ModeloTablaClientes();
		modelo = new DefaultTableModel(mod.llenarTabla(op), columnNames);
	}
	
	public DefaultTableModel getModelo(){
		return modelo;
	}
	
	public void regenerarModelo(){
		remove(scrollPane);
		crearPanel();
	}
	
	public void crearPanel(){
		final ModeloTablaClientes mod = new ModeloTablaClientes();
		modelo = new DefaultTableModel(mod.llenarTabla(op), columnNames);
		tabla = new JTable(modelo);
		tabla.setVisible(true);
		tabla.setEnabled(false); //no editable
		tabla.setPreferredScrollableViewportSize(new Dimension(990, 480));
		popup = new JPopupMenu();
		JMenuItem copiar = new JMenuItem("Copiar NIF/NIE"); //lo copia al portapapeles
        popup.add(copiar);
        JMenuItem eliminar = new JMenuItem("Eliminar cliente");
        popup.add(eliminar);
        copiar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
		        int row = tabla.rowAtPoint(p);
		        //copio código
		        String codigo = mod.obtenerDNI(op, row);
		        Toolkit toolkit = Toolkit.getDefaultToolkit();
				Clipboard clipboard = toolkit.getSystemClipboard();
				StringSelection strSel = new StringSelection(codigo);
				clipboard.setContents(strSel, null);
			}
		});
        eliminar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = tabla.rowAtPoint(p);
		        //int col = tabla.columnAtPoint(p);
		        //elimino factura
				NIF codigo = mod.obtenerDNICompleto(op, row);
				op.borrarCliente(codigo);
				JOptionPane.showMessageDialog(null, "Operación realizada satisfactoriamente.");
		        
			}
		});
        tabla.addMouseListener(new PopupListener());
		scrollPane = new JScrollPane(tabla);
		setLayout(new BorderLayout());
		add(scrollPane, BorderLayout.CENTER);
	}

	private class PopupListener extends MouseAdapter {
	    @Override
	    public void mousePressed(MouseEvent e) {
	        maybeShowPopup(e);
	    }

	    @Override
	    public void mouseReleased(MouseEvent e) {
	        maybeShowPopup(e);
	    }

	    private void maybeShowPopup(MouseEvent e) {
	    	int r = tabla.rowAtPoint(e.getPoint());
            if (r >= 0 && r < tabla.getRowCount()) {
                tabla.setRowSelectionInterval(r, r);
            } else {
                tabla.clearSelection();
            }

            int rowindex = tabla.getSelectedRow();
            if (rowindex < 0)
                return;
	        if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
	            popup.show(e.getComponent(), e.getX(), e.getY());
	        }
	        p = e.getPoint();
	    }
	}

}
