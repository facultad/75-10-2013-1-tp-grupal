package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.test;

import static org.junit.Assert.*;
import org.junit.Test;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.*;


public class VentaTest {

	@Test
	public void testCajaIntegral() {
		// Creacion de productos, sucursal, rubro y medio de pago
		// Puede ser reemplazado por un import de un archivo
		
		IMarca cocacola = new Marca("CocaCola");
		IMarca pepsi = new Marca("Pepsi");

		IRubro bebidas = new Rubro("Bebidas");
		
		ISucursal suc1 = new Sucursal("Saavedra");
		IMedioPago medio1 = new MedioPago("tarjeta");
		
		
		// Creacion de productos
		// Puede ser reemplazado por un input un programa
		IProducto p1 = new Producto(10.5 , cocacola, bebidas);
		IItemVenta item1 = new ItemVenta (p1,2);
		
		IVenta venta = new Venta(suc1,medio1);
		
		IProducto p2 = new Producto(9.7 , pepsi, bebidas);
		IItemVenta item2 = new ItemVenta (p2,6);
		
		venta.addItem(item1);
		venta.addItem(item2);
		
		assertEquals(venta.getCantidadUnidades(),8);
	}

	
}
