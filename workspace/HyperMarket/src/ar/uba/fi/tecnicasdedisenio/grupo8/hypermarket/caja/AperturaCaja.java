package ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.excepciones.VentaNoIniciadaException;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.caja.excepciones.VentaYaIniciada;
import ar.uba.fi.tecnicasdedisenio.grupo8.hypermarket.promocion.ItemDescuento;

public class AperturaCaja {

	private IVenta ventaActual=null;
	private Collection<IVenta> ventas=new ArrayList<IVenta>();
	private ISucursal sucursal=null;

	public AperturaCaja(ISucursal sucursal) {
		this.sucursal=sucursal;
	}
	
	public void iniciarVenta() {
		if (this.ventaIniciada())
			throw new VentaYaIniciada();
		this.addVenta(new Venta(this.sucursal,MedioPago.NoDefinido));
	}

	private void addVenta(IVenta venta) {
		this.ventas.add(venta);
		this.ventaActual=venta;
	}

	public boolean ventaIniciada() {
		return this.ventaActual!=null;
	}

	public void addProducto(IProducto producto, int cantidad) {
		if (!this.ventaIniciada())
			throw new VentaNoIniciadaException();
		this.ventaActual.addItem(new ItemVenta(producto, cantidad));
	}

	public double getImporteVentaActual() {
		if (!this.ventaIniciada())
			throw new VentaNoIniciadaException();
		this.getVentaActual().calcularDescuento(ProveedorPromociones.getInstance().getPromociones());
		return this.getVentaActual().getImporteTotalConDescuento();
	}

	private IVenta getVentaActual() {
		if (!this.ventaIniciada())
			throw new VentaNoIniciadaException();
		return this.ventaActual;
	}

	public Collection<ItemDescuento> getItemsDescuentoVentaActual() {
		if (!this.ventaIniciada())
			throw new VentaNoIniciadaException();
		this.getVentaActual().calcularDescuento(
				ProveedorPromociones.getInstance().getPromociones());
		return this.getVentaActual().getItemsDescuento();
	}

	public void setMedioPagoVentaActual(IMedioPago medioPago) {
		if (!this.ventaIniciada())
			throw new VentaNoIniciadaException();
		this.getVentaActual().setMedioPago(medioPago);
	}

	public void confirmarVentaActual() {
		if (!this.ventaIniciada())
			throw new VentaNoIniciadaException();
		this.getVentaActual().calcularDescuento(ProveedorPromociones.getInstance().getPromociones());
		this.ventaActual=null;
	}

	public void cancelarVentaActual() {
		if (!this.ventaIniciada())
			throw new VentaNoIniciadaException();
		this.ventas.remove(this.getVentaActual());
		this.ventaActual=null;
	}

	public double getImporteTotalVentasConDescuento() {
		double total=0;
		for(IVenta venta:this.ventas)
			total+=venta.getImporteTotalConDescuento();
		return total;
	}

	public double getImporteTotalVentasSinDescuento() {
		double total=0;
		for(IVenta venta:this.ventas)
			total+=venta.getImporteTotalSinDescuento();
		return total;
	}

	public Map<IMedioPago, Double> getImporteTotalConDescuentoPorMedioPago() {
		HashMap<IMedioPago, Double> importeTotalPorMedioPago=
				new HashMap<IMedioPago, Double>();

		for(IVenta venta:this.ventas){
			
			double importeExistente=0;
			if (importeTotalPorMedioPago.containsKey(venta.getMedioPago()))
				importeExistente=
					importeTotalPorMedioPago.get(venta.getMedioPago()).doubleValue();
			
			if (venta==this.getVentaActual())
				venta.calcularDescuento(ProveedorPromociones.getInstance().getPromociones());
			Double nuevoImporte=new Double(importeExistente+
					venta.getImporteTotalConDescuento());
			
			importeTotalPorMedioPago.put(venta.getMedioPago(), nuevoImporte);
		}
		
		return importeTotalPorMedioPago;
	}

	
}
