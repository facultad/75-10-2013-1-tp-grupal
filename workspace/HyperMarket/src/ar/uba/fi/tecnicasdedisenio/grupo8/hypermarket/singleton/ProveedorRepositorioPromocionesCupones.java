package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.singleton;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.RepositorioPromociones;

public class ProveedorRepositorioPromocionesCupones {

	private static RepositorioPromociones instance=new RepositorioPromociones();

	public static RepositorioPromociones getInstance() {
		return instance;
	}
	
	public static void setInstance(RepositorioPromociones instance) {
		ProveedorRepositorioPromocionesCupones.instance=instance;
	}
}
