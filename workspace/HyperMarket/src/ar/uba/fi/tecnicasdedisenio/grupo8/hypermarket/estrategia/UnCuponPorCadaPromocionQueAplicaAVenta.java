package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.estrategia;

import java.util.ArrayList;
import java.util.Collection;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.CuponDescuento;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.IPromocion;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.RepositorioPromociones;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.singleton.ProveedorEstrategiaAplicacionPromociones;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.singleton.ProveedorRepositorioPromocionesCupones;

public class UnCuponPorCadaPromocionQueAplicaAVenta implements
		IEstrategiaGeneracionCupones {

	@Override
	public Collection<CuponDescuento> generarCupones(IVenta venta) {
		Collection<CuponDescuento> cupones=new ArrayList<CuponDescuento>();
		
		IEstrategiaAplicacionPromociones estrategiaAplicacionPromociones=
				ProveedorEstrategiaAplicacionPromociones.getInstance().getEstrategia();
		RepositorioPromociones repositorioPromocionesCupones=
				ProveedorRepositorioPromocionesCupones.getInstance();
		
		for (IItemVenta itemVenta:venta.getItemsVenta()){

			Collection<IPromocion> todasLasPromocionesQueAplicanAlItem=
					repositorioPromocionesCupones.getPromocionesAplicaItemVenta(itemVenta);
			Collection<IPromocion> promocionesQueAplicanAlItem = 
					estrategiaAplicacionPromociones.getPromocionesAAplicar(
							itemVenta, todasLasPromocionesQueAplicanAlItem);
			
			for (IPromocion promocion:promocionesQueAplicanAlItem){
				double importeCupon=promocion.getImporteADescontar(itemVenta);
				CuponDescuento cupon=new CuponDescuento(importeCupon, 0.2);
				cupones.add(cupon);
			}
		}
		
		return cupones;
	}

}
