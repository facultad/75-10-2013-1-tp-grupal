package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.EntidadFinanciera;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IEntidadFinanciera;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IMedioPago;

public class CondicionEntidadFinanciera extends BaseCondicionPromocion {

	IEntidadFinanciera entidadFinanciera;
	
	public CondicionEntidadFinanciera(IEntidadFinanciera entidadEntidad) {
		this.entidadFinanciera = entidadEntidad;
		
	}
	
	@Override
	protected boolean evaluarCondicion(IItemVenta itemVenta) {
			
		IMedioPago pago = itemVenta.getVenta().getPago();
		
		if (pago.hasEntidadFinanciera()) {
			return pago.getEntidadFinanciera().getId() == entidadFinanciera.getId();
		}
		
		return false;
	}

}
