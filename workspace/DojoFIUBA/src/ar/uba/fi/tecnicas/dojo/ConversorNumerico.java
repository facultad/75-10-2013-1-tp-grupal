package ar.uba.fi.tecnicas.dojo;

import java.util.ArrayList;
import java.util.List;

public class ConversorNumerico {

	// M D C
	List<ConversorNumero> mapeo = new ArrayList<ConversorNumero>();
	
	public ConversorNumerico(){
		mapeo.add(new ConversorNumero(50, 'L', 10, 'X'));
		mapeo.add(new ConversorNumero(10, 'X', 1, 'I'));
		mapeo.add(new ConversorNumero(5, 'V', 1, 'I'));
	}
	
	public String fromIntToRoman(int entero) {		
		if (entero <= 0){
			throw new IllegalArgumentException("No hay numeros negativos.");
		}
		String ret = "";
		for (ConversorNumero conversor : mapeo){
			if (conversor.valorAnterior + entero >= conversor.valor)
			{
				entero -= conversor.valorAnterior;
				ret += conversor.letraAnterior;
				entero -= conversor.valor;
				ret += conversor.letra;
			}
			else if (entero >= conversor.valor)
			{
				entero -= conversor.valor;
				ret += conversor.letra;
			}
		}
		while (entero > 0){
			ret += "I";
			entero -= 1;
		}
		return ret;
		
	}
}
