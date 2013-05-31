package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.excepciones.CajaCerradaException;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.excepciones.CajaYaAbiertaException;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.excepciones.CajaYaCerradaException;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.ItemDescuento;

public class Caja {
	
	private AperturaCaja aperturaCajaActual;
	private Collection<AperturaCaja> aperturas=new ArrayList<AperturaCaja>();
	private ISucursal sucursal;
	
	public Caja(ISucursal sucursal){
		this.sucursal=sucursal;
	}

	public void abrirCaja(){
		if (this.abierta())
			throw new CajaYaAbiertaException();
		this.aperturaCajaActual=new AperturaCaja(this.sucursal);
		this.aperturas.add(this.aperturaCajaActual);
	}
	
	private boolean abierta() {
		return this.aperturaCajaActual!=null;
	}

	public void iniciarVenta(){
		if (this.cerrada())
			throw new CajaCerradaException();
		this.getAperturaCajaActual().iniciarVenta();
	}
	
	private boolean cerrada() {
		return !this.abierta();
	}

	private AperturaCaja getAperturaCajaActual() {
		if (this.cerrada())
			throw new CajaCerradaException();
		return this.aperturaCajaActual;
	}

	public void addProducto(IProducto producto,int cantidad){
		if (this.cerrada())
			throw new CajaCerradaException();
		this.getAperturaCajaActual().addProducto(producto,cantidad);
	}
	
	public double getImporteVentaActual(){
		if (this.cerrada())
			throw new CajaCerradaException();
		return this.getAperturaCajaActual().getImporteVentaActual();
	}
	
	public Collection<ItemDescuento> getItemsDescuentoVentaActual(){
		if (this.cerrada())
			throw new CajaCerradaException();
		return this.getAperturaCajaActual().getItemsDescuentoVentaActual();
	}
	
	public void setMedioPago(IMedioPago medioPago) {
		if (this.cerrada())
			throw new CajaCerradaException();
		this.getAperturaCajaActual().setMedioPagoVentaActual(medioPago);
	}
	
	public void confirmarVenta(){
		if (this.cerrada())
			throw new CajaCerradaException();
		this.getAperturaCajaActual().confirmarVentaActual();
	}
	
	public void cancelarVenta(){
		if (this.cerrada())
			throw new CajaCerradaException();
		this.getAperturaCajaActual().cancelarVentaActual();
	}
	
	
	public void cerrarCaja(){
		if (this.cerrada())
			throw new CajaYaCerradaException();
		this.aperturaCajaActual=null;
	}

	public double getImporteTotalVentasConDescuento(){
		double total=0;
		for(AperturaCaja aperturaCaja:this.aperturas)
			total+=aperturaCaja.getImporteTotalVentasConDescuento();
		return total;
	}
	
	public double getImporteTotalDescuentos(){
		return this.getImporteVentasSinDescuento()-
				this.getImporteTotalVentasConDescuento();
	}

	public double getImporteVentasSinDescuento() {
		double total=0;
		for(AperturaCaja aperturaCaja:this.aperturas)
			total+=aperturaCaja.getImporteTotalVentasSinDescuento();
		return total;
	}
	
	Map<IMedioPago, Double> getImporteTotalConDescuentoPorMedioPago(){
		HashMap<IMedioPago, Double> importeTotalPorMedioPago=
				new HashMap<IMedioPago, Double>();
		for(AperturaCaja aperturaCaja:this.aperturas){
			for (Map.Entry<IMedioPago, Double> entryImporteMedioPagoApertura:
				aperturaCaja.getImporteTotalConDescuentoPorMedioPago().entrySet()){

				IMedioPago clave=entryImporteMedioPagoApertura.getKey();
				double importeApertura=entryImporteMedioPagoApertura.getValue().doubleValue();

				double importeExistente=0;
				if (importeTotalPorMedioPago.containsKey(clave))
					importeExistente=importeTotalPorMedioPago.get(clave).doubleValue();
				
				Double nuevoImporte=new Double(importeExistente+importeApertura);
				importeTotalPorMedioPago.put(clave,nuevoImporte);

			}
		}
		
		return importeTotalPorMedioPago;
	}

	public TreeMap<IProducto, Integer> obtenerRankingProductosMasVendidos() {

		TreeMap<IProducto, Integer> ranking = new TreeMap<IProducto, Integer>();
		
		for (AperturaCaja apertura : aperturas) {
			Collection<IVenta> ventas = apertura.getVentas();
			
			for (IVenta venta : ventas) {
				Iterator<IItemVenta> iterator = venta.getItemsIterator(); 
				do {
					ItemVenta itemVenta = (ItemVenta) iterator.next();
					if (itemVenta != null) {
						IProducto producto = itemVenta.getProducto();
						Integer cantidad = new Integer(itemVenta.getCantidadProductos());
						
						Integer total = ranking.get(producto);
						if (total == null) {
							total = cantidad;
						}
						else {
							total = total + cantidad;
						}
						
						ranking.put(producto, total);
					}					
				} while(iterator.hasNext());			
			}
		}
		
		return ranking;
	}
}
