package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;


public class CondicionProducto extends CondicionItemVenta{

	private IProducto producto;

	public CondicionProducto(IProducto producto) {
		this.producto=producto;
	}

	protected boolean evaluarCondicion(IItemVenta itemVenta) {
		return this.producto.getId()==itemVenta.getProducto().getId();
	}

}
