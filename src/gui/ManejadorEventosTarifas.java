package gui;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JCheckBox;

public class ManejadorEventosTarifas implements ItemListener{
	
	JCheckBox tarde;
	JCheckBox domingo;
	boolean tarde_activado;
	boolean domingo_activado;

	public ManejadorEventosTarifas(JCheckBox tarde, JCheckBox domingo, boolean tarde_activado, boolean domingo_activado){
		this.tarde = tarde;
		this.domingo = domingo;
		this.tarde_activado = tarde_activado;
		this.domingo_activado = domingo_activado;
	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		if(tarde.isSelected() && domingo.isSelected()){
			tarde_activado = true;
			domingo_activado = true;
		}else if(tarde.isSelected()){
			System.out.println("tarde seleccionado");
			tarde_activado = true;
			domingo_activado = false;
		}else if(domingo.isSelected()){
			System.out.println("domingo seleccionado");
			domingo_activado = true;
			tarde_activado = false;
		}else{
			System.out.println("nada seleccionado");
			domingo_activado = false;
			tarde_activado = false;
		}
	}

}
