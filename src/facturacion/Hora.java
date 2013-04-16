package facturacion;

public class Hora {
	private int hora;
	private int minutos;
	
	public Hora(int hora, int minutos){
		this.setHora(hora);
		this.setMinutos(minutos);
	}
	
	public String toString(){
		return hora+":"+minutos;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getMinutos() {
		return minutos;
	}

	public void setMinutos(int minutos) {
		this.minutos = minutos;
	}
}
