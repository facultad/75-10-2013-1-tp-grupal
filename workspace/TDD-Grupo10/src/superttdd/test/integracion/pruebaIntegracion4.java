package superttdd.test.integracion;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import superttdd.caja.Caja;
import superttdd.caja.DiaSemana;
import superttdd.caja.MedioPago;
import superttdd.ofertas.OfertaDia;
import superttdd.producto.CategoriaProducto;
import superttdd.producto.IProducto;
import superttdd.producto.Inventario;
import superttdd.producto.MarcaProducto;
import superttdd.producto.Producto;
import superttdd.producto.RegistroProducto;
import superttdd.promociones.DescuentoFactura;
import superttdd.promociones.DescuentoJubilado;

public class pruebaIntegracion4 {

	private static final double PRECIO_LAMPARITA = 15.0;
	private static final double PRECIO_MACETA = 10.0;
	private static final double DESCUENTO_JUBILADO = 10.0;
	private List<DescuentoFactura> descuentos_factura;
	private List<Producto> productos_a_comprar;
	private Inventario inventario;
	private CategoriaProducto categoriaMaceta, categoriaLamparita;
	private MarcaProducto marcaMaceta, marcaLamparita;
	private String nombreLamparita, nombreMaceta;

	@Before
	public void setUp() throws Exception {
		descuentos_factura = new ArrayList<DescuentoFactura>();
		productos_a_comprar = new ArrayList<Producto>();
		inventario = Inventario.getInstance();
		registrarProductosInventario();
		crearProductosAComprar();
		cargarDescuentosFactura();
	}

	@Test
	public void pruebaIntegracion_4() {
		Caja caja = new Caja();
		caja.abrirCaja();
		for (DescuentoFactura descuento : descuentos_factura) {
			caja.agregarDescuentoFactura(descuento);
		}
		caja.iniciarCompra();

		for (Producto producto : productos_a_comprar) {
			caja.agregarProducto(producto);
		}

		caja.confirmarCompra(MedioPago.TARJETA_XYZ);
		Double total_obtenido = caja.obtenerTotalCompraConDescuentos();
		caja.cerrarCompra();
		caja.cerrarCaja();

		Double total_esperado = (PRECIO_MACETA + PRECIO_LAMPARITA)*((100-DESCUENTO_JUBILADO)/100.0);
		Assert.assertEquals(total_esperado, total_obtenido);

	}

	private void cargarDescuentosFactura() {
		List<DiaSemana> ofertas_dia = new ArrayList<DiaSemana>();
		ofertas_dia.add(DiaSemana.HOY);
		OfertaDia oferta_martes = new OfertaDia(0.0, ofertas_dia);
		DescuentoJubilado descuentoJubilado = new DescuentoJubilado(
				DESCUENTO_JUBILADO, oferta_martes);
		descuentos_factura.add(descuentoJubilado);
	}

	private void registrarProductosInventario() {
		Double precioMaceta, precioLamparita;
		categoriaMaceta = new CategoriaProducto("Patio");
		categoriaLamparita = new CategoriaProducto("Electrodomesticos");
		marcaMaceta = new MarcaProducto("Plantul");
		marcaLamparita = new MarcaProducto("Electrolux");
		precioMaceta = PRECIO_MACETA;
		precioLamparita = PRECIO_LAMPARITA;
		nombreMaceta = "MacetaPlantul";
		RegistroProducto registroMaceta = new RegistroProducto(categoriaMaceta,
				marcaMaceta, nombreMaceta, precioMaceta);
		nombreLamparita = "LamparitaPlantul";
		RegistroProducto registroLamparita = new RegistroProducto(
				categoriaLamparita, marcaLamparita, nombreLamparita,
				precioLamparita);
		inventario.agregarRegistroProducto(registroLamparita);
		inventario.agregarRegistroProducto(registroMaceta);
	}

	private void crearProductosAComprar() {
		Producto maceta = new Producto(inventario.obtenerRegistroProducto(
				marcaMaceta, categoriaMaceta, nombreMaceta));
		Producto lamparita = new Producto(inventario.obtenerRegistroProducto(
				marcaLamparita, categoriaLamparita, nombreLamparita));
		productos_a_comprar.add(maceta);
		productos_a_comprar.add(lamparita);
	}

}
