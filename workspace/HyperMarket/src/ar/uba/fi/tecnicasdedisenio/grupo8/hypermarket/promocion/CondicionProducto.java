package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;


public class CondicionProducto extends CondicionItemVenta{

	private IProducto producto;
	private boolean _negada;

	public CondicionProducto(IProducto producto) {
		this.producto=producto;
	}

	public boolean valida(IItemVenta itemVenta) {
		if (this.negada())
			return !evaluarCondicion(itemVenta);	
		return evaluarCondicion(itemVenta);
	}

	private boolean evaluarCondicion(IItemVenta itemVenta) {
		return this.producto.getId()==itemVenta.getProducto().getId();
	}

	public void negar() {
		this._negada=(!this.negada());
	}

	private boolean negada() {
		return this._negada;
	}

}
