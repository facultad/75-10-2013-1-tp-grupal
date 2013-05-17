package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;

public class ItemVentaMock implements IItemVenta{

	private IProducto producto;
	private int cantidad;

	public ItemVentaMock(IProducto producto, int cantidad) {
		this.producto=producto;
		this.cantidad=cantidad;
	}

	@Override
	public IProducto getProducto() {
		return this.producto;
	}

	@Override
	public int getCantidadProductos() {
		return this.cantidad;
	}

}
