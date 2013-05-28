package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.excepciones.ItemVentaNoEstaAsociadoANingunaVenta;

public class ItemVenta implements IItemVenta{
	private IProducto producto;
	private int cantidad;
	private IVenta venta;
	
	public ItemVenta(IProducto producto, int cantidad) {
		this.producto=producto;
		this.cantidad=cantidad;
		this.venta=null;
	}
	
	public ItemVenta(IProducto producto, int cantidad, IVenta venta) {
		this.producto=producto;
		this.cantidad=cantidad;
		this.venta=venta;
	}
	
	@Override
	public IProducto getProducto() {
		return this.producto;
	}

	@Override
	public int getCantidadProductos() {
		return this.cantidad;
	}

	@Override
	public IVenta getVenta() throws ItemVentaNoEstaAsociadoANingunaVenta {
		if (this.venta==null)
			throw new ItemVentaNoEstaAsociadoANingunaVenta();
		else return this.venta;
	}

	@Override
	public void setVenta(IVenta venta) {
		this.venta=venta;
	}

	@Override
	public double getImporteSinDescuento() {
		return this.cantidad*this.producto.getPrecioUnitario();
	}

}
