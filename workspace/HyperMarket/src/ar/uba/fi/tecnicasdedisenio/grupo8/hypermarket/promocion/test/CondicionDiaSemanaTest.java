package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.ItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionDiaSemana;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionDiaSemana.DiaSemana;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.MedioPagoMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ProductoMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.SucursalMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.VentaMock;

public class CondicionDiaSemanaTest {

	@Test
	public void pasaCondicion() {
		IProducto producto=new ProductoMock(1,10);
		CondicionDiaSemana condicion=new CondicionDiaSemana(DiaSemana.MARTES);
		IVenta venta=new VentaMock(new SucursalMock(1), new MedioPagoMock(1));
		venta.setFechaVenta(Fecha.getFecha(2013, 05, 28));
		IItemVenta itemVenta = new ItemVenta(producto, 10, venta);
		assertTrue(condicion.valida(itemVenta));
	}

	@Test
	public void noPasaCondicion() {
		IProducto producto=new ProductoMock(1,10);
		CondicionDiaSemana condicion=new CondicionDiaSemana(DiaSemana.SABADO);
		IVenta venta=new VentaMock(new SucursalMock(1), new MedioPagoMock(1));
		venta.setFechaVenta(Fecha.getFecha(2013, 05, 29));
		IItemVenta itemVenta = new ItemVenta(producto, 10, venta);
		assertFalse(condicion.valida(itemVenta));
	}

	@Test
	public void noPasaCondicionNegada() {
		IProducto producto=new ProductoMock(1,10);
		CondicionDiaSemana condicion=new CondicionDiaSemana(DiaSemana.MARTES);
		condicion.negar();
		IVenta venta=new VentaMock(new SucursalMock(1), new MedioPagoMock(1));
		venta.setFechaVenta(Fecha.getFecha(2013, 06, 04));
		IItemVenta itemVenta = new ItemVenta(producto, 10, venta);
		assertFalse(condicion.valida(itemVenta));
	}

	@Test
	public void pasaCondicionNegada() {
		IProducto producto=new ProductoMock(1,10);
		CondicionDiaSemana condicion=new CondicionDiaSemana(DiaSemana.SABADO);
		condicion.negar();
		IVenta venta=new VentaMock(new SucursalMock(1), new MedioPagoMock(1));
		venta.setFechaVenta(Fecha.getFecha(2013, 05, 31));
		IItemVenta itemVenta = new ItemVenta(producto, 10, venta);
		assertTrue(condicion.valida(itemVenta));
	}

}
