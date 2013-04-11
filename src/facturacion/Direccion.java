package facturacion;

import java.io.Serializable;

public class Direccion implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int codigo_postal;
	private String provincia;
	private String poblacion;
	
	public Direccion(int codigo_postal, String provincia, String poblacion){
		this.codigo_postal = codigo_postal;
		this.provincia = provincia;
		this.poblacion = poblacion;
	}
	
	public int getCodigo_postal() {
		return codigo_postal;
	}
	public void setCodigo_postal(int codigo_postal) {
		this.codigo_postal = codigo_postal;
	}
	public String getProvincia() {
		return provincia;
	}
	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
	public String getPoblacion() {
		return poblacion;
	}
	public void setPoblacion(String poblacion) {
		this.poblacion = poblacion;
	}
	public String toString(){
		return "CP: "+codigo_postal+"\nProvincia: "+provincia+"\nPoblación: "+poblacion+"\n";
	}
}
