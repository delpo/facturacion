package testing;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;

import org.junit.Test;

import facturacion.Cliente;
import facturacion.CodigoFactura;
import facturacion.CodigoLlamada;
import facturacion.Direccion;
import facturacion.Email;
import facturacion.ExcepcionEmailNoValido;
import facturacion.ExcepcionFacturaNoEncontrada;
import facturacion.ExcepcionNIFnoValido;
import facturacion.Factura;
import facturacion.Llamada;
import facturacion.NIF;
import facturacion.Operador;
import facturacion.Operador_telefonia;
import facturacion.Particular;
import facturacion.Periodo_facturacion;

public class AllTests {
	
	@Test
	public void testTarde() throws ExcepcionNIFnoValido, ExcepcionEmailNoValido{
		Operador operador = new Operador_telefonia();
		System.out.println("Bienvenido al programa de tests básico de clientela de operador.");
		//ALTA DE CLIENTE
		Cliente cliente = new Particular("Ángel Carlos", "del Pozo Muela", new NIF("20905219J"),
				new Direccion(12005, "Castellón", "Castellón de la Plana"), new Email("al151990@uji.es"), -1);
		operador.darAlta(cliente);
		//AÑADO TARIFAS
		operador.anyadirTarifa(new NIF("20905219J"), 0);
		operador.anyadirTarifa(new NIF("20905219J"), 1);
		//AÑADO FACTURA EN DÍA QUE NO ES DOMINGO POR LA TARDE
		Calendar fecha_emision = new GregorianCalendar();
		fecha_emision.set(2013, 4, 23);
		Calendar fecha_inicio = new GregorianCalendar();
		fecha_inicio.set(2013, 0, 1);
		Calendar fecha_fin = new GregorianCalendar();
		fecha_fin.set(2013, 11, 31);
		Periodo_facturacion periodo = new Periodo_facturacion(fecha_inicio, fecha_fin);
		HashMap<CodigoLlamada, Llamada> llamadas = new HashMap<CodigoLlamada, Llamada>();
		CodigoLlamada cod = new CodigoLlamada();
		cod = cod.crearCodigoLlamada();
		Calendar fecha_llamada = new GregorianCalendar();
		fecha_llamada.set(2013, 3, 23, 17, 20);
		llamadas.put(cod, new Llamada(964001122, fecha_llamada, 70));
		CodigoFactura codigo_fac = operador.emitirFactura(new NIF("20905219J"),
				new Factura(fecha_emision, cliente.getTarifa(), periodo, llamadas));
		BigDecimal importe = null;
		try {
			importe = operador.obtenerFactura(codigo_fac).getImporte();
		} catch (ExcepcionFacturaNoEncontrada e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//comprobar valores
	    BigDecimal big = new BigDecimal(0.1+(0.1*0.21));
	    big = big.setScale(2, RoundingMode.HALF_UP);
		assertEquals(big, importe);
	}
}
