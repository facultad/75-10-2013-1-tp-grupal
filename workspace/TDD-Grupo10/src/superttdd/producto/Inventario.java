package superttdd.producto;
import java.util.ArrayList;
import java.util.Collections;

public class Inventario {
	private ArrayList<RegistroProducto> productosRegistrados;
	private static Inventario INSTANCE = new Inventario();
	
	private Inventario() {	
		this.productosRegistrados = new ArrayList<RegistroProducto>();
	}
	
	public static Inventario getInstance() {
		return INSTANCE;
	}
	
	public void agregarRegistroProducto(RegistroProducto unRegProducto)
	{
		for (RegistroProducto registro : this.productosRegistrados) {
			if (registro.equals(unRegProducto) == true) {
				throw new RuntimeException("El Producto ingresado ya se encuentra registrado");		
			}
		}
		
		this.productosRegistrados.add(unRegProducto);	
	}
	
	public RegistroProducto obtenerRegistroProducto(MarcaProducto marca, CategoriaProducto categoria, String nombre) {
		for (RegistroProducto unProducto : this.productosRegistrados) {
			if (unProducto.getMarca().sonIguales(marca) && unProducto.getCategoria().sonIguales(categoria) &&
				unProducto.getNombre().equals(nombre)) {
				return unProducto;
			}
		}
		return null;
	}
	
	private void ordenarRegistroDeProductosDescendentemente() {
		Collections.sort(this.productosRegistrados);
	}
	
	public ArrayList<RegistroProducto> obtenerRankingDeProductos() {
		this.ordenarRegistroDeProductosDescendentemente();
		return this.productosRegistrados;
		
	}
	
	public void reiniciarRankingDeVentas() {
		for (RegistroProducto registro : this.productosRegistrados) {
			registro.resetearCantidadVendida();
		}
	}
	
	public void borrarInventario() {
		this.productosRegistrados.clear();
	}
}
