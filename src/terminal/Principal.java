package terminal;

import java.io.Serializable;

import terminal.Prompt;
//import es.uji.www.GeneradorDatosINE;

public class Principal implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Principal() {
		super();
	}


	private void ejecutar() {
		Prompt prompt = new Prompt();
		prompt.iniciarPrompt();
	}

	public static void main(String[] args) {
		new Principal().ejecutar();
	}
}
