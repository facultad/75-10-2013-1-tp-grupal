package superttdd.ofertas;

import java.util.ArrayList;
import java.util.List;

import superttdd.producto.IProducto;

public class OfertaCompuestaAND extends Oferta {

	private List<Oferta> ofertas;
	
	public OfertaCompuestaAND(List<Oferta> ofertas, Double porcentajeDescuento) {
		super(porcentajeDescuento);
		this.ofertas =  ofertas;
	}

	@Override
	public void aplicarOferta(List<IProducto> productos) {
		List<IProducto> prodsAplican = this.filtrarProductos(productos);
		for(IProducto producto: prodsAplican) {
			producto.addPorcentajeDescuento(porcentajeDescuento);
		}
	}

	@Override
	public List<IProducto> filtrarProductos(List<IProducto> productos) {
		List<IProducto> prodsAplican = new ArrayList<IProducto>();
		for(IProducto producto: productos) {
			prodsAplican.add(producto);
		}

		for(Oferta oferta: ofertas) {
			prodsAplican = oferta.filtrarProductos(prodsAplican);
		}
		
		return prodsAplican;
	}

}
