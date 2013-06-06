package superttdd.comprobante;
import java.util.ArrayList;
import java.util.List;

import superttdd.caja.MedioPago;
import superttdd.ofertas.Oferta;
import superttdd.producto.IProducto;
import superttdd.producto.Producto;
import superttdd.promociones.PromoCuponFuturo;

public class OrdenDeCompra {
	
	private ArrayList<IProducto> listaDeProductos;
	private List<PromoCuponFuturo> cuponesFuturos;
	private ArrayList<Producto> copiaListaDeProductos;
	private ArrayList<Oferta> listaDeOfertas;
	private EstadoOrdenDeCompra estado;
	
	private boolean esValidoCrearFactura() {
		return (this.estado == EstadoOrdenDeCompra.CERRADA && this.listaDeProductos.size() > 0);
	}
	
	private void borrarDescuentosEnListaDeProductos() {
		this.listaDeProductos.clear();
		
		for (IProducto producto: this.copiaListaDeProductos) {
			this.listaDeProductos.add( producto.clonar()); 	
		}
		
	}
	
	public OrdenDeCompra(ArrayList<Oferta> listadoDeOfertas, List<PromoCuponFuturo> cuponesFuturos) {
		this.cuponesFuturos = cuponesFuturos;
 		this.listaDeOfertas = listadoDeOfertas;
		this.listaDeProductos = new ArrayList<IProducto>();
		this.copiaListaDeProductos = new ArrayList<Producto>();
		this.estado = EstadoOrdenDeCompra.CERRADA;
	}

	public void agregarProducto(Producto producto) {
		listaDeProductos.add(producto);
		copiaListaDeProductos.add(producto);
	}
	
	public void aplicarOfertas() {	
		this.borrarDescuentosEnListaDeProductos();
		
		for (Oferta oferta: this.listaDeOfertas) {
			oferta.aplicarOferta(this.listaDeProductos);
		}
	}
	
	public Double obtenerSubtotalConDescuentos() {
		Double subtotal = 0.0;
		
		for (IProducto producto: this.listaDeProductos) {
			subtotal += producto.getPrecioFinal();
		}
		return subtotal;
	}
	
	public Double obtenerSubtotalSinDescuentos() {
		Double subtotal = 0.0;
		
		for (IProducto producto: this.listaDeProductos) {
			subtotal += producto.getPrecioBase();
		}
		return subtotal;
	}
	
	public Double obtenerSubtotalDeDescuentosAplicados() {
		return (this.obtenerSubtotalSinDescuentos() - this.obtenerSubtotalConDescuentos());	
	}

	public void abrirOrdenDeCompra() {
		if (this.estado != EstadoOrdenDeCompra.ABIERTA) {
			this.estado = EstadoOrdenDeCompra.ABIERTA;
		}
		else {
			throw new RuntimeException("La orden de compra ya se encuentra abierta para agregar productos");
		}
	}
	
	public void cerrarOrdenDeCompra() {
		if (this.estado != EstadoOrdenDeCompra.CERRADA) {
			this.estado = EstadoOrdenDeCompra.CERRADA;
		}
		else {
			throw new RuntimeException("La orden de compra ya se encuentra cerrada");
		}
	}
	
	public EstadoOrdenDeCompra getEstado() {
		return this.estado;
	}
	
	public Factura generarFactura(MedioPago medioDePago, long numeroDeFactura) {
		if (esValidoCrearFactura()) {
			actualizarVentas();
			Double montoCuponDescuento = this.generarCuponDescuento();
			Factura factura = new Factura(numeroDeFactura, medioDePago, this.listaDeProductos); 
			factura.ingresarMontoCuponFuturo(montoCuponDescuento);
			return factura;
		}
		else {
			throw new RuntimeException("La orden de compra no fue cerrada o no hay productos para armar la factura");
		}
	}

	public Double generarCuponDescuento() {
		Double montoCupon = 0.0;
		for (PromoCuponFuturo cupon: cuponesFuturos ) {
			montoCupon += cupon.obtenerDescuento(listaDeProductos);
		}
		return montoCupon;
	}
	
	private void actualizarVentas() {
		for(Producto producto: copiaListaDeProductos) {
			producto.getRegistroProducto().agregarVenta();
		}
	}
	
}
