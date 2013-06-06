package superttdd.ofertas;

import java.util.ArrayList;
import java.util.List;

import superttdd.producto.CategoriaProducto;
import superttdd.producto.IProducto;

public class OfertaCategoria extends Oferta {
	
	CategoriaProducto categoria;

	public OfertaCategoria(CategoriaProducto categoria, Double porcentajeDescuento) {
		super(porcentajeDescuento);
		this.categoria = categoria;	
	}

	@Override
	public void aplicarOferta(List<IProducto> productos) {
		for(IProducto producto: productos) {
			if(perteneceACategoria(producto)) {
				producto.addPorcentajeDescuento(porcentajeDescuento);
			}
		}
	}

	private Boolean perteneceACategoria(IProducto producto) {
		Boolean pertenece = false;
		if(producto != null) {
			pertenece = (producto.validarCategoria(categoria));
		}
		return pertenece;
	}

	@Override
	public List<IProducto> filtrarProductos(List<IProducto> productos) {
		// TODO: TemplateMethod?
		List<IProducto> prodsAplican = new ArrayList<IProducto>(); 
		for(IProducto producto: productos) {
			if(perteneceACategoria(producto)) {
				prodsAplican.add(producto);
			}
		}
		return prodsAplican;
	}
	
}
