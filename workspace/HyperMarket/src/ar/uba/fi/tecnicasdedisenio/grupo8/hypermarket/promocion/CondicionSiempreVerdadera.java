package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;

public class CondicionSiempreVerdadera implements ICondicionPromocion {

	@Override
	public boolean valida(IItemVenta itemVenta) {
		// Siempre devuelve verdadero
		return true;
	}

}
