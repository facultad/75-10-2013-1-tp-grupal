package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

import java.util.ArrayList;
import java.util.Iterator;

public class Venta implements IVenta{
	private ArrayList<IItemVenta> listaItems = new ArrayList();
	
	@Override
	public void addItem(IItemVenta itemVenta) {
		this.listaItems.add(itemVenta);
		
	}

	@Override
	public Iterator<IItemVenta> getItemsIterator() {
		return this.listaItems.iterator();
	}

	public void imprimeItems(){
		Iterator<IItemVenta> itr=this.getItemsIterator();
		while (itr.hasNext()){
			Object element = itr.hasNext();
			System.out.print(element);
		}
	}
}
