package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;

public class RepositorioPromociones {

	Collection<IPromocion> promociones=new ArrayList<IPromocion>();
	
	public void add(IPromocion promocion) {
		this.promociones.add(promocion);
	}

	public Collection<IPromocion> getPromocionesAplicaItemVenta(
			IItemVenta itemVenta) {
		Collection<IPromocion> promocionesAplicaItenVenta=new ArrayList<IPromocion>();
		Iterator<IPromocion> iterPromocion=this.promociones.iterator();
		while (iterPromocion.hasNext()){
			IPromocion promocion=iterPromocion.next();
			if (promocion.aplicaParaItemVenta(itemVenta))
				promocionesAplicaItenVenta.add(promocion);
		}
		return promocionesAplicaItenVenta;
	}

}
