package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Test;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.CondicionProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.Promocion;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.RepositorioPromociones;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ItemVentaMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.ProductoMock;

public class RepositorioPromocionesTest {

	@Test
	public void testGetPromocionesAplicaItemVenta() {
		
		IProducto producto;
		RepositorioPromociones repositorio=new RepositorioPromociones();

		producto=new ProductoMock(1);
		Promocion promocionQueAplica1=new Promocion(new CondicionProducto(producto), 1, 0.1);
		repositorio.add(promocionQueAplica1);

		producto=new ProductoMock(2);
		Promocion promocionQueNoAplica1=new Promocion(new CondicionProducto(producto), 1, 0.2);
		repositorio.add(promocionQueNoAplica1);

		producto=new ProductoMock(3);
		Promocion promocionQueNoAplica2=new Promocion(new CondicionProducto(producto), 1, 0.2);
		repositorio.add(promocionQueNoAplica2);

		producto=new ProductoMock(1);
		Promocion promocionQueAplica2=new Promocion(new CondicionProducto(producto), 1, 0.2);
		repositorio.add(promocionQueAplica2);

		producto=new ProductoMock(4);
		Promocion promocionQueNoAplica3=new Promocion(new CondicionProducto(producto), 1, 0.2);
		repositorio.add(promocionQueNoAplica3);

		producto=new ProductoMock(1, 10);
		IItemVenta itemVenta=new ItemVentaMock(producto, 10);
		
		Collection<Promocion> promocionesQueAplican=repositorio.getPromocionesAplicaItemVenta(itemVenta);
		assertTrue(promocionesQueAplican.contains(promocionQueAplica1));
		assertTrue(promocionesQueAplican.contains(promocionQueAplica2));
		assertFalse(promocionesQueAplican.contains(promocionQueNoAplica1));
		assertFalse(promocionesQueAplican.contains(promocionQueNoAplica2));
		assertFalse(promocionesQueAplican.contains(promocionQueNoAplica3));

	}

}
