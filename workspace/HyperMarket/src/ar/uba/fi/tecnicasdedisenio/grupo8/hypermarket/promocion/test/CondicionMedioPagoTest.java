package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.ItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.MedioPago;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.Sucursal;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.Venta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionMedioPago;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.MedioPagoMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ProductoMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.SucursalMock;

public class CondicionMedioPagoTest {

	@Test
	public void pasaCondicion() {
		IProducto producto = new ProductoMock(1,10);
		CondicionMedioPago condicion = new CondicionMedioPago(new MedioPagoMock(1));
		IVenta venta = new Venta(new SucursalMock(1), new MedioPagoMock(1));
		IItemVenta itemVenta = new ItemVenta(producto, 10, venta);
		assertTrue(condicion.valida(itemVenta));
	}

	@Test
	public void noPasaCondicion() {
		IProducto producto = new ProductoMock(1,10);
		CondicionMedioPago condicion = new CondicionMedioPago(new MedioPagoMock(4));
		IVenta venta=new Venta(new SucursalMock(1), new MedioPagoMock(1));
		IItemVenta itemVenta = new ItemVenta(producto, 10, venta);
		assertFalse(condicion.valida(itemVenta));
	}

	@Test
	public void noPasaCondicionNegada() {
		IProducto producto = new ProductoMock(1,10);
		CondicionMedioPago condicion = new CondicionMedioPago(new MedioPagoMock(1));
		condicion.negar();
		IVenta venta = new Venta(new SucursalMock(1), new MedioPagoMock(1));
		IItemVenta itemVenta = new ItemVenta(producto, 10, venta);
		assertFalse(condicion.valida(itemVenta));
	}

	@Test
	public void pasaCondicionNegada() {
		IProducto producto = new ProductoMock(1,10);
		CondicionMedioPago condicion = new CondicionMedioPago(new MedioPagoMock(3));
		condicion.negar();
		IVenta venta = new Venta(new SucursalMock(1), new MedioPagoMock(1));
		IItemVenta itemVenta = new ItemVenta(producto, 10, venta);
		assertTrue(condicion.valida(itemVenta));
	}

}