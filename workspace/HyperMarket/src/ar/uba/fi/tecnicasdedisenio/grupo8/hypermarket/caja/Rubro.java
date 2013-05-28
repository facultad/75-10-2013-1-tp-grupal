package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

public class Rubro implements IRubro{

	private String nombre;
	private long id = IdGenerator.getInstance().getNewId();
	
	public Rubro(String nombre) {
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
