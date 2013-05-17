package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.CondicionProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.ICondicionPromocion;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.Promocion;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.excepciones.PromocionNoAplicaParaItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ItemVentaMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ProductoMock;

public class PromocionTest {

	@Test
	public void testImporteADescontarPorUnidad() {
		IProducto producto=new ProductoMock(1,10);
		ICondicionPromocion condicion=new CondicionProducto(producto);
		Promocion promocion=new Promocion(condicion, 1, 0.15);

		for (int i = 1; i <= 10; i++) {
			IItemVenta itemVenta=new ItemVentaMock(producto, i);
			assertEquals(1.5*i,promocion.getImporteADescontar(itemVenta),0);
		}
	}

	@Test
	public void testImporteADescontarNoAplica() {
		IProducto producto=new ProductoMock(1,10);
		ICondicionPromocion condicion=new CondicionProducto(producto);
		Promocion promocion=new Promocion(condicion, 1, 0.15);
		IItemVenta itemVenta=new ItemVentaMock(new ProductoMock(2,10),1);
		try {
			promocion.getImporteADescontar(itemVenta);
			fail();
		} catch (PromocionNoAplicaParaItemVenta e) {
			// La prueba es correcta porque se espera que lance la excepción 
			// PromocionNoAplicaParaItemVenta. 
		}
	}
	
	public void testImporteADescontarCincuentaPorcientoEnSegundaUnidad() {
		IProducto producto=new ProductoMock(1,100);
		ICondicionPromocion condicion=new CondicionProducto(producto);
		Promocion promocion=new Promocion(condicion, 2, 0.25);
		
		IItemVenta itemVenta=new ItemVentaMock(producto, 1);
		assertEquals(0,promocion.getImporteADescontar(itemVenta),0);

		itemVenta=new ItemVentaMock(producto, 2);
		assertEquals(50,promocion.getImporteADescontar(itemVenta),0);

		itemVenta=new ItemVentaMock(producto, 3);
		assertEquals(50,promocion.getImporteADescontar(itemVenta),0);

		itemVenta=new ItemVentaMock(producto, 4);
		assertEquals(100,promocion.getImporteADescontar(itemVenta),0);

		itemVenta=new ItemVentaMock(producto, 51);
		assertEquals(1250,promocion.getImporteADescontar(itemVenta),0);
	}


	public void testImporteADescontarElQuintoGratis() {
		IProducto producto=new ProductoMock(1,15);
		ICondicionPromocion condicion=new CondicionProducto(producto);
		Promocion promocion=new Promocion(condicion, 5, 0.20);
		
		IItemVenta itemVenta=new ItemVentaMock(producto, 1);
		assertEquals(0,promocion.getImporteADescontar(itemVenta),0);

		itemVenta=new ItemVentaMock(producto, 4);
		assertEquals(0,promocion.getImporteADescontar(itemVenta),0);

		itemVenta=new ItemVentaMock(producto, 5);
		assertEquals(15*4,promocion.getImporteADescontar(itemVenta),0);

		itemVenta=new ItemVentaMock(producto, 6);
		assertEquals(15*5,promocion.getImporteADescontar(itemVenta),0);

		itemVenta=new ItemVentaMock(producto, 14);
		assertEquals(15*12,promocion.getImporteADescontar(itemVenta),0);
	}

}
