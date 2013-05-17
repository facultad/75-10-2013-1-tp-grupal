package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;

public class Promocion {

	private ICondicionPromocion condicion;
	private int cantidadProductosAplica;
	private double porcentajeADescontar;

	public Promocion(ICondicionPromocion condicion, int cantidadProductosAplica, double porcentajeADescontar) {
		this.setCondicion(condicion);
		this.setCantidadProductosAplica(cantidadProductosAplica);
		this.setPorcentajeADescontar(porcentajeADescontar);
	}

	public ICondicionPromocion getCondicion() {
		return condicion;
	}

	public void setCondicion(ICondicionPromocion condicion) {
		this.condicion = condicion;
	}

	public double getImporteADescontar(IItemVenta itemVenta) {
		return this.getCantidadProductosAplicaParaItemVenta(itemVenta)*
			itemVenta.getProducto().getPrecioUnitario()*
			this.getPorcentajeADescontar();
		
	}

	private int getCantidadProductosAplicaParaItemVenta(IItemVenta itemVenta) {
		return itemVenta.getCantidadProductos()-
			(itemVenta.getCantidadProductos()%this.getCantidadProductosAplica());
	}

	public int getCantidadProductosAplica() {
		return cantidadProductosAplica;
	}

	public void setCantidadProductosAplica(int cantidadProductosAplica) {
		this.cantidadProductosAplica = cantidadProductosAplica;
	}

	public double getPorcentajeADescontar() {
		return porcentajeADescontar;
	}

	public void setPorcentajeADescontar(double porcentajeADescontar) {
		this.porcentajeADescontar = porcentajeADescontar;
	}

	public boolean aplicaParaItemVenta(IItemVenta itemVenta) {
		return this.condicion.valida(itemVenta);
	}

	public static Promocion crearPromocionNoAplicable() {
		return new Promocion(new CondicionSiempreVerdadera(), 1, 0);
	}

}
