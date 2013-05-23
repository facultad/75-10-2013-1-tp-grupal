package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.excepciones.ItemVentaNoEstaAsociadoANingunaVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.excepciones.CondicionAAplicarNoDefinidas;

public class CondicionDependiente extends CondicionItemVenta {

	ICondicionPromocion condicionAAplicar;
	
	public CondicionDependiente(ICondicionPromocion condicionAAplicar){
		this.condicionAAplicar=condicionAAplicar;
	}
	
	@Override
	protected boolean evaluarCondicion(IItemVenta itemVenta) {
		IVenta venta=itemVenta.getVenta();
		if (venta==null)
			throw new ItemVentaNoEstaAsociadoANingunaVenta();
		Iterator<IItemVenta> iterItemVenta=venta.getItemsIterator();
		while (iterItemVenta.hasNext()){
			IItemVenta itemVentaIterado=iterItemVenta.next();
			// Una condición dependiente solo se verifica en el resto de los items.
			if (itemVentaIterado==itemVenta)
				continue;
			if (this.condicionAAplicar.valida(itemVentaIterado))
				return true;
		}
		return false;
	}

}
