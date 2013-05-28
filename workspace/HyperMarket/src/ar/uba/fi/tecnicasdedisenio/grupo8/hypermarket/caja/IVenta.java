package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

import java.util.Date;
import java.util.Iterator;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.RepositorioPromociones;

public interface IVenta {

	void addItem(IItemVenta itemVenta);

	Iterator<IItemVenta> getItemsIterator();
	public void imprimeItems();
	public int getCantidadUnidades();

	double getImporteTotalConDescuento(RepositorioPromociones promociones);

	double getImporteTotalSinDescuento();

	Date getFechaVenta();

	void setFechaVenta(Date date);
	
	public IMedioPago getPago();
	
}
