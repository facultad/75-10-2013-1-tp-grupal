package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.RepositorioPromociones;

public class ItemVentaMock implements IItemVenta{

	private IProducto producto;
	private int cantidad;
	private IVenta venta;

	public ItemVentaMock(IProducto producto, int cantidad) {
		this.producto=producto;
		this.cantidad=cantidad;
	}

	public ItemVentaMock(IProducto producto, int cantidad, IVenta venta) {
		this.producto = producto;
		this.venta = venta;
		this.cantidad = cantidad;
	}

	@Override
	public IProducto getProducto() {
		return this.producto;
	}

	@Override
	public int getCantidadProductos() {
		return this.cantidad;
	}

	@Override
	public IVenta getVenta() {
		return this.venta;
	}

	@Override
	public void setVenta(IVenta venta) {
		this.venta=venta;
	}

	@Override
	public double getImporteSinDescuento() {
		throw new UnsupportedOperationException();
	}

	@Override
	public long getId() {
		return 0;
	}

	@Override
	public double calcularImporteConDescuento(
			RepositorioPromociones repositorioPromociones) {
		throw new UnsupportedOperationException();
	}

}
