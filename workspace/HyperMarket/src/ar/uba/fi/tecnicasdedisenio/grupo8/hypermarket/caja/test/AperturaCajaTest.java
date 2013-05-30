package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.AperturaCaja;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IMedioPago;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.MedioPago;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.Producto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.ProveedorPromociones;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.Sucursal;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.excepciones.VentaNoIniciadaException;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.excepciones.VentaYaIniciada;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.IPromocion;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.Promocion;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.RepositorioPromociones;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionMedioPago;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.ICondicionPromocion;

public class AperturaCajaTest {

	@Test
	public void testEstadoLuegoDeCrear() {
		AperturaCaja aperturaCaja=new AperturaCaja(new Sucursal("Central"));
		assertFalse(aperturaCaja.ventaIniciada());
		assertTrue(aperturaCaja.getImporteTotalConDescuentoPorMedioPago().isEmpty());
		assertEquals(aperturaCaja.getImporteTotalVentasConDescuento(), 0,0);
		assertEquals(aperturaCaja.getImporteTotalVentasSinDescuento(), 0,0);
	}

	@Test
	public void testIniciarVenta() {
		AperturaCaja aperturaCaja=new AperturaCaja(new Sucursal("Central"));
		aperturaCaja.iniciarVenta();
		assertTrue(aperturaCaja.ventaIniciada());
		assertEquals(1,aperturaCaja.getImporteTotalConDescuentoPorMedioPago().size());
		assertEquals(aperturaCaja.getImporteTotalVentasConDescuento(), 0,0);
		assertEquals(aperturaCaja.getImporteTotalVentasSinDescuento(), 0,0);
		try {
			aperturaCaja.iniciarVenta();
			fail();
		} catch (VentaYaIniciada e) {
			// OK! Prueba superada!
		}
	}


	@Test
	public void testAddProducto() {
		ProveedorPromociones.getInstance().setPromociones(new RepositorioPromociones());
		AperturaCaja aperturaCaja=new AperturaCaja(new Sucursal("Central"));
		IProducto coca=new Producto(10);

		try {
			aperturaCaja.addProducto(coca, 3);
			fail();
		} catch (VentaNoIniciadaException e) {
			// OK! Prueba superada!
		}

		aperturaCaja.iniciarVenta();
		
		aperturaCaja.addProducto(coca, 3);
		
		assertTrue(aperturaCaja.ventaIniciada());
		assertEquals(1,aperturaCaja.getImporteTotalConDescuentoPorMedioPago().size());
		assertEquals(aperturaCaja.getImporteTotalVentasConDescuento(), 3*10,0);
		assertEquals(aperturaCaja.getImporteTotalVentasSinDescuento(), 3*10,0);

		IProducto pepsi=new Producto(9);
		aperturaCaja.addProducto(pepsi, 2);
		
		assertTrue(aperturaCaja.ventaIniciada());
		assertEquals(1,aperturaCaja.getImporteTotalConDescuentoPorMedioPago().size());
		assertEquals(aperturaCaja.getImporteTotalVentasConDescuento(), 3*10+2*9,0);
		assertEquals(aperturaCaja.getImporteTotalVentasSinDescuento(), 3*10+2*9,0);
	}
	
	@Test
	public void testGetImporteVentaActual(){
		ProveedorPromociones.getInstance().setPromociones(new RepositorioPromociones());
		AperturaCaja aperturaCaja=new AperturaCaja(new Sucursal("Central"));
		IProducto coca=new Producto(10);

		try {
			aperturaCaja.addProducto(coca, 3);
			fail();
		} catch (VentaNoIniciadaException e) {
			// OK! Prueba superada!
		}

		aperturaCaja.iniciarVenta();
		
		aperturaCaja.addProducto(coca, 3);
		
		assertTrue(aperturaCaja.ventaIniciada());
		assertEquals(1,aperturaCaja.getImporteTotalConDescuentoPorMedioPago().size());
		assertEquals(aperturaCaja.getImporteVentaActual(), 3*10,0);

		aperturaCaja.confirmarVentaActual();
		
		aperturaCaja.iniciarVenta();
		IProducto pepsi=new Producto(9);
		aperturaCaja.addProducto(pepsi, 2);
		
		assertTrue(aperturaCaja.ventaIniciada());
		assertEquals(1,aperturaCaja.getImporteTotalConDescuentoPorMedioPago().size());
		assertEquals(aperturaCaja.getImporteVentaActual(), 2*9,0);
		
		aperturaCaja.confirmarVentaActual();
		
		try {
			aperturaCaja.getImporteVentaActual();
			fail();
		} catch (VentaNoIniciadaException e) {
			// OK! Prueba superada!
		}
	}
	
