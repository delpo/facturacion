package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import facturacion.Operador;

import terminal.Datos;

public class MenuSuperior extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Operador op;
	private JMenuBar mb;
    private JMenu menu1;
    private JMenuItem mi1,mi2,mi3;
    private JMenu menu2;
    private JMenuItem mi1_2;
    
    public MenuSuperior(Operador op, int ancho) {
        this.op = op;
        mb=new JMenuBar();
        mb.setSize(ancho, 50);
        setJMenuBar(mb);
        //ARCHIVO
        menu1=new JMenu("Archivo");
        mb.add(menu1);
        mi1=new JMenuItem("Cargar últimos datos");
        mi1.addActionListener(this);
        menu1.add(mi1);
        mi2=new JMenuItem("Almacenar datos actuales");
        mi2.addActionListener(this);
        menu1.add(mi2);
        mi3=new JMenuItem("Salir");
        mi3.addActionListener(this);
        menu1.add(mi3);
        //AYUDA
        menu2=new JMenu("Ayuda");
        mb.add(menu2);
        mi1_2=new JMenuItem("Acerca de");
        mi1_2.addActionListener(this);
        menu2.add(mi1_2);
    }

	@Override
	public void actionPerformed(ActionEvent e) {
    	Datos dato = new Datos();
        if (e.getSource()==mi1) {
        	op = dato.recuperarDatos();
        }
        if (e.getSource()==mi2) {
    		dato.almacenarDatos(op);
        }
        if (e.getSource()==mi3) {
        	System.exit(0); 
        } 
        if (e.getSource()==mi1_2) {
        	JOptionPane.showMessageDialog(this, "© Ángel Carlos del Pozo Muela, 2013.", "Acerca de", JOptionPane.WARNING_MESSAGE);
        } 
	}

	public JMenuBar getMenu(){
		return this.mb;
	}
}
