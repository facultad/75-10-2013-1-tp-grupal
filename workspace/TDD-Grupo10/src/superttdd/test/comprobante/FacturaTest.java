package superttdd.test.comprobante;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import superttdd.caja.MedioPago;
import superttdd.comprobante.Factura;
import superttdd.ofertas.OfertaProducto;
import superttdd.producto.CategoriaProducto;
import superttdd.producto.IProducto;
import superttdd.producto.MarcaProducto;
import superttdd.producto.Producto;
import superttdd.producto.RegistroProducto;
import superttdd.promociones.PromoMedioPago;
import superttdd.promociones.PromoMedioPagoSimple;


public class FacturaTest {
	
	private static final String NOMBRE_PRODUCTO_1 = "Producto 1";
	private static final String NOMBRE_PRODUCTO_2 = "Producto 2";
	private static final Double PRECIO_BASE_PRODUCTO_1 = 10.0;
	private static final Double PRECIO_BASE_PRODUCTO_2 = 20.0;
	private static final Double DESCUENTO_PRODUCTO_1 = 10.0;
	private ArrayList<IProducto> listaDeProductos;
	private PromoMedioPagoSimple mockPromoSimple;
	private Producto mockProd1;
	private Producto mockProd2;
	private ArrayList<PromoMedioPago> listaPromociones;
	
	
	OfertaProducto ofertaProducto;
	Double porcentajeDescuento=10.0;
	

	@Before
	public void setUp() throws Exception {
		// TODO: Esto debería ser un Mock, pero no sé como hacer un Mock tan complejo :S
		mockPromoSimple = new PromoMedioPagoSimple(MedioPago.TARJETA, 20.0);
		listaPromociones = new ArrayList<PromoMedioPago>();
		listaPromociones.add(mockPromoSimple);
		
		RegistroProducto registro1 = new RegistroProducto(mock(CategoriaProducto.class), mock(MarcaProducto.class), NOMBRE_PRODUCTO_1, PRECIO_BASE_PRODUCTO_1 );
		RegistroProducto registro2 = new RegistroProducto(mock(CategoriaProducto.class), mock(MarcaProducto.class), NOMBRE_PRODUCTO_2, PRECIO_BASE_PRODUCTO_2 );
		mockProd1 = spy(new Producto(registro1));
		mockProd1.addPorcentajeDescuento(DESCUENTO_PRODUCTO_1);
		mockProd2 = spy(new Producto(registro2));
		listaDeProductos = new ArrayList<IProducto>();
		listaDeProductos.add(mockProd1);
		listaDeProductos.add(mockProd2);
	}

	@Test 
	public void TotalFacturaSinDescuentos() {
		Factura factura = new Factura(0, MedioPago.EFECTIVO, listaDeProductos);
		factura.procesarFactura();
		Double totalEsperado = 30.0;
		
		assertEquals(totalEsperado, factura.getMontoTotalSinDescuentos());
	}
	
	@Test
	public void TotalFacturaSinPromosYConProductosSinDescuentosAplicados() {
		Factura factura = new Factura(0, MedioPago.EFECTIVO, listaDeProductos);
		factura.procesarFactura();
		Double totalEsperado = 29.0;
		
		assertEquals(totalEsperado, factura.getMontoTotalConDescuentos());
	}
	
	@Test
	public void TotalFacturaSinPromosYConProductosConDescuentoAplicados() {
		Factura factura = new Factura(0, MedioPago.TARJETA, listaDeProductos);
		factura.procesarFactura();
		Double totalEsperado = 10 * 0.9 + 20 * 1.0;
		
		assertEquals(totalEsperado, factura.getMontoTotalConDescuentos());	
	}
	
	@Test
	public void TotalFacturaConPromosQueNoAfectanALosProductosDeLaMisma() {
		Factura factura = new Factura(0, MedioPago.EFECTIVO, listaDeProductos);
		factura.cargarPromocionesPorMedioDePago(listaPromociones);
		factura.procesarFactura();
		Double totalEsperado = 10 * 0.9 + 20 * 1.0;
		
		assertEquals(totalEsperado, factura.getMontoTotalConDescuentos());	
	}
	
	@Test
	public void TotalFacturaConPromosQueAfectanALosProductosDeLaMisma() {
		Factura factura = new Factura(0, MedioPago.TARJETA, listaDeProductos);
		factura.cargarPromocionesPorMedioDePago(listaPromociones);
		factura.procesarFactura();
		Double totalEsperado = 9 * 0.8 + 20 * 0.8;
		
		assertEquals(totalEsperado, factura.getMontoTotalConDescuentos());
	}

}
