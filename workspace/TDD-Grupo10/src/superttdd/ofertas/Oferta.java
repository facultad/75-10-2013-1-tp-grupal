package superttdd.ofertas;

import java.util.List;

import superttdd.producto.IProducto;

public abstract class Oferta {
	protected Double porcentajeDescuento;
	
	public Oferta(Double porcentajeDescuento){
		this.porcentajeDescuento=porcentajeDescuento;
	}

	public abstract void aplicarOferta(List<IProducto> productos);
	
	public Double getPorcentajeDescuento() {
		return this.porcentajeDescuento;
	}	

	public abstract List<IProducto> filtrarProductos(List<IProducto> productos);

}
