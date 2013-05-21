package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionProductoDependiente;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.excepciones.ProductoNoPuedeDependerDeSiMismoException;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ItemVentaMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ProductoMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.VentaMock;

public class CondicionProductoDependienteTest {

	@Test
	public void unProductoNoPuedeDependerDeSiMismo() {
		IProducto producto1=new ProductoMock(1);
		CondicionProductoDependiente condicion=new CondicionProductoDependiente(producto1);
		try {
			condicion.addDependencia(producto1);
			fail();
		} catch (ProductoNoPuedeDependerDeSiMismoException e) {
			// Test exitoso
		}
	}
	
	@Test
	public void pasaCondicion() {
		IProducto producto1=new ProductoMock(1);
		IProducto producto2=new ProductoMock(2);
		IProducto producto3=new ProductoMock(3);
		CondicionProductoDependiente condicion=new CondicionProductoDependiente(producto1);
		condicion.addDependencia(producto3);
		IVenta venta=new VentaMock();
		IItemVenta itemVenta1=new ItemVentaMock(producto1,1);
		IItemVenta itemVenta2=new ItemVentaMock(producto2,1);
		IItemVenta itemVenta3=new ItemVentaMock(producto3,1);
		venta.addItem(itemVenta1);
		venta.addItem(itemVenta2);
		venta.addItem(itemVenta3);
		assertTrue(condicion.valida(itemVenta1));
	}
	
	@Test
	public void noPasaCondicion() {
		IProducto producto1=new ProductoMock(1);
		IProducto producto2=new ProductoMock(2);
		IProducto producto3=new ProductoMock(3);
		CondicionProductoDependiente condicion=new CondicionProductoDependiente(producto1);
		condicion.addDependencia(producto3);
		IVenta venta=new VentaMock();
		IItemVenta itemVenta1=new ItemVentaMock(producto1,1);
		IItemVenta itemVenta2=new ItemVentaMock(producto2,1);
		IItemVenta itemVenta3=new ItemVentaMock(producto3,1);
		venta.addItem(itemVenta1);
		venta.addItem(itemVenta2);
		venta.addItem(itemVenta3);
		assertFalse(condicion.valida(itemVenta3));
	}
	
	@Test
	public void noPasaCondicionNegada() {
		IProducto producto1=new ProductoMock(1);
		IProducto producto2=new ProductoMock(2);
		IProducto producto3=new ProductoMock(3);
		CondicionProductoDependiente condicion=new CondicionProductoDependiente(producto1);
		condicion.addDependencia(producto3);
		IVenta venta=new VentaMock();
		IItemVenta itemVenta1=new ItemVentaMock(producto1,1);
		IItemVenta itemVenta2=new ItemVentaMock(producto2,1);
		IItemVenta itemVenta3=new ItemVentaMock(producto3,1);
		venta.addItem(itemVenta1);
		venta.addItem(itemVenta2);
		venta.addItem(itemVenta3);
		condicion.negar();
		assertFalse(condicion.valida(itemVenta1));
	}

	@Test
	public void pasaCondicionNegada() {
		IProducto producto1=new ProductoMock(1);
		IProducto producto2=new ProductoMock(2);
		IProducto producto3=new ProductoMock(3);
		CondicionProductoDependiente condicion=new CondicionProductoDependiente(producto1);
		condicion.addDependencia(producto3);
		IVenta venta=new VentaMock();
		IItemVenta itemVenta1=new ItemVentaMock(producto1,1);
		IItemVenta itemVenta2=new ItemVentaMock(producto2,1);
		IItemVenta itemVenta3=new ItemVentaMock(producto3,1);
		venta.addItem(itemVenta1);
		venta.addItem(itemVenta2);
		venta.addItem(itemVenta3);
		condicion.negar();
		assertTrue(condicion.valida(itemVenta3));	
	}

}
