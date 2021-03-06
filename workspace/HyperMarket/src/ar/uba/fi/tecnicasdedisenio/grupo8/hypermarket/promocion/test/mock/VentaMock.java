package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.EstadoLaboral;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IEstadoLaboral;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IMedioPago;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.ISucursal;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.ItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.CuponDescuento;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.ItemDescuento;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.RepositorioPromociones;

public class VentaMock implements IVenta {

	Collection<IItemVenta> items=new ArrayList<IItemVenta>();
	private Date date;
	private IMedioPago medioPago;
	private ISucursal sucursal;

	public VentaMock() {
		// TODO Auto-generated constructor stub
	}
	
	public VentaMock(IMedioPago medioPago) {
		this.medioPago = medioPago;
	}
	
	
	public VentaMock(ISucursal sucursal, IMedioPago medioPago) {
		this.medioPago = medioPago;
		this.sucursal = sucursal;
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
	public double getImporteTotalConDescuento() {
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
		if (medioPago == null) medioPago = new MedioPagoMock(1);
		return medioPago;
	}

	@Override
	public long getId() {
		return 0;
	}
	
	public ISucursal getSucursal(){
		if (this.sucursal == null) this.sucursal = new SucursalMock(1);
		return this.sucursal;
	}

	@Override
	public Collection<ItemDescuento> getItemsDescuento() {
		throw new UnsupportedOperationException();
	}

	@Override
	public double calcularDescuento(RepositorioPromociones promociones) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setMedioPago(IMedioPago medioPago) {
		throw new UnsupportedOperationException();
	}

	@Override
	public IMedioPago getMedioPago() {
		throw new UnsupportedOperationException();
	}

	@Override
	public IEstadoLaboral getEstadoLaboral() {
		throw new UnsupportedOperationException();
	}

	@Override
	public void setEstadoLaboral(IEstadoLaboral jubilado) {
		throw new UnsupportedOperationException();
	}

	@Override
	public void addCuponDescuento(CuponDescuento cuponDescuento) {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<CuponDescuento> getCupones() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<CuponDescuento> getCuponesProximaVenta() {
		throw new UnsupportedOperationException();
	}

	@Override
	public Collection<IItemVenta> getItemsVenta() {
		throw new UnsupportedOperationException();
	}
	
}
