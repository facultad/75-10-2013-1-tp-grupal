package superttdd.promociones;

import superttdd.comprobante.Factura;

public class CuponDescuento implements DescuentoFactura {
	
	Double montoDescuento;
	Double porcentajeMaximo;
	
	public CuponDescuento(Double montoDescuento, Double porcentajeMaximo) {
		this.montoDescuento = montoDescuento;
		this.porcentajeMaximo = porcentajeMaximo / 100.0;
	}
	
	public Double obtenerMontoConDescuento(Double monto) {
		return (monto - calcularDescuento(monto));
	}
	
	private Double calcularDescuento(Double monto) {
		Double porcentajeDescuento = (montoDescuento/monto);
		if(porcentajeDescuento > this.porcentajeMaximo ) {
			return (monto * this.porcentajeMaximo);
		}
		return montoDescuento; 
	}

	@Override
	public void aplicarDescuento(Factura factura) {
		Double monto = factura.getMontoTotalConDescuentos();
		Double montoConDesc = this.calcularDescuento(monto);
		factura.descontarMonto(montoConDesc);
	}
}
