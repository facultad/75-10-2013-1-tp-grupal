package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.excepciones.ItemVentaNoEstaAsociadoANingunaVenta;


public interface IItemVenta {

	IProducto getProducto();

	int getCantidadProductos();

	IVenta getVenta() throws ItemVentaNoEstaAsociadoANingunaVenta;

	void setVenta(IVenta venta);

}
