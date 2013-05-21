package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;

public class RepositorioPromociones {

	Collection<Promocion> promociones=new ArrayList<Promocion>();
	
	public void add(Promocion promocion) {
		this.promociones.add(promocion);
	}

	public Collection<IPromocion> getPromocionesAplicaItemVenta(
			IItemVenta itemVenta) {
		Collection<IPromocion> promocionesAplicaItenVenta=new ArrayList<IPromocion>();
		Iterator<Promocion> iterPromocion=this.promociones.iterator();
		while (iterPromocion.hasNext()){
			Promocion promocion=iterPromocion.next();
			if (promocion.aplicaParaItemVenta(itemVenta))
				promocionesAplicaItenVenta.add(promocion);
		}
		return promocionesAplicaItenVenta;
	}

}
