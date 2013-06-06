package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.MedioPago;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.Sucursal;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.Venta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.DescuentoVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.RepositorioPromociones;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ItemVentaMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ProductoMock;

public class DescuentoVentaTest {

	@Test
	public void testVentaSinDescuento() {
		IVenta venta=new Venta(new Sucursal("Central"),new MedioPago("debito"));
		IItemVenta itemVenta=new ItemVentaMock(new ProductoMock(1,10.0), 10);
		venta.addItem(itemVenta);
		DescuentoVenta descuentoVenta=new DescuentoVenta(venta,new RepositorioPromociones());
		assertEquals(0, descuentoVenta.calcularImporteDescuento(),0);
	}

}
