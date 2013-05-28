package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion;

import java.util.Date;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;

public class CondicionPeriodo extends BaseCondicionPromocion {

	Date desde;
	Date hasta;
	
	public CondicionPeriodo(Date hasta) {
		this.desde = null;
		this.hasta = hasta;
		
		if (hasta == null) {
			throw new NullPointerException("'hasta' no puede ser null");
		}
	}
	
	public CondicionPeriodo(Date desde, Date hasta) {
		this.desde = desde;
		this.hasta = hasta;
		
		if (hasta == null) {
			throw new NullPointerException("'hasta' no puede ser null");
		}
		else if (desde != null && desde.after(hasta)) {
			throw new IllegalArgumentException("'desde' tiene que ser una fecha anterior a 'hasta'");
		}				
	}
	
	@Override
	protected boolean evaluarCondicion(IItemVenta itemVenta) {
		
		Date fechaVenta = itemVenta.getVenta().getFechaVenta();
	
		if (desde != null) {
			return (fechaVenta.after(desde) & fechaVenta.before(hasta));
		}
		
		return fechaVenta.before(hasta);	
	}

}
