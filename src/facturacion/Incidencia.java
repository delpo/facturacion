package facturacion;

public abstract class Incidencia {
	private boolean resuelta;

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
}
