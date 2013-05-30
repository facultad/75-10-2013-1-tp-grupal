package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.excepciones.DescuentoNoCalculadoException;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.excepciones.DescuentoVentaNoCalculado;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.DescuentoVenta;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.ItemDescuento;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.RepositorioPromociones;

public class Venta implements IVenta{
	private ArrayList<IItemVenta> listaItems;
	private ISucursal sucursal;
	private IMedioPago mediopago;
	private Double importeTotalConDescuentoVenta=null;
	private Date fechaVenta=new Date();
	private long id = IdGenerator.getInstance().getNewId();
	private DescuentoVenta descuentoVenta;
	
	public Venta(ISucursal sucu, IMedioPago mpago){
		this.listaItems = new ArrayList<IItemVenta>();
		this.sucursal=sucu;
		this.mediopago=mpago;
		this.id= IdGenerator.getInstance().getNewId();
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
	public double getImporteTotalConDescuento() {
		if (this.importeTotalConDescuentoVenta==null)
			throw new DescuentoNoCalculadoException();
		return this.importeTotalConDescuentoVenta.doubleValue();
	}

	public double calcularDescuento(RepositorioPromociones promociones) {
		this.descuentoVenta=new DescuentoVenta(this, promociones);
		this.importeTotalConDescuentoVenta=
				new Double(getImporteTotalSinDescuento()-
						this.descuentoVenta.calcularImporteDescuento());
		return this.descuentoVenta.getImporteDescuento();
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

	@Override
	public long getId() {
		return this.id;
	}

	@Override
	public Collection<ItemDescuento> getItemsDescuento() {
		if (!this.descuentoVentaCalculado())
			throw new DescuentoVentaNoCalculado();
		return this.descuentoVenta.getItemsDescuento();
	}

	private boolean descuentoVentaCalculado() {
		return this.descuentoVenta!=null;
	}

	@Override
	public void setMedioPago(IMedioPago medioPago) {
		this.mediopago=medioPago;
	}

	@Override
	public IMedioPago getMedioPago() {
		return this.mediopago;
	}

}
