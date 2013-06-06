package superttdd.promociones;

import java.util.ArrayList;
import java.util.List;

import superttdd.ofertas.Oferta;
import superttdd.producto.IProducto;

public class PromoCuponFuturo {
	Oferta oferta;
	
	public PromoCuponFuturo(Oferta oferta){
		this.oferta=oferta;
	}
	
	public Double obtenerDescuento(List<IProducto> productos) {
		ArrayList<IProducto> productos_copia = clonarProductos(productos);
		oferta.aplicarOferta(productos_copia);
		return calcularDescuento(productos_copia);
	}
	
	private ArrayList<IProducto> clonarProductos(List<IProducto> productos) {
		ArrayList<IProducto> productos_copia = new ArrayList<IProducto>();
		for(IProducto producto : productos){
			productos_copia.add(producto.clonar());
		}
		return productos_copia;
	}
	
	private Double calcularDescuento(List<IProducto> productos){
		Double descuento = 0.0;
		for(IProducto producto : productos ) {
			descuento += (producto.getPrecioBase() - producto.getPrecioFinal());
		}
		return descuento;
	}

}
