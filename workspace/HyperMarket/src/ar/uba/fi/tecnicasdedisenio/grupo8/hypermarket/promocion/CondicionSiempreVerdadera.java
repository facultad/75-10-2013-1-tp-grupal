package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;

public class CondicionSiempreVerdadera implements ICondicionPromocion {

	@Override
	public boolean valida(IItemVenta itemVenta) {
		// TODO Auto-generated method stub
		return true;
	}

}
