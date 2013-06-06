package superttdd.test.comprobante;


import static org.junit.Assert.assertEquals;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import superttdd.comprobante.OrdenDeCompra;
import superttdd.ofertas.Oferta;
import superttdd.ofertas.OfertaCategoria;
import superttdd.ofertas.OfertaMarca;
import superttdd.producto.CategoriaProducto;
import superttdd.producto.MarcaProducto;
import superttdd.producto.Producto;
import superttdd.producto.RegistroProducto;

public class OrdenDeCompraTest {
	
	private static final String NOMBRE_PRODUCTO_1 = "Producto 1";
	private static final String NOMBRE_PRODUCTO_2 = "Producto 2";
	private static final String NOMBRE_PRODUCTO_3 = "Producto 3";
	private static final String NOMBRE_PRODUCTO_4 = "Producto 4";
	private static final Double PRECIO_BASE_PRODUCTO_1 = 10.0;
	private static final Double PRECIO_BASE_PRODUCTO_2 = 20.0;
	private static final Double PRECIO_BASE_PRODUCTO_3 = 30.0;
	private static final Double PRECIO_BASE_PRODUCTO_4 = 40.0;
	private static final Double DESCUENTO_PRODUCTO_1 = 0.0;
	private static final Double DESCUENTO_PRODUCTO_2 = 10.0;
	private static final Double DESCUENTO_PRODUCTO_3 = 20.0;
	private static final Double DESCUENTO_PRODUCTO_4 = 30.0;
	
	private MarcaProducto marca1;
	private MarcaProducto marca2;
	private CategoriaProducto categoria1;
	private CategoriaProducto categoria2;
	
	private Producto producto1;
	private Producto producto2;
	private Producto producto3;
	private Producto producto4;
	
	private OfertaMarca oferta1;
	private OfertaCategoria oferta2;
	private ArrayList<Oferta> listaDeOfertas;
	private static final Double DESCUENTO_OFERTA_1 = 5.0;
	private static final Double DESCUENTO_OFERTA_2 = 15.0;
	
	private void inicializarProductos() {
		marca1 = new MarcaProducto("Pepsi");
		marca2 = new MarcaProducto("La Mocita");
		categoria1 = new CategoriaProducto("Gaseosas");
		categoria2 = new CategoriaProducto("Pastas");
		
		RegistroProducto registro1 = new RegistroProducto(categoria1, marca1, NOMBRE_PRODUCTO_1, PRECIO_BASE_PRODUCTO_1);
		RegistroProducto registro2 = new RegistroProducto(categoria1, marca2, NOMBRE_PRODUCTO_2, PRECIO_BASE_PRODUCTO_2);
		RegistroProducto registro3 = new RegistroProducto(categoria2, marca1, NOMBRE_PRODUCTO_3, PRECIO_BASE_PRODUCTO_3);
		RegistroProducto registro4 = new RegistroProducto(categoria2, marca2, NOMBRE_PRODUCTO_4, PRECIO_BASE_PRODUCTO_4);
		
		producto1 = new Producto(registro1);
		producto1.addPorcentajeDescuento(DESCUENTO_PRODUCTO_1);
		producto2 = new Producto(registro2);
		producto2.addPorcentajeDescuento(DESCUENTO_PRODUCTO_2);
		producto3 = new Producto(registro3);
		producto3.addPorcentajeDescuento(DESCUENTO_PRODUCTO_3);
		producto4 = new Producto(registro4);
		producto4.addPorcentajeDescuento(DESCUENTO_PRODUCTO_4);
		
	}
	
	private void inicializarOfertas() {
		MarcaProducto marca = new MarcaProducto("Pepsi");
		CategoriaProducto categoria = new CategoriaProducto("Pastas");
		oferta1 = new OfertaMarca(marca, DESCUENTO_OFERTA_1);
		oferta2 = new OfertaCategoria(categoria, DESCUENTO_OFERTA_2);
		listaDeOfertas = new ArrayList<Oferta>();
		listaDeOfertas.add(oferta1);
		listaDeOfertas.add(oferta2);
		
	}

	@Before
	public void setUp() throws Exception {
		inicializarProductos();
		inicializarOfertas();
	}

	@Test(expected=RuntimeException.class)
	public void AbrirOrdenDeCompraCuandoLaMismaYaFueAbierta() throws RuntimeException {
		OrdenDeCompra orden = new OrdenDeCompra(null, null);
		orden.abrirOrdenDeCompra();
		orden.abrirOrdenDeCompra();
	}
	
	@Test(expected=RuntimeException.class)
	public void CerrarOrdenDeCompraCuandoLaMismaEstabaCerrada() throws RuntimeException {
		OrdenDeCompra orden = new OrdenDeCompra(null, null);
		
		orden.abrirOrdenDeCompra();
		orden.cerrarOrdenDeCompra();
		
		orden.cerrarOrdenDeCompra();
	}
	
	private OrdenDeCompra crearOrdenConProductosSinOfertas() {
		OrdenDeCompra orden = new OrdenDeCompra(null, null);
		orden.agregarProducto(producto1);
		orden.agregarProducto(producto2);
		orden.agregarProducto(producto3);
		orden.agregarProducto(producto4);
		
		return orden;
	}
	
	private OrdenDeCompra crearOrdenConProductosConOfertas() {
		OrdenDeCompra orden = new OrdenDeCompra(listaDeOfertas, null);
		orden.agregarProducto(producto1);
		orden.agregarProducto(producto2);
		orden.agregarProducto(producto3);
		orden.agregarProducto(producto4);
		
		return orden;
	}
	
	@Test
	public void VerSubtotalProductosSinDescuentosEnOrdenSinOfertasAplicadas() {
		OrdenDeCompra orden = crearOrdenConProductosSinOfertas();
		Double valorEsperado = 100.0;
		
		assertEquals(valorEsperado, orden.obtenerSubtotalSinDescuentos());
	}
	
	@Test
	public void VerSubtotalProductosConDescuentosEnOrdenSinOfertasAplicadas() {
		OrdenDeCompra orden = crearOrdenConProductosSinOfertas();
		Double valorEsperado = 80.0;
		
		assertEquals(valorEsperado, orden.obtenerSubtotalConDescuentos());
	}
	
	@Test
	public void VerSubtotalProductosConDescuentosEnOrdenConOfertasAplicadas() {
		OrdenDeCompra orden = crearOrdenConProductosConOfertas();
		orden.aplicarOfertas();
		Double valorEsperado = 70.68;
		
		assertEquals(valorEsperado, orden.obtenerSubtotalConDescuentos());
		
	}
	
	@Test
	public void VerSubtotalConDescuentosLuegoDeHaberAplicadoMasDeUnaVezLasOfertas() {
		OrdenDeCompra orden = crearOrdenConProductosConOfertas();
		Double valorEsperado = 70.68;
		
		orden.aplicarOfertas();
		assertEquals(valorEsperado, orden.obtenerSubtotalConDescuentos());
		
		orden.aplicarOfertas();
		assertEquals(valorEsperado, orden.obtenerSubtotalConDescuentos());
	}

}
