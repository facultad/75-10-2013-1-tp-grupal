package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion;

import java.util.ArrayList;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;

public class CondicionOR extends BaseCondicionPromocion {

	ArrayList<ICondicionPromocion> miembros;
	
	public CondicionOR() {
		miembros = new ArrayList<ICondicionPromocion>();
	}
		
	
	@Override
	protected boolean evaluarCondicion(IItemVenta itemVenta) {
		boolean evaluacion = false;
		
		for (ICondicionPromocion icp : miembros) {
			if (icp.valida(itemVenta)) {
				evaluacion = true;
				break;
			}
		}		
		
		return evaluacion;
	}

	public void agregarCondicion(ICondicionPromocion condicion) {
		miembros.add(condicion);
	}

}
