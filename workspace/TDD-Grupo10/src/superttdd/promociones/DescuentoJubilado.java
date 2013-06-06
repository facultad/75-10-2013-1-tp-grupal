package superttdd.promociones;

import java.util.ArrayList;
import java.util.List;

import superttdd.comprobante.Factura;
import superttdd.ofertas.OfertaDia;

public class DescuentoJubilado implements DescuentoFactura {

	private Double porcentaje_descuento;
	private OfertaDia oferta_dia_filtro;

	public DescuentoJubilado(Double porcentaje_descuento, OfertaDia oferta_dia) {
		this.porcentaje_descuento = porcentaje_descuento;
		this.oferta_dia_filtro = oferta_dia;
	}

	@Override
	public void aplicarDescuento(Factura factura) {
		if (esValidoDescuento()) {
			Double monto_descuento = factura.getMontoTotalConDescuentos()
					* (porcentaje_descuento / 100);
			factura.descontarMonto(monto_descuento);
		}
	}

	private boolean esValidoDescuento() {
		if (oferta_dia_filtro.hoyEsDiaDePromo()) {
			return true;
		}
		return false;
	}
}
