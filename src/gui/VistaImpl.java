package gui;

import java.awt.Canvas;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class VistaImpl implements Vista{
	private static final int CANVAS_HORIZONTAL_SIZE = 800;
	private static final int CANVAS_VERTICAL_SIZE = 600;

	private Canvas canvas;
	private JFrame mainWindow;

	@Override
	public void crearGUI() {
		mainWindow = new JFrame("FactuTel");

		Container container = mainWindow.getContentPane();

		canvas = new Canvas();
		container.add(canvas);

		JPanel components = cargarComponentes();
		container.add(components);

		showWindow();
	}

	@Override
	public void cargarBarraMenus() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void crearPaneles() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void crearPanelClientes() {
		// TODO Auto-generated method stub
		
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
		// TODO Auto-generated method stub
		return null;
	}
	
	private void showWindow() {
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.pack();
		mainWindow.setVisible(true);
	}

}
