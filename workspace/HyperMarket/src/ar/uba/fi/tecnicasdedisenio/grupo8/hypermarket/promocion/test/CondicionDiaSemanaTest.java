package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.ItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.MedioPago;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.Sucursal;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.Venta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionDiaSemana;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionDiaSemana.DiaSemana;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ProductoMock;

public class CondicionDiaSemanaTest {

	@Test
	public void pasaCondicion() {
		IProducto producto=new ProductoMock(1,10);
		CondicionDiaSemana condicion=new CondicionDiaSemana(DiaSemana.MARTES);
		IVenta venta=new Venta(new Sucursal(1), new MedioPago(1));
		IItemVenta itemVenta = new ItemVenta(producto, 10, venta);
		assertTrue(condicion.valida(itemVenta));
	}

	@Test
	public void noPasaCondicion() {
		IProducto producto=new ProductoMock(1,10);
		CondicionDiaSemana condicion=new CondicionDiaSemana(DiaSemana.SABADO);
		IVenta venta=new Venta(new Sucursal(1), new MedioPago(1));
		IItemVenta itemVenta = new ItemVenta(producto, 10, venta);
		assertFalse(condicion.valida(itemVenta));
	}

	@Test
	public void noPasaCondicionNegada() {
		IProducto producto=new ProductoMock(1,10);
		CondicionDiaSemana condicion=new CondicionDiaSemana(DiaSemana.MARTES);
		condicion.negar();
		IVenta venta=new Venta(new Sucursal(1), new MedioPago(1));
		IItemVenta itemVenta = new ItemVenta(producto, 10, venta);
		assertFalse(condicion.valida(itemVenta));
	}

	@Test
	public void pasaCondicionNegada() {
		IProducto producto=new ProductoMock(1,10);
		CondicionDiaSemana condicion=new CondicionDiaSemana(DiaSemana.SABADO);
		condicion.negar();
		IVenta venta=new Venta(new Sucursal(1), new MedioPago(1));
		IItemVenta itemVenta = new ItemVenta(producto, 10, venta);
		assertTrue(condicion.valida(itemVenta));
	}

}
