package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;

public class EstrategiaAplicaElMayorDescuentoPorItem implements
		IEstrategiaAplicacionPromociones {

	@Override
	public Collection<IPromocion> getPromocionesAAplicar(IItemVenta itemVenta,
			Collection<IPromocion> promocionesQueAplicanAlItem) {
		Iterator<IPromocion> iterIPromocion;
		iterIPromocion=promocionesQueAplicanAlItem.iterator();

		IPromocion promocionAAplicar=Promocion.crearPromocionNoAplicable();
		
		while(iterIPromocion.hasNext()){
			IPromocion promocion=iterIPromocion.next();
			if (promocion.getImporteADescontar(itemVenta)>=
				promocionAAplicar.getImporteADescontar(itemVenta))
				promocionAAplicar=promocion;
		}
		
		Collection<IPromocion> promocionesAAplicar=new ArrayList<IPromocion>();
		promocionesAAplicar.add(promocionAAplicar);
		return promocionesAAplicar;
	}

}
