package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion;

import java.util.Calendar;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;

public class CondicionDiaSemana extends CondicionVenta {

	public enum DiaSemana {
		DOMINGO, LUNES, MARTES, MIERCOLES, JUEVES, VIERNES, SABADO
	}
	
	DiaSemana diaCondicion;
	
	public CondicionDiaSemana(DiaSemana dia) {
		diaCondicion = dia;
	}

	@Override
	protected boolean evaluarCondicion(IItemVenta itemVenta) {
		Calendar c = Calendar.getInstance();
		c.setTime(itemVenta.getVenta().getFechaVenta());
		int dayOfWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
		return diaCondicion.ordinal() == dayOfWeek;
	}

}
