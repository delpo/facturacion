package facturacion;

import java.io.Serializable;

public abstract class Incidencia implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private boolean resuelta;
	private Fecha fecha;

	public boolean estaResuelta() {
		return resuelta;
	}

	public void setResuelta(boolean resuelta) {
		this.resuelta = resuelta;
	}
	
	public void resolverIncidencia(){
		this.resuelta = true;
	}

	public String getNombreIncidencia() {
		//se sobreescribe en los tipos de incidencia
		return null;
	}

	public Fecha getFecha() {
		return fecha;
	}

	public void setFecha(Fecha fecha) {
		this.fecha = fecha;
	}
}
