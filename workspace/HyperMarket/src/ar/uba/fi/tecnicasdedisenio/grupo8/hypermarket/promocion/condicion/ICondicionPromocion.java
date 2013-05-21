package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;

public interface ICondicionPromocion {

	boolean valida(IItemVenta itemVenta);
	
	void negar();
	
	boolean negada();

}
