package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion;

import java.util.ArrayList;
import java.util.Collection;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;

public class CondicionSiempreVerdadera extends BaseCondicionPromocion {

	@Override
	protected boolean evaluarCondicion(IItemVenta itemVenta) {
		// Siempre devuelve verdadero
		return true;
	}

	public Collection<IItemVenta> getItemsDeLosQueDepende(IItemVenta itemVenta){
		return new ArrayList<IItemVenta>();
	}

}
