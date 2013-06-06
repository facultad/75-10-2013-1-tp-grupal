package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.estrategia;

import java.util.Collection;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.CuponDescuento;


public class AplicaTodosLosCupones implements IEstrategiaAplicacionCupones {

	@Override
	public double getImporteDescuento(IVenta venta) {
		Collection<CuponDescuento> cupones=venta.getCupones();
		
		double importeDescuentoCupones=0;
		
		for(CuponDescuento cupon:cupones){
			importeDescuentoCupones+=cupon.getImporteADescontar(venta);
		}
		
		return importeDescuentoCupones;
	}

}
