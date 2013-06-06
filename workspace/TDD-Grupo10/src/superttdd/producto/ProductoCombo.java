package superttdd.producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoCombo implements IProducto {
	List<IProducto> productos;
	
	public ProductoCombo(List<IProducto> productos) {
		this.productos=new ArrayList<IProducto>();
		this.productos.addAll(productos);
	}
	
	public IProducto clonar() {
		List<IProducto> productos_clonados = new ArrayList<IProducto>();
		for(IProducto producto : productos) {
			productos_clonados.add(producto.clonar());
		}
		IProducto producto_copia = new ProductoCombo(productos_clonados);
		return producto_copia;
	}

	@Override
	public boolean validarCategoria(CategoriaProducto categoria) {
		return false;
	}

	@Override
	public boolean validarMarca(MarcaProducto marca) {
		return false;
	}

	@Override
	public boolean validarRegistroProducto(RegistroProducto registro) {
		return false;
	}

	@Override
	public Double getPrecioFinal() {
		Double sumatoria = 0.0;
		for(IProducto p: productos) {
			sumatoria += p.getPrecioFinal();
		}
		return sumatoria;
	}

	/*
	 * Agrega el descuento a cada producto que lo compone
	 * TODO: deberia ser solo al que aplico la oferta
	 */
	@Override
	public void addPorcentajeDescuento(Double descuento) {
		for(IProducto producto: productos) {
			producto.addPorcentajeDescuento(descuento);
		}
	}

	@Override
	public String getNombre() {
		String nombre = "Combo: ";
		for(IProducto p : productos) {
			nombre+=p.getNombre()+" ";
		}
		return nombre;
	}

	@Override
	public Double getPrecioBase() {
		Double sumatoria = 0.0;
		for(IProducto p : productos) {
			sumatoria+=p.getPrecioBase();
		}
		return sumatoria;
	}
	
	@Override
	public boolean equals(Object o){
		ProductoCombo productoCombo = (ProductoCombo)o;
		for(IProducto p: productos) {
			if(!productoCombo.productos.contains(p)){
				return false;
			}
		}
		return true;
	}
}
