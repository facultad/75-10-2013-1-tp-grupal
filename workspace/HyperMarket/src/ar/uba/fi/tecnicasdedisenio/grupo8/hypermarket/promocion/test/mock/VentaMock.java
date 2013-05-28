package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.RepositorioPromociones;

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
	public void imprimeItems() {
		throw new UnsupportedOperationException();
	}

	@Override
	public int getCantidadUnidades() {
		throw new UnsupportedOperationException();
	}

	@Override
	public double getImporteTotalConDescuento(RepositorioPromociones promociones) {
		throw new UnsupportedOperationException();
	}

	@Override
	public double getImporteTotalSinDescuento() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Date getFechaVenta() {
		return new Date();
	}

	@Override
	public void setFechaVenta(Date date) {
		throw new UnsupportedOperationException();
	}

	@Override
	public long getId() {
		throw new UnsupportedOperationException();
	}

}
