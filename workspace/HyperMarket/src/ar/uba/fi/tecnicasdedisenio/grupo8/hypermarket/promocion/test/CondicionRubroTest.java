package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IRubro;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionRubro;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ItemVentaMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ProductoMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.RubroMock;

public class CondicionRubroTest {

	@Test
	public void pasaCondicion() {
		IRubro rubro=new RubroMock(1);
		IProducto producto=new ProductoMock(1,10,rubro);
		CondicionRubro condicion=new CondicionRubro(rubro);
		IItemVenta itemVenta=new ItemVentaMock(producto,10);
		assertTrue(condicion.valida(itemVenta));
	}
	
	@Test
	public void noPasaCondicion() {
		IRubro rubro=new RubroMock(1);
		IProducto producto=new ProductoMock(1,10,rubro);
		CondicionRubro condicion=new CondicionRubro(new RubroMock(2));
		IItemVenta itemVenta=new ItemVentaMock(producto,10);
		assertFalse(condicion.valida(itemVenta));
	}
	
	@Test
	public void noPasaCondicionNegada() {
		IRubro rubro=new RubroMock(1);
		IProducto producto=new ProductoMock(1,10,rubro);
		CondicionRubro condicion=new CondicionRubro(rubro);
		condicion.negar();
		IItemVenta itemVenta=new ItemVentaMock(producto,10);
		assertFalse(condicion.valida(itemVenta));
	}

	@Test
	public void pasaCondicionNegada() {
		IRubro rubro=new RubroMock(1);
		IProducto producto=new ProductoMock(1,10,rubro);
		CondicionRubro condicion=new CondicionRubro(new RubroMock(2));
		condicion.negar();
		IItemVenta itemVenta=new ItemVentaMock(producto,10);
		assertTrue(condicion.valida(itemVenta));	}

}
