package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.EstadoLaboral;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IEstadoLaboral;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.ItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.MedioPago;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.Producto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.Sucursal;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.Venta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.estrategia.EstrategiaAdicionaPromocionesObligatorias;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.estrategia.EstrategiaAplicaElMayorDescuentoPorItem;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.estrategia.IEstrategiaAplicacionPromociones;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.IPromocion;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.Promocion;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.RepositorioPromociones;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionEstadoLaboral;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.ICondicionPromocion;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.MedioPagoMock;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock.SucursalMock;

public class EstrategiaAdicionaPromocionesObligatoriasTest {

	@Test
	public void testPromocionQueAplicaAlEstadoLaboral() {
		IEstrategiaAplicacionPromociones  estrategiaBase = new EstrategiaAplicaElMayorDescuentoPorItem();
		RepositorioPromociones repositorioPromocionesObligatorias = new RepositorioPromociones();
		
		IEstadoLaboral jubilado = new EstadoLaboral("Jubilado");
		ICondicionPromocion condicionJubilado = new CondicionEstadoLaboral(jubilado);
		
		IPromocion promocionJubilado = new Promocion(condicionJubilado,1,0.1);
		repositorioPromocionesObligatorias.add(promocionJubilado);
		
		IEstrategiaAplicacionPromociones estrategia = new EstrategiaAdicionaPromocionesObligatorias(estrategiaBase, repositorioPromocionesObligatorias);
		
		IProducto maceta = new Producto(10);
		//ICondicionPromocion condicionEsMaceta = new CondicionProducto(maceta);
		
		IItemVenta itemVenta = new ItemVenta(maceta, 1);
		IVenta venta = new Venta(new Sucursal("Bernal"),new MedioPago("Efectivo"));
		venta.setEstadoLaboral(jubilado);
		venta.addItem(itemVenta);
		
		Collection<IPromocion> promocionesQueAplican = estrategia.getPromocionesAAplicar(itemVenta, new ArrayList<IPromocion>());
		
		assertFalse(promocionesQueAplican.isEmpty());
		
		RepositorioPromociones repositorioDePromocionesAplicables = new RepositorioPromociones();
		for (IPromocion promocion : promocionesQueAplican) {
			repositorioDePromocionesAplicables.add(promocion);
		}
		
		venta.calcularDescuento(repositorioDePromocionesAplicables);
		
		assertEquals(9.0, venta.getImporteTotalConDescuento(),0);		
	}
	
}
