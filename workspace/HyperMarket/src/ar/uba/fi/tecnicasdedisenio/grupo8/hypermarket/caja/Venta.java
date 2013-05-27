package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class Venta implements IVenta{
	private ArrayList<IItemVenta> listaItems;
	private ISucursal sucursal;
	private IMedioPago mediopago;
	
	public Venta(ISucursal sucu, IMedioPago mpago){
		this.listaItems = new ArrayList<IItemVenta>();
		this.sucursal=sucu;
		this.mediopago=mpago;
	}
	
	public ISucursal getSucursal(){
		return this.sucursal;
	}
	
	public IMedioPago getPago(){
		return this.mediopago;
	}

	public void imprimeItems(){
		Iterator<IItemVenta> itr=this.getItemsIterator();
		while (itr.hasNext()){
			Object element = itr.hasNext();
			System.out.print(element);
		}
	}

	public int getCantidadUnidades(){
		int cant = 0;
		Iterator<IItemVenta> itr=this.getItemsIterator();
		while (itr.hasNext()){
			IItemVenta item = itr.next();
			cant = cant + item.getCantidadProductos();
		}
	return cant;
	}
		
	@Override
	public void addItem(IItemVenta itemVenta) {
		this.listaItems.add(itemVenta);
		itemVenta.setVenta(this);
	}

	@Override
	public Iterator<IItemVenta> getItemsIterator() {
		return this.listaItems.iterator();
	}

	
}
