package superttdd.caja;

import java.util.ArrayList;
import java.util.List;

import superttdd.comprobante.Factura;
import superttdd.comprobante.OrdenDeCompra;
import superttdd.ofertas.Oferta;
import superttdd.producto.Producto;
import superttdd.promociones.DescuentoFactura;
import superttdd.promociones.PromoCuponFuturo;
import superttdd.promociones.PromoMedioPago;

public class Caja {

	private EstadoCaja estadoCaja;
	private OrdenDeCompra ordenDeCompra;
	private ArrayList<Oferta> listaDeOfertas;
	private ArrayList<Factura> listaDeFacturas;
	private ArrayList<PromoMedioPago> listaDePromos;
	private Factura facturaCompraActual;
	private long contadorNumerosDeFactura;
	private List<PromoCuponFuturo> cuponesFuturos;
	private List<DescuentoFactura> descuentosFactura;

	private void agregarFactura(Factura unaFactura) {
		this.listaDeFacturas.add(unaFactura);
	}

	public Caja() {
		estadoCaja = EstadoCaja.CERRADA;
		ordenDeCompra = null;
		facturaCompraActual = null;
		listaDeOfertas = new ArrayList<Oferta>();
		listaDePromos = new ArrayList<PromoMedioPago>();
		listaDeFacturas = new ArrayList<Factura>();
		descuentosFactura = new ArrayList<DescuentoFactura>();
		contadorNumerosDeFactura = 0;
		cuponesFuturos = new ArrayList<PromoCuponFuturo>();
	}

	public void abrirCaja() {
		if (estadoCaja != EstadoCaja.ABIERTA) {
			estadoCaja = EstadoCaja.ABIERTA;
		} else {
			throw new RuntimeException("La caja ya se encuentra abierta");
		}
	}

	public void cerrarCaja() {
		if (estadoCaja != EstadoCaja.CERRADA) {
			estadoCaja = EstadoCaja.CERRADA;
		} else {
			throw new RuntimeException("La caja ya se encuentra cerrada");
		}
	}

	public void iniciarCompra() {
		if (estadoCaja == EstadoCaja.ABIERTA && ordenDeCompra == null) {
			this.ordenDeCompra = new OrdenDeCompra(this.listaDeOfertas, this.cuponesFuturos);
		}
		this.ordenDeCompra.abrirOrdenDeCompra();
	}

	public void agregarProducto(Producto producto) {
		ordenDeCompra.agregarProducto(producto);
		ordenDeCompra.aplicarOfertas();
	}

	public void confirmarCompra(MedioPago medioDePago) {
		this.ordenDeCompra.cerrarOrdenDeCompra();
		this.facturaCompraActual = this.ordenDeCompra.generarFactura(
				medioDePago, ++contadorNumerosDeFactura);
		this.facturaCompraActual.cargarPromocionesPorMedioDePago(listaDePromos);
		this.facturaCompraActual.agregarDescuentosFactura(descuentosFactura);
		this.facturaCompraActual.procesarFactura();
		this.ordenDeCompra = null;
	}

	public void cerrarCompra() {
		this.agregarFactura(this.facturaCompraActual);
		this.facturaCompraActual = null;
	}

	public Double obtenerSubTotalCompraSinDescuentos() {
		if (this.ordenDeCompra != null) {
			return this.ordenDeCompra.obtenerSubtotalSinDescuentos();
		} else {
			throw new RuntimeException(
					"Orden de Compra no existe o ya fue procesada");
		}
	}

	public Double obtenerSubTotalCompraConDescuentos() {
		if (this.ordenDeCompra != null) {
			return this.ordenDeCompra.obtenerSubtotalConDescuentos();
		} else {
			throw new RuntimeException(
					"Orden de Compra no existe o ya fue procesada");
		}
	}

	
	public Double obtenerMontoCuponDescuento() {
		if(hayFacturaCompra()) {
			return facturaCompraActual.obtenerMontoCuponFuturo();
		}
		return 0.0;
	}
	
	public Double obtenerTotalCompraSinDescuentos() {
		return this.facturaCompraActual.getMontoTotalSinDescuentos();
	}

	public Double obtenerTotalCompraConDescuentos() {
		return this.facturaCompraActual.getMontoTotalConDescuentos();
	}

	/*
	 * Política tomada: Monto en caja por cada medio de pago se toma como la
	 * cantidad de dinero total (con descuentos) abonados por cada medio de pago
	 */
	public Double visualizarMontoEnCajaPorMedioDePago(MedioPago medioDePago) {
		Double total = 0.0;
		for (Factura factura : this.listaDeFacturas) {
			if (factura.getMedioDePago() == medioDePago) {
				total += factura.getMontoTotalConDescuentos();
			}
		}
		return total;
	}

	public void cargarOfertas(ArrayList<Oferta> ofertasNuevas) {
		for (Oferta oferta : ofertasNuevas) {
			this.listaDeOfertas.add(oferta);
		}
	}

	public void cargarPromocionesDeMedioDePago(
			ArrayList<PromoMedioPago> promosNuevas) {
		for (PromoMedioPago promocion : promosNuevas) {
			this.listaDePromos.add(promocion);
		}
	}

	public void cargarDescuentosCuponesFuturos(
			List<PromoCuponFuturo> cuponesFuturos) {
		for (PromoCuponFuturo cupon : cuponesFuturos) {
			this.cuponesFuturos.add(cupon);
		}
	}

	public void agregarDescuentoFactura(DescuentoFactura descuento) {
		this.descuentosFactura.add(descuento);
	}

	private Boolean hayFacturaCompra() {
		return facturaCompraActual != null;
	}

}
