package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.excepciones.ItemVentaNoEstaAsociadoANingunaVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionDependiente;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.ICondicionPromocion;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.excepciones.ProductoNoPuedeDependerDeSiMismoException;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.excepciones.PromocionNoAplicaParaItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ItemVentaMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ProductoMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.VentaMock;

public class CondicionDependienteTest {

	@Test
	public void pasaCondicion() {
		IProducto producto1=new ProductoMock(1);
		IProducto producto2=new ProductoMock(2);
		IProducto producto3=new ProductoMock(3);
		
		ICondicionPromocion condicionProducto=new CondicionProducto(producto2);
		CondicionDependiente condicionDependiente=new CondicionDependiente(condicionProducto);
		
		IVenta venta=new VentaMock();
		IItemVenta itemVenta1=new ItemVentaMock(producto1,1);
		IItemVenta itemVenta2=new ItemVentaMock(producto2,1);
		IItemVenta itemVenta3=new ItemVentaMock(producto3,1);
		venta.addItem(itemVenta1);
		venta.addItem(itemVenta2);
		venta.addItem(itemVenta3);
		
		assertTrue(condicionDependiente.valida(itemVenta1));
		assertTrue(condicionDependiente.valida(itemVenta3));
	}
	
	@Test
	public void noPasaCondicion() {
		
		IProducto producto1=new ProductoMock(1);
		IProducto producto2=new ProductoMock(2);
		IProducto producto3=new ProductoMock(3);
		IProducto producto4=new ProductoMock(4);
		
		ICondicionPromocion condicionProducto=new CondicionProducto(producto4);
		CondicionDependiente condicionDependiente=new CondicionDependiente(condicionProducto);
		
		IVenta venta=new VentaMock();
		IItemVenta itemVenta1=new ItemVentaMock(producto1,1);
		IItemVenta itemVenta2=new ItemVentaMock(producto2,1);
		IItemVenta itemVenta3=new ItemVentaMock(producto3,1);
		
		venta.addItem(itemVenta1);
		venta.addItem(itemVenta2);
		venta.addItem(itemVenta3);
		
		assertFalse(condicionDependiente.valida(itemVenta1));
		assertFalse(condicionDependiente.valida(itemVenta2));
		assertFalse(condicionDependiente.valida(itemVenta3));
	}
	
	@Test
	public void noPasaCondicionCuandoCondicionAAplicarAplicaParaItemEvaluado() {
		
		IProducto producto1=new ProductoMock(1);
		IProducto producto2=new ProductoMock(2);
		IProducto producto3=new ProductoMock(3);
		
		ICondicionPromocion condicionProducto=new CondicionProducto(producto1);
		CondicionDependiente condicionDependiente=new CondicionDependiente(condicionProducto);
		
		IVenta venta=new VentaMock();
		IItemVenta itemVenta1=new ItemVentaMock(producto1,1);
		IItemVenta itemVenta2=new ItemVentaMock(producto2,1);
		IItemVenta itemVenta3=new ItemVentaMock(producto3,1);
		
		venta.addItem(itemVenta1);
		venta.addItem(itemVenta2);
		venta.addItem(itemVenta3);
		
		assertFalse(condicionDependiente.valida(itemVenta1));
		assertTrue(condicionDependiente.valida(itemVenta2));
		assertTrue(condicionDependiente.valida(itemVenta3));
	}
	
	@Test
	public void noPasaCondicionNegada() {
		
		IProducto producto1=new ProductoMock(1);
		IProducto producto2=new ProductoMock(2);
		IProducto producto3=new ProductoMock(3);
		
		ICondicionPromocion condicionProducto=new CondicionProducto(producto2);
		CondicionDependiente condicionDependiente=new CondicionDependiente(condicionProducto);
		
		IVenta venta=new VentaMock();
		IItemVenta itemVenta1=new ItemVentaMock(producto1,1);
		IItemVenta itemVenta2=new ItemVentaMock(producto2,1);
		IItemVenta itemVenta3=new ItemVentaMock(producto3,1);
		venta.addItem(itemVenta1);
		venta.addItem(itemVenta2);
		venta.addItem(itemVenta3);
		
		condicionDependiente.negar();
		
		assertFalse(condicionDependiente.valida(itemVenta1));
		assertFalse(condicionDependiente.valida(itemVenta3));
	}

	@Test
	public void pasaCondicionNegada() {
		
		IProducto producto1=new ProductoMock(1);
		IProducto producto2=new ProductoMock(2);
		IProducto producto3=new ProductoMock(3);
		IProducto producto4=new ProductoMock(4);
		
		ICondicionPromocion condicionProducto=new CondicionProducto(producto4);
		CondicionDependiente condicionDependiente=new CondicionDependiente(condicionProducto);
		
		IVenta venta=new VentaMock();
		IItemVenta itemVenta1=new ItemVentaMock(producto1,1);
		IItemVenta itemVenta2=new ItemVentaMock(producto2,1);
		IItemVenta itemVenta3=new ItemVentaMock(producto3,1);
		
		venta.addItem(itemVenta1);
		venta.addItem(itemVenta2);
		venta.addItem(itemVenta3);
		
		condicionDependiente.negar();
		
		assertTrue(condicionDependiente.valida(itemVenta1));
		assertTrue(condicionDependiente.valida(itemVenta2));
		assertTrue(condicionDependiente.valida(itemVenta3));
	}

	@Test
	public void testCondicionDependienteNecesitaConexionConVenta(){
		
		IProducto producto=new ProductoMock(1,10);
		
		ICondicionPromocion condicionDeDependencia=new CondicionProducto(producto);
		ICondicionPromocion condicionDependiente=new CondicionDependiente(condicionDeDependencia);

		IItemVenta itemVenta=new ItemVentaMock(producto, 1);
		
		try {
			condicionDependiente.valida(itemVenta);
			fail();
		} catch (ItemVentaNoEstaAsociadoANingunaVenta e) {
			// Paso el test
		}
	}
}
