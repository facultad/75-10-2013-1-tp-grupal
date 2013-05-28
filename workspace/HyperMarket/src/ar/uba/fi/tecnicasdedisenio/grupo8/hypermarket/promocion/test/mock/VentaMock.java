package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IMedioPago;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.RepositorioPromociones;

public class VentaMock implements IVenta {

	Collection<IItemVenta> items=new ArrayList<IItemVenta>();
	private Date date;
	private IMedioPago medioPago;

	public VentaMock() {
		// TODO Auto-generated constructor stub
	}
	
	public VentaMock(IMedioPago medioPago) {
		this.medioPago = medioPago;
	}
	
	
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
		if (this.date == null) this.date = new Date();
		return this.date;
	}

	@Override
	public void setFechaVenta(Date date) {
		this.date = date;
	}

	@Override
	public IMedioPago getPago() {
		return medioPago;
	}

	@Override
	public long getId() {
		return 0;
	}

	@Override
	public long getId() {
		throw new UnsupportedOperationException();
	}

}
