package gui;

import java.awt.BorderLayout;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

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
    private JMenu menu3;
    private JMenuItem mi1_3, mi2_2, mi3_2;
    private JMenu menu4;
    private JMenuItem mi1_4, mi2_4;

	private JMenu menu5;

	private JMenuItem mi1_5;

	private JMenuItem mi2_5;
    
    public MenuSuperior(Operador op, int ancho) {
        this.op = op;
        mb=new JMenuBar();
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
        //CLIENTES
        menu2=new JMenu("Clientes");
        mb.add(menu2);
        mi1_2=new JMenuItem("Añadir cliente");
        mi1_2.addActionListener(this);
        menu2.add(mi1_2);
        mi2_2=new JMenuItem("Eliminar cliente");
        mi2_2.addActionListener(this);
        menu2.add(mi2_2);
        mi3_2=new JMenuItem("Añadir tarifa a cliente");
        mi3_2.addActionListener(this);
        menu2.add(mi3_2);
        //FACTURAS
        menu4=new JMenu("Facturas");
        mb.add(menu4);
        mi1_4=new JMenuItem("Emitir factura");
        mi1_4.addActionListener(this);
        menu4.add(mi1_4);
        mi2_4=new JMenuItem("Eliminar factura");
        mi2_4.addActionListener(this);
        menu4.add(mi2_4);
        //INCIDENCIAS
        menu5=new JMenu("Incidencias");
        mb.add(menu5);
        mi1_5=new JMenuItem("Añadir incidencia");
        mi1_5.addActionListener(this);
        menu5.add(mi1_5);
        mi2_5=new JMenuItem("Eliminar incidencia");
        mi2_5.addActionListener(this);
        menu5.add(mi2_5);
        //AYUDA
        menu3=new JMenu("Ayuda");
        mb.add(menu3);
        mi1_3=new JMenuItem("Acerca de");
        mi1_3.addActionListener(this);
        menu3.add(mi1_3);
        mb.setSize(ancho, 50);
        mb.setVisible(true);
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
        	JFrame ventana = new JFrame("Añadir cliente");
        	        	
        	String html = "<html>" +
                    "<b>Paso 1: </b><br/>" +
                    " <i>Seleccionar tipo de cliente.</i><br/>" +
                    "</html>";
        	JLabel etiqueta = new JLabel(html);
        	ventana.getContentPane().add(etiqueta, BorderLayout.NORTH);
        	ventana.setAlwaysOnTop(true);
        	
        	JButton boton_particular = new JButton("Particular");

        	boton_particular.addActionListener(new EscuchadorBotonParticular(ventana, op));//Registro escuchador

        	ventana.getContentPane().add(boton_particular, BorderLayout.EAST);
        	
        	JButton boton_empresa = new JButton("Empresa");

        	boton_empresa.addActionListener(new EscuchadorBotonEmpresa(ventana, op));//Registro escuchador

        	ventana.getContentPane().add(boton_empresa, BorderLayout.WEST);
        	
        	ventana.setSize(500, 500);
        	ventana.setResizable(false);
        	ventana.setLocationRelativeTo(null);
        	ventana.pack();
        	ventana.setVisible(true);

        } 
        if (e.getSource()==mi2_2) {
        	JFrame ventana = new JFrame("Eliminar cliente");
        	String html = "<html>" +
                    "<b>Eliminar cliente:</b><br/>" +
                    " <i>Escribe el NIF/NIE para eliminar cliente.</i><br/>" +
                    " <i>Con click derecho en el cuadro, puedes pegar el NIF/NIE si lo</i><br/>" +
                    " <i>has copiado. Puedes emplear click derecho en la tabla de clientes.</i><br/>" +
                    " <i>-----------------------------------------------------------------</i><br/>" +
                    "</html>";
        	JLabel etiqueta = new JLabel(html);
        	ventana.getContentPane().add(etiqueta, BorderLayout.NORTH);
        	ventana.setAlwaysOnTop(true);
        	
        	//do stuff
        	final JTextField nif = new JTextField(10);
        	JPopupMenu popup = new JPopupMenu();
        	nif.add(popup);
        	nif.setComponentPopupMenu(popup);
        	JMenuItem pegar = new JMenuItem("Pegar");
        	popup.add(pegar);
        	pegar.addActionListener(new ActionListener() {
    			
    			@Override
    			public void actionPerformed(ActionEvent arg0) {
    				Clipboard clipboard = getToolkit ().getSystemClipboard ();
    				Transferable clipData = clipboard.getContents(clipboard);
    	            if (clipData != null) {
    	              try {
    	                if 
    	                  (clipData.isDataFlavorSupported
    					    (DataFlavor.stringFlavor)) {
    	                      String s = (String)(clipData.getTransferData(
    	                        DataFlavor.stringFlavor));
    	                  nif.setText(s);
    	                }
    	              } catch (UnsupportedFlavorException ufe) {
    	                System.err.println("Flavor unsupported: " + ufe);
    	              } catch (IOException ioe) {
    	                System.err.println("Data not available: " + ioe);
    	              }
    	            }
    	          }
        	}
    		);
    		JLabel nifLabel = new JLabel("NIF: ");
    		ventana.getContentPane().add(nifLabel);
    		ventana.getContentPane().add(nif);
    		JButton boton_eliminar = new JButton("Eliminar");
        	boton_eliminar.addActionListener(new EscuchadorBotonEliminarCliente(ventana, op, nif));//Registro escuchador

        	ventana.getContentPane().add(boton_eliminar, BorderLayout.EAST);
        	
        	ventana.setSize(500, 500);
        	ventana.setResizable(false);
        	ventana.setLocationRelativeTo(null);
        	ventana.pack();
        	ventana.setVisible(true);
        }
        if(e.getSource()==mi3_2){
        	//do stuff
        	JFrame ventana = new JFrame("Añadir tarifa a cliente");
        	
        	String html = "<html>" +
                    "<b>Seleccione las tarifas a añadir:</b><br/>" +
                    " <i>Escribe el NIF/NIE para añadir tarifa.</i><br/>" +
                    " <i>Las tarifas seleccionadas se complementarán</i><br/>" +
                    " <i>con la tarifa normal.</i><br/>" +
                    " ----------------------------------------------<br/>" +
                    "</html>";
        	JLabel etiqueta = new JLabel(html);
        	ventana.getContentPane().add(etiqueta, BorderLayout.NORTH);
        	ventana.setAlwaysOnTop(true);
        	ventana.setSize(500, 500);
        	JTextField nif = new JTextField(10);
    		JLabel nifLabel = new JLabel("NIF: ");
    		ventana.getContentPane().add(nifLabel, BorderLayout.WEST);
    		ventana.getContentPane().add(nif);
    		JButton boton_aceptar = new JButton("Aceptar");
        	boton_aceptar.addActionListener(new EscuchadorBotonModificarTarifa(ventana, op, nif));//Registro escuchador
        	ventana.getContentPane().add(boton_aceptar, BorderLayout.SOUTH);
        	ventana.setResizable(false);
        	ventana.setLocationRelativeTo(null);
        	ventana.pack();
        	ventana.setVisible(true);
        }
        if (e.getSource()==mi1_3) {
        	String html = "<html>" +
                    "<b>FactuTel versión 0.78 RC</b><br/>" +
                    " <i>© Ángel Carlos del Pozo Muela, 2013.</i><br/>" +
                    " <br/>" +
                    " <i>&#171;Siempre que te pregunten si puedes hacer un trabajo,</i><br/>" +
                    " <i>contesta que sí y ponte enseguida a aprender como se hace&#187;.</i><br/>" +
                    "<i>                                             - Franklin D. Roosevelt</i><br/>"+
                    "</html>";
        	JOptionPane.showMessageDialog(this, html, "Acerca de", JOptionPane.INFORMATION_MESSAGE);
        } 
        
        if(e.getSource() == mi1_4){
        	//do stuff
        	JFrame ventana = new JFrame("Emitir factura (paso 1 de 4)");
        	
        	String html = "<html>" +
                    "<b>Seleccione las tarifas a añadir:</b><br/>" +
                    " <i>Escribe el NIF/NIE del cliente al que</i><br/>" +
                    " <i>se va a emitir factura.</i><br/>" +
                    " ----------------------------------------------<br/>" +
                    "</html>";
        	JLabel etiqueta = new JLabel(html);
        	ventana.getContentPane().add(etiqueta, BorderLayout.NORTH);
        	ventana.setAlwaysOnTop(true);
        	JTextField nif = new JTextField(10);
    		JLabel nifLabel = new JLabel("NIF: ");
    		ventana.getContentPane().add(nifLabel, BorderLayout.WEST);
    		ventana.getContentPane().add(nif);
    		JButton boton_aceptar = new JButton("Aceptar");
        	boton_aceptar.addActionListener(new EscuchadorBotonEmitirFactura(ventana, op, nif));//Registro escuchador
        	ventana.getContentPane().add(boton_aceptar, BorderLayout.SOUTH);
        	ventana.setResizable(false);
        	ventana.setLocationRelativeTo(null);
        	ventana.pack();
        	ventana.setVisible(true);
        	ventana.setSize(300, 145);
        }
        if(e.getSource() == mi2_4){
        	JFrame ventana = new JFrame("Eliminar factura");
        	
        	String html = "<html>" +
                    "<b>Eliminar factura: </b><br/>" +
                    " <i>Escribe el código de la factura para eliminarla.</i><br/>" +
                    " <i>Con click derecho en el cuadro, puedes pegar el código si lo</i><br/>" +
                    " <i>has copiado. Puedes emplear click derecho en la tabla de facturas.</i><br/>" +
                    " <i>-----------------------------------------------------------------</i><br/>" +
                    "</html>";
        	JLabel etiqueta = new JLabel(html);
        	ventana.getContentPane().add(etiqueta, BorderLayout.NORTH);
        	ventana.setAlwaysOnTop(true);
        	
        	//do stuff
        	final JTextField cod = new JTextField(10);
        	JPopupMenu popup = new JPopupMenu();
        	cod.add(popup);
        	cod.setComponentPopupMenu(popup);
        	JMenuItem pegar = new JMenuItem("Pegar");
        	popup.add(pegar);
        	pegar.addActionListener(new ActionListener() {
    			
    			@Override
    			public void actionPerformed(ActionEvent arg0) {
    				Clipboard clipboard = getToolkit ().getSystemClipboard ();
    				Transferable clipData = clipboard.getContents(clipboard);
    	            if (clipData != null) {
    	              try {
    	                if 
    	                  (clipData.isDataFlavorSupported
    					    (DataFlavor.stringFlavor)) {
    	                      String s = (String)(clipData.getTransferData(
    	                        DataFlavor.stringFlavor));
    	                  cod.setText(s);
    	                }
    	              } catch (UnsupportedFlavorException ufe) {
    	                System.err.println("Flavor unsupported: " + ufe);
    	              } catch (IOException ioe) {
    	                System.err.println("Data not available: " + ioe);
    	              }
    	            }
    	          }
        	}
    		);
    		JLabel nifLabel = new JLabel("Código de factura: ");
    		ventana.getContentPane().add(nifLabel);
    		ventana.getContentPane().add(cod);
    		JButton boton_eliminar = new JButton("Eliminar");
        	boton_eliminar.addActionListener(new EscuchadorBotonEliminarFactura(ventana, op, cod));//Registro escuchador

        	ventana.getContentPane().add(boton_eliminar, BorderLayout.EAST);
        	
        	ventana.setResizable(false);
        	ventana.setLocationRelativeTo(null);
        	ventana.pack();
        	ventana.setSize(500, 140);
        	ventana.setVisible(true);
        }
        if(e.getSource() == mi1_5){
        	//añadir incidencia a cliente
        	JFrame ventana = new JFrame("Añadir incidencia a cliente (paso 1 de 2)");
        	
        	String html = "<html>" +
                    "<b>Añadir incidencia a cliente.</b><br/>" +
                    " <i>Escribe el NIF/NIE del cliente al que</i><br/>" +
                    " <i>se le va a añadir incidencia.</i><br/>" +
                    " ----------------------------------------------<br/>" +
                    "</html>";
        	JLabel etiqueta = new JLabel(html);
        	ventana.getContentPane().add(etiqueta, BorderLayout.NORTH);
        	ventana.setAlwaysOnTop(true);
        	JTextField nif = new JTextField(10);
    		JLabel nifLabel = new JLabel("NIF: ");
    		ventana.getContentPane().add(nifLabel, BorderLayout.WEST);
    		ventana.getContentPane().add(nif);
    		JButton boton_aceptar = new JButton("Aceptar");
        	boton_aceptar.addActionListener(new EscuchadorBotonIncidencia(ventana, op, nif));//Registro escuchador
        	ventana.getContentPane().add(boton_aceptar, BorderLayout.SOUTH);
        	ventana.setResizable(false);
        	ventana.setLocationRelativeTo(null);
        	ventana.pack();
        	ventana.setVisible(true);
        	ventana.setSize(300, 145);
        }
        if(e.getSource() == mi2_5){
        	//eliminar incidencia
JFrame ventana = new JFrame("Eliminar factura");
        	
        	String html = "<html>" +
                    "<b>Eliminar incidencia: </b><br/>" +
                    " <i>Escribe el código de la incidencia para eliminarla.</i><br/>" +
                    " <i>Con click derecho en el cuadro, puedes pegar el código si lo</i><br/>" +
                    " <i>has copiado. Puedes emplear click derecho en la tabla de incidencias.</i><br/>" +
                    " <i>-----------------------------------------------------------------</i><br/>" +
                    "</html>";
        	JLabel etiqueta = new JLabel(html);
        	ventana.getContentPane().add(etiqueta, BorderLayout.NORTH);
        	ventana.setAlwaysOnTop(true);
        	
        	//do stuff
        	final JTextField cod = new JTextField(10);
        	JPopupMenu popup = new JPopupMenu();
        	cod.add(popup);
        	cod.setComponentPopupMenu(popup);
        	JMenuItem pegar = new JMenuItem("Pegar");
        	popup.add(pegar);
        	pegar.addActionListener(new ActionListener() {
    			
    			@Override
    			public void actionPerformed(ActionEvent arg0) {
    				Clipboard clipboard = getToolkit ().getSystemClipboard ();
    				Transferable clipData = clipboard.getContents(clipboard);
    	            if (clipData != null) {
    	              try {
    	                if 
    	                  (clipData.isDataFlavorSupported
    					    (DataFlavor.stringFlavor)) {
    	                      String s = (String)(clipData.getTransferData(
    	                        DataFlavor.stringFlavor));
    	                  cod.setText(s);
    	                }
    	              } catch (UnsupportedFlavorException ufe) {
    	                System.err.println("Flavor unsupported: " + ufe);
    	              } catch (IOException ioe) {
    	                System.err.println("Data not available: " + ioe);
    	              }
    	            }
    	          }
        	}
    		);
    		JLabel nifLabel = new JLabel("Código de incidencia: ");
    		ventana.getContentPane().add(nifLabel);
    		ventana.getContentPane().add(cod);
    		JButton boton_eliminar = new JButton("Eliminar");
        	boton_eliminar.addActionListener(new EscuchadorBotonEliminarIncidencia(ventana, op, cod));//Registro escuchador

        	ventana.getContentPane().add(boton_eliminar, BorderLayout.EAST);
        	
        	ventana.setResizable(false);
        	ventana.setLocationRelativeTo(null);
        	ventana.pack();
        	ventana.setSize(500, 140);
        	ventana.setVisible(true);
        }
	}

	public JMenuBar getMenu(){
		return this.mb;
	}
}
