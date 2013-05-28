package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion;

import java.util.ArrayList;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;

public class CondicionAND extends BaseCondicionPromocion {

	ArrayList<ICondicionPromocion> miembros;
	
	public CondicionAND() {
		miembros = new ArrayList<ICondicionPromocion>();
	}
	
	@Override
	protected boolean evaluarCondicion(IItemVenta itemVenta) {
		boolean evaluacion = true;
		
		for (ICondicionPromocion icp : miembros) {
			if (icp.valida(itemVenta) == false) {
				evaluacion = false;
				break;
			}
		}		
		
		return evaluacion;
	}

	public void agregarCondicion(ICondicionPromocion condicion) {
		miembros.add(condicion);
	}

}
