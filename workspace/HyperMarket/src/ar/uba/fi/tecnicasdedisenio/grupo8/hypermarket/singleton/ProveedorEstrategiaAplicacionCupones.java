package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.singleton;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.estrategia.AplicaTodosLosCupones;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.estrategia.IEstrategiaAplicacionCupones;

public class ProveedorEstrategiaAplicacionCupones {
	
	private static IEstrategiaAplicacionCupones instance=new AplicaTodosLosCupones();

	public static IEstrategiaAplicacionCupones getInstance() {
		return instance;
	}
	
	public static void getInstance(IEstrategiaAplicacionCupones instance) {
		ProveedorEstrategiaAplicacionCupones.instance=instance;
	}
}
