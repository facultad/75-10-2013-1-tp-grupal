package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.EstadoLaboral;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.Venta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionEstadoLaboral;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionMedioPago;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.ICondicionPromocion;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ItemVentaMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.MedioPagoMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ProductoMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.SucursalMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.VentaMock;

public class CondicionEstadoLaboralTest {

	@Test
	public void pasaCondicion() {
		EstadoLaboral jubilado = new EstadoLaboral("Jubilado");
		
		IProducto producto = new ProductoMock(1,10);
		
		ICondicionPromocion condicion = new CondicionEstadoLaboral(jubilado);
		
		IVenta venta = new Venta(new SucursalMock(1), new MedioPagoMock(1));
		venta.setEstadoLaboral(jubilado);
		
		IItemVenta itemVenta = new ItemVentaMock(producto, 10, venta);
		
		assertTrue(condicion.valida(itemVenta));
	}

	@Test
	public void noPasaCondicion() {
		EstadoLaboral jubilado = new EstadoLaboral("Jubilado");
		EstadoLaboral empleado = new EstadoLaboral("Empleado");
		
		IProducto producto = new ProductoMock(1,10);
		
		ICondicionPromocion condicion = new CondicionEstadoLaboral(jubilado);
		
		IVenta venta = new Venta(new SucursalMock(1), new MedioPagoMock(1));
		venta.setEstadoLaboral(empleado);
		
		IItemVenta itemVenta = new ItemVentaMock(producto, 10, venta);
		
		assertFalse(condicion.valida(itemVenta));
	}

	@Test
	public void noPasaCondicionNegada() {
		EstadoLaboral jubilado = new EstadoLaboral("Jubilado");
		
		IProducto producto = new ProductoMock(1,10);
		
		ICondicionPromocion condicion = new CondicionEstadoLaboral(jubilado);
		condicion.negar();
		IVenta venta = new Venta(new SucursalMock(1), new MedioPagoMock(1));
		venta.setEstadoLaboral(jubilado);
		
		IItemVenta itemVenta = new ItemVentaMock(producto, 10, venta);
		
		assertFalse(condicion.valida(itemVenta));
	}

	@Test
	public void pasaCondicionNegada() {
		EstadoLaboral jubilado = new EstadoLaboral("Jubilado");
		EstadoLaboral empleado = new EstadoLaboral("Empleado");
		
		IProducto producto = new ProductoMock(1,10);
		
		ICondicionPromocion condicion = new CondicionEstadoLaboral(jubilado);
		condicion.negar();
		
		IVenta venta = new Venta(new SucursalMock(1), new MedioPagoMock(1));
		venta.setEstadoLaboral(empleado);
		
		IItemVenta itemVenta = new ItemVentaMock(producto, 10, venta);
		
		assertTrue(condicion.valida(itemVenta));
	}

	
}
