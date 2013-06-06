package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.singleton;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.estrategia.EstrategiaAplicaElMayorDescuentoPorItem;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.estrategia.IEstrategiaAplicacionPromociones;

public class ProveedorEstrategiaAplicacionPromociones {

	private static ProveedorEstrategiaAplicacionPromociones instance;

	public static ProveedorEstrategiaAplicacionPromociones getInstance() {
		if (instance==null)
			instance=new ProveedorEstrategiaAplicacionPromociones();
		return instance;
	}

	private IEstrategiaAplicacionPromociones estrategia;
	
	private ProveedorEstrategiaAplicacionPromociones(){
		estrategia=new EstrategiaAplicaElMayorDescuentoPorItem();
	}

	public void setEstrategia(IEstrategiaAplicacionPromociones estrategia) {
		this.estrategia = estrategia;
	}

	public IEstrategiaAplicacionPromociones getEstrategia() {
		return this.estrategia;
	}

}
