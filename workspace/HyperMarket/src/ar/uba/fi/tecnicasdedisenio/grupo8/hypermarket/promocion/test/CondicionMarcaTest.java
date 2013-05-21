package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IMarca;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionMarca;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ItemVentaMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.MarcaMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ProductoMock;

public class CondicionMarcaTest {

	@Test
	public void pasaCondicion() {
		IMarca marca=new MarcaMock(1);
		IProducto producto=new ProductoMock(1,10,marca);
		CondicionMarca condicion=new CondicionMarca(marca);
		IItemVenta itemVenta=new ItemVentaMock(producto,10);
		assertTrue(condicion.valida(itemVenta));
	}
	
	@Test
	public void noPasaCondicion() {
		IMarca marca=new MarcaMock(1);
		IProducto producto=new ProductoMock(1,10,marca);
		CondicionMarca condicion=new CondicionMarca(new MarcaMock(2));
		IItemVenta itemVenta=new ItemVentaMock(producto,10);
		assertFalse(condicion.valida(itemVenta));
	}
	
	@Test
	public void noPasaCondicionNegada() {
		IMarca marca=new MarcaMock(1);
		IProducto producto=new ProductoMock(1,10,marca);
		CondicionMarca condicion=new CondicionMarca(new MarcaMock(2));
		condicion.negar();
		IItemVenta itemVenta=new ItemVentaMock(producto,10);
		assertFalse(condicion.valida(itemVenta));
	}

	@Test
	public void pasaCondicionNegada() {
		IMarca marca=new MarcaMock(1);
		IProducto producto=new ProductoMock(1,10,marca);
		CondicionMarca condicion=new CondicionMarca(new MarcaMock(1));
		condicion.negar();
		IItemVenta itemVenta=new ItemVentaMock(producto,10);
		assertTrue(condicion.valida(itemVenta));	}

}
