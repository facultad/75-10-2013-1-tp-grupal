package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.condicion;

import java.util.ArrayList;
import java.util.Collection;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.excepciones.CondicionNoValidaParaItemVenta;

public class CondicionOR extends BaseCondicionPromocion {

	ArrayList<ICondicionPromocion> miembros;
	
	public CondicionOR() {
		miembros = new ArrayList<ICondicionPromocion>();
	}
		
	
	@Override
	protected boolean evaluarCondicion(IItemVenta itemVenta) {
		boolean evaluacion = false;
		
		for (ICondicionPromocion icp : miembros) {
			if (icp.valida(itemVenta)) {
				evaluacion = true;
				break;
			}
		}		
		
		return evaluacion;
	}

	public void agregarCondicion(ICondicionPromocion condicion) {
		miembros.add(condicion);
	}

	public Collection<IItemVenta> getItemsDeLosQueDepende(IItemVenta itemVenta){
		Collection<IItemVenta> itemsDeLosQueDepende=new ArrayList<IItemVenta>();
		boolean valido=false;
		for (ICondicionPromocion icp : miembros) {
			if (icp.valida(itemVenta)){
				valido=true;
				itemsDeLosQueDepende.addAll(icp.getItemsDeLosQueDepende(itemVenta));
				break;
			}
		}
		if (!valido)
			throw new CondicionNoValidaParaItemVenta();
		return itemsDeLosQueDepende;
	}

}
