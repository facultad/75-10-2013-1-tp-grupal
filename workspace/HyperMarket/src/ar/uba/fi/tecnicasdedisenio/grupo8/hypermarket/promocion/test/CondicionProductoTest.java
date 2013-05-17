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
		IProducto producto=this.obtenerCualquierProducto();
		CondicionProducto condicion=new CondicionProducto(producto);
		IItemVenta itemVenta=this.obtenerCualquierItemVenta(producto);
		assertTrue(condicion.valida(itemVenta));
	}

	private IItemVenta obtenerCualquierItemVenta(IProducto producto) {
		// TODO Auto-generated method stub
		return new ItemVentaMock(producto,10);
	}

	private IProducto obtenerCualquierProducto() {
		// TODO Auto-generated method stub
		return new ProductoMock(1);
	}

}
