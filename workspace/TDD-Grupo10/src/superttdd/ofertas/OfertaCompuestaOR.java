package superttdd.ofertas;

import java.util.ArrayList;
import java.util.List;

import superttdd.producto.IProducto;

public class OfertaCompuestaOR extends Oferta {

	List<Oferta> ofertas;

	public OfertaCompuestaOR(List<Oferta> ofertas, Double descuento) {
		super(descuento);
		this.ofertas = ofertas;
	}

	@Override
	public void aplicarOferta(List<IProducto> productos) {
		List<IProducto> productos_que_aplican = filtrarProductos(productos);
		for(IProducto producto : productos_que_aplican) {
			producto.addPorcentajeDescuento(this.porcentajeDescuento);
		}
	}

	@Override
	public List<IProducto> filtrarProductos(List<IProducto> productos) {
		ArrayList<IProducto> productos_que_aplican = new ArrayList<IProducto>();
		List<IProducto> productos_que_aplican_oferta;
		for (Oferta oferta : ofertas) {
			productos_que_aplican_oferta = oferta
					.filtrarProductos(productos);
			agregarSinDuplicados(productos_que_aplican_oferta,
					productos_que_aplican);
		}
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
