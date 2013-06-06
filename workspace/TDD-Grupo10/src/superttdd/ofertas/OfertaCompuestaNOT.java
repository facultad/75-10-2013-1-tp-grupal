package superttdd.ofertas;

import java.util.ArrayList;
import java.util.List;

import superttdd.producto.IProducto;

public class OfertaCompuestaNOT extends Oferta {
	List<Oferta> ofertas;

	public OfertaCompuestaNOT(List<Oferta> ofertas, Double descuento) {
		super(descuento);
		this.ofertas = ofertas;
	}

	@Override
	public void aplicarOferta(List<IProducto> productos) {
		List<IProducto> productos_aplican = filtrarProductos(productos);
		for(IProducto producto: productos_aplican) {
			producto.addPorcentajeDescuento(porcentajeDescuento);
		}
	}

	@Override
	public List<IProducto> filtrarProductos(List<IProducto> productos) {
		List<IProducto> productos_que_aplican=new ArrayList<IProducto>(productos);
		
		ArrayList<IProducto> productos_no_aplican = new ArrayList<IProducto>();
		
		for (Oferta oferta : ofertas) {
			List<IProducto> productos_resultado = oferta.filtrarProductos(productos);
			agregarSinDuplicados(productos_resultado,productos_no_aplican);
		}
		productos_que_aplican.removeAll(productos_no_aplican);
		return productos_que_aplican;
	}

	private void agregarSinDuplicados(List<IProducto> nuevos_productos,
			ArrayList<IProducto> productos) {
		for (IProducto producto : nuevos_productos) {
			if (!productos.contains(producto)) {
				productos.add(producto);
			}
		}
	}
}
