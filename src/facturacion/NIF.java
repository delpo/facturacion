package facturacion;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NIF {
	String NIF;
	
	public NIF(String nif) throws ExcepcionNIFnoValido{
		if(isNifNie(nif)){
			this.NIF = nif.toUpperCase();
		}else{
			//lanzar excepción NIF no válido
			throw new ExcepcionNIFnoValido();
		}
	}
	
	public static boolean isNifNie(String nif){

		//si es NIE, eliminar la x,y,z inicial para tratarlo como nif
		if (nif.toUpperCase().startsWith("X")||nif.toUpperCase().startsWith("Y")||nif.toUpperCase().startsWith("Z"))
			nif = nif.substring(1);

		Pattern nifPattern =
				Pattern.compile("(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])");
		Matcher m = nifPattern.matcher(nif);
		if(m.matches()){
			String letra = m.group(2);
			//Extraer letra del NIF
			String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
			int dni = Integer.parseInt(m.group(1));
			dni = dni % 23;
			String reference = letras.substring(dni,dni+1);
			if (reference.equalsIgnoreCase(letra)){
				return true;
			}else{
				return false;
			}
		}
		else
			return false;
	}
	@Override
	public String toString(){
		return NIF;
	}
}
