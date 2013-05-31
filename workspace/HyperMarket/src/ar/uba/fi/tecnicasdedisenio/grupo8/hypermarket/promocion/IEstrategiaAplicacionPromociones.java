package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion;

import java.util.Collection;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;

public interface IEstrategiaAplicacionPromociones {

	Collection<IPromocion> getPromocionesAAplicar(IItemVenta itemVenta,
			Collection<IPromocion> promocionesQueAplicanAlItem);

}
