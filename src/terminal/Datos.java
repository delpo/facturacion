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

	public static void almacenarDatos(Operador_telefonia op){
		try{
			ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("basededatos.bin"));
			os.writeObject(op);
			os.close();
			System.out.println("Elementos que se van a guardar: ");
			System.out.println("Se guardaron los datos correctamente.");
			
		}catch (FileNotFoundException e){
			System.out.println("No se pudo almacenar los datos.");
		}catch (IOException e){
			System.out.println("Error de acceso a datos.");
		}
	}
	
	public static Operador_telefonia recuperarDatos(){
		try{
			ObjectInputStream fis = new ObjectInputStream(new FileInputStream("basededatos.bin"));
			Operador_telefonia op = (Operador_telefonia) fis.readObject();
			fis.close();
			System.out.println("Elementos recuperados:  ");
			return op;
		}catch (FileNotFoundException e){
			System.out.println("No se encontró archivo de configuración.");
		}catch (IOException e){
			System.out.println("Error de acceso a datos.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}
}
