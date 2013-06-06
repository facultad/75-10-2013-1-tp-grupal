package superttdd.test.promociones;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import superttdd.ofertas.Oferta;
import superttdd.ofertas.OfertaMarca;
import superttdd.producto.CategoriaProducto;
import superttdd.producto.IProducto;
import superttdd.producto.MarcaProducto;
import superttdd.producto.Producto;
import superttdd.producto.RegistroProducto;
import superttdd.promociones.PromoCuponFuturo;

public class PromoCuponFuturoTest {

	private static final double DESCUENTO_OFERTA = 10.0;
	private PromoCuponFuturo cuponFuturo;
	private List<IProducto> productosCompra;
	private RegistroProducto regProd;
	private static Double PRECIO_PRODUCTO = 40.0;
	private String MARCA_PRODUCTO = "Coca cola";
	private IProducto producto; 
	private Oferta oferta;

	@Before 
	public void setUp() {
		regProd = new RegistroProducto(mock(CategoriaProducto.class), new MarcaProducto(MARCA_PRODUCTO), "Producto1" , PRECIO_PRODUCTO);
		productosCompra = new ArrayList<IProducto>();
		oferta = new OfertaMarca(new MarcaProducto(MARCA_PRODUCTO), DESCUENTO_OFERTA);
		producto = new Producto(regProd);
		productosCompra.add(producto);
		cuponFuturo = new PromoCuponFuturo(oferta);
	}
	
	@Test
	public void seGeneraCuponFuturo() {
		Double descuentoEsperado = PRECIO_PRODUCTO * DESCUENTO_OFERTA / 100.0;
		Double descuento = cuponFuturo.obtenerDescuento(productosCompra);
		
		assertEquals(descuentoEsperado, descuento);
		
	}
}
