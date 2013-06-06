package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IMarca;

public class CondicionMarca extends CondicionItemVenta{

	private IMarca marca;

	public CondicionMarca(IMarca marca) {
		this.marca=marca;
	}

	@Override
	protected boolean evaluarCondicion(IItemVenta itemVenta) {
		return this.marca.getId()==itemVenta.getProducto().getMarca().getId();
	}

}
