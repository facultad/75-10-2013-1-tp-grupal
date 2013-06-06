package superttdd.producto;

public class CategoriaProducto {

	public String nombre;
	
	public CategoriaProducto(String nombre) {
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public Boolean sonIguales(CategoriaProducto categoria) {
		return (nombre.equals(categoria.nombre));
	}
}


