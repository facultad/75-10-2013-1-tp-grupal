package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

public class Marca implements IMarca{
	private String nombre;
	private long Id;
	
	public Marca(String nombre, long cuit){
		this.setNombre(nombre);
		this.setId(cuit);
	}

	
	private void setNombre(String nombre){
		this.nombre = nombre;
	}
	private void setId(long cuit){
		this.Id = cuit;
	}
	
	@Override
	public long getId() {
		return this.Id;
	}

}
