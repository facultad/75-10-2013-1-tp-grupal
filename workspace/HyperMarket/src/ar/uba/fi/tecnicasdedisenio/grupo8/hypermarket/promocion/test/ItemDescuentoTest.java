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
	
	@Test
	public void testMayorDescuentoAplicadoProducto() {
		
		IProducto producto=new ProductoMock(1,10);
		IItemVenta itemVenta;
		ItemDescuento itemDescuento;
		
		RepositorioPromociones repositorioPromociones=new RepositorioPromociones();
		// 20% por unidad
		repositorioPromociones.add(
				new Promocion(new CondicionProducto(producto),1,0.2));
		// 50% en segunda unidad
		repositorioPromociones.add(
				new Promocion(new CondicionProducto(producto),2,0.25));
		
		itemVenta=new ItemVentaMock(producto, 1);
		itemDescuento=new ItemDescuento(itemVenta,repositorioPromociones);
		assertEquals(2,itemDescuento.getImporteDescuento(),0);

		itemVenta=new ItemVentaMock(producto, 2);
		itemDescuento=new ItemDescuento(itemVenta,repositorioPromociones);
		assertEquals(5,itemDescuento.getImporteDescuento(),0);

		itemVenta=new ItemVentaMock(producto, 3);
		itemDescuento=new ItemDescuento(itemVenta,repositorioPromociones);
		assertEquals(6,itemDescuento.getImporteDescuento(),0);

		itemVenta=new ItemVentaMock(producto, 4);
		itemDescuento=new ItemDescuento(itemVenta,repositorioPromociones);
		assertEquals(10,itemDescuento.getImporteDescuento(),0);

		itemVenta=new ItemVentaMock(producto, 5);
		itemDescuento=new ItemDescuento(itemVenta,repositorioPromociones);
		assertEquals(10,itemDescuento.getImporteDescuento(),0);

		itemVenta=new ItemVentaMock(producto, 6);
		itemDescuento=new ItemDescuento(itemVenta,repositorioPromociones);
		assertEquals(15,itemDescuento.getImporteDescuento(),0);

		itemVenta=new ItemVentaMock(producto, 6);
		itemDescuento=new ItemDescuento(itemVenta,repositorioPromociones);
		assertEquals(15,itemDescuento.getImporteDescuento(),0);
	}

}
