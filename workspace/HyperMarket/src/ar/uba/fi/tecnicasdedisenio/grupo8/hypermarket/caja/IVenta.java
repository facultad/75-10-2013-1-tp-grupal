package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

import java.util.Iterator;

public interface IVenta {

	void addItem(IItemVenta itemVenta);

	Iterator<IItemVenta> getItemsIterator();

	boolean contieneProducto(IProducto producto);

}
