package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test;

import java.util.Calendar;
import java.util.Date;

public class Fecha {
	
	public static Date getFecha(int year, int month, int day) {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month-1);
		cal.set(Calendar.DAY_OF_MONTH, day);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return new Date(cal.getTimeInMillis());
	}


}
