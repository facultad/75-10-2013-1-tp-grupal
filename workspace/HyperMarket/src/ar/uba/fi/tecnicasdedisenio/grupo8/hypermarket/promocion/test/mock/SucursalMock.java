package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.ISucursal;

public class SucursalMock implements ISucursal {

	long id;
	public SucursalMock(long id) {
		this.id = id;
	}
	
	@Override
	public long getId() {
		return id;
	}

}
