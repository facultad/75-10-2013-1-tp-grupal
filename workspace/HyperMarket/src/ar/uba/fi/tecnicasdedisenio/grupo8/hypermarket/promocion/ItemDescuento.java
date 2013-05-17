package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion;

import java.util.Collection;
import java.util.Iterator;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;

public class ItemDescuento {

	private IItemVenta itemVenta;
	private RepositorioPromociones repositorioPromociones;

	public ItemDescuento(IItemVenta itemVenta, RepositorioPromociones repositorioPromociones) {
		// TODO Auto-generated constructor stub
		this.setItemVenta(itemVenta);
		this.setRepositorioPromociones(repositorioPromociones);
	}

	public IItemVenta getItemVenta() {
		return itemVenta;
	}

	public void setItemVenta(IItemVenta itemVenta) {
		this.itemVenta = itemVenta;
	}

	public RepositorioPromociones getRepositorioPromociones() {
		return repositorioPromociones;
	}

	public void setRepositorioPromociones(RepositorioPromociones repositorioPromociones) {
		this.repositorioPromociones = repositorioPromociones;
	}
	
	public double getImporteDescuento() {		
		Promocion promocionAAplicar = this.getPromocionAAplicar();
		
		return promocionAAplicar.getImporteADescontar(this.itemVenta);
	}

	private Promocion getPromocionAAplicar() {
		Collection<Promocion> promocionesAplicaItem=
				this.repositorioPromociones.getPromocionesAplicaItemVenta(this.itemVenta);
		
		Iterator<Promocion> iterPromocion;
		iterPromocion=promocionesAplicaItem.iterator();

		Promocion promocionAAplicar=Promocion.crearPromocionNoAplicable();
		
		while(iterPromocion.hasNext()){
			Promocion promocion=iterPromocion.next();
			if (promocion.getImporteADescontar(this.itemVenta)>=
				promocionAAplicar.getImporteADescontar(this.itemVenta))
				promocionAAplicar=promocion;
		}
		return promocionAAplicar;
	}

}
