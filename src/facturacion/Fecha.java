package facturacion;

public class Fecha {
	private int dia;
	private int mes;
	private int anyo;
	
	public Fecha(int dia, int mes, int anyo){
		this.dia = dia;
		this.mes = mes;
		this.anyo = anyo;
	}

	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getMes() {
		return mes;
	}

	public void setMes(int mes) {
		this.mes = mes;
	}

	public int getAnyo() {
		return anyo;
	}

	public void setAnyo(int anyo) {
		this.anyo = anyo;
	}
	@Override
	public String toString(){
		return this.getDia()+"/"+this.getMes()+"/"+this.getAnyo();
	}
	
	public int compareTo(Fecha otraFecha){
		if(this.dia == otraFecha.dia && this.mes == otraFecha.mes && this.anyo == otraFecha.anyo){
			return 0; //son iguales
		}
		
		if(this.anyo == otraFecha.anyo){
			if(this.mes == otraFecha.mes){
				return this.dia - otraFecha.dia; //mismo mes
			}else{
				return this.mes - otraFecha.mes;  //mismo año
			}
		}else{
			return this.anyo - otraFecha.anyo;  //distinto año
		}
	}
	
}
