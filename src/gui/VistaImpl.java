package gui;

import java.awt.Canvas;
import java.awt.Container;
import java.awt.event.KeyEvent;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import facturacion.Operador;

public class VistaImpl implements Vista{

	private Canvas canvas;
	private JFrame mainWindow;
	private Operador operador;

	
	public VistaImpl(Operador op){
		this.operador = op;
	}
	
	@Override
	public void crearGUI() {
		mainWindow = new JFrame("FactuTel");
		Container container = mainWindow.getContentPane();

		canvas = new Canvas();
		container.add(canvas);

		JPanel components = cargarComponentes();
		container.add(components);
		MenuSuperior menu = cargarBarraMenus();
		mainWindow.add(menu.getMenu());
		mainWindow.setJMenuBar(menu.getMenu());
		
		JTabbedPane pestanyas = crearPaneles();
		mainWindow.add(pestanyas);
		mainWindow.setVisible(true);
		showWindow();
	}

	@Override
	public MenuSuperior cargarBarraMenus() {
		@SuppressWarnings("static-access")
		int ancho = mainWindow.WIDTH;
		MenuSuperior menu = new MenuSuperior(operador, ancho);
		return menu;
	}

	@Override
	public JTabbedPane crearPaneles() {
		JTabbedPane tabbedPane = new JTabbedPane();
		
		JComponent panel_clientes = crearPanelClientes();
        tabbedPane.addTab("Clientes", null, panel_clientes,
                "Abre las opciones para manejar clientes");
        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
         
        JComponent panel2 = crearPanelFacturas();
        tabbedPane.addTab("Facturas", null, panel2,
                "Abre las opciones para manejar facturas");
        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
         
        JComponent panel3 = crearPanelIncidencias();
        tabbedPane.addTab("Incidencias", null, panel3,
                "Abre las opciones para manejar incidencias");
        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
        
		return tabbedPane;
	}

	@Override
	public JComponent crearPanelClientes() {
		return new PanelClientes(operador);
	}

	@Override
	public JComponent crearPanelFacturas() {
		return new PanelFacturas();
	}

	@Override
	public JComponent crearPanelIncidencias() {
		return new PanelIncidencias();
	}

	@Override
	public JPanel cargarComponentes() {
		JPanel components = new JPanel();
		return components;
	}
	
	private void showWindow() {
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.pack();
        mainWindow.setSize(1024, 600);
        mainWindow.setResizable(false);
        mainWindow.setVisible(true);
	}

}
