package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

public class Rubro implements IRubro{

	private long id;
	private String nombre;
	
	public Rubro(long id) {
		this.id=id;
	}

	public Rubro(String nombre,long id) {
		this.id=id;
		this.nombre=nombre;
	}
	
	@Override
	public long getId() {
		return id;
	}

	public String getNombre() {
		return this.nombre;
	}

}
