package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.caja.IItemVenta;

public class ItemVentaMock implements IItemVenta{

	private IProducto producto;
	private int cantidad;

	public ItemVentaMock(IProducto producto, int cantidad) {
		// TODO Auto-generated constructor stub
		this.producto=producto;
		this.cantidad=cantidad;
	}

	@Override
	public IProducto getProducto() {
		// TODO Auto-generated method stub
		return this.producto;
	}

}
