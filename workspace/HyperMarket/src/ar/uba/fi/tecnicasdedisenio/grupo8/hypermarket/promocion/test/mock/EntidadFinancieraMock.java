package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IEntidadFinanciera;

public class EntidadFinancieraMock implements IEntidadFinanciera {

	@Override
	public long getId() {
		return 501;
	}

	@Override
	public String getNombreEntidad() {
		return "ICBC";
	}

}
