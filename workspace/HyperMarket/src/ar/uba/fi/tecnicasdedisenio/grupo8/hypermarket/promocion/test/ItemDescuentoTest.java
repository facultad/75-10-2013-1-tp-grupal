package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.estrategia.EstrategiaAplicaElMayorDescuentoPorItem;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.ItemDescuento;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.Promocion;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.RepositorioPromociones;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionDependiente;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ItemVentaMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ProductoMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.VentaMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.singleton.ProveedorEstrategiaAplicacionPromociones;

public class ItemDescuentoTest {

	@Test
	public void descuentoAplicadoProducto() {
		ProveedorEstrategiaAplicacionPromociones.getInstance().setEstrategia(new EstrategiaAplicaElMayorDescuentoPorItem());		IProducto producto=new ProductoMock(1,10);
		IItemVenta itemVenta=new ItemVentaMock(producto, 3);
		
		RepositorioPromociones repositorioPromociones=new RepositorioPromociones();
		Promocion promocion=new Promocion(new CondicionProducto(producto),1,0.2);
		repositorioPromociones.add(promocion);
		
		ItemDescuento itemDescuento=new ItemDescuento(itemVenta,repositorioPromociones);
		assertEquals(6,itemDescuento.calcularImporteDescuento(),0);
	}
	
	@Test
	public void testMayorDescuentoAplicadoProducto() {
		ProveedorEstrategiaAplicacionPromociones.getInstance().setEstrategia(new EstrategiaAplicaElMayorDescuentoPorItem());
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
		assertEquals(2,itemDescuento.calcularImporteDescuento(),0);

		itemVenta=new ItemVentaMock(producto, 2);
		itemDescuento=new ItemDescuento(itemVenta,repositorioPromociones);
		assertEquals(5,itemDescuento.calcularImporteDescuento(),0);

		itemVenta=new ItemVentaMock(producto, 3);
		itemDescuento=new ItemDescuento(itemVenta,repositorioPromociones);
		assertEquals(6,itemDescuento.calcularImporteDescuento(),0);

		itemVenta=new ItemVentaMock(producto, 4);
		itemDescuento=new ItemDescuento(itemVenta,repositorioPromociones);
		assertEquals(10,itemDescuento.calcularImporteDescuento(),0);

		itemVenta=new ItemVentaMock(producto, 5);
		itemDescuento=new ItemDescuento(itemVenta,repositorioPromociones);
		assertEquals(10,itemDescuento.calcularImporteDescuento(),0);

		itemVenta=new ItemVentaMock(producto, 6);
		itemDescuento=new ItemDescuento(itemVenta,repositorioPromociones);
		assertEquals(15,itemDescuento.calcularImporteDescuento(),0);

		itemVenta=new ItemVentaMock(producto, 6);
		itemDescuento=new ItemDescuento(itemVenta,repositorioPromociones);
		assertEquals(15,itemDescuento.calcularImporteDescuento(),0);
	}

	@Test
	public void descuentoAplicadoDependiente() {
		ProveedorEstrategiaAplicacionPromociones.getInstance().setEstrategia(new EstrategiaAplicaElMayorDescuentoPorItem());
		IProducto producto1=new ProductoMock(1,10);
		IProducto producto2=new ProductoMock(2,20);
		IProducto producto3=new ProductoMock(3,30);
		
		IItemVenta itemVenta1=new ItemVentaMock(producto1, 2);
		IItemVenta itemVenta2=new ItemVentaMock(producto2, 2);
		IItemVenta itemVenta3=new ItemVentaMock(producto3, 3);
		IVenta venta=new VentaMock();
		venta.addItem(itemVenta1);
		venta.addItem(itemVenta2);
		venta.addItem(itemVenta3);
		
		RepositorioPromociones repositorioPromociones=new RepositorioPromociones();
		Promocion promocion=new Promocion(
				new CondicionDependiente(new CondicionProducto(producto1)),2,0.1);
		repositorioPromociones.add(promocion);
		
		ItemDescuento itemDescuento1=new ItemDescuento(itemVenta1,repositorioPromociones);
		ItemDescuento itemDescuento2=new ItemDescuento(itemVenta2,repositorioPromociones);
		ItemDescuento itemDescuento3=new ItemDescuento(itemVenta3,repositorioPromociones);
		
		assertEquals(0,itemDescuento1.calcularImporteDescuento(),0);
		assertEquals(4,itemDescuento2.calcularImporteDescuento(),0);
		assertEquals(6,itemDescuento3.calcularImporteDescuento(),0);
	}

	@Test
	public void testMayorDescuentoConDependenciaAplicado() {
		ProveedorEstrategiaAplicacionPromociones.getInstance().setEstrategia(new EstrategiaAplicaElMayorDescuentoPorItem());
		IProducto producto1=new ProductoMock(1,10);
		IProducto producto2=new ProductoMock(2,20);
		IProducto producto3=new ProductoMock(3,30);
		IProducto producto4=new ProductoMock(4,40);
		IProducto producto5=new ProductoMock(5,50);
		IProducto producto6=new ProductoMock(6,60);
		
		IItemVenta itemVenta1=new ItemVentaMock(producto1, 1);
		IItemVenta itemVenta2=new ItemVentaMock(producto2, 2);
		IItemVenta itemVenta3=new ItemVentaMock(producto3, 3);
		IItemVenta itemVenta4=new ItemVentaMock(producto4, 4);
		IItemVenta itemVenta5=new ItemVentaMock(producto5, 5);
		IItemVenta itemVenta6=new ItemVentaMock(producto6, 6);
		IVenta venta=new VentaMock();
		venta.addItem(itemVenta1);
		venta.addItem(itemVenta2);
		venta.addItem(itemVenta3);
		venta.addItem(itemVenta4);
		venta.addItem(itemVenta5);
		venta.addItem(itemVenta6);
		
		RepositorioPromociones repositorioPromociones=new RepositorioPromociones();
		Promocion promocion=new Promocion(
				new CondicionDependiente(new CondicionProducto(producto1)),1,0.2);
		repositorioPromociones.add(promocion);
		// 20% por unidad
		repositorioPromociones.add(
				new Promocion(new CondicionProducto(producto2),1,0.2));
		// 50% en segunda unidad
		repositorioPromociones.add(
				new Promocion(new CondicionProducto(producto3),2,0.25));

		ItemDescuento itemDescuento1=new ItemDescuento(itemVenta1,repositorioPromociones);
		ItemDescuento itemDescuento2=new ItemDescuento(itemVenta2,repositorioPromociones);
		ItemDescuento itemDescuento3=new ItemDescuento(itemVenta3,repositorioPromociones);
		ItemDescuento itemDescuento4=new ItemDescuento(itemVenta4,repositorioPromociones);
		ItemDescuento itemDescuento5=new ItemDescuento(itemVenta5,repositorioPromociones);
		ItemDescuento itemDescuento6=new ItemDescuento(itemVenta6,repositorioPromociones);
		
		assertEquals(0,itemDescuento1.calcularImporteDescuento(),0);
		assertEquals(8,itemDescuento2.calcularImporteDescuento(),0);
		assertEquals(15,itemDescuento3.calcularImporteDescuento(),0);
		assertEquals(8,itemDescuento4.calcularImporteDescuento(),0);
		assertEquals(10,itemDescuento5.calcularImporteDescuento(),0);
		assertEquals(12,itemDescuento6.calcularImporteDescuento(),0);
	}

}
