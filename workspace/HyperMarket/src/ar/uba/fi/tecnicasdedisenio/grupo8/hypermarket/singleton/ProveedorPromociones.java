package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.singleton;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.excepciones.RepositorioPromocionesIvalido;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.RepositorioPromociones;

public class ProveedorPromociones {

	private static ProveedorPromociones instance=null;
	private RepositorioPromociones repositorioPromociones=new RepositorioPromociones();

	public static ProveedorPromociones getInstance() {
		if (instance==null)
			instance=new ProveedorPromociones();
		return instance;
	}

	public RepositorioPromociones getPromociones() {
		return this.repositorioPromociones;
	}
	
	public void setPromociones(RepositorioPromociones repositorioPromociones) {
		if (repositorioPromociones==null)
			throw new RepositorioPromocionesIvalido();
		this.repositorioPromociones=repositorioPromociones;
	}
	
}
