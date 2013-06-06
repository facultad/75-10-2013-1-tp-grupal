package superttdd.ofertas;

import java.util.ArrayList;
import java.util.List;

import superttdd.producto.IProducto;
import superttdd.producto.RegistroProducto;

public class OfertaProducto extends Oferta {

	RegistroProducto registro;

	public OfertaProducto(RegistroProducto registro, Double porcentajeDescuento) {
		super(porcentajeDescuento);
		this.registro = registro;
	}

	@Override
	public void aplicarOferta(List<IProducto> productos) {
		for(IProducto producto : productos) {
			if(producto.validarRegistroProducto(registro)) {
				producto.addPorcentajeDescuento(porcentajeDescuento);
			}
		}
	}

	public Boolean esProductoEnOferta(IProducto producto) {
		return producto.validarRegistroProducto(registro);
	}

	@Override
	public List<IProducto> filtrarProductos(List<IProducto> productos) {
		// TODO: TemplateMethod?
		List<IProducto> prodsAplican = new ArrayList<IProducto>(productos);
		for (IProducto producto : productos) {
			if (esProductoEnOferta(producto)) {
				prodsAplican.add(producto);
			}
		}
		return prodsAplican;
	}

}
