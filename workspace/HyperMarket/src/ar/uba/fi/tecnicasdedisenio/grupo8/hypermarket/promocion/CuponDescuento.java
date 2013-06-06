package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IVenta;

public class CuponDescuento {

	private double importeADescontar;
	private double coeficienteDescuentoMaximo;

	public CuponDescuento(double importeADescontar, double coeficienteDescuentoMaximo) {
		this.importeADescontar=importeADescontar;
		this.coeficienteDescuentoMaximo=coeficienteDescuentoMaximo;
	}

	public double getImporteMaximoADescontar() {
		return this.importeADescontar;
	}

	public double getCoeficienteDescuentoMaximo() {
		return this.coeficienteDescuentoMaximo;
	}

	public double getImporteADescontar(IVenta venta) {
		double maximoInporteADescontar=
				venta.getImporteTotalSinDescuento()*
				this.getCoeficienteDescuentoMaximo();
		if (this.getImporteMaximoADescontar()>maximoInporteADescontar)
			return maximoInporteADescontar;
		return this.getImporteMaximoADescontar();
	}

}
