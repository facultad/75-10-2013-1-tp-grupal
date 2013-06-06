package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.singleton;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.estrategia.IEstrategiaGeneracionCupones;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.estrategia.UnCuponPorCadaPromocionQueAplicaAVenta;


public class ProveedorGeneradorCupones {

	private static IEstrategiaGeneracionCupones instance=new UnCuponPorCadaPromocionQueAplicaAVenta();

	public static IEstrategiaGeneracionCupones getInstance() {
		return instance;
	}

	private ProveedorGeneradorCupones() {
	}

	public void setInstance(IEstrategiaGeneracionCupones instance) {
		ProveedorGeneradorCupones.instance = instance;
	}
	
}
