package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.ISucursal;

public class CondicionSucursal extends CondicionVenta {

	ISucursal sucursalCond;
	
	public CondicionSucursal(ISucursal sucursal){
		sucursalCond = sucursal;
	}
	
	@Override
	protected boolean evaluarCondicion(IItemVenta itemVenta) {
		return sucursalCond.getId() == itemVenta.getVenta().getSucursal().getId();
	}

}
