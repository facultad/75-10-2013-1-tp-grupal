package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion;

import java.util.Collection;
import java.util.Iterator;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.excepciones.ImporteDescuentoNoCalculado;

public class ItemDescuento {

	private IItemVenta itemVenta;
	private RepositorioPromociones repositorioPromociones;
	private double importeDescuentoVenta;
	private boolean importeDescuentoVentaCalculado;
	private IEstrategiaAplicacionPromociones estrategiaAplicacionPromociones=
			new EstrategiaAplicaElMayorDescuentoPorItem();

	public ItemDescuento(IItemVenta itemVenta, RepositorioPromociones repositorioPromociones) {
		this.setItemVenta(itemVenta);
		this.setRepositorioPromociones(repositorioPromociones);
	}

	public IItemVenta getItemVenta() {
		return itemVenta;
	}

	private void setItemVenta(IItemVenta itemVenta) {
		this.itemVenta = itemVenta;
	}

	private RepositorioPromociones getRepositorioPromociones() {
		return repositorioPromociones;
	}

	private void setRepositorioPromociones(RepositorioPromociones repositorioPromociones) {
		this.repositorioPromociones = repositorioPromociones;
	}
	
	public double calcularImporteDescuento() {
		Collection<IPromocion> promocionesAAplicar = this.getPromocionesAAplicar();
		Iterator<IPromocion> iterPromocionAAplicar = promocionesAAplicar.iterator();
		this.importeDescuentoVenta=0;
		while (iterPromocionAAplicar.hasNext()){
			IPromocion promocionAAplicar=iterPromocionAAplicar.next();
			this.importeDescuentoVenta+=promocionAAplicar.getImporteADescontar(this.itemVenta);
		}
		this.importeDescuentoVentaCalculado=true;
		return this.importeDescuentoVenta;
	}

	public double getImporteDescuento() {
		if (!this.importeDescuentoVentaCalculado)
			throw new ImporteDescuentoNoCalculado();
		return this.importeDescuentoVenta;
	}

	private Collection<IPromocion> getPromocionesAAplicar() {
		Collection<IPromocion> promocionesAplicaItem=
				this.getRepositorioPromociones().getPromocionesAplicaItemVenta(this.itemVenta);

		return this.estrategiaAplicacionPromociones.getPromocionesAAplicar(this.itemVenta,promocionesAplicaItem);
	}

}
