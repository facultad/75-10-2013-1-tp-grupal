package superttdd.promociones;

import java.util.List;

import superttdd.caja.MedioPago;
import superttdd.producto.IProducto;


public abstract class PromoMedioPago implements DescuentoFactura {
	MedioPago medioPago;
	Double descuento;
	
	public PromoMedioPago(MedioPago medioPago, Double descuento) {
		this.medioPago=medioPago;
		this.descuento=descuento;
	}
	
	public boolean esMedioPagoPromo(MedioPago medioPagoCompra) {
		return this.medioPago==medioPagoCompra;
	}
}
