package terminal;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import facturacion.Operador_telefonia;

public class Datos implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Datos(){
		
	}

	public void almacenarDatos(Operador_telefonia operador){
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
	
	public Operador_telefonia recuperarDatos(){
		Operador_telefonia op = new Operador_telefonia();
		try{
			ObjectInputStream fis = new ObjectInputStream(new FileInputStream("basededatos.bin"));
			op = (Operador_telefonia) fis.readObject();
			fis.close();
			System.out.println("Se cargaron los datos correctamente.");
			if(op == null){
				System.out.println("nullll");
			}
//			System.out.println("Op: " + op);
			op.listarClientes();
			return op;
		}catch (FileNotFoundException e){
			System.out.println("No se encontró archivo de configuración.");
		}catch (IOException e){
			System.out.println("Error de acceso a datos.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch(NullPointerException e){
		}
		return op;
	}
}