	@Test
	public void testGetItemsDescuentoVentaActual(){
		RepositorioPromociones repositorioPromociones=new RepositorioPromociones();
		IProducto coca=new Producto(10);
		ICondicionPromocion condicionEsCoca=new CondicionProducto(coca);
		IPromocion promocionCoca2X1=new Promocion(condicionEsCoca, 2, 0.5);
		repositorioPromociones.add(promocionCoca2X1);
		ProveedorPromociones.getInstance().setPromociones(repositorioPromociones);
		
		AperturaCaja aperturaCaja=new AperturaCaja(new Sucursal("Central"));

		aperturaCaja.iniciarVenta();
		
		aperturaCaja.addProducto(coca, 3);
		
		assertTrue(aperturaCaja.ventaIniciada());
		assertEquals(1,aperturaCaja.getImporteTotalConDescuentoPorMedioPago().size());
		assertEquals(aperturaCaja.getImporteVentaActual(), 2*10,0);
		assertEquals(1, aperturaCaja.getItemsDescuentoVentaActual().size());
		assertEquals(10, aperturaCaja.getItemsDescuentoVentaActual().iterator().next().getImporteDescuento(),0);
		
		aperturaCaja.confirmarVentaActual();
		
		aperturaCaja.iniciarVenta();
		IProducto pepsi=new Producto(9);
		aperturaCaja.addProducto(pepsi, 2);
		
		assertEquals(aperturaCaja.getImporteVentaActual(), 2*9,0);
		assertEquals(1,aperturaCaja.getItemsDescuentoVentaActual().size());
		assertEquals(0, aperturaCaja.getItemsDescuentoVentaActual().iterator().next().getImporteDescuento(),0);
		
		aperturaCaja.confirmarVentaActual();
	}
	
	@Test
	public void testSetMedioPagoVentaActual(){
		RepositorioPromociones repositorioPromociones=new RepositorioPromociones();
		IMedioPago efectivo=new MedioPago("Efectivo");
		ICondicionPromocion condicionEfectivo=new CondicionMedioPago(efectivo);
		IPromocion promocionCoca2X1=new Promocion(condicionEfectivo, 1, 0.1);
		repositorioPromociones.add(promocionCoca2X1);
		ProveedorPromociones.getInstance().setPromociones(repositorioPromociones);
		
		AperturaCaja aperturaCaja=new AperturaCaja(new Sucursal("Central"));

		aperturaCaja.iniciarVenta();
		
		IProducto coca=new Producto(10);
		aperturaCaja.addProducto(coca, 3);
		aperturaCaja.setMedioPagoVentaActual(efectivo);
		
		assertTrue(aperturaCaja.ventaIniciada());
		assertEquals(1,aperturaCaja.getImporteTotalConDescuentoPorMedioPago().size());
		assertTrue(aperturaCaja.getImporteTotalConDescuentoPorMedioPago().containsKey(efectivo));
		assertEquals(aperturaCaja.getImporteVentaActual(), 3*10*0.9,0);
		assertEquals(1, aperturaCaja.getItemsDescuentoVentaActual().size());
		assertEquals(3*10*0.1, aperturaCaja.getItemsDescuentoVentaActual().iterator().next().getImporteDescuento(),0);
		
		aperturaCaja.confirmarVentaActual();
		
		aperturaCaja.iniciarVenta();
		
		IMedioPago debito=new MedioPago("Tarjeta de debito");
		aperturaCaja.setMedioPagoVentaActual(debito);
		
		IProducto pepsi=new Producto(9);
		aperturaCaja.addProducto(pepsi, 2);
		
		assertEquals(aperturaCaja.getImporteVentaActual(), 2*9,0);
		assertEquals(2,aperturaCaja.getImporteTotalConDescuentoPorMedioPago().size());
		assertTrue(aperturaCaja.getImporteTotalConDescuentoPorMedioPago().containsKey(debito));
		assertTrue(aperturaCaja.getImporteTotalConDescuentoPorMedioPago().containsKey(efectivo));
		assertEquals(1,aperturaCaja.getItemsDescuentoVentaActual().size());
		assertEquals(0, aperturaCaja.getItemsDescuentoVentaActual().iterator().next().getImporteDescuento(),0);
		
		aperturaCaja.confirmarVentaActual();
	}

}
