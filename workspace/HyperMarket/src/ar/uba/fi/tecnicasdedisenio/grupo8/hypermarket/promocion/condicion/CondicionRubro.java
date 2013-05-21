package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IRubro;

public class CondicionRubro extends CondicionItemVenta {

	private IRubro rubro;

	public CondicionRubro(IRubro rubro) {
		this.rubro=rubro;
	}

	@Override
	protected boolean evaluarCondicion(IItemVenta itemVenta) {
		return this.rubro.getId()==itemVenta.getProducto().getRubro().getId();
	}

}
