package facturacion;

public abstract class Info {
	private Fecha fecha_emision;
	
	public Fecha getFecha() {
		return fecha_emision;
	}

	public void setFecha(Fecha fecha) {
		this.fecha_emision = fecha;
	}
}
