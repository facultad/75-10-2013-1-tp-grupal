package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IRubro;

public class RubroMock implements IRubro {

	private long id;

	public RubroMock(long id) {
		this.id=id;
	}

	@Override
	public long getId() {
		return id;
	}

}
