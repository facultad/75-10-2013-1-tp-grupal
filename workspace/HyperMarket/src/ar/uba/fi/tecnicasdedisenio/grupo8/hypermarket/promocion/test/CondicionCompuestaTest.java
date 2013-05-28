package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IMarca;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IRubro;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.ItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.Marca;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.Producto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.Rubro;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionAND;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionMarca;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionOR;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionRubro;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ItemVentaMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.MarcaMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ProductoMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.RubroMock;

public class CondicionCompuestaTest {

	@Test
	public void testANDTwoConditionsFail() {
		IMarca marca1 = new MarcaMock(30123456784L);
		CondicionMarca cm1 = new CondicionMarca(marca1);
		CondicionMarca cm2 = new CondicionMarca(new MarcaMock(20123456784L));

		CondicionAND andCondition = new CondicionAND();
		andCondition.agregarCondicion(cm1);
		andCondition.agregarCondicion(cm2);
				
		IProducto producto = new ProductoMock(1, 100, marca1);
		assertFalse(andCondition.valida(new ItemVenta(producto, 1)));		
	}

	@Test
	public void testANDTwoConditionsOkey() {
		IMarca marca1 = new MarcaMock(30123456784L);
		IProducto producto = new ProductoMock(1, 100, marca1);
		
		CondicionMarca cm1 = new CondicionMarca(marca1);
		CondicionProducto cm2 = new CondicionProducto(producto);

		CondicionAND andCondition = new CondicionAND();
		andCondition.agregarCondicion(cm1);
		andCondition.agregarCondicion(cm2);
						
		assertTrue(andCondition.valida(new ItemVentaMock(producto, 1)));		
	}		
	
	@Test
	public void testORTwoConditionsOkey() {
		IMarca marca1 = new MarcaMock(30123456784L);
		CondicionMarca cm1 = new CondicionMarca(marca1);
		CondicionMarca cm2 = new CondicionMarca(new MarcaMock(20123456784L));

		CondicionOR orCondition = new CondicionOR();
		orCondition.agregarCondicion(cm1);
		orCondition.agregarCondicion(cm2);
				
		IProducto producto = new ProductoMock(1, 100, marca1);
		assertTrue(orCondition.valida(new ItemVenta(producto, 1)));		
	}
	
	@Test
	public void testORTwoConditionsFail() {
		CondicionMarca cm1 = new CondicionMarca(new MarcaMock(30123456784L));
		CondicionMarca cm2 = new CondicionMarca(new MarcaMock(20123456784L));
		
		CondicionOR orCondition = new CondicionOR();
		orCondition.agregarCondicion(cm1);
		orCondition.agregarCondicion(cm2);
				
		IMarca marca3 = new MarcaMock(30023456784L);
		IProducto producto = new ProductoMock(1, 100, marca3);
		assertFalse(orCondition.valida(new ItemVenta(producto, 1)));		
	}		
	
	@Test
	public void testANDTwoORConditionsFail() {
		
		IMarca marca2 = new MarcaMock(20123456784L);
		CondicionMarca cm1 = new CondicionMarca(new MarcaMock(30123456784L));
		CondicionMarca cm2 = new CondicionMarca(marca2);
		CondicionOR orCondition = new CondicionOR();
		orCondition.agregarCondicion(cm1);
		orCondition.agregarCondicion(cm2);
			
		IRubro rubro1 = new RubroMock(1);
		CondicionRubro cr1 = new CondicionRubro(rubro1);
		CondicionRubro cr2 = new CondicionRubro(new RubroMock(2));
		CondicionOR orCondition2 = new CondicionOR();
		orCondition2.agregarCondicion(cr1);
		orCondition2.agregarCondicion(cr2);
				
		CondicionAND andCondition = new CondicionAND();
		andCondition.agregarCondicion(orCondition);
		andCondition.agregarCondicion(orCondition2);
		
		IProducto producto1 = new ProductoMock(1,100,new RubroMock(3),new MarcaMock(5));
		IItemVenta itemVenta = new ItemVentaMock(producto1, 1);
		
		boolean testOR1 = orCondition.valida(itemVenta);
		boolean testOR2 = orCondition2.valida(itemVenta);
		boolean testAND = testOR1 & testOR2;
		
		assertTrue(testAND == andCondition.valida(itemVenta) && testAND == false);		
	}	
	
	@Test
	public void testANDTwoORConditions() {
		
		IMarca marca2 = new MarcaMock(20123456784L);
		CondicionMarca cm1 = new CondicionMarca(new MarcaMock(30123456784L));
		CondicionMarca cm2 = new CondicionMarca(marca2);
		CondicionOR orCondition = new CondicionOR();
		orCondition.agregarCondicion(cm1);
		orCondition.agregarCondicion(cm2);
			
		IRubro rubro1 = new RubroMock(1);
		CondicionRubro cr1 = new CondicionRubro(rubro1);
		CondicionRubro cr2 = new CondicionRubro(new RubroMock(2));
		CondicionOR orCondition2 = new CondicionOR();
		orCondition2.agregarCondicion(cr1);
		orCondition2.agregarCondicion(cr2);
				
		CondicionAND andCondition = new CondicionAND();
		andCondition.agregarCondicion(orCondition);
		andCondition.agregarCondicion(orCondition2);
		
		IProducto producto1 = new ProductoMock(1, 200, rubro1,marca2);
		assertTrue(andCondition.valida(new ItemVenta(producto1, 1)));
	}
}
