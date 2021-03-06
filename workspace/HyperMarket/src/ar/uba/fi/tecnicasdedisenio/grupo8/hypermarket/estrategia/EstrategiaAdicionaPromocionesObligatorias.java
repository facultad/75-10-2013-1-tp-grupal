package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.estrategia;

import java.util.Collection;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.IPromocion;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.RepositorioPromociones;

public class EstrategiaAdicionaPromocionesObligatorias implements
		IEstrategiaAplicacionPromociones {

	private IEstrategiaAplicacionPromociones estrategiaBase;
	private RepositorioPromociones repositorioPromocionesObligatorias;

	public EstrategiaAdicionaPromocionesObligatorias(
			IEstrategiaAplicacionPromociones estrategiaBase,
			RepositorioPromociones repositorioPromocionesObligatorias) {

		this.estrategiaBase = estrategiaBase;
		this.repositorioPromocionesObligatorias = repositorioPromocionesObligatorias;
	}

	@Override
	public Collection<IPromocion> getPromocionesAAplicar(IItemVenta itemVenta,
			Collection<IPromocion> promocionesQueAplicanAlItem) {

		Collection<IPromocion> promocionesQueAplican = estrategiaBase.getPromocionesAAplicar(itemVenta, promocionesQueAplicanAlItem);
	
		Collection<IPromocion> promocionesObligatorias = repositorioPromocionesObligatorias.getPromocionesAplicaItemVenta(itemVenta);
		
		promocionesQueAplican.addAll(promocionesObligatorias);
		
		return promocionesQueAplican;
	}

}
