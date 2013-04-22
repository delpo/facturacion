package facturacion;

public class Llamada {
	private int telefono;
	private Fecha fecha_llamada;
	private int duracion; //en segundos
	private Hora hora;
	
	public Llamada(){
		super();
	}
	
	public Llamada(int telefono, Fecha fecha, int duracion, Hora hora){
		this.telefono = telefono;
		this.fecha_llamada = fecha;
		this.duracion = duracion;
		this.hora = hora;
	}

	public int getTelefono() {
		return telefono;
	}

	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}

	public Fecha getFecha_llamada() {
		return fecha_llamada;
	}

	public void setFecha_llamada(Fecha fecha_llamada) {
		this.fecha_llamada = fecha_llamada;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public Hora getHora() {
		return hora;
	}

	public void setHora(Hora hora) {
		this.hora = hora;
	}
}
