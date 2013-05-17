package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.CondicionProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ItemVentaMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ProductoMock;

public class CondicionProductoTest {

	@Test
	public void pasaCondicion() {
		IProducto producto=new ProductoMock(1);
		CondicionProducto condicion=new CondicionProducto(producto);
		IItemVenta itemVenta=new ItemVentaMock(producto,10);
		assertTrue(condicion.valida(itemVenta));
	}
	
	@Test
	public void noPasaCondicion() {
		CondicionProducto condicion=new CondicionProducto(new ProductoMock(1));
		IItemVenta itemVenta=new ItemVentaMock(new ProductoMock(2),10);
		assertFalse(condicion.valida(itemVenta));
	}
	
	@Test
	public void noPasaCondicionNegada() {
		IProducto producto=new ProductoMock(1);
		CondicionProducto condicion=new CondicionProducto(producto);
		condicion.negar();
		IItemVenta itemVenta=new ItemVentaMock(producto,10);
		assertFalse(condicion.valida(itemVenta));
	}

	@Test
	public void pasaCondicionNegada() {
		CondicionProducto condicion=new CondicionProducto(new ProductoMock(1));
		condicion.negar();
		IItemVenta itemVenta=new ItemVentaMock(new ProductoMock(2),10);
		assertTrue(condicion.valida(itemVenta));
	}

}
