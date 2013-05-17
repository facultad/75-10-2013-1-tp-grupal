package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.excepciones.PromocionNoAplicaParaItemVenta;

public class Promocion {

	private ICondicionPromocion condicion;
	private int cantidadProductosAplica;
	private double coeficienteDescuento;

	public Promocion(ICondicionPromocion condicion, int cantidadProductosAplica, double coeficienteDescuento) {
		this.setCondicion(condicion);
		this.setCantidadProductosAplica(cantidadProductosAplica);
		this.setCoeficienteDescuento(coeficienteDescuento);
	}

	public ICondicionPromocion getCondicion() {
		return condicion;
	}

	public void setCondicion(ICondicionPromocion condicion) {
		this.condicion = condicion;
	}

	public double getImporteADescontar(IItemVenta itemVenta) {
		if (!this.aplicaParaItemVenta(itemVenta))
			throw new PromocionNoAplicaParaItemVenta();
		return this.getCantidadProductosAplicaParaItemVenta(itemVenta)*
			itemVenta.getProducto().getPrecioUnitario()*
			this.getCoeficienteDescuento();
		
	}

	public int getCantidadProductosAplicaParaItemVenta(IItemVenta itemVenta) {
		if (!this.aplicaParaItemVenta(itemVenta))
			throw new PromocionNoAplicaParaItemVenta();
		return itemVenta.getCantidadProductos()-
			(itemVenta.getCantidadProductos()%this.getCantidadProductosAplica());
	}

	public int getCantidadProductosAplica() {
		return cantidadProductosAplica;
	}

	public void setCantidadProductosAplica(int cantidadProductosAplica) {
		this.cantidadProductosAplica = cantidadProductosAplica;
	}

	public double getCoeficienteDescuento() {
		return coeficienteDescuento;
	}

	public void setCoeficienteDescuento(double coeficienteDescuento) {
		this.coeficienteDescuento = coeficienteDescuento;
	}

	public boolean aplicaParaItemVenta(IItemVenta itemVenta) {
		return this.condicion.valida(itemVenta);
	}

	public static Promocion crearPromocionNoAplicable() {
		return new Promocion(new CondicionSiempreVerdadera(), 1, 0);
	}

}
