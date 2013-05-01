package gui;

import java.awt.Canvas;
import java.awt.Container;

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
		
		mainWindow.setVisible(true);
		mainWindow.setExtendedState(JFrame.MAXIMIZED_BOTH);
		mainWindow.setResizable(false);

		Container container = mainWindow.getContentPane();

		canvas = new Canvas();
		container.add(canvas);

		JPanel components = cargarComponentes();
		container.add(components);
		MenuSuperior menu = cargarBarraMenus();
		mainWindow.add(menu.getMenu());
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
		JTabbedPane pestanyas = new JTabbedPane();
		pestanyas.add("Clientes", crearPanelClientes());
		pestanyas.add("Facturas", new PanelFacturas());
		pestanyas.add("Incidencias", new PanelIncidencias());
		return pestanyas;
	}

	@Override
	public PanelClientes crearPanelClientes() {
		return new PanelClientes();
	}

	@Override
	public void crearPanelFacturas() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void crearPanelIncidencias() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public JPanel cargarComponentes() {
		JPanel components = new JPanel();
		return components;
	}
	
	private void showWindow() {
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.pack();
	}

}
