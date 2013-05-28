package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IEntidadFinanciera;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.ItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionEntidadFinanciera;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.EntidadFinancieraMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.MedioPagoMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ProductoMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.VentaMock;

public class CondicionEntidadFinancieraTest {

	@Test
	public void testEntidadFinancieraPerteneceAPromocion() {
		
		IEntidadFinanciera entidadFinanciera = new EntidadFinancieraMock();
		CondicionEntidadFinanciera condicion = new CondicionEntidadFinanciera(entidadFinanciera);
				
		MedioPagoMock medioPago = new MedioPagoMock(entidadFinanciera);
		VentaMock venta = new VentaMock(medioPago);
		
		ItemVenta iv = new ItemVenta(new ProductoMock(1), 1);
		iv.setVenta(venta);
		
		condicion.valida(iv);
	}

	@Test
	public void testEntidadFinancieraNoPerteneceAPromocion() {
		
		IEntidadFinanciera entidadFinanciera = new EntidadFinancieraMock();
		CondicionEntidadFinanciera condicion = new CondicionEntidadFinanciera(entidadFinanciera);
				
		MedioPagoMock medioPago = new MedioPagoMock(null);
		VentaMock venta = new VentaMock(medioPago);
		
		ItemVenta iv = new ItemVenta(new ProductoMock(1), 1);
		iv.setVenta(venta);
		
		condicion.valida(iv);
	}
	
	
}
