package gui;

import javax.swing.JPanel;

public interface Vista {
	
	//CREO GUI

	public void crearGUI();
	
	//CREO COMPONENTES
	public JPanel cargarComponentes();
	
	//CREO BARRA MENUS
	
	public void cargarBarraMenus();
	
	//CREO PESTAÑAS
	
	public void crearPaneles();
	
	//SETEAR PESTAÑAS
	
	public void crearPanelClientes();
	
	public void crearPanelFacturas();
	
	public void crearPanelIncidencias();
	
}
