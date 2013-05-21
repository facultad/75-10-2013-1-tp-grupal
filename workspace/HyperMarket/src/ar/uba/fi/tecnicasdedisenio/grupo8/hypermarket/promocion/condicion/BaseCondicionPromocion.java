package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;

public abstract class BaseCondicionPromocion implements ICondicionPromocion {

	private boolean _negada;
	
	public void negar() {
		this._negada=(!this.negada());
	}

	public boolean negada() {
		return this._negada;
	}

}
