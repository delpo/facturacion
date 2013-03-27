package facturacion;

public class Particular extends Cliente {

	private String apellidos;
	public static final double IVA = 0.21;
	
	public Particular(String nombre, String apellidos, NIF nif, Direccion direccion, Email email, Tarifa tarifa){
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

	public void setApellidos(String apellidos){
		this.apellidos = apellidos;
	}
	
	@Override
	public void listarCliente(){
		System.out.println("Nombre: "+this.getNombre());
		System.out.println("Apellidos: "+this.getApellidos());
		System.out.println("NIF: "+this.getNif().toString());
		System.out.println("Dirección: "+this.getDireccion().toString());
		System.out.println(this.getEmail().toString());
		System.out.println("Tarifa: "+this.getTarifa().getNombre());	
	}
	
	@Override
	public double getIVA(){
		return IVA;
	}
	
	@Override
	public void emitirFactura(Factura factura){
		factura.setIVA(IVA);
		CodigoFactura codigo = CodigoFactura.crearCodigoFactura();
		while(!Operador_telefonia.claveValida(codigo)){
			codigo = CodigoFactura.crearCodigoFactura();
		}
		facturas.put(codigo, factura);
		new EnvioCorreo().envia(getEmail(), factura);
	}
}
