package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.TreeMap;

import org.junit.Test;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.EstadoLaboral;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IEstadoLaboral;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.ItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.Marca;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.MedioPago;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.Producto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.Sucursal;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.Venta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.EstrategiaAdicionaPromocionesObligatorias;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.EstrategiaAplicaElMayorDescuentoPorItem;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.IEstrategiaAplicacionPromociones;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.IPromocion;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.Promocion;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.RepositorioPromociones;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionEstadoLaboral;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.CondicionProducto;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion.ICondicionPromocion;

public class NuevosRequerimientosTest {

	
	//TODO: Considerar la hipotesis en el informe
	@Test
	public void testPromocionesRequerimiento1() {
		IEstrategiaAplicacionPromociones  estrategiaBase = new EstrategiaAplicaElMayorDescuentoPorItem();
		RepositorioPromociones repositorioPromocionesObligatorias = new RepositorioPromociones();
		
		IEstadoLaboral jubilado = new EstadoLaboral("Jubilado");
		ICondicionPromocion condicionJubilado = new CondicionEstadoLaboral(jubilado);
		
		IPromocion promocionJubilado = new Promocion(condicionJubilado,1,0.1);
		repositorioPromocionesObligatorias.add(promocionJubilado);
		
		IEstrategiaAplicacionPromociones estrategia = new EstrategiaAdicionaPromocionesObligatorias(estrategiaBase, repositorioPromocionesObligatorias);
		
		IProducto maceta = new Producto(10);
		ICondicionPromocion condicionEsMaceta = new CondicionProducto(maceta);
		IPromocion promocionMaceta = new Promocion(condicionEsMaceta, 1, 0.1);
		
		
		IItemVenta itemVenta = new ItemVenta(maceta, 1);
		IVenta venta = new Venta(new Sucursal("Bernal"),new MedioPago("Efectivo"));
		venta.setEstadoLaboral(jubilado);
		venta.addItem(itemVenta);
		
		Collection<IPromocion> promocionesAplicanItem = new ArrayList<IPromocion>();
		promocionesAplicanItem.add(promocionMaceta);

		Collection<IPromocion> promocionesQueAplican = estrategia.getPromocionesAAplicar(itemVenta, promocionesAplicanItem);
		
		assertFalse(promocionesQueAplican.isEmpty());
		assertEquals(2, promocionesQueAplican.size());
		
		RepositorioPromociones repositorioDePromocionesAplicables = new RepositorioPromociones();
		for (IPromocion promocion : promocionesQueAplican) {
			repositorioDePromocionesAplicables.add(promocion);
		}
		
		venta.calcularDescuento(repositorioDePromocionesAplicables);
		
		assertEquals(10*0.9*0.9, venta.getImporteTotalConDescuento(),0);		
	}
	
	@Test
	public void testRankingDeVentas() {
		
		Sucursal bernal = new Sucursal("Bernal");
		IVenta venta1 = new Venta(bernal, new MedioPago("Efectivo"));
		IVenta venta2 = new Venta(bernal, new MedioPago("Efectivo"));
		
		Date hoy = new Date(System.currentTimeMillis());
		Date ayer = new Date(System.currentTimeMillis() - 1000*60*60*24);
		
		IProducto coca = new Producto(10.0, new Marca("Coca-Cola"));
		IProducto sprite = new Producto(8.0, new Marca("Coca-Cola"));
		IProducto maceta = new Producto(7.0, new Marca("El Reino de la Maceta"));
		
		{			
			IItemVenta iv1 = new ItemVenta(coca, 3);
			IItemVenta iv2 = new ItemVenta(sprite, 2);
			IItemVenta iv3 = new ItemVenta(maceta, 1);
			
			venta1.addItem(iv1);
			venta1.addItem(iv2);
			venta1.addItem(iv3);
			venta1.setFechaVenta(hoy);
		}
		
		{			
			IItemVenta iv1 = new ItemVenta(coca, 1);
			IItemVenta iv2 = new ItemVenta(sprite, 4);
			IItemVenta iv3 = new ItemVenta(maceta, 4);
			
			venta2.addItem(iv1);
			venta2.addItem(iv2);
			venta2.addItem(iv3);	
			venta1.setFechaVenta(hoy);			
		}
				
		TreeMap<IProducto, Integer>rankingDeProductos = bernal.obtenerRankingProductosMasVendidos();
		
		int i = 0;
		for (IProducto producto : rankingDeProductos.keySet()) {
			int cantidad = rankingDeProductos.get(producto).intValue();
			
			if (i == 0) {				
				assertEquals(sprite.getId(),producto.getId());
				assertEquals(6,cantidad);
			}
			else if (i == 1) {
				assertEquals(maceta.getId(),producto.getId());
				assertEquals(5,cantidad);				
			}
			else if (i == 2) {
				assertEquals(coca.getId(),producto.getId());
				assertEquals(4,cantidad);
			}			
			i++;
		}
		
	}
	

}
