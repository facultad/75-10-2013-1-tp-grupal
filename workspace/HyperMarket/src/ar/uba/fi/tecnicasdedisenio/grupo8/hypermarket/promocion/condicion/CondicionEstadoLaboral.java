package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion;

import java.util.Collection;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IEstadoLaboral;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IMedioPago;

public class CondicionEstadoLaboral extends CondicionVenta {

	IEstadoLaboral estadoLaboral;
	
	public CondicionEstadoLaboral(IEstadoLaboral estadoLaboral){
		this.estadoLaboral = estadoLaboral;
	}
	
	@Override
	protected boolean evaluarCondicion(IItemVenta itemVenta) {
		return estadoLaboral.getId() == itemVenta.getVenta().getEstadoLaboral().getId();
	}
	
}
