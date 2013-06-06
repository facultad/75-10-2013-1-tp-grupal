package superttdd.promociones;

import java.util.List;

import superttdd.caja.MedioPago;
import superttdd.comprobante.Factura;
import superttdd.ofertas.Oferta;
import superttdd.ofertas.OfertaDia;
import superttdd.producto.IProducto;

public class PromoMedioPagoCompuestaAND extends PromoMedioPago {

	List<OfertaDia> ofertas;

	public PromoMedioPagoCompuestaAND(MedioPago medioPago,
			List<OfertaDia> ofertas, Double descuento) {
		super(medioPago, descuento);
		this.ofertas = ofertas;
	}

	@Override
	public void aplicarDescuento(Factura factura) {
		if (esMedioPagoPromo(factura.getMedioDePago())) {
			if (EsDiaPromo()) {
				Double monto_descuento=factura.getMontoTotalConDescuentos()*(descuento/100);
				factura.descontarMonto(monto_descuento);
			}
		}
	}

	private boolean EsDiaPromo() {
		for (OfertaDia oferta : ofertas) {
			if (oferta.hoyEsDiaDePromo())
				return true;
		}
		return false;
	}

}
