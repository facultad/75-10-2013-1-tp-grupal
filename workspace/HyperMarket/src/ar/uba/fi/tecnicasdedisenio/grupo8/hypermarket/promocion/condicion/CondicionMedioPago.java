package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IMedioPago;

public class CondicionMedioPago extends CondicionVenta {

	IMedioPago medioPagoCond;
	
	public CondicionMedioPago(IMedioPago medio){
		medioPagoCond = medio;
	}
	
	@Override
	protected boolean evaluarCondicion(IItemVenta itemVenta) {
		return medioPagoCond.getId() == itemVenta.getVenta().getPago().getId();
	}
	
}
