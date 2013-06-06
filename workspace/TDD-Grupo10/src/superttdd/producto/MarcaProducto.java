package superttdd.producto;

public class MarcaProducto {

	private String nombre;

	public MarcaProducto(String nombre) {
		this.nombre = nombre;
	}
	
	public String getNombre() {
		return nombre;
	}

	public boolean sonIguales(MarcaProducto marca) {
		return (this.nombre.equals(marca.getNombre())); 
	}
}
