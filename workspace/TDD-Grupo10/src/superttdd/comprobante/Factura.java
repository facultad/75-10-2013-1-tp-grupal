package superttdd.comprobante;

import java.util.ArrayList;
import java.util.List;
// import java.util.Date;

import superttdd.caja.MedioPago;
import superttdd.producto.IProducto;
import superttdd.promociones.DescuentoFactura;
import superttdd.promociones.PromoMedioPago;

public class Factura {
	private long numeroDeFactura;
	private Double montoTotalSinDescuentos;
	private Double montoTotalConDescuentos;
	private MedioPago medioDePago;
	private Double montoCuponFuturo;
	
	// private Date fecha;
	private List<IProducto> listaDeProductos;
	private ArrayList<PromoMedioPago> listaDePromocionesMedioPago;
	private List<DescuentoFactura> listaDescuentosFactura;

	private void generarMontoTotalSinDescuentos() {
		this.montoTotalSinDescuentos = 0.0;
		for (IProducto producto : listaDeProductos) {
			this.montoTotalSinDescuentos += producto.getPrecioBase();
		}
	}

	private void generarMontoTotalConDescuentos() {
		if (this.montoTotalConDescuentos == 0.0) {
			for (IProducto producto : listaDeProductos) {
				this.montoTotalConDescuentos += producto.getPrecioFinal();
			}
		}
	}

	// Por el momento implemento esto s��lo para la promo simple
	private void aplicarDescuentoMedioDePago() {
		for (PromoMedioPago promo : listaDePromocionesMedioPago) {
			promo.aplicarDescuento(this);
		}
	}

	public void agregarDescuentosFactura(List<DescuentoFactura> descuentos) {
		if (descuentos != null) {
			this.listaDescuentosFactura = descuentos;
		}
	}

	private void aplicarDescuentosFactura() {
		for (DescuentoFactura descuento : listaDescuentosFactura) {
			descuento.aplicarDescuento(this);
		}
	}

	public Factura(long numeroDeFactura, MedioPago medioDePago,
			List<IProducto> listaDeProductos) {
		this.montoTotalSinDescuentos = 0.0;
		this.montoTotalConDescuentos = 0.0;
		this.numeroDeFactura = numeroDeFactura;
		this.medioDePago = medioDePago;
		this.listaDeProductos = listaDeProductos;
		this.listaDePromocionesMedioPago = new ArrayList<PromoMedioPago>();
		this.listaDescuentosFactura = new ArrayList<DescuentoFactura>();
	}

	public Factura(Factura unaFactura) {
		this.montoTotalSinDescuentos = 0.0;
		this.montoTotalConDescuentos = 0.0;
		this.numeroDeFactura = unaFactura.numeroDeFactura;
		this.medioDePago = unaFactura.medioDePago;
		this.montoTotalConDescuentos = unaFactura.montoTotalConDescuentos;
		this.montoTotalSinDescuentos = unaFactura.montoTotalSinDescuentos;
	}

	public void descontarMonto(Double monto) {
		this.montoTotalConDescuentos -= monto;
	}

	public void cargarPromocionesPorMedioDePago(
			ArrayList<PromoMedioPago> listadoDePromos) {
		for (PromoMedioPago promo : listadoDePromos) {
			this.listaDePromocionesMedioPago.add(promo);
		}
	}

	// No es el mejor nombre para este método, pensar en uno mejor
	// SMELL #1 - no es un buen nombre para el método
	// SMELL #2 - los métodos no tienen nombres explicativos
	// SMELL #3 - es un "super" método concentra todas las responsabilidades del "procesamiento"
	public void procesarFactura() {
		this.generarMontoTotalConDescuentos();
		descontarMontosDescuentosFactura();
		this.generarMontoTotalSinDescuentos();
	}

	private void descontarMontosDescuentosFactura() {
		this.aplicarDescuentoMedioDePago();
		this.aplicarDescuentosFactura();
	}

	public void ingresarMontoCuponFuturo(Double monto) {
		montoCuponFuturo = monto;
	}
	
	public Double obtenerMontoCuponFuturo() {
		return this.montoCuponFuturo;
	}	
	
	
	public long getNumeroDeFactura() {
		return this.numeroDeFactura;
	}

	public Double getMontoTotalSinDescuentos() {
		return this.montoTotalSinDescuentos;
	}

	public Double getMontoTotalConDescuentos() {
		return this.montoTotalConDescuentos;
	}

	public MedioPago getMedioDePago() {
		return this.medioDePago;
	}

	public List<IProducto> getListaProductos() {
		return listaDeProductos;
	}

}
