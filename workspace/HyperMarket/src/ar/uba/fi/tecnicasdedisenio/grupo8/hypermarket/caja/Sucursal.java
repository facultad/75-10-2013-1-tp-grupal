package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

public class Sucursal implements ISucursal{

	private long id;

	public Sucursal(long id) {
		this.id=id;
	}

	public long getId() {
		return id;
	}

}