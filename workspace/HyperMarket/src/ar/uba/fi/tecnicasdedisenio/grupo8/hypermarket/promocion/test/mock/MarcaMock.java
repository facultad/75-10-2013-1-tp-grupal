package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.test.mock;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IMarca;

public class MarcaMock implements IMarca {

	private long id;

	public MarcaMock(long id) {
		// TODO Auto-generated constructor stub
		this.id=id;
	}

	@Override
	public long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

}
