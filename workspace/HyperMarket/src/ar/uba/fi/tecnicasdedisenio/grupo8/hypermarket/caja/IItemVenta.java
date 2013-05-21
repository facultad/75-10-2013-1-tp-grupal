package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;


public interface IItemVenta {

	IProducto getProducto();

	int getCantidadProductos();

	IVenta getVenta();

	void setVenta(IVenta venta);

}
