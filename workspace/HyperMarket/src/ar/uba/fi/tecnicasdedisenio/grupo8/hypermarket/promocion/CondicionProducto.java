package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;


public class CondicionProducto extends CondicionItemVenta{

	private IProducto producto;

	public CondicionProducto(IProducto producto) {
		// TODO Auto-generated constructor stub
		this.producto=producto;
	}

	public boolean valida(IItemVenta itemVenta) {
		// TODO Auto-generated method stub
		return this.producto.getId()==itemVenta.getProducto().getId();
	}

}
