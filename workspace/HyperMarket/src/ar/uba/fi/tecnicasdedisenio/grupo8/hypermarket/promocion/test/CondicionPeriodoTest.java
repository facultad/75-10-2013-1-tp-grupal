package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionPeriodo;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ItemVentaMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ProductoMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.VentaMock;

public class CondicionPeriodoTest {

	@Test
	public void testFechaEnPeriodoPromocion() {
		@SuppressWarnings("deprecation")
		Date desde = new Date(2012,01,1);
		@SuppressWarnings("deprecation")
		Date hasta = new Date(2013,01,1);
		
		CondicionPeriodo condicion = new CondicionPeriodo(desde, hasta);
		
		@SuppressWarnings("deprecation")
		Date fecha = new Date(2012,05,15);		
		VentaMock venta = new VentaMock();
		venta.setFechaVenta(fecha);
		
		ItemVentaMock iv = new ItemVentaMock(new ProductoMock(1), 1);
		iv.setVenta(venta);
		
		assertTrue(condicion.valida(iv));
	}

	@Test
	public void testFechaFueraDePeriodoPromocion() {
		@SuppressWarnings("deprecation")
		Date desde = new Date(2012,01,1);
		@SuppressWarnings("deprecation")
		Date hasta = new Date(2013,01,1);
		
		CondicionPeriodo condicion = new CondicionPeriodo(desde, hasta);
		
		@SuppressWarnings("deprecation")
		Date fecha = new Date(2010,05,15);		
		VentaMock venta = new VentaMock();
		venta.setFechaVenta(fecha);
		
		ItemVentaMock iv = new ItemVentaMock(new ProductoMock(1), 1);
		iv.setVenta(venta);
		
		assertFalse(condicion.valida(iv));
	}
	
	@Test
	public void testFechaEnPromocion() {
		@SuppressWarnings("deprecation")
		Date hasta = new Date(2013,01,1);
		
		CondicionPeriodo condicion = new CondicionPeriodo(hasta);
		
		@SuppressWarnings("deprecation")
		Date fecha = new Date(2012,05,15);
		VentaMock venta = new VentaMock();
		venta.setFechaVenta(fecha);
		
		ItemVentaMock iv = new ItemVentaMock(new ProductoMock(1), 1);
		iv.setVenta(venta);
		
		assertTrue(condicion.valida(iv));
	}
	
	@Test
	public void testPromocionInvalida() {
		try {
			new CondicionPeriodo(null);	
			assertTrue(false);
		} catch (NullPointerException e) {
			assertTrue(true);
		}		
	}
		
	@Test
	public void testFechaPromocionMalDefinida() {
		try {
			@SuppressWarnings("deprecation")
			Date hasta = new Date(2012,01,1);
			@SuppressWarnings("deprecation")
			Date desde = new Date(2013,01,1);			
			
			new CondicionPeriodo(desde,hasta);	
			assertTrue(false);
		} catch (IllegalArgumentException e) {
			assertTrue(true);
		}		
	}

	
}
