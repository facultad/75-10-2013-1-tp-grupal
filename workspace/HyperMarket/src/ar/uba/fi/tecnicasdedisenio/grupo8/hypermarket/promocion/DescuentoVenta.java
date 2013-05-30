package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IItemVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.IVenta;

public class DescuentoVenta {

	private IVenta venta;
	private RepositorioPromociones repositorioPromociones;
	private Collection<ItemDescuento> itemsDescuento;
	private double importeDescuentoVenta;
	private boolean importeDescuentoVentaCalculado; 

	public DescuentoVenta(IVenta venta,RepositorioPromociones repositorioPromociones) {
		this.venta=venta;
		this.repositorioPromociones=repositorioPromociones;
		this.importeDescuentoVentaCalculado=false;
	}

	public double calcularImporteDescuento() {
		this.itemsDescuento=new ArrayList<ItemDescuento>();
		this.importeDescuentoVenta=0;
		
		Iterator<IItemVenta> iterItemVenta=this.venta.getItemsIterator();
		
		while (iterItemVenta.hasNext()){
			IItemVenta itemVenta=iterItemVenta.next();
			ItemDescuento itemDescuento=new ItemDescuento(itemVenta, this.repositorioPromociones);
			this.itemsDescuento.add(itemDescuento);
			this.importeDescuentoVenta+=itemDescuento.calcularImporteDescuento();
		}

		this.importeDescuentoVentaCalculado=true;
		return this.importeDescuentoVenta;
	}
	
	public double getImporteDescuento(){
		if (!this.importeDescuentoVentaCalculado)
			return this.calcularImporteDescuento();
		return this.importeDescuentoVenta;
	}

	public Collection<ItemDescuento> getItemsDescuento() {
		return this.itemsDescuento;
	}

}
