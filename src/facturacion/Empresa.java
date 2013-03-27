package facturacion;

public class Empresa extends Cliente {

	public static final double IVA = 0.16;

	public Empresa(String nombre, NIF nif, Direccion direccion, Email email, Tarifa tarifa){
		this.setNombre(nombre);
		this.setNif(nif);
		this.setDireccion(direccion);
		this.setEmail(email);
		this.setTarifa(tarifa);
	}
	
	@Override
	public void listarCliente(){
		System.out.println("Empresa: "+this.getNombre());
		System.out.println("NIF: "+this.getNif().toString());
		System.out.println("Dirección: "+this.getDireccion().toString());
		System.out.println("Email: "+this.getEmail().toString());
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
