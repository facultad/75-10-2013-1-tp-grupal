package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.ItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.Marca;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.Producto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.Rubro;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionAND;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionMarca;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionOR;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionRubro;

public class CondicionCompuestaTest {

	@Test
	public void testANDTwoConditionsFail() {
		Marca marca1 = new Marca("Marca1", 30123456784L);
		CondicionMarca cm1 = new CondicionMarca(marca1);
		CondicionMarca cm2 = new CondicionMarca(new Marca("Marca2", 20123456784L));

		CondicionAND andCondition = new CondicionAND();
		andCondition.agregarCondicion(cm1);
		andCondition.agregarCondicion(cm2);
				
		Producto producto = new Producto(1, 100, marca1);
		assertFalse(andCondition.valida(new ItemVenta(producto, 1)));		
	}

	@Test
	public void testANDTwoConditionsOkey() {
		Marca marca1 = new Marca("Marca1", 30123456784L);
		Producto producto = new Producto(1, 100, marca1);
		
		CondicionMarca cm1 = new CondicionMarca(marca1);
		CondicionProducto cm2 = new CondicionProducto(producto);

		CondicionAND andCondition = new CondicionAND();
		andCondition.agregarCondicion(cm1);
		andCondition.agregarCondicion(cm2);
						
		assertTrue(andCondition.valida(new ItemVenta(producto, 1)));		
	}		
	
	@Test
	public void testORTwoConditionsOkey() {
		Marca marca1 = new Marca("Marca1", 30123456784L);
		CondicionMarca cm1 = new CondicionMarca(marca1);
		CondicionMarca cm2 = new CondicionMarca(new Marca("Marca2", 20123456784L));

		CondicionOR orCondition = new CondicionOR();
		orCondition.agregarCondicion(cm1);
		orCondition.agregarCondicion(cm2);
				
		Producto producto = new Producto(1, 100, marca1);
		assertTrue(orCondition.valida(new ItemVenta(producto, 1)));		
	}
	
	@Test
	public void testORTwoConditionsFail() {
		CondicionMarca cm1 = new CondicionMarca(new Marca("Marca1", 30123456784L));
		CondicionMarca cm2 = new CondicionMarca(new Marca("Marca2", 20123456784L));
		
		CondicionOR orCondition = new CondicionOR();
		orCondition.agregarCondicion(cm1);
		orCondition.agregarCondicion(cm2);
				
		Marca marca3 = new Marca("Marca3", 30023456784L);
		Producto producto = new Producto(1, 100, marca3);
		assertFalse(orCondition.valida(new ItemVenta(producto, 1)));		
	}		
	
	@Test
	public void testANDTwoORConditionsFail() {
		
		Marca marca2 = new Marca("Marca2", 20123456784L);
		CondicionMarca cm1 = new CondicionMarca(new Marca("Marca1", 30123456784L));
		CondicionMarca cm2 = new CondicionMarca(marca2);
		CondicionOR orCondition = new CondicionOR();
		orCondition.agregarCondicion(cm1);
		orCondition.agregarCondicion(cm2);
			
		Rubro rubro1 = new Rubro("Rubro1",1);
		CondicionRubro cr1 = new CondicionRubro(rubro1);
		CondicionRubro cr2 = new CondicionRubro(new Rubro("Rubro2",2));
		CondicionOR orCondition2 = new CondicionOR();
		orCondition2.agregarCondicion(cr1);
		orCondition2.agregarCondicion(cr2);
				
		CondicionAND andCondition = new CondicionAND();
		andCondition.agregarCondicion(orCondition);
		andCondition.agregarCondicion(orCondition2);
		
		Producto producto1 = new Producto(1, 100, marca2, new Rubro("Rubro3", 3));
		ItemVenta itemVenta = new ItemVenta(producto1, 1);
		
		boolean testOR1 = orCondition.valida(itemVenta);
		boolean testOR2 = orCondition2.valida(itemVenta);
		boolean testAND = testOR1 & testOR2;
		
		assertTrue(testAND == andCondition.valida(itemVenta) && testAND == false);		
	}	
	
	@Test
	public void testANDTwoORConditions() {
		
		Marca marca2 = new Marca("Marca2", 20123456784L);
		CondicionMarca cm1 = new CondicionMarca(new Marca("Marca1", 30123456784L));
		CondicionMarca cm2 = new CondicionMarca(marca2);
		CondicionOR orCondition = new CondicionOR();
		orCondition.agregarCondicion(cm1);
		orCondition.agregarCondicion(cm2);
			
		Rubro rubro1 = new Rubro("Rubro1",1);
		CondicionRubro cr1 = new CondicionRubro(rubro1);
		CondicionRubro cr2 = new CondicionRubro(new Rubro("Rubro2",2));
		CondicionOR orCondition2 = new CondicionOR();
		orCondition2.agregarCondicion(cr1);
		orCondition2.agregarCondicion(cr2);
				
		CondicionAND andCondition = new CondicionAND();
		andCondition.agregarCondicion(orCondition);
		andCondition.agregarCondicion(orCondition2);
		
		Producto producto1 = new Producto(1, 200, marca2, rubro1);
		assertTrue(andCondition.valida(new ItemVenta(producto1, 1)));
	}
}
