package superttdd.caja;

import java.util.Calendar;

public enum DiaSemana {
	
 	DOMINGO(0), LUNES(1), MARTES(2), MIERCOLES(3),
    JUEVES(4), VIERNES(5), SABADO(6), HOY(Calendar.DAY_OF_WEEK); 
	
	private int dia;
	
	DiaSemana(int dia) {
		this.dia = dia;
	}
	
	public int getDia() {
		return dia;
	}
	
	public Boolean mismoDia(int dia) {
		return (this.dia == dia);
	}
}
