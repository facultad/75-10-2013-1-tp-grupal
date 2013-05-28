package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

public class Marca implements IMarca{
	private String nombre;
	private long Id;
	
	public Marca(String nombre, long id){
		this.setNombre(nombre);
		this.setId(id);
	}
	
	private void setId(long id){
		this.Id = id;
	}

	private void setNombre(String nombre){
		this.nombre = nombre;
	}
	
	@Override
	public long getId() {
		return this.Id;
	}
	
	public String getNombre() {
		return this.nombre;
	}
}
