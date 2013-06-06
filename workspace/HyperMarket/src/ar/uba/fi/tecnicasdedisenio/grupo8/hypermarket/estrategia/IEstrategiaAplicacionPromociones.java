package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.estrategia;

import java.util.Collection;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.IPromocion;

public interface IEstrategiaAplicacionPromociones {

	Collection<IPromocion> getPromocionesAAplicar(IItemVenta itemVenta,
			Collection<IPromocion> promocionesQueAplicanAlItem);

}
