package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IMedioPago;

public class CondicionMedioPago extends CondicionVenta {

	public CondicionMedioPago(IMedioPago debito) {
		throw new UnsupportedOperationException(); 
	}

	@Override
	protected boolean evaluarCondicion(IItemVenta itemVenta) {
		throw new UnsupportedOperationException();
	}

}
