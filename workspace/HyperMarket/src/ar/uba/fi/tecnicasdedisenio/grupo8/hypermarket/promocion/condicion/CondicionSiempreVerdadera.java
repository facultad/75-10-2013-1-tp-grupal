package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;

public class CondicionSiempreVerdadera extends BaseCondicionPromocion {

	@Override
	protected boolean evaluarCondicion(IItemVenta itemVenta) {
		// Siempre devuelve verdadero
		return true;
	}

}
