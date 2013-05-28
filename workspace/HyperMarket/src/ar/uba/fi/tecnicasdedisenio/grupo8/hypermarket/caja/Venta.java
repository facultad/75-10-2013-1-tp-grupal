package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.DescuentoVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.RepositorioPromociones;

public class Venta implements IVenta{
	private ArrayList<IItemVenta> listaItems;
	private ISucursal sucursal;
	private IMedioPago mediopago;
	private Double importeTotalConDescuentoVenta=null;
	private Date fechaVenta=new Date();
	
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

	@Override
	public double getImporteTotalConDescuento(RepositorioPromociones promociones) {
		if (this.importeTotalConDescuentoVenta==null){ 
			DescuentoVenta descuentoVenta=new DescuentoVenta(this, promociones);
			this.importeTotalConDescuentoVenta=
				new Double(getImporteTotalSinDescuento()-descuentoVenta.getImporteDescuento());
		}
		return this.importeTotalConDescuentoVenta.doubleValue();
	}

	@Override
	public double getImporteTotalSinDescuento() {
		double importeTotal=0;
		Iterator<IItemVenta> iterItemVenta=this.getItemsIterator();
		while (iterItemVenta.hasNext())
			importeTotal+=iterItemVenta.next().getImporteSinDescuento();
		return importeTotal;
	}

	@Override
	public Date getFechaVenta() {
		return this.fechaVenta;
	}
	
	public void setFechaVenta(Date fechaVenta) {
		this.fechaVenta=fechaVenta;
	}

}
