package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;

public interface IPromocion {

	public double getImporteADescontar(IItemVenta itemVenta);
	
	public int getCantidadProductosAplicaParaItemVenta(IItemVenta itemVenta);
	
	public int getCantidadProductosAplica();
	
	public boolean aplicaParaItemVenta(IItemVenta itemVenta);

}
