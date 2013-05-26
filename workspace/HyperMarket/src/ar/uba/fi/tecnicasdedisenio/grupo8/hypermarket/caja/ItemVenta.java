package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.excepciones.ItemVentaNoEstaAsociadoANingunaVenta;

public class ItemVenta implements IItemVenta{

	@Override
	public IProducto getProducto() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getCantidadProductos() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public IVenta getVenta() throws ItemVentaNoEstaAsociadoANingunaVenta {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setVenta(IVenta venta) {
		// TODO Auto-generated method stub
		
	}

}
