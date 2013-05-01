package facturacion;

import java.io.Serializable;

public class Particular extends Cliente implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String apellidos;
	public static final double IVA = 0.21;
	
	public Particular(String nombre, String apellidos, NIF nif, Direccion direccion, Email email, int tarifa){
		this.setNombre(nombre);
		this.setNif(nif);
		this.setDireccion(direccion);
		this.setEmail(email);
		this.setTarifa(tarifa);
		this.apellidos = apellidos;
	}
	
	@Override
	public String getApellidos(){
		return apellidos;
	}

	@Override
	public void setApellidos(String apellidos){
		this.apellidos = apellidos;
	}
	
	@Override
	public void listarCliente(){
		System.out.println("Nombre: "+this.getNombre());
		System.out.println("Apellidos: "+this.getApellidos());
		System.out.println("NIF: "+this.getNif().toString());
		System.out.print(this.getDireccion().toString());
		System.out.println(this.getEmail().toString());
		System.out.println("Tarifa: "+this.getTarifa().getNombre());	
	}
	
	@Override
	public double getIVA(){
		return IVA;
	}
	
	@Override
	public CodigoFactura emitirFactura(Factura factura){
		factura.setIVA(IVA);
		factura.importe = factura.calcularCoste();
		CodigoFactura codigo = CodigoFactura.crearCodigoFactura();
		Operador op = new Operador_telefonia();
		while(!op.claveValida(codigo)){
			codigo = CodigoFactura.crearCodigoFactura();
		}
		facturas.put(codigo, factura);
		new EnvioCorreo().envia(getEmail(), factura);
		return codigo;
	}
}
