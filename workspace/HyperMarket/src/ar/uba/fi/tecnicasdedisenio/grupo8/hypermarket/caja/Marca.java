package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

public class Marca implements IMarca {
	private String nombre;
	private long id = IdGenerator.getInstance().getNewId();
	
	public Marca(String nombre){
		this.setNombre(nombre);
	}

	private void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	@Override
	public long getId() {
		return this.id;
	}
	
	public String getNombre() {
		return this.nombre;
	}
}
