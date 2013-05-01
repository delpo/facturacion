package gui;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public interface Vista {
	
	//CREO GUI

	public void crearGUI();
	
	//CREO COMPONENTES
	public JPanel cargarComponentes();
	
	//CREO BARRA MENUS
	
	public MenuSuperior cargarBarraMenus();
	
	//CREO PESTAÑAS
	
	public JTabbedPane crearPaneles();
	
	//SETEAR PESTAÑAS
	
	public JComponent crearPanelClientes();
	
	public JComponent crearPanelFacturas();
	
	public JComponent crearPanelIncidencias();
	
}
