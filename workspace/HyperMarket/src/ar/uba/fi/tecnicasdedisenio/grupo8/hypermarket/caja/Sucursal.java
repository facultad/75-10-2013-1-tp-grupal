package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

public class Sucursal implements ISucursal{

	private String nombre;
	private long id = IdGenerator.getInstance().getNewId();
	
	public Sucursal(String n) {
		this.nombre=n;
	}

	public long getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}
}
