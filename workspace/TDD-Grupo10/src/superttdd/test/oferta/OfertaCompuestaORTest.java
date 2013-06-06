package superttdd.test.oferta;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import superttdd.ofertas.Oferta;
import superttdd.ofertas.OfertaCategoria;
import superttdd.ofertas.OfertaCompuestaOR;
import superttdd.ofertas.OfertaMarca;
import superttdd.ofertas.OfertaProducto;
import superttdd.producto.IProducto;
import superttdd.producto.Producto;
import superttdd.producto.RegistroProducto;

public class OfertaCompuestaORTest {

	private static final Double DESCUENTO_OF_COMPUESTA_OR=10.0;
	private ArrayList<IProducto> productos;
	private ArrayList<Oferta> ofertas;
	private OfertaCompuestaOR ofertaCompuesta;
	
	@Before
	public void setUp() throws Exception {
		productos=new ArrayList<IProducto>();
		ofertas = new ArrayList<Oferta>();		
		Producto mockProducto1 = spy(new Producto(mock(RegistroProducto.class)));
		Producto mockProducto2 = spy(new Producto(mock(RegistroProducto.class)));
		productos.add(mockProducto1);
		productos.add(mockProducto2);
		Oferta oferta1 = mock(OfertaMarca.class);
		Oferta oferta2 = mock(OfertaCategoria.class);
		Oferta oferta3 = mock(OfertaProducto.class);
		ofertas.add(oferta1);
		ofertas.add(oferta2);
		ofertas.add(oferta3);
		ofertaCompuesta=new OfertaCompuestaOR(ofertas, DESCUENTO_OF_COMPUESTA_OR);
	}

	@Test
	public void OfertaCompuestaORAplicaDescuentoATodosLosProductos() {
		ofertaCompuesta.aplicarOferta(productos);
		List<IProducto> productos_que_aplican = ofertaCompuesta.filtrarProductos(productos);
		for(IProducto producto : productos_que_aplican) {
			verify(producto, times(1)).addPorcentajeDescuento(DESCUENTO_OF_COMPUESTA_OR);
		}
	}

}
