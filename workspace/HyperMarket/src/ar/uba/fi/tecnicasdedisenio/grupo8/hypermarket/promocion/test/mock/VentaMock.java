package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IVenta;

public class VentaMock implements IVenta {

	Collection<IItemVenta> items=new ArrayList<IItemVenta>();
	
	@Override
	public void addItem(IItemVenta itemVenta) {
		this.items.add(itemVenta);
		itemVenta.setVenta(this);
	}

	@Override
	public Iterator<IItemVenta> getItemsIterator() {
		return this.items.iterator();
	}

	@Override
	public boolean contieneProducto(IProducto producto) {
		Iterator<IItemVenta> iterItemVenta=this.items.iterator();
		while (iterItemVenta.hasNext()){
			IItemVenta itemVenta=iterItemVenta.next();
			if (itemVenta.getProducto().getId()==producto.getId())
				return true;
		}
		return false;
	}

}
