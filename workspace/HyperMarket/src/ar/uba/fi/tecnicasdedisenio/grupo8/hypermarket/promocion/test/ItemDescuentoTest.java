package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.CondicionProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.ItemDescuento;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.Promocion;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.RepositorioPromociones;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ItemVentaMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ProductoMock;

public class ItemDescuentoTest {

	@Test
	public void descuentoAplicadoProducto() {
		
		IProducto producto=new ProductoMock(1,10);
		IItemVenta itemVenta=new ItemVentaMock(producto, 3);
		
		RepositorioPromociones repositorioPromociones=new RepositorioPromociones();
		Promocion promocion=new Promocion(new CondicionProducto(producto),1,0.2);
		repositorioPromociones.add(promocion);
		
		ItemDescuento itemDescuento=new ItemDescuento(itemVenta,repositorioPromociones);
		assertEquals(6,itemDescuento.getImporteDescuento(),0);
	}

}
