package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion;

public class ProveedorEstrategia {

	private static ProveedorEstrategia instance;

	public static ProveedorEstrategia getInstance() {
		if (instance==null)
			instance=new ProveedorEstrategia();
		return instance;
	}

	private IEstrategiaAplicacionPromociones estrategia;
	
	private ProveedorEstrategia(){
		estrategia=new EstrategiaAplicaElMayorDescuentoPorItem();
	}

	public void setEstrategia(IEstrategiaAplicacionPromociones estrategia) {
		this.estrategia = estrategia;
	}

	public IEstrategiaAplicacionPromociones getEstrategia() {
		return this.estrategia;
	}

}
