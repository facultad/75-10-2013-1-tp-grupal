package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.CuponDescuento;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.ItemDescuento;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.RepositorioPromociones;

public interface IVenta extends Identificable{

	void addItem(IItemVenta itemVenta);

	Iterator<IItemVenta> getItemsIterator();
	public void imprimeItems();
	public int getCantidadUnidades();

	double getImporteTotalConDescuento();

	double getImporteTotalSinDescuento();

	Date getFechaVenta();

	void setFechaVenta(Date date);
	
	public IMedioPago getPago();
	
	ISucursal getSucursal();

	Collection<ItemDescuento> getItemsDescuento();
	
	public double calcularDescuento(RepositorioPromociones promociones);

	void setMedioPago(IMedioPago medioPago);

	IMedioPago getMedioPago();
	
	IEstadoLaboral getEstadoLaboral();

	void setEstadoLaboral(IEstadoLaboral estadoLaboral);

	void addCuponDescuento(CuponDescuento cuponDescuento);

	Collection<CuponDescuento> getCupones();

	Collection<CuponDescuento> getCuponesProximaVenta();

	Collection<IItemVenta> getItemsVenta();
	
}
