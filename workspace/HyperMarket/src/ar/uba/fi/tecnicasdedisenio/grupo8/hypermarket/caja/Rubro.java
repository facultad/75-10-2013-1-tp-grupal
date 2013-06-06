package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

public class Rubro implements IRubro{

	private static IRubro rubroNoDefinido=new Rubro("No definido");
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

	public static IRubro getRubroNoDefinido() {
		return Rubro.rubroNoDefinido;
	}

}
