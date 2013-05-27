package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

public class Rubro implements IRubro{

	private long id;

	public Rubro(long id) {
		this.id=id;
	}

	@Override
	public long getId() {
		return id;
	}

}
