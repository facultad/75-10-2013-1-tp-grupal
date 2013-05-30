package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.excepciones.ItemVentaNoEstaAsociadoANingunaVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.RepositorioPromociones;


public interface IItemVenta extends Identificable{

	IProducto getProducto();

	int getCantidadProductos();

	IVenta getVenta() throws ItemVentaNoEstaAsociadoANingunaVenta;

	void setVenta(IVenta venta);

	double getImporteSinDescuento();
	
	double calcularImporteConDescuento(RepositorioPromociones repositorioPromociones);

}
