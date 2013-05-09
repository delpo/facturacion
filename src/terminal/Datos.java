package terminal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import facturacion.Operador;
import facturacion.Operador_telefonia;

public class Datos implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Datos(){
		
	}

	public void almacenarDatos(Operador operador){
		try{
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("basededatos.bin"));
			os.writeObject(operador);
			os.close();
			System.out.println("Se guardaron los datos correctamente.");
			
		}catch (FileNotFoundException e){
			System.out.println("No se encontró archivo de configuración.");
		}catch (IOException e){
			System.out.println("Error de acceso a datos.");
		}
	}
	
	public Operador recuperarDatos(){
		Operador op = new Operador_telefonia();
		try{
			ObjectInputStream fis = new ObjectInputStream(new FileInputStream("basededatos.bin"));
			op = (Operador) fis.readObject();
			fis.close();
			System.out.println("Se cargaron los datos correctamente.");
			System.out.println("Número de clientes: "+op.getClientes().size());
			return op;
		}catch (FileNotFoundException e){
			System.out.println("No se encontró archivo de configuración.");
			System.out.println("Guardando fichero por primera vez.");
			almacenarDatos(op);
		}catch (IOException e){
			System.out.println("Error de acceso a datos.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(NullPointerException e){
		}
		return op;
	}
}
