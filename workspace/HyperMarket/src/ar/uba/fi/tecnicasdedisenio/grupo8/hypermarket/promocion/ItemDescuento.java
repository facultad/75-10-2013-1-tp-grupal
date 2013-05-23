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
		IPromocion promocionAAplicar = this.getIPromocionAAplicar();
		this.importeDescuentoVenta=promocionAAplicar.getImporteADescontar(this.itemVenta);
		this.importeDescuentoVentaCalculado=true;
		return this.importeDescuentoVenta;
	}

	public double getImporteDescuento() {
		if (!this.importeDescuentoVentaCalculado)
			throw new ImporteDescuentoNoCalculado();
		return this.importeDescuentoVenta;
	}

	private IPromocion getIPromocionAAplicar() {
		Collection<IPromocion> promocionesAplicaItem=
				this.repositorioPromociones.getPromocionesAplicaItemVenta(this.itemVenta);
		
		Iterator<IPromocion> iterIPromocion;
		iterIPromocion=promocionesAplicaItem.iterator();

		IPromocion promocionAAplicar=Promocion.crearPromocionNoAplicable();
		
		while(iterIPromocion.hasNext()){
			IPromocion promocion=iterIPromocion.next();
			if (promocion.getImporteADescontar(this.itemVenta)>=
				promocionAAplicar.getImporteADescontar(this.itemVenta))
				promocionAAplicar=promocion;
		}
		return promocionAAplicar;
	}

}
